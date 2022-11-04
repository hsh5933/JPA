package com.springboot.relationship.data.repository;

import com.google.common.collect.Lists;
import com.springboot.relationship.data.entity.Product;
import com.springboot.relationship.data.entity.Provider;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class ProviderRepositoryTest {

    @Autowired
    ProductRepository productRepository;

    @Autowired
    ProviderRepository providerRepository;

    @Test
    void relationshipTest1(){
        //테스트 데이터 생성
        Provider provider = new Provider();
        provider.setName("OO물산");

        providerRepository.save(provider);

        Product product = new Product();
        product.setName("가위");
        product.setPrice(500);
        product.setStock(50);
        product.setProvider(provider);

        productRepository.save(product);

        //테스트
        System.out.println(
                "product : "+productRepository.findById(1L)
                        .orElseThrow(RuntimeException::new)
        );

        System.out.println("provider : "+productRepository.findById(1L)
                .orElseThrow(RuntimeException::new).getProvider());
    }

    @Test
    void cascadeTest(){
        Provider provider = saveProvider("새로운 공급업체");

        Product product1 = saveProduct("상품1",1000,1000);
        Product product2 = saveProduct("상품2",500, 1500);
        Product product3 = saveProduct("상품3", 750, 500);

        //연관관계설정
        product1.setProvider(provider);
        product2.setProvider(provider);
        product3.setProvider(provider);

        provider.getProductList().addAll(Lists.newArrayList(product1,product2,product3));

        providerRepository.save(provider);
    }

    private Provider saveProvider(String name){
        Provider provider = new Provider();
        provider.setName(name);

        return provider;
    }

    private Product saveProduct(String name, Integer price, Integer stock){
        Product product = new Product();
        product.setName(name);
        product.setPrice(price);
        product.setStock(stock);

        return product;
    }
}
