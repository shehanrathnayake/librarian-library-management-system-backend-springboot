package com.shehanrathnayake.service.util;

import com.shehanrathnayake.converter.UserPropertiesConverter;
import com.shehanrathnayake.entity.User;
import com.shehanrathnayake.to.UserTO;
import com.shehanrathnayake.util.UserRole;
import org.modelmapper.ModelMapper;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class UserTransformer {
    private final ModelMapper mapper;
    private final UserPropertiesConverter userPropsConverter;

    public UserTransformer(ModelMapper mapper, UserPropertiesConverter userPropsConverter) {
        this.mapper = mapper;
        this.userPropsConverter = userPropsConverter;

        mapper.typeMap(UserRole.class, String.class)
                .setConverter(ctx -> ctx.getSource().getRole());

        mapper.typeMap(String.class, UserRole.class)
                .setConverter(ctx -> userPropsConverter.convert(ctx.getSource()));
    }

    public User fromUserTO(UserTO userTO) {
        User user = mapper.map(userTO, User.class);
        if (userTO.getId() != null) user.setId(userPropsConverter.convertIdToInt(userTO.getId()));
        return user;
    }
    public UserTO toUserTO(User user) {
        UserTO userTO = mapper.map(user, UserTO.class);
        userTO.setId(userPropsConverter.convertIdToString(user.getId()));
        return userTO;
    }

    public List<UserTO> toUserTOList(List<User> userList) {
        return userList.stream().map(this::toUserTO).collect(Collectors.toList());
    }
    public org.springframework.security.core.userdetails.User fromUserToUserDetails(User user) {
        List<SimpleGrantedAuthority> authorities = List.of(new SimpleGrantedAuthority(user.getRoles()));
        return new org.springframework.security.core.userdetails.User(userPropsConverter.convertIdToString(user.getId()), user.getPassword(), authorities);
    }
}
