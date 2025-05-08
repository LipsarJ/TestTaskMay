package org.example.repository;

import org.example.dto.entity.ServiceName;
import org.example.dto.entity.Subscription;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface SubscriptionRepository extends JpaRepository<Subscription, Long> {

    Optional<Subscription> findByServiceName(ServiceName serviceName);

    boolean existsByServiceName(ServiceName serviceName);

}
