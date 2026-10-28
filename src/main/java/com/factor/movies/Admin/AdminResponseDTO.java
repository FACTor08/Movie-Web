package com.factor.movies.Admin;

import com.factor.movies.entity.Roles;
import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class AdminResponseDTO {

        private String email;
        private String firstname;
        private String phone;
        private String lastname;
        private String username;
        private Roles role;
}
