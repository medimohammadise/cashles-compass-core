package com.ecanteen.service.impl;

import com.ecanteen.domain.ProductCategory;
import com.ecanteen.repository.ProductCategoryRepository;
import com.ecanteen.service.dto.ProductCategoryDTO;
import com.ecanteen.service.mapper.ProductCategoryMapper;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import javax.persistence.EntityManager;
import java.time.Instant;
import java.time.ZoneId;
import java.time.ZoneOffset;
import java.time.ZonedDateTime;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
public class ProductCategoryServiceIntTest {



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


    private ProductCategory productCategory;

    private ProductCategoryRepository productCategoryRepository;

    private ProductCategoryMapper productCategoryMapper;
    private EntityManager em;
    @Autowired
    private MockMvc mockMvc;


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

    @BeforeEach
    void setUp() {
        productCategory = createEntity(em);
    }


    @Test
    void shouldReturnAllProductCategory() throws Exception {
        mockMvc.perform(get("/api/productsCategory"))
            .andExpect(status().isOk());
    }

    @Test
    void shouldUpdateProductCategory() throws Exception {
        productCategoryRepository.saveAndFlush(productCategory);
         ProductCategory updatedProductCategory = productCategoryRepository.findById(productCategory.getId()).get();
         em.detach(updatedProductCategory);
        updatedProductCategory
            .name(UPDATED_NAME)
            .code(UPDATED_CODE)
            .createdDate(UPDATED_CREATED_DATE)
            .modifiedDate(UPDATED_MODIFIED_DATE)
            .createdBy(UPDATED_CREATED_BY)
            .modifiedBy(UPDATED_MODIFIED_BY);

        ProductCategoryDTO productCategoryDTO = productCategoryMapper.toDto(updatedProductCategory);

        mockMvc.perform( MockMvcRequestBuilders
                .put("/api/productCategories/{id}", 14)
                .content(TestUtil.convertObjectToJsonBytes(updatedProductCategory))
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isOk());
    }
}
