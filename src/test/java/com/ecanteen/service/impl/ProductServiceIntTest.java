package com.ecanteen.service.impl;

import com.ecanteen.domain.Product;
 import com.ecanteen.domain.enumeration.Category;
import com.ecanteen.domain.enumeration.Grade;

import com.ecanteen.repository.ProductRepository;
 import com.ecanteen.service.dto.ProductDTO;
 import com.ecanteen.service.mapper.ProductMapper;
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
public class ProductServiceIntTest {
    private static final String DEFAULT_NAME = "AAAAAAAAAA";

    private static final String UPDATED_NAME = "BBBBBBBBBB";

    private static final double DEFAULT_PRICE = 0.0 ;

    private static final double UPDATED_PRICE= 0.0 ;

    private static final String DEFAULT_BARCODE = "AAAAAAAAAA";

    private static final String UPDATED_BARCODE = "BBBBBBBBBB";
    private static final ZonedDateTime DEFAULT_CREATED_DATE = ZonedDateTime.ofInstant(Instant.ofEpochMilli(0L), ZoneOffset.UTC);
    private static final ZonedDateTime UPDATED_CREATED_DATE = ZonedDateTime.now(ZoneId.systemDefault()).withNano(0);
    private static final ZonedDateTime DEFAULT_MODIFIED_DATE = ZonedDateTime.ofInstant(Instant.ofEpochMilli(0L), ZoneOffset.UTC);
    private static final ZonedDateTime UPDATED_MODIFIED_DATE = ZonedDateTime.now(ZoneId.systemDefault()).withNano(0);
    private static final String DEFAULT_CREATED_BY = "AAAAAAAAAA";
    private static final String UPDATED_CREATED_BY = "BBBBBBBBBB";
    private static final String DEFAULT_MODIFIED_BY = "AAAAAAAAAA";
    private static final String UPDATED_MODIFIED_BY = "BBBBBBBBBB";
    private static final String DEFAULT_IMAGEURL= "AAAAAAAAAA";
    private static final String UPDATED_IMAGEURL = "BBBBBBBBBB";

    private static final ZonedDateTime DEFAULT_EXPIRY_DATE = ZonedDateTime.now(ZoneId.systemDefault()).withNano(0);

    private static final ZonedDateTime UPDATED_EXPIRY_DATE = ZonedDateTime.now(ZoneId.systemDefault()).withNano(0);


    private static final Grade DEFAULT_GRADE = Grade.FAIR;
    private static final Grade UPDATED_GRADE = Grade.FAIR;

    private static final Category DEFAULT_CATEGORY = Category.EATS;
    private static final Category UPDATED_CATEGORY = Category.DRINK;

    private Product product;

    private ProductRepository productRepository;

    private ProductMapper productMapper;
    private EntityManager em;
    @Autowired
    private MockMvc mockMvc;


    public static Product createEntity(EntityManager em) {
        Product product = new Product();
        product.setName(DEFAULT_NAME);
        product.setPrice(DEFAULT_PRICE);
        product.setBarcode(DEFAULT_BARCODE);
        product.setExpiryDate(DEFAULT_EXPIRY_DATE);
        product.setImageUrl(DEFAULT_IMAGEURL);
        product.setCreatedDate(DEFAULT_CREATED_DATE);
        product.setGrade(DEFAULT_GRADE);
        product.setCategory(DEFAULT_CATEGORY);
        product.setModifiedDate(DEFAULT_MODIFIED_DATE);
        product.setCreatedBy(DEFAULT_CREATED_BY);
        product.setModifiedBy(DEFAULT_MODIFIED_BY);

        return product;
    }

    @BeforeEach
    void setUp() {
        product = createEntity(em);
    }


    @Test
    void shouldReturnAllProducts() throws Exception {
        mockMvc.perform(get("/api/products"))
            .andExpect(status().isOk());
    }

    @Test
    void shouldUpdateProducts() throws Exception {
        productRepository.saveAndFlush(product);
        // Update the student
        Product updatedProduct = productRepository.findById(product.getId()).get();
         em.detach(updatedProduct);
        updatedProduct
            .name(UPDATED_NAME)
            .price( UPDATED_PRICE)
            .barcode(UPDATED_BARCODE)
            .expiryDate(UPDATED_EXPIRY_DATE)
            .imageUrl(UPDATED_IMAGEURL)
            .category(UPDATED_CATEGORY)
            .grade(UPDATED_GRADE)
            .createdDate(UPDATED_CREATED_DATE)
            .modifiedDate(UPDATED_MODIFIED_DATE)
            .createdBy(UPDATED_CREATED_BY)
            .modifiedBy(UPDATED_MODIFIED_BY);

        ProductDTO productDTO = productMapper.toDto(updatedProduct);

        mockMvc.perform( MockMvcRequestBuilders
                .put("/api/products/{id}", 14)
                .content(TestUtil.convertObjectToJsonBytes(updatedProduct))
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isOk());
    }
}
