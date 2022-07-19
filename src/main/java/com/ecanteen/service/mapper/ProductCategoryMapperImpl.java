package com.ecanteen.service.mapper;


import com.ecanteen.domain.ProductCategory;
import com.ecanteen.service.dto.ProductCategoryDTO;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class ProductCategoryMapperImpl implements ProductCategoryMapper {
    @Override
    public ProductCategory toEntity(ProductCategoryDTO dto) {
        if (dto == null) {
            return null;
        }
        ProductCategory productCategory = new ProductCategory();

        productCategory.setId(dto.getId());
        productCategory.setName(dto.getName());
        productCategory.setCode(dto.getCode());
        productCategory.setCreatedBy(dto.getCreatedBy());
        productCategory.setCreatedDate(dto.getModifiedDate());
        productCategory.setModifiedBy(dto.getModifiedBy());
        productCategory.setModifiedDate(dto.getModifiedDate());

        return productCategory;
    }

    @Override
    public ProductCategoryDTO toDto(ProductCategory entity) {
        if (entity == null) {
            return null;
        }

        ProductCategoryDTO productCategoryDTO = new ProductCategoryDTO();

        productCategoryDTO.setId(entity.getId());
        productCategoryDTO.setName(entity.getName());
        productCategoryDTO.setCode(entity.getCode());
        productCategoryDTO.setCreatedBy(entity.getCreatedBy());
        productCategoryDTO.setCreatedDate(entity.getModifiedDate());
        productCategoryDTO.setModifiedBy(entity.getModifiedBy());
        productCategoryDTO.setModifiedDate(entity.getModifiedDate());

        return productCategoryDTO;

    }


    @Override
    public List<ProductCategory> toEntity(List<ProductCategoryDTO> dtoList) {
        if (dtoList == null) {
            return null;
        }

        List<ProductCategory> list = new ArrayList<ProductCategory>(dtoList.size());
        for (ProductCategoryDTO productCategoryDTO : dtoList) {
            list.add(toEntity(productCategoryDTO));
        }

        return list;
    }

    @Override
    public List<ProductCategoryDTO> toDto(List<ProductCategory> entityList) {
        if (entityList == null) {
            return null;
        }

        List<ProductCategoryDTO> list = new ArrayList<ProductCategoryDTO>(entityList.size());
        for (ProductCategory productCategory : entityList) {
            list.add(toDto(productCategory));
        }

        return list;
    }

    @Override
    public void partialUpdate(ProductCategory entity, ProductCategoryDTO dto) {
        if (dto == null) {
            return;
        }

        if (dto.getId() != null) {
            entity.setId(dto.getId());
        }
        if (dto.getName() != null) {
            entity.setName(dto.getName());
        }
        if (dto.getCode() != null) {
            entity.setCode(dto.getCode());
        }
        if (dto.getCreatedDate() != null) {
            entity.createdDate(dto.getCreatedDate());
        }
        if (dto.getModifiedDate() != null) {
            entity.modifiedDate(dto.getModifiedDate());
        }
        if (dto.getModifiedBy() != null) {
            entity.modifiedBy(dto.getModifiedBy());
        }
        if (dto.getModifiedDate() != null) {
            entity.modifiedDate(dto.getModifiedDate());
        }

    }
}

