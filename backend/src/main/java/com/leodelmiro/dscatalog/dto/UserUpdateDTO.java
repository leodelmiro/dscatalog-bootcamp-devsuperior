package com.leodelmiro.dscatalog.dto;

import com.leodelmiro.dscatalog.services.validation.UserUpdateValid;

@UserUpdateValid
public class UserUpdateDTO extends UserDTO {
    private static final long serialVersionUID = 1L;

    public UserUpdateDTO() {
        super();
    }
}
