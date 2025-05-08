package org.example.service;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.example.controlleradvice.Errors;
import org.example.dto.entity.User;
import org.example.dto.request.RequestUserDTO;
import org.example.dto.response.ResponseUserDTO;
import org.example.exception.extend.user.UserNotFoundException;
import org.example.exception.extend.user.UsernameTakenException;
import org.example.mapper.UserMapper;
import org.example.repository.UserRepository;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepo;
    private final UserMapper userMapper;


    public ResponseUserDTO getUserById(Long id) {
        User user = userRepo.findById(id).orElseThrow(() -> new UserNotFoundException("User not found"));
        return userMapper.toResponseUserDTO(user);
    }

    @Transactional
    public ResponseUserDTO createUser(RequestUserDTO requestUserDTO) {
        User user = new User();
        setUserParams(user, requestUserDTO);

        userRepo.save(user);
        return userMapper.toResponseUserDTO(user);
    }

    @Transactional
    public ResponseUserDTO updateUser(Long id, RequestUserDTO requestUserDTO) {
        User user = userRepo.findById(id).orElseThrow(() -> new UserNotFoundException("User not found"));
        setUserParams(user, requestUserDTO);
        userRepo.save(user);
        return userMapper.toResponseUserDTO(user);
    }

    public void deleteUser(Long id) {
        userRepo.deleteById(id);
    }

    private void setUserParams(User user, RequestUserDTO requestUserDTO) {
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
