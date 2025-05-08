package org.example.mapper;

import org.example.dto.response.ResponseUserDTO;
import org.example.dto.entity.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring", uses = {BaseLocalDateTimeOffsetDateTimeMapper.class, SubscriptionMapper.class})
public interface UserMapper {

    @Mapping(target = "subscriptions", source = "user.subscriptionSet")
    ResponseUserDTO toResponseUserDTO(User user);
}
