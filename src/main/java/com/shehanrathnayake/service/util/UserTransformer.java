package com.shehanrathnayake.service.util;

import com.shehanrathnayake.converter.UserRoleConverter;
import com.shehanrathnayake.entity.User;
import com.shehanrathnayake.to.UserTO;
import com.shehanrathnayake.util.UserRole;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class UserTransformer {
    private final ModelMapper mapper;
    private final UserRoleConverter userRoleConverter;

    public UserTransformer(ModelMapper mapper, UserRoleConverter userRoleConverter) {
        this.mapper = mapper;
        this.userRoleConverter = userRoleConverter;

        mapper.typeMap(UserRole.class, String.class)
                .setConverter(ctx -> ctx.getSource().getRole());

        mapper.typeMap(String.class, UserRole.class)
                .setConverter(ctx -> userRoleConverter.convert(ctx.getSource()));

        mapper.typeMap(Integer.class, String.class)
                .setConverter(ctx -> (ctx.getSource() != null) ? String.format("U%06d",ctx.getSource()) : null);
        mapper.typeMap(String.class, Integer.class)
                .setConverter(ctx -> (ctx.getSource() != null) ? Integer.parseInt(ctx.getSource().substring(1)): null);
    }

    public User fromUserTO(UserTO userTO) {
        return mapper.map(userTO, User.class);
    }
    public UserTO toUserTO(User user) {
        return mapper.map(user, UserTO.class);
    }

    public List<UserTO> toUserTOList(List<User> userList) {
        return userList.stream().map(this::toUserTO).collect(Collectors.toList());
    }

}
