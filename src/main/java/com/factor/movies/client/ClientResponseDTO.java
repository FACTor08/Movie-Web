package com.factor.movies.client;

import com.factor.movies.entity.Roles;
import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class ClientResponseDTO {
    private String email;
    private String username;
    private Roles roles;
}
