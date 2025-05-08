package org.example.service;

import org.example.dto.request.RequestSubscriptionDTO;
import org.example.dto.response.ResponseSubscriptionDTO;

import java.util.List;

public interface SubscriptionService {

    ResponseSubscriptionDTO createSubscription(RequestSubscriptionDTO requestSubscriptionDTO);

    List<ResponseSubscriptionDTO> getTopOfSubscriptions();
}
