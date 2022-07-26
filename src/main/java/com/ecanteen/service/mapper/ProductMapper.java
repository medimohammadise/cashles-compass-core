package com.ecanteen.service.mapper;


import com.ecanteen.domain.Product;
import com.ecanteen.domain.ProductCategory;
import com.ecanteen.service.dto.ProductCategoryDTO;
import com.ecanteen.service.dto.ProductDTO;
import org.mapstruct.Mapper;

import java.util.List;

/**
 * Mapper for the entity {@link com.ecanteen.domain.Product} and its DTO {@link com.ecanteen.service.dto.ProductDTO}.
 */

@Mapper(componentModel = "spring" ,imports = { Product.class, ProductDTO.class })
public interface ProductMapper extends EntityMapper<ProductDTO, Product> {


}
