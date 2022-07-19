package com.ecanteen.service.mapper;


import com.ecanteen.domain.Product;
import com.ecanteen.service.dto.ProductDTO;
import org.mapstruct.Mapper;

/**
 * Mapper for the entity {@link com.ecanteen.domain.Product} and its DTO {@link com.ecanteen.service.dto.ProductDTO}.
 */

@Mapper(componentModel = "spring")
public interface ProductMapper extends EntityMapper<ProductDTO, Product> {}
