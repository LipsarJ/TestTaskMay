package org.example.service.impl;


import lombok.RequiredArgsConstructor;
import org.example.dto.entity.User;
import org.example.dto.request.RequestUserDTO;
import org.example.exception.extend.user.UsernameTakenException;
import org.example.repository.UserRepository;
import org.example.responses.Errors;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Component
public class UserUtilService {

    private final UserRepository userRepo;

    @Transactional
    public void setUserParams(User user, RequestUserDTO requestUserDTO) {
        if (validateUsername(requestUserDTO.getUsername(), user.getId())) {
            throw new UsernameTakenException("Username is taken", Errors.USERNAME_TAKEN);
        }
        if (validateEmail(requestUserDTO.getEmail(), user.getId())) {
            throw new UsernameTakenException("Email is invalid or taken", Errors.EMAIL_TAKEN);
        }
        user.setUsername(requestUserDTO.getUsername());
        user.setEmail(requestUserDTO.getEmail());
        user.setFirstname(requestUserDTO.getFirstname());
        user.setLastname(requestUserDTO.getLastname());
        user.setMiddlename(requestUserDTO.getMiddlename());

    }

    private boolean validateUsername(String username, Long id) {
        return userRepo.existsByUsernameAndIdNot(username, id);
    }

    private boolean validateEmail(String email, Long id) {
        return userRepo.existsByEmailAndIdNot(email, id);
    }

}
