package com.ecanteen.service.impl;

import com.ecanteen.domain.ProductCategory;

import com.ecanteen.repository.ProductCategoryRepository;

import com.ecanteen.service.dto.ProductCategoryDTO;

import com.ecanteen.service.mapper.ProductCategoryMapper;

import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;
import org.mapstruct.factory.Mappers;
import org.mockito.Mockito;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.test.web.servlet.MockMvc;

import javax.persistence.EntityManager;
import java.time.Instant;
import java.time.ZoneId;
import java.time.ZoneOffset;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.atomic.AtomicLong;

import static org.assertj.core.api.Assertions.assertThat;

public class ProductCategoryServiceImplTest {




    private static final String DEFAULT_NAME = "AAAAAAAAAA";

    private static final String UPDATED_NAME = "BBBBBBBBBB";

    private static final String DEFAULT_CODE = "AAAAAAAAAA";

    private static final String UPDATED_CODE = "BBBBBBBBBB";

    private static final ZonedDateTime DEFAULT_CREATED_DATE = ZonedDateTime.ofInstant(Instant.ofEpochMilli(0L), ZoneOffset.UTC);
    private static final ZonedDateTime UPDATED_CREATED_DATE = ZonedDateTime.now(ZoneId.systemDefault()).withNano(0);
    private static final ZonedDateTime DEFAULT_MODIFIED_DATE = ZonedDateTime.ofInstant(Instant.ofEpochMilli(0L), ZoneOffset.UTC);
    private static final ZonedDateTime UPDATED_MODIFIED_DATE = ZonedDateTime.now(ZoneId.systemDefault()).withNano(0);
    private static final String DEFAULT_CREATED_BY = "AAAAAAAAAA";
    private static final String UPDATED_CREATED_BY = "BBBBBBBBBB";
    private static final String DEFAULT_MODIFIED_BY = "AAAAAAAAAA";
    private static final String UPDATED_MODIFIED_BY = "BBBBBBBBBB";


    private static final Random random = new Random();

    private static final AtomicLong count = new AtomicLong(random.nextInt() + (2L * Integer.MAX_VALUE));



     private ProductCategoryServiceImpl productCategoryServiceImpl;

    private ProductCategoryRepository productCategoryRepository;

    private EntityManager em;


    private MockMvc mockMvc;


    private ProductCategoryMapper productCategoryMapper;

    private ProductCategory productCategory;



    @BeforeEach
    void setUp() {
        productCategory = createEntity(em);
        productCategoryRepository = Mockito.mock(ProductCategoryRepository.class);
        productCategoryMapper = Mockito.mock(ProductCategoryMapper.class);
        productCategoryServiceImpl = new ProductCategoryServiceImpl(productCategoryMapper, productCategoryRepository);
    }


    //create data for test

    public static ProductCategory createEntity(EntityManager em) {
        ProductCategory productCategory = new ProductCategory();
        productCategory.name(DEFAULT_NAME);
        productCategory.code(DEFAULT_CODE);
        productCategory.setCreatedDate(DEFAULT_CREATED_DATE);
        productCategory.setModifiedDate(DEFAULT_MODIFIED_DATE);
        productCategory.setCreatedBy(DEFAULT_CREATED_BY);
        productCategory.setModifiedBy(DEFAULT_MODIFIED_BY);

        return productCategory;
    }




    @Test
    void shouldReturnAllProductCategorys() {



        List<ProductCategory> productCategoryList = new ArrayList<>();
        productCategoryList.add(productCategory);
        productCategoryList.add(productCategory);
        Pageable pageable = PageRequest.of(0, 5);
        Page<ProductCategory> productCategoryPage = new PageImpl<>(productCategoryList, pageable, productCategoryList.size());
        ProductCategoryDTO productCategoryDTO1 = new ProductCategoryDTO();
        productCategoryDTO1.setId(1L);
        ProductCategoryDTO productCategoryDTO2 = new ProductCategoryDTO();
        productCategoryDTO2.setId(2L);


        //act
        //in this section we invoke method of services for test
        List<ProductCategoryDTO> productCategoryDTOList = new ArrayList<>();
        productCategoryDTOList.add(productCategoryDTO1);
        productCategoryDTOList.add(productCategoryDTO2);

        //asser
        //and finally we write this section To get the result we expect

        Mockito.when(productCategoryMapper.toDto(productCategory)).thenReturn(productCategoryDTO1);
        Mockito.when(productCategoryMapper.toDto(productCategory)).thenReturn(productCategoryDTO2);
        Mockito.when(productCategoryRepository.findAll(pageable)).thenReturn(productCategoryPage);

        assertThat(productCategoryServiceImpl.findAll(pageable)).hasSize(2);
    }

    @Test
    void shouldCreateProductCategory() {
        ProductCategoryDTO productCategoryDTO = org.mapstruct.factory.Mappers.getMapper(ProductCategoryMapper.class).toDto(productCategory);
        Mockito.when(productCategoryMapper.toDto(productCategory)).thenReturn(productCategoryDTO);
        Mockito.when(productCategoryRepository.save(Mockito.any(ProductCategory.class)))
            .thenAnswer(i -> i.getArguments()[0]);
    }

    @Test
    void shouldUpdateProductCategory() {

        productCategoryRepository.saveAndFlush(productCategory);
        int databaseSizeBeforeUpdate = productCategoryRepository.findAll().size();

        ProductCategory partialUpdatedProductCategory = new ProductCategory();
        partialUpdatedProductCategory.setId(productCategory.getId());

        partialUpdatedProductCategory.setName(UPDATED_NAME);
        partialUpdatedProductCategory.setCode(UPDATED_CODE);

        partialUpdatedProductCategory.setCreatedDate(UPDATED_CREATED_DATE);
        partialUpdatedProductCategory.setModifiedDate(UPDATED_MODIFIED_DATE);
        partialUpdatedProductCategory.setCreatedBy(UPDATED_CREATED_BY);
        partialUpdatedProductCategory.setModifiedBy(UPDATED_MODIFIED_BY);


        ProductCategoryDTO productCategoryDTO = Mappers.getMapper(ProductCategoryMapper.class).toDto(partialUpdatedProductCategory);
        Mockito.when(productCategoryMapper.toDto(partialUpdatedProductCategory)).thenReturn(productCategoryDTO);
        Mockito.when(productCategoryRepository.save(Mockito.any(ProductCategory.class)))
            .thenAnswer(i -> i.getArguments()[0]);

        assertThat(productCategoryRepository.findAll().size()).isEqualTo(databaseSizeBeforeUpdate);
    }
}
