package com.topica.tea.web.rest;

import com.codahale.metrics.annotation.Timed;
import com.topica.tea.service.BrandkeyService;
import com.topica.tea.web.rest.util.HeaderUtil;
import com.topica.tea.web.rest.util.PaginationUtil;
import com.topica.tea.service.dto.BrandkeyDTO;
import io.swagger.annotations.ApiParam;
import io.github.jhipster.web.util.ResponseUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.net.URI;
import java.net.URISyntaxException;

import java.util.List;
import java.util.Optional;

/**
 * REST controller for managing Brandkey.
 */
@RestController
@RequestMapping("/api")
public class BrandkeyResource {

    private final Logger log = LoggerFactory.getLogger(BrandkeyResource.class);

    private static final String ENTITY_NAME = "brandkey";

    private final BrandkeyService brandkeyService;

    public BrandkeyResource(BrandkeyService brandkeyService) {
        this.brandkeyService = brandkeyService;
    }

    /**
     * POST  /brandkeys : Create a new brandkey.
     *
     * @param brandkeyDTO the brandkeyDTO to create
     * @return the ResponseEntity with status 201 (Created) and with body the new brandkeyDTO, or with status 400 (Bad Request) if the brandkey has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/brandkeys")
    @Timed
    public ResponseEntity<BrandkeyDTO> createBrandkey(@Valid @RequestBody BrandkeyDTO brandkeyDTO) throws URISyntaxException {
        log.debug("REST request to save Brandkey : {}", brandkeyDTO);
        if (brandkeyDTO.getId() != null) {
            return ResponseEntity.badRequest().headers(HeaderUtil.createFailureAlert(ENTITY_NAME, "idexists", "A new brandkey cannot already have an ID")).body(null);
        }
        BrandkeyDTO result = brandkeyService.save(brandkeyDTO);
        return ResponseEntity.created(new URI("/api/brandkeys/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /brandkeys : Updates an existing brandkey.
     *
     * @param brandkeyDTO the brandkeyDTO to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated brandkeyDTO,
     * or with status 400 (Bad Request) if the brandkeyDTO is not valid,
     * or with status 500 (Internal Server Error) if the brandkeyDTO couldnt be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/brandkeys")
    @Timed
    public ResponseEntity<BrandkeyDTO> updateBrandkey(@Valid @RequestBody BrandkeyDTO brandkeyDTO) throws URISyntaxException {
        log.debug("REST request to update Brandkey : {}", brandkeyDTO);
        if (brandkeyDTO.getId() == null) {
            return createBrandkey(brandkeyDTO);
        }
        BrandkeyDTO result = brandkeyService.save(brandkeyDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, brandkeyDTO.getId().toString()))
            .body(result);
    }

    /**
     * GET  /brandkeys : get all the brandkeys.
     *
     * @param pageable the pagination information
     * @return the ResponseEntity with status 200 (OK) and the list of brandkeys in body
     */
    @GetMapping("/brandkeys")
    @Timed
    public ResponseEntity<List<BrandkeyDTO>> getAllBrandkeys(@ApiParam Pageable pageable) {
        log.debug("REST request to get a page of Brandkeys");
        Page<BrandkeyDTO> page = brandkeyService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/brandkeys");
        return new ResponseEntity<>(page.getContent(), headers, HttpStatus.OK);
    }

    /**
     * GET  /brandkeys/:id : get the "id" brandkey.
     *
     * @param id the id of the brandkeyDTO to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the brandkeyDTO, or with status 404 (Not Found)
     */
    @GetMapping("/brandkeys/{id}")
    @Timed
    public ResponseEntity<BrandkeyDTO> getBrandkey(@PathVariable Long id) {
        log.debug("REST request to get Brandkey : {}", id);
        BrandkeyDTO brandkeyDTO = brandkeyService.findOne(id);
        return ResponseUtil.wrapOrNotFound(Optional.ofNullable(brandkeyDTO));
    }

    /**
     * DELETE  /brandkeys/:id : delete the "id" brandkey.
     *
     * @param id the id of the brandkeyDTO to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/brandkeys/{id}")
    @Timed
    public ResponseEntity<Void> deleteBrandkey(@PathVariable Long id) {
        log.debug("REST request to delete Brandkey : {}", id);
        brandkeyService.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }
}
