//
// WebConfig.java
// produk-api-service 
//
// Created by Agung Pramono on 25/10/2016 
// Copyright (c) 2016 Java Development. All rights reserved.
//

package com.agung.produk.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

/**
 *
 */
@Configuration
public class WebConfig extends WebMvcConfigurerAdapter{
    @Override
    public void addViewControllers(ViewControllerRegistry registry){
        registry.addViewController("/produk/listproduk").setViewName("/produk/listproduk");
        registry.addViewController("/halo/halo").setViewName("/halo/halo");
        //registry.addViewController("/error").setViewName("/error");
    }
}
