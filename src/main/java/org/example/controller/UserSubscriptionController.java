package org.example.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import lombok.RequiredArgsConstructor;
import org.example.dto.request.RequestSubscriptionDTO;
import org.example.dto.response.ResponseSubscriptionDTO;
import org.example.dto.response.ResponseUserDTO;
import org.example.responses.CommonErrorApiResponsesWith404;
import org.example.service.UserSubscriptionService;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/users/{id}/subscriptions")
@RequiredArgsConstructor
public class UserSubscriptionController {

    private final UserSubscriptionService userSubscriptionService;

    @Operation(summary = "Получение подписок для пользователя по id")
    @ApiResponse(responseCode = "200", description = "Успешное выполнение операции",
            content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                    schema = @Schema(implementation = ResponseSubscriptionDTO.class)))
    @CommonErrorApiResponsesWith404
    @GetMapping
    public ResponseEntity<List<ResponseSubscriptionDTO>> getSubscriptions(@PathVariable Long id) {
        return ResponseEntity.ok(userSubscriptionService.getUserSubscriptions(id));
    }

    @Operation(summary = "Добавление подписки пользователю")
    @ApiResponse(responseCode = "200", description = "Успешное выполнение операции",
            content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                    schema = @Schema(implementation = ResponseUserDTO.class)))
    @CommonErrorApiResponsesWith404
    @PostMapping
    public ResponseEntity<ResponseUserDTO> addSubscription(@PathVariable Long id, @RequestBody RequestSubscriptionDTO requestSubscriptionDTO) {
        return ResponseEntity.ok(userSubscriptionService.addSubscription(id, requestSubscriptionDTO));
    }

    @Operation(summary = "Удаление подписки для пользователя по id")
    @ApiResponse(responseCode = "200", description = "Успешное выполнение операции")
    @CommonErrorApiResponsesWith404
    @DeleteMapping("/{subId}")
    public void deleteSubscription(@PathVariable Long id, @PathVariable Long subId) {
        userSubscriptionService.deleteSubscription(id, subId);
    }
}
