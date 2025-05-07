package org.example.service;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.example.controlleradvice.Errors;
import org.example.dto.response.ResponseSubscriptionDTO;
import org.example.entity.Subscription;
import org.example.exception.extend.SubscriptionNameTaken;
import org.example.mapper.SubscriptionMapper;
import org.example.repo.SubscriptionRepo;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class SubscriptionService {

    private final SubscriptionRepo subscriptionRepo;
    private final SubscriptionMapper subscriptionMapper;

    @Transactional
    public ResponseSubscriptionDTO createSubscription(dto.request.RequestSubscriptionDTO requestSubscriptionDTO) {
        Subscription subscription = new Subscription();
        if (subscriptionRepo.existsByServiceName(requestSubscriptionDTO.getServiceName())) {
            throw new SubscriptionNameTaken("Subscription with that name is already exists", Errors.SUB_NAME_TAKEN);
        }
        subscription.setServiceName(requestSubscriptionDTO.getServiceName());
        subscriptionRepo.save(subscription);
        return subscriptionMapper.toResponseSubscriptionDTO(subscription);
    }

    public List<ResponseSubscriptionDTO> getTopOfSubscriptions() {
        List<Subscription> subscriptions = subscriptionRepo.findAll();
        return subscriptions.stream().sorted(Comparator.comparingInt((Subscription sub) -> sub.getUsers().size()).reversed())
                .limit(3)
                .map(subscriptionMapper::toResponseSubscriptionDTO)
                .collect(Collectors.toList());
    }
}
