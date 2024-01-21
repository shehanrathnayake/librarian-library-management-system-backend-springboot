package com.shehanrathnayake.service.util;

import com.shehanrathnayake.converter.UserPropertiesConverter;
import com.shehanrathnayake.entity.User;
import com.shehanrathnayake.to.UserTO;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class UserTransformer {
    private final ModelMapper mapper;

    public UserTransformer(ModelMapper mapper, UserPropertiesConverter userPropsConverter) {
        this.mapper = mapper;

        mapper.typeMap(Integer.class, String.class)
                .setConverter(ctx -> (ctx.getSource() != null) ? userPropsConverter.convertIdToString(ctx.getSource()) : null);
        mapper.typeMap(String.class, Integer.class)
                .setConverter(ctx -> (ctx.getSource() != null) ? userPropsConverter.convertIdToInt(ctx.getSource()): null);
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
