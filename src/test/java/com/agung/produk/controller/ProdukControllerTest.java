//
// ProdukControllerTest.java
// produk-api-service 
//
// Created by Agung Pramono on 23/10/2016 
// Copyright (c) 2016 Java Development. All rights reserved.
//
package com.agung.produk.controller;

import com.agung.produk.ProdukApiApplication;
import com.agung.produk.entity.Produk;
import com.jayway.restassured.RestAssured;
import com.jayway.restassured.http.ContentType;
import java.math.BigDecimal;
import org.hamcrest.CoreMatchers;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.boot.test.WebIntegrationTest;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = ProdukApiApplication.class)
@Sql(scripts = {"/mysql/delete-data.sql", "/mysql/sample-product.sql"})
@WebIntegrationTest(randomPort = true)
public class ProdukControllerTest {

    private static final String BASE_URL = "/api/produk";

    @Value("${local.server.port}")
    int serverPort;

    @Before
    public void setup() {
        RestAssured.port = serverPort;
    }

    @Test
    public void testCreateProduk() {
        Produk p = new Produk();

        p.setCode("p-004");
        p.setName("Produk-004");
        p.setPrice(new BigDecimal(65000.0));

        RestAssured.given()
                .body(p)
                .contentType(ContentType.JSON)
                .when()
                .post(BASE_URL + "/")
                .then()
                .statusCode(201)
                .header("Location", CoreMatchers.containsString(BASE_URL + "/"))
                .log().headers();

        //kode kurang dari 3
        Produk p2 = new Produk();
        p2.setCode("PT");
        p2.setName("Produk-test");
        p2.setPrice(new BigDecimal(65000.0));

        RestAssured.given()
                .body(p2)
                .contentType(ContentType.JSON)
                .when()
                .post(BASE_URL + "/")
                .then()
                .statusCode(400);

        //Harga Negatif
        Produk p2p3 = new Produk();
        p2p3.setCode("P-006");
        p2p3.setName("Produk-212");
        p2p3.setPrice(new BigDecimal(-65000.0));

        RestAssured.given()
                .body(p2p3)
                .contentType(ContentType.JSON)
                .when()
                .post(BASE_URL + "/")
                .then()
                .statusCode(400);
    }
    
    @Test
    public void testFindAll() {
        RestAssured.given()
                .get(BASE_URL + "/")
                .then()
                .body("totalElements", CoreMatchers.equalTo(1))
                .body("content.id", CoreMatchers.hasItems("test123"));
    }
    
    @Test
    public void testFindById() {
        RestAssured.given()
                .get(BASE_URL + "/test123")
                .then()
                .statusCode(200)
                .body("id", CoreMatchers.equalTo("test123"))
                .body("code", CoreMatchers.equalTo("P-001"));

        RestAssured.given().
                get(BASE_URL + "/990")
                .then()
                .statusCode(500);
    }
//    
    @Test
    public void testUpdate() {
        Produk p4 = new Produk();
        p4.setCode("PX-009");
        p4.setName("Product 909");
        p4.setPrice(BigDecimal.valueOf(2000));

        RestAssured.given().
                given()
                .body(p4)
                .contentType(ContentType.JSON)
                .when()
                .put(BASE_URL + "/test123")
                .then()
                .statusCode(200);

//        RestAssured.given().
//                get(BASE_URL + "/test123")
//                .then()
//                .statusCode(200)
//                .body("id", CoreMatchers.equalTo("test123"))
//                .body("code", CoreMatchers.equalTo("PX-009"))
//                .body("name", CoreMatchers.equalTo("Product 909"));
    }
//
//    @Test
//    public void testDelete() {
//        RestAssured.given().
//                delete(BASE_URL + "/abc123")
//                .then()
//                .statusCode(200);
//        
//        RestAssured.given().
//                get(BASE_URL + "/abc123")
//                .then()
//                .statusCode(404);
//
//    }
}
