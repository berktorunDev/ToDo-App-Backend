package com.project.to_do_backend.util.mapper;

import org.springframework.stereotype.Component;

import com.project.to_do_backend.dto.MainTaskDTO;
import com.project.to_do_backend.model.MainTask;

@Component
public class MainTaskMapper {

    private final BaseMapper baseMapper;

    public MainTaskMapper(BaseMapper baseMapper) {
        this.baseMapper = baseMapper;
    }

    public MainTaskDTO entityToDTO(MainTask entity) {
        return baseMapper.convertToDTO(entity, MainTaskDTO.class);
    }

    public MainTask dtoToEntity(MainTaskDTO dto) {
        return baseMapper.convertToEntity(dto, MainTask.class);
    }
}
