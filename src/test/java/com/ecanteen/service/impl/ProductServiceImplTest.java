package com.ecanteen.service.impl;

import com.ecanteen.domain.Product;

import com.ecanteen.domain.enumeration.Category;
import com.ecanteen.domain.enumeration.Grade;

import com.ecanteen.repository.ProductRepository;

import com.ecanteen.service.ProductServiceImpl;
import com.ecanteen.service.dto.ProductDTO;

import com.ecanteen.service.mapper.ProductMapper;

import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;
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

public class ProductServiceImplTest {


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

    private static final Random random = new Random();

    private static final AtomicLong count = new AtomicLong(random.nextInt() + (2L * Integer.MAX_VALUE));



     private ProductServiceImpl  productServiceImpl;

    private ProductRepository productRepository;

    private EntityManager em;


    private MockMvc mockMvc;


    private ProductMapper productMapper;

    private Product product;



    @BeforeEach
    void setUp() {
        product = createEntity(em);
        productRepository = Mockito.mock(ProductRepository.class);
        productMapper = Mockito.mock(ProductMapper.class);
        productServiceImpl = new ProductServiceImpl(productRepository, productMapper);
    }


    //create data for test

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




    @Test
    void shouldReturnAllProducts() {



        //arrange
        //in this section we prepare required things
        List<Product> productList = new ArrayList<>();
        productList.add(product);
        productList.add(product);
        Pageable pageable = PageRequest.of(0, 5);
        Page<Product> productPage = new PageImpl<>(productList, pageable, productList.size());
        ProductDTO productDTO1 = new ProductDTO();
        productDTO1.setId(1L);
        ProductDTO productDTO2 = new ProductDTO();
        productDTO2.setId(2L);


        //act
        //in this section we invoke method of services for test
        List<ProductDTO> productDTOList = new ArrayList<>();
        productDTOList.add(productDTO1);
        productDTOList.add(productDTO2);

        //asser
        //and finally we write this section To get the result we expect

        Mockito.when(productMapper.toDto( product)).thenReturn(productDTO1);
        Mockito.when(productMapper.toDto( product)).thenReturn(productDTO2);
        Mockito.when(productRepository.findAll(pageable)).thenReturn(productPage);

        assertThat(productServiceImpl.findAll(pageable)).hasSize(2);
    }

    @Test
    void shouldCreateProducts() {
        ProductDTO productDTO = org.mapstruct.factory.Mappers.getMapper(ProductMapper.class).toDto(product);
        Mockito.when(productMapper.toDto(product)).thenReturn(productDTO);
        Mockito.when(productRepository.save(Mockito.any(Product.class)))
            .thenAnswer(i -> i.getArguments()[0]);
    }

    @Test
    void shouldUpdateProduct() {

        productRepository.saveAndFlush(product);
        int databaseSizeBeforeUpdate = productRepository.findAll().size();

        Product partialUpdatedProduct = new Product();
        partialUpdatedProduct.setId(product.getId());

        partialUpdatedProduct.setName( UPDATED_NAME);
        partialUpdatedProduct.setPrice(UPDATED_PRICE );
        partialUpdatedProduct.setBarcode(UPDATED_BARCODE );
        partialUpdatedProduct.setExpiryDate (UPDATED_EXPIRY_DATE );
        partialUpdatedProduct.setImageUrl(UPDATED_IMAGEURL);
        partialUpdatedProduct.setGrade (UPDATED_GRADE );
        partialUpdatedProduct.setCategory(UPDATED_CATEGORY );
        partialUpdatedProduct.setCreatedDate(UPDATED_CREATED_DATE);
        partialUpdatedProduct.setModifiedDate(UPDATED_MODIFIED_DATE);
        partialUpdatedProduct.setCreatedBy(UPDATED_CREATED_BY);
        partialUpdatedProduct.setModifiedBy(UPDATED_MODIFIED_BY);


        ProductDTO productDTO = org.mapstruct.factory.Mappers.getMapper(ProductMapper.class).toDto(partialUpdatedProduct);
        Mockito.when(productMapper.toDto(partialUpdatedProduct)).thenReturn(productDTO);
        Mockito.when(productRepository.save(Mockito.any(Product.class)))
            .thenAnswer(i -> i.getArguments()[0]);

        assertThat(productRepository.findAll().size()).isEqualTo(databaseSizeBeforeUpdate);
    }


}
