package org.example.repo;

import org.example.entity.Subscription;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface SubscriptionRepo extends JpaRepository<Subscription, Long> {

    Optional<Subscription> findByServiceName(String serviceName);

    boolean existsByServiceName(String serviceName);

}
