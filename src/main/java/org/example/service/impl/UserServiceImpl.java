package org.example.service.impl;

import lombok.RequiredArgsConstructor;
import org.example.dto.entity.User;
import org.example.dto.request.RequestUserDTO;
import org.example.dto.response.ResponseUserDTO;
import org.example.exception.extend.user.UserNotFoundException;
import org.example.mapper.UserMapper;
import org.example.repository.UserRepository;
import org.example.service.UserService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepo;
    private final UserMapper userMapper;
    private final UserUtilService userUtilService;


    @Override
    @Transactional(readOnly = true)
    public ResponseUserDTO getUserById(Long id) {
        User user = userRepo.findById(id).orElseThrow(() -> new UserNotFoundException("User not found"));
        return userMapper.toResponseUserDTO(user);
    }

    @Override
    @Transactional
    public ResponseUserDTO createUser(RequestUserDTO requestUserDTO) {
        User user = new User();
        userUtilService.setUserParams(user, requestUserDTO);

        userRepo.save(user);
        return userMapper.toResponseUserDTO(user);
    }

    @Override
    @Transactional
    public ResponseUserDTO updateUser(Long id, RequestUserDTO requestUserDTO) {
        User user = userRepo.findById(id).orElseThrow(() -> new UserNotFoundException("User not found"));
        userUtilService.setUserParams(user, requestUserDTO);
        userRepo.save(user);
        return userMapper.toResponseUserDTO(user);
    }

    @Override
    public void deleteUser(Long id) {
        userRepo.deleteById(id);
    }

}
