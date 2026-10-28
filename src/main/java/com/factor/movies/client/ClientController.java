package com.factor.movies.client;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;


@RestController
@RequiredArgsConstructor
public class ClientController {

     private final ClientLogic logic;

    @PostMapping("/register/user")
    public ResponseEntity<ClientResponseDTO> addUsers(@RequestBody ClientDTO user){
        ClientResponseDTO data = logic.registerUsers(user);
        return ResponseEntity.ok(data);
    }
    @PatchMapping("/register/user/update")
    public ResponseEntity<ClientResponseDTO> updateData(Authentication auth, @RequestBody ClientDTO user){
       ClientResponseDTO response = logic.updateData(auth.getName(), user);
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/account/delete")
    public ResponseEntity<Void> deleteAccount(@RequestBody ClientDTO user, Authentication auth){

        logic.deleteAccount(user.getUsername(), auth.getName());

        return ResponseEntity.noContent().build();
    }
}
