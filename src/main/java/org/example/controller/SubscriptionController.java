package org.example.controller;

import dto.request.RequestSubscriptionDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import lombok.RequiredArgsConstructor;
import org.example.controlleradvice.CommonErrorApiResponses;
import org.example.dto.response.ResponseSubscriptionDTO;
import org.example.service.SubscriptionService;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/subscriptions")
@RequiredArgsConstructor
public class SubscriptionController {

    private final SubscriptionService subscriptionService;

    @Operation(summary = "Получение топ 3 подписок по кол-ву пользователей")
    @ApiResponse(responseCode = "200", description = "Успешное выполнение операции",
            content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                    schema = @Schema(implementation = ResponseSubscriptionDTO.class)))
    @CommonErrorApiResponses
    @GetMapping("/top")
    public ResponseEntity<List<ResponseSubscriptionDTO>> getTopOfSubscriptions() {
        return ResponseEntity.ok(subscriptionService.getTopOfSubscriptions());
    }


    @Operation(summary = "Добавление новой подписки в базу")
    @ApiResponse(responseCode = "200", description = "Успешное выполнение операции",
            content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                    schema = @Schema(implementation = ResponseSubscriptionDTO.class)))
    @CommonErrorApiResponses
    @PostMapping
    public ResponseEntity<ResponseSubscriptionDTO> createSubscription(@RequestBody RequestSubscriptionDTO requestSubscriptionDTO) {
        return ResponseEntity.ok(subscriptionService.createSubscription(requestSubscriptionDTO));
    }
}
