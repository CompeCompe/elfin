package ru.eremenko.elfin.dto;

import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;

/**
 * @author eremenko
 */
@Getter
@Setter
public class UserDataDto {
    @NonNull
    private String username;
    @NonNull
    private String email;
    @NonNull
    private String password;
}
