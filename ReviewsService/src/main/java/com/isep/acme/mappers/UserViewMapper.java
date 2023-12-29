package com.isep.acme.mappers;

import com.isep.acme.model.User;
import com.isep.acme.model.dto.UserView;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public abstract class UserViewMapper {

    public abstract UserView toUserView(User user);
}
