package org.example.service.impl;

import lombok.RequiredArgsConstructor;
import org.example.dto.entity.Subscription;
import org.example.dto.request.RequestSubscriptionDTO;
import org.example.dto.response.ResponseSubscriptionDTO;
import org.example.exception.extend.subscription.SubscriptionNameIllegal;
import org.example.exception.extend.subscription.SubscriptionNameTaken;
import org.example.mapper.SubscriptionMapper;
import org.example.repository.SubscriptionRepository;
import org.example.responses.Errors;
import org.example.service.SubscriptionService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class SubscriptionServiceImpl implements SubscriptionService {

    private final SubscriptionRepository subscriptionRepo;
    private final SubscriptionMapper subscriptionMapper;

    @Override
    @Transactional
    public ResponseSubscriptionDTO createSubscription(RequestSubscriptionDTO requestSubscriptionDTO) {
        Subscription subscription = new Subscription();
        if (subscriptionRepo.existsByServiceName(requestSubscriptionDTO.getServiceName())) {
            throw new SubscriptionNameTaken("Subscription with that name is already exists", Errors.SUB_NAME_TAKEN);
        }

        try {
            subscription.setServiceName(requestSubscriptionDTO.getServiceName());
        } catch (IllegalArgumentException e) {
            throw new SubscriptionNameIllegal("Subscription has wrong name", Errors.SUB_NAME_ILLEGAL);
        }
        subscriptionRepo.save(subscription);
        return subscriptionMapper.toResponseSubscriptionDTO(subscription);
    }

    @Override
    @Transactional(readOnly = true)
    public List<ResponseSubscriptionDTO> getTopOfSubscriptions() {
        List<Subscription> subscriptions = subscriptionRepo.findAll();
        return subscriptions.stream().sorted(Comparator.comparingInt((Subscription sub) -> sub.getUsers().size()).reversed())
                .limit(3)
                .map(subscriptionMapper::toResponseSubscriptionDTO)
                .collect(Collectors.toList());
    }
}
