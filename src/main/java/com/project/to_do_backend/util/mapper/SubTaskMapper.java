package com.project.to_do_backend.util.mapper;

import org.springframework.stereotype.Component;

import com.project.to_do_backend.dto.SubTaskDTO;
import com.project.to_do_backend.model.SubTask;

@Component
public class SubTaskMapper {

    private final BaseMapper baseMapper;

    public SubTaskMapper(BaseMapper baseMapper) {
        this.baseMapper = baseMapper;
    }

    public SubTaskDTO entityToDTO(SubTask entity) {
        return baseMapper.convertToDTO(entity, SubTaskDTO.class);
    }

    public SubTask dtoToEntity(SubTaskDTO dto) {
        return baseMapper.convertToEntity(dto, SubTask.class);
    }
}
