package com.springboot.relationship.data.repository;

import com.google.common.collect.Lists;
import com.springboot.relationship.data.entity.Producer;
import com.springboot.relationship.data.entity.Product;
import io.swagger.models.auth.In;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

@SpringBootTest
public class ProducerRepositoryTest {

    @Autowired
    ProducerRepository producerRepository;

    @Autowired
    ProductRepository productRepository;

    @Test
    @Transactional
    void relationshipTest(){
        Product product1 = saveProduct("동글펜",500,1000);
        Product product2 = saveProduct("네모공책",100,2000);
        Product product3 = saveProduct("지우개",152,1234);

        Producer producer1 = saveProduct("flature");
        Producer producer2 = saveProduct("wikibookx");

        producer1.addProduct(product1);
        producer1.addProduct(product2);

        producer2.addProduct(product2);
        producer2.addProduct(product3);

        producerRepository.saveAll(Lists.newArrayList(producer1,producer2));

        System.out.println(producerRepository.findById(1L).get().getProducts());
    }

    private Product saveProduct(String name, Integer price, Integer stock){
        Product product = new Product();
        product.setName(name);
        product.setPrice(price);
        product.setStock(stock);

        return productRepository.save(product);
    }

    private Producer saveProduct(String name){
        Producer producer = new Producer();
        producer.setName(name);

       return producerRepository.save(producer);
    }

}
