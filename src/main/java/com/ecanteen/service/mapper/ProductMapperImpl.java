package com.ecanteen.service.mapper;

import com.ecanteen.domain.Product;
import com.ecanteen.service.dto.ProductDTO;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class ProductMapperImpl implements ProductMapper {
    @Override
    public Product toEntity(ProductDTO dto) {
        if (dto == null) {
            return null;
        }
        Product product = new Product();

        product.setId(dto.getId());
        product.setName(dto.getName());
        product.setImageUrl(dto.getImageUrl());
        product.setBarcode(dto.getBarcode());
        product.setCategory(dto.getCategory());
        product.setExpiryDate(dto.getExpiryDate());
        product.setGrade(dto.getGrade());
        product.setPrice(dto.getPrice());
        product.setCreatedBy(dto.getCreatedBy());
        product.setCreatedDate(dto.getModifiedDate());
        product.setModifiedBy(dto.getModifiedBy());
        product.setModifiedDate(dto.getModifiedDate());


        return product;
    }

    @Override
    public ProductDTO toDto(Product entity) {
        if (entity == null) {
            return null;
        }

        ProductDTO productDTO = new ProductDTO();

        productDTO.setId(entity.getId());
        productDTO.setName(entity.getName());
        productDTO.setImageUrl(entity.getImageUrl());
        productDTO.setBarcode(entity.getBarcode());
        productDTO.setCategory(entity.getCategory());
        productDTO.setExpiryDate(entity.getExpiryDate());
        productDTO.setGrade(entity.getGrade());
        productDTO.setPrice(entity.getPrice());
        productDTO.setCreatedBy(entity.getCreatedBy());
        productDTO.setCreatedDate(entity.getCreatedDate());
        productDTO.setModifiedBy(entity.getModifiedBy());
        productDTO.setModifiedDate(entity.getModifiedDate());

        return productDTO;
    }


    @Override
    public List<Product> toEntity(List<ProductDTO> dtoList) {
        if (dtoList == null) {
            return null;
        }

        List<Product> list = new ArrayList<Product>(dtoList.size());
        for (ProductDTO productDTO : dtoList) {
            list.add(toEntity(productDTO ));
        }

        return list;
    }

    @Override
    public List<ProductDTO> toDto(List<Product> entityList) {
        if (entityList == null) {
            return null;
        }

        List<ProductDTO> list = new ArrayList<ProductDTO>(entityList.size());
        for (Product product : entityList) {
            list.add(toDto(product));
        }

        return list;
    }

    @Override
    public void partialUpdate(Product entity, ProductDTO dto) {
        if (dto == null) {
            return;
        }

        if (dto.getId() != null) {
            entity.setId(dto.getId());
        }
        if (dto.getName() != null) {
            entity.name(dto.getName());
        }
        if (dto.getBarcode() != null) {
            entity.barcode(dto.getBarcode());
        }
        if (dto.getCategory() != null) {
            entity.category(dto.getCategory());
        }
        if (dto.getImageUrl() != null) {
            entity.imageUrl(dto.getImageUrl());
        }
        if (dto.getExpiryDate() != null) {
            entity.expiryDate(dto.getExpiryDate());
        }
        if (dto.getPrice() != null) {
            entity.price(dto.getPrice());
        }
        if (dto.getGrade() != null) {

            entity.setGrade(dto.getGrade());
        }
        if (dto.getCreatedDate() != null) {
            entity.createdDate(dto.getCreatedDate());
        }
        if (dto.getModifiedDate() != null) {
            entity.modifiedDate(dto.getModifiedDate());
        }
        if (dto.getCreatedBy() != null) {
            entity.createdBy(dto.getCreatedBy());
        }
        if (dto.getModifiedBy() != null) {
            entity.modifiedBy(dto.getModifiedBy());
        }


    }
}
