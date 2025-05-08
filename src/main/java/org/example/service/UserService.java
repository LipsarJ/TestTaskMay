package org.example.service;

import org.example.dto.request.RequestUserDTO;
import org.example.dto.response.ResponseUserDTO;

public interface UserService {

    ResponseUserDTO getUserById(Long id);

    ResponseUserDTO createUser(RequestUserDTO requestUserDTO);

    ResponseUserDTO updateUser(Long id, RequestUserDTO requestUserDTO);

    void deleteUser(Long id);
}
