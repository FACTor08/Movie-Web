package com.factor.movies.controller;

import com.factor.movies.entity.Admin;
import com.factor.movies.entity.AdminDTO;
import com.factor.movies.service.AdminLogic;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController
public class AdminRegistration {

@Autowired
private AdminLogic logic;

@PostMapping("/register/admin")
public ResponseEntity<String> registerAdmin(@RequestBody AdminDTO admin){
    String msg = logic.registerAdmin(admin);
    return ResponseEntity.ok(msg);
}
@PatchMapping("/register/admin/update")
    public ResponseEntity<Admin> updateData(Authentication auth,@RequestBody AdminDTO admin){
     return logic.updateData(auth.getName(), admin)
             .map(ResponseEntity::ok)
             .orElse(ResponseEntity.notFound().build());

}
}
