package org.example.service.impl;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.example.dto.entity.Subscription;
import org.example.dto.entity.User;
import org.example.dto.request.RequestSubscriptionDTO;
import org.example.dto.response.ResponseSubscriptionDTO;
import org.example.dto.response.ResponseUserDTO;
import org.example.exception.extend.subscription.SubscriptionNotFoundException;
import org.example.exception.extend.user.UserNotFoundException;
import org.example.mapper.SubscriptionMapper;
import org.example.mapper.UserMapper;
import org.example.repository.SubscriptionRepository;
import org.example.repository.UserRepository;
import org.example.service.UserSubscriptionService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserSubscriptionServiceImpl implements UserSubscriptionService {

    private final UserRepository userRepo;
    private final SubscriptionRepository subscriptionRepo;
    private final UserMapper userMapper;
    private final SubscriptionMapper subscriptionMapper;

    @Override
    public List<ResponseSubscriptionDTO> getUserSubscriptions(Long userId) {
        User user = userRepo.findById(userId).orElseThrow(() -> new UserNotFoundException("User not found"));
        return user.getSubscriptionSet().stream().map(subscriptionMapper::toResponseSubscriptionDTO).collect(Collectors.toList());
    }

    @Override
    @Transactional
    public ResponseUserDTO addSubscription(Long userId, RequestSubscriptionDTO requestSubscriptionDTO) {
        User user = userRepo.findById(userId).orElseThrow(() -> new UserNotFoundException("User not found"));
        Subscription subscription = subscriptionRepo.findByServiceName(requestSubscriptionDTO.getServiceName()).orElseThrow(() -> new SubscriptionNotFoundException("Subscription is not found"));

        user.getSubscriptionSet().add(subscription);
        userRepo.save(user);
        return userMapper.toResponseUserDTO(user);
    }

    @Override
    public void deleteSubscription(Long userId, Long subscriptionId) {
        User user = userRepo.findById(userId).orElseThrow(() -> new UserNotFoundException("User not found"));
        Subscription subscription = subscriptionRepo.findById(subscriptionId).orElseThrow(() -> new SubscriptionNotFoundException("Subscription is not found"));
        user.getSubscriptionSet().remove(subscription);
        userRepo.save(user);
    }
}
