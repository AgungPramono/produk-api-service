//
// produkDaoTest.java
// produk-api-service 
//
// Created by Agung Pramono on 23/10/2016 
// Copyright (c) 2016 Java Development. All rights reserved.
//

package com.agung.produk.dao;

import com.agung.produk.ProdukApiApplication;
import com.agung.produk.entity.Produk;
import java.math.BigDecimal;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
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
@Sql(scripts = {"/mysql/delete-data.sql","/mysql/sample-product.sql"})
public class produkDaoTest {

    @Autowired
    private ProdukDao pd;
    
    @Test
    public void testSimpan(){
        Produk p = new Produk();
        
        p.setCode("p-001");
        p.setName("Produk-001");
        p.setPrice(new BigDecimal(36000.0));
        
        Assert.assertNull(p.getId());
        pd.save(p);
        Assert.assertNotNull(p.getId());
    }
    
    @Test
    public void testFindById(){
        Produk p = pd.findOne("test123");
        
        Assert.assertNotNull(p);
        Assert.assertEquals("test123", p.getId());
        Assert.assertEquals("P-001", p.getCode());
        Assert.assertEquals("produk-01", p.getName());
        Assert.assertEquals(BigDecimal.valueOf(321000.01), p.getPrice());
        
        Assert.assertNull(pd.findOne("xxx"));
        
    }
}
