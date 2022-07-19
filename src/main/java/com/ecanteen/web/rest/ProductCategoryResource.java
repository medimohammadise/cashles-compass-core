package com.ecanteen.web.rest;

import com.ecanteen.repository.ProductCategoryRepository;

import com.ecanteen.service.ProductCategoryService;

import com.ecanteen.service.dto.ProductCategoryDTO;

import com.ecanteen.web.rest.errors.BadRequestAlertException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Objects;


@RestController
@RequestMapping("/api")
public class ProductCategoryResource {
    private static final String ENTITY_NAME = "productCategory";
    private final Logger log = LoggerFactory.getLogger(ProductCategoryResource.class);

    private final ProductCategoryService ProductCategoryService;

    private final ProductCategoryRepository ProductCategoryRepository;

    @Autowired
    public ProductCategoryResource(ProductCategoryService ProductCategoryService, ProductCategoryRepository ProductCategoryRepository) {
        this.ProductCategoryService = ProductCategoryService;
        this.ProductCategoryRepository = ProductCategoryRepository;
    }

    /**
     * {@code POST  /products} : Create a new productCategory.
     *
     * @param productCategoryDTO the productCategoryDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new productCategoryDTO, or with status {@code 400 (Bad Request)} if the student has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/productCategory")
    public ResponseEntity<ProductCategoryDTO> createProductCategory(@RequestBody ProductCategoryDTO productCategoryDTO) throws URISyntaxException {
        log.debug("REST request to save  productCategory : {}", productCategoryDTO);
        if (productCategoryDTO.getId() != null) {
            throw new BadRequestAlertException("A new productCategory cannot already have an ID", ENTITY_NAME, "id-exists");
        }
        ProductCategoryDTO result = ProductCategoryService.save(productCategoryDTO);
        return ResponseEntity
            .created(new URI("/api/productCategories/" + result.getId()))
            .body(result);
    }


    /**
     * {@code GET  /productCategories} : get all the productCategories.
     *
     * @param pageable the pagination information.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of productCategories in body.
     */
    @GetMapping("/ProductCategories")
    public ResponseEntity<List<ProductCategoryDTO>> getAllProductCategories(Pageable pageable) {
        Page<ProductCategoryDTO> page = ProductCategoryService.findAll(pageable);
        return ResponseEntity.ok().body(page.getContent());
    }


    /**
     * {@code PUT  /productCategories/:id} : Updates an existing productCategory.
     *
     * @param id                 the id of the productCategoryDTO to save.
     * @param productCategoryDTO the  productCategoryDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated productCategoryDTO,
     * or with status {@code 400 (Bad Request)} if the productCategoryDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the productCategoryDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/productCategories/{id}")
    public ResponseEntity<ProductCategoryDTO> updateProductCategory(
        @PathVariable(value = "id", required = false) final Long id,
        @RequestBody ProductCategoryDTO productCategoryDTO
    ) throws URISyntaxException {
        log.debug("REST request to update productCategory : {}, {}", id, productCategoryDTO);
        if (productCategoryDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, productCategoryDTO.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!ProductCategoryRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        ProductCategoryDTO result = ProductCategoryService.update(productCategoryDTO);
        return ResponseEntity
            .ok()
            .body(result);
    }

    /**
     * {@code DELETE  /productCategories/:id} : delete the "id" productCategory.
     *
     * @param id the id of the productCategoryDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/productCategories/{id}")
    public ResponseEntity<Void> deleteProductCategory(@PathVariable Long id) {
        log.debug("REST request to delete productCategory : {}", id);
        ProductCategoryService.delete(id);
        return ResponseEntity
            .noContent()
            .build();
    }
}

