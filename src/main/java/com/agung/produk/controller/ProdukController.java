//
// ProdukController.java
// produk-api-service 
//
// Created by Agung Pramono on 23/10/2016 
// Copyright (c) 2016 Java Development. All rights reserved.
//

package com.agung.produk.controller;

import com.agung.produk.dao.ProdukDao;
import com.agung.produk.entity.Produk;
import com.muhardin.endy.belajar.ci.exception.DataNotFoundException;
import java.net.URI;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

/**
 *
 */
@RestController
@RequestMapping(value = "/api/produk")
@Transactional
public class ProdukController {

    @Autowired
    private ProdukDao pd;
    
    @RequestMapping(value = "/", method = RequestMethod.POST)
    @Transactional(readOnly = true)
    public ResponseEntity<Void> createProduk(@RequestBody @Valid Produk p,UriComponentsBuilder uriBuilder){
        pd.save(p);
        
        URI location = uriBuilder.path("/api/produk/{id}")
                .buildAndExpand(p.getId()).toUri();
        
        HttpHeaders header = new HttpHeaders();
        header.setLocation(location);
        return new ResponseEntity<>(header,HttpStatus.CREATED);
    }
    
    @RequestMapping(value = "/", method = RequestMethod.GET)
    public Page<Produk> findAll(Pageable page){
        return pd.findAll(page);
    }
    
    @RequestMapping(value = "/{id}", method = RequestMethod.GET )
    public Produk findById(@PathVariable("id") Produk p){
        Produk produk = pd.findOne(p.getId());
        
        if (produk == null) {
            throw new DataNotFoundException("Produk dengan id "+p.getId()+" tidak dapat ditemukan");
        }
        return produk;
    }
    
    @RequestMapping(value = "/{id}",method = RequestMethod.PUT)
    @ResponseStatus(HttpStatus.OK)
    @Transactional(readOnly = false)
    public void update(@PathVariable("id") String id, @RequestBody @Valid Produk p){
        if (!pd.exists(id)) {
            throw new DataNotFoundException("Produk tidak ada");
        }
        p.setId(id);
        pd.save(p);
    }
    
    @RequestMapping(value = "/{id}",method = RequestMethod.DELETE)
    @ResponseStatus(HttpStatus.OK)
    @Transactional(readOnly = false)
    public void delete(@PathVariable("id") String id){
        if (!pd.exists(id)) {
            throw new DataNotFoundException("Produk tidak ada");
        }
        pd.delete(id);
    }
}
