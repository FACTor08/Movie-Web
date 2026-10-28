package com.factor.movies.movies;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import com.factor.movies.exception.FailedUploadException;
import com.factor.movies.exception.MovieNotFoundException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;
import java.util.Map;

@RequiredArgsConstructor
@Service
@Transactional
@Slf4j
public class MoviesLogic {

    private final Moviesdb repo;
    private final MovieMapper dataTransfer;
    private final Cloudinary cloudinary;


    private File covertToFile(MultipartFile multipartFile) throws IOException {
        File file = File.createTempFile("upload_", multipartFile.getOriginalFilename());

        try {
            FileOutputStream fos = new FileOutputStream(file);
            fos.write(multipartFile.getBytes());

        } catch (IOException e) {
            throw new IOException();

        }
        return file;
    }

    public MovieResponseDTO uploadMovies(MoviesDTO data, MultipartFile movie, MultipartFile movieImage
            , MultipartFile subtitle) throws IOException {
        log.info("Starting upload process for: {}", data.getTitle());
        if (movie == null || movie.isEmpty()) {
            log.warn("Upload failed: no movie file was uploaded");
            throw new MovieNotFoundException("no video file ");

        }
        if (!List.of("video/mp4", "video/quicktime", "video/x-matroska").contains(movie.getContentType())) {
            log.error("Unsupported video format: {}", movie.getContentType());
            throw new IllegalArgumentException("video file not supported");
        }
        Map<String, Object> movieFinal = null;
                File videoFile = covertToFile(movie);
            try {
                log.info("Uploading movie to cloudinary: {}", movie.getOriginalFilename());
                movieFinal = cloudinary.uploader().uploadLarge(videoFile, ObjectUtils.asMap(
                        "resource_type", "video",
                        "folder", "movies/video"
                ));
            } catch (FailedUploadException e) {
                log.error("Upload failed for: {} : {}",movie.getOriginalFilename(), e.getMessage());
                throw new FailedUploadException("movie upload to cloudinary failed");
            } finally {

                if(videoFile.delete()){
                    log.debug("Video file deleted: {}", videoFile.getAbsolutePath());
                }
                else {
                    log.warn("Video file could not be deleted: {}", videoFile.getAbsolutePath());
                }
                log.info("Video file verified: {}", videoFile.getAbsolutePath());
            }
        if (movieImage == null || movieImage.isEmpty()) {
            log.warn("Upload failed: no image file was uploaded");
            throw new IllegalArgumentException("no movie image");
        }
        if (!List.of("image/jpeg", "image/png").contains(movieImage.getContentType())) {
            log.error("Unsupported image format: {}", movieImage.getContentType());
            throw new IllegalArgumentException("image file not supported");
        }
        log.info("Uploading image to cloudinary: {}", movieImage.getOriginalFilename());
            Map<String, Object> imageFinal = cloudinary.uploader().upload(movieImage.getBytes(), ObjectUtils.asMap(
                    "resource_type", "image",
                    "folder", "movies/image"
            ));


        String subtitleFile = subtitle.getOriginalFilename();
        if (subtitleFile == null || !(subtitleFile.endsWith(".srt") || subtitleFile.endsWith(".vtt") || subtitleFile.endsWith(".ass"))) {
            log.warn("Unsupported subtitle format: {}", subtitle.getContentType());
            throw new IllegalArgumentException("subtitle type not supported");
        }
        log.info("uploading subtitle to cloudinary: {}", subtitleFile);
            Map<String, Object> subtitleFinal = cloudinary.uploader().upload(subtitle.getBytes(), ObjectUtils.asMap(
                    "resource_type", "raw",
                    "folder", "movies/subtitle"
            ));
            log.debug("Upload to cloudinary was successful");
        Movies movies = dataTransfer.transferObj(data, movieFinal, imageFinal, subtitleFinal);
        log.info("Data sent to database");
        Movies saved = repo.save(movies);
        return dataTransfer.responseDTO(saved);
    }

    public MovieResponseDTO findMovieByTitle(String title) {
        Movies movie = repo.findByTitleContainingIgnoreCase(title)
                .orElseThrow(()-> new MovieNotFoundException("movie not found"));

        return dataTransfer.responseDTO(movie);
    }
}
