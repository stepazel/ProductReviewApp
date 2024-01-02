package com.isep.api.gateway.mappers;

import com.isep.api.gateway.model.User;
import com.isep.api.gateway.model.dto.UserView;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public abstract class UserViewMapper {

    public abstract UserView toUserView(User user);
}
