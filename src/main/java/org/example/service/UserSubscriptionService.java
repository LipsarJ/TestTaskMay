package org.example.service;

import org.example.dto.request.RequestSubscriptionDTO;
import org.example.dto.response.ResponseSubscriptionDTO;
import org.example.dto.response.ResponseUserDTO;

import java.util.List;

public interface UserSubscriptionService {

    List<ResponseSubscriptionDTO> getUserSubscriptions(Long userId);

    ResponseUserDTO addSubscription(Long userId, RequestSubscriptionDTO requestSubscriptionDTO);

    void deleteSubscription(Long userId, Long subscriptionId);
}
