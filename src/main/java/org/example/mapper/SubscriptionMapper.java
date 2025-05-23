package org.example.mapper;

import org.example.dto.response.ResponseSubscriptionDTO;
import org.example.dto.entity.Subscription;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface SubscriptionMapper {
    ResponseSubscriptionDTO toResponseSubscriptionDTO(Subscription subscription);
}
