package com.ecanteen.service.mapper;

import com.ecanteen.domain.School;
import com.ecanteen.service.dto.SchoolDTO;
import org.mapstruct.Mapper;

/**
 * Mapper for the entity {@link School} and its DTO {@link SchoolDTO}.
 */
@Mapper(componentModel = "spring")
public interface SchoolMapper extends EntityMapper<SchoolDTO, School> {}
