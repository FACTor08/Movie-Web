package com.factor.movies.Admin;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
public class AdminController {

private final AdminLogic logic;

@PostMapping("/register/admin")
public ResponseEntity<AdminResponseDTO> registerAdmin(@RequestBody AdminDTO admin){
    AdminResponseDTO data = logic.registerAdmin(admin);
    return ResponseEntity.ok(data);
}
@PatchMapping("/register/admin/update_data")
    public ResponseEntity<AdminResponseDTO> updateData(Authentication auth, @RequestBody AdminDTO admin){
     AdminResponseDTO response = logic.updateData(auth.getName(), admin);
     return ResponseEntity.ok(response);
         }
}
