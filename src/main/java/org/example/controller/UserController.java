package org.example.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import lombok.RequiredArgsConstructor;
import org.example.dto.request.RequestUserDTO;
import org.example.dto.response.ResponseUserDTO;
import org.example.responses.CommonErrorApiResponses;
import org.example.responses.CommonErrorApiResponsesWith404;
import org.example.service.UserService;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @Operation(summary = "Получение пользователя по id")
    @ApiResponse(responseCode = "200", description = "Успешное выполнение операции",
            content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                    schema = @Schema(implementation = ResponseUserDTO.class)))
    @CommonErrorApiResponsesWith404
    @GetMapping("/{id}")
    public ResponseEntity<ResponseUserDTO> getUserById(@PathVariable("id") Long id) {
        return ResponseEntity.ok(userService.getUserById(id));
    }

    @Operation(summary = "Создание пользователя")
    @ApiResponse(responseCode = "200", description = "Успешное выполнение операции",
            content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                    schema = @Schema(implementation = ResponseUserDTO.class)))
    @CommonErrorApiResponses
    @PostMapping
    public ResponseEntity<ResponseUserDTO> createUser(@RequestBody RequestUserDTO userDTO) {
        return ResponseEntity.ok(userService.createUser(userDTO));
    }

    @Operation(summary = "Обновление пользователя по id")
    @ApiResponse(responseCode = "200", description = "Успешное выполнение операции",
            content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                    schema = @Schema(implementation = ResponseUserDTO.class)))
    @CommonErrorApiResponsesWith404
    @PutMapping("/{id}")
    public ResponseEntity<ResponseUserDTO> updateUser(@PathVariable Long id, @RequestBody RequestUserDTO userDTO) {
        return ResponseEntity.ok(userService.updateUser(id, userDTO));
    }

    @Operation(summary = "Удаление пользователя по id")
    @ApiResponse(responseCode = "200", description = "Успешное выполнение операции")
    @CommonErrorApiResponsesWith404
    @DeleteMapping("/{id}")
    public void deleteUser(@PathVariable Long id) {
        userService.deleteUser(id);
    }
}
