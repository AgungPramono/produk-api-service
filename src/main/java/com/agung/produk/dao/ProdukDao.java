//
// ProdukDao.java
// produk-api-service 
//
// Created by Agung Pramono on 23/10/2016 
// Copyright (c) 2016 Java Development. All rights reserved.
//

package com.agung.produk.dao;

import com.agung.produk.entity.Produk;
import org.springframework.data.repository.PagingAndSortingRepository;

/**
 *
 */
public interface ProdukDao extends PagingAndSortingRepository<Produk, String>{

}
