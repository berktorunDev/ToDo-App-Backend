package com.project.to_do_backend.util.mapper;

import org.springframework.stereotype.Component;

import com.project.to_do_backend.dto.UserDTO;
import com.project.to_do_backend.model.User;

@Component
public class UserMapper {

    private final BaseMapper baseMapper;

    public UserMapper(BaseMapper baseMapper) {
        this.baseMapper = baseMapper;
    }

    public UserDTO entityToDTO(User entity) {
        return baseMapper.convertToDTO(entity, UserDTO.class);
    }

    public User dtoToEntity(UserDTO dto) {
        return baseMapper.convertToEntity(dto, User.class);
    }
}