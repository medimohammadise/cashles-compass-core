package com.ecanteen.service.mapper;



import com.ecanteen.domain.ProductCategory;

import com.ecanteen.service.dto.ProductCategoryDTO;
import org.mapstruct.Mapper;

import java.util.List;

/**
 * Mapper for the entity {@link com.ecanteen.domain.ProductCategory} and its DTO {@link com.ecanteen.service.dto.ProductCategoryDTO}.
 */

@Mapper(componentModel = "spring" ,imports = { ProductCategory.class, ProductCategoryDTO.class })
public interface ProductCategoryMapper extends EntityMapper<ProductCategoryDTO, ProductCategory> {


}
