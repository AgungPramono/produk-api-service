//
// produkDaoTest.java
// produk-api-service 
//
// Created by Agung Pramono on 23/10/2016 
// Copyright (c) 2016 Java Development. All rights reserved.
//

package com.agung.produk.dao;

import com.agung.produk.ProdukApiApplication;
import org.junit.runner.RunWith;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = ProdukApiApplication.class)
@Transactional
@Sql(scripts = {"/mysql/delete-data.sql","/mysql/sample-produk.sql"})
public class produkDaoTest {

}
