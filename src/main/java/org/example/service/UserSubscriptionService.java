package org.example.service;

import dto.request.RequestSubscriptionDTO;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.example.dto.response.ResponseSubscriptionDTO;
import org.example.dto.response.ResponseUserDTO;
import org.example.entity.Subscription;
import org.example.entity.User;
import org.example.exception.extend.SubscriptionNotFoundException;
import org.example.exception.extend.UserNotFoundException;
import org.example.mapper.SubscriptionMapper;
import org.example.mapper.UserMapper;
import org.example.repo.SubscriptionRepo;
import org.example.repo.UserRepo;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserSubscriptionService {

    private final UserRepo userRepo;
    private final SubscriptionRepo subscriptionRepo;
    private final UserMapper userMapper;
    private final SubscriptionMapper subscriptionMapper;

    public List<ResponseSubscriptionDTO> getUserSubscriptions(Long userId) {
        User user = userRepo.findById(userId).orElseThrow(() -> new UserNotFoundException("User not found"));
        return user.getSubscriptionSet().stream().map(subscriptionMapper::toResponseSubscriptionDTO).collect(Collectors.toList());
    }

    @Transactional
    public ResponseUserDTO addSubscription(Long userId, RequestSubscriptionDTO requestSubscriptionDTO) {
        User user = userRepo.findById(userId).orElseThrow(() -> new UserNotFoundException("User not found"));
        Subscription subscription = subscriptionRepo.findByServiceName(requestSubscriptionDTO.getServiceName()).orElseThrow(() -> new SubscriptionNotFoundException("Subscription is not found"));

        user.getSubscriptionSet().add(subscription);
        userRepo.save(user);
        return userMapper.toResponseUserDTO(user);
    }

    public void deleteSubscription(Long userId, Long subscriptionId) {
        User user = userRepo.findById(userId).orElseThrow(() -> new UserNotFoundException("User not found"));
        Subscription subscription = subscriptionRepo.findById(subscriptionId).orElseThrow(() -> new SubscriptionNotFoundException("Subscription is not found"));
        user.getSubscriptionSet().remove(subscription);
        userRepo.save(user);
    }
}
