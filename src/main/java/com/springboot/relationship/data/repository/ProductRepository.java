package com.springboot.relationship.data.repository;

import com.springboot.relationship.data.entity.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface ProductRepository extends JpaRepository<Product,Long> {

    //find... By
    Optional<Product> findByNumber(Long number);
    List<Product> findAllByName(String name);
    Product queryByNumber(Long number);

    //exists... By 특정 데이터가 존재하는지 확인하는 키워드
    boolean existsByNumber(Long number);

    //count... By 쿼리결과로 나온 레코드의 개수를 리턴
    long countByName(String name);

    //delete... By, remove...By 삭제 쿼리를 수행 리턴타입이 없거나 삭제횟수 리턴
    void deleteByNumber(Long number);
    long removeByName(String name);

    //First<Number> , Top<Number>
    //조회된 결과값의 개수를 제한하는 키워드
    List<Product> findFirstByName(String name);
    List<Product> findTop10ByName(String name);


    /*쿼리 메서드의 조건자 키워드*/

    //Is 값의 일치를 조건으로 사용하는 키워드
    //생략 가능. Equals와 동일하느 기능을 수행한다.
    Product findByNumberIs(Long number);
    Product findByNumberEquals(Long number);

    //(Is)Not
    //값의 불일치를 조건으로 사용하는 조건자 키워드
    Product findByNumberIsNot(Long number);
    Product findByNumberNot(Long number);

    //(Is)Null, (Is)NotNull
    //값이 null인지 검사하는 조건자 키워드
    List<Product> findByUpdatedAtNull();
    List<Product> findByUpdatedAtIsNull();
    List<Product> findByUpdatedAtNotNull();
    List<Product> findByUpdatedAtIsNotNull();


    //And, Or 키워드
    Product findByNumberAndName(Long number, String name);
    Product findByNumberOrName(Long number, String name);


    //(Is)GreaterThan, (Is)LessThan, (Is)Between
    //숫자나 datetime 칼럼을 대상으로 한 비교연산에 사용할수 있는 조건자 키워드
    List<Product> findByPriceIsGreaterThan(Long price);
    List<Product> findByPriceGreaterThan(Long price);
    List<Product> findByPriceGreaterThanEqual(Long price);
    List<Product> findByPriceIsLessThan(Long price);
    List<Product> findByPriceLessThan(Long price);
    List<Product> findByPriceLessThanEqual(Long price);
    List<Product> findByPriceIsBetween(Long lowPrice, Long highPrice);
    List<Product> findByPriceBetween(Long lowPrice, Long highPrice);

    //(Is)StartingWith(==StarsWith), (Is)EndingWith(==EndsWith),
    //(Is)Containing(==contains),(Is)Like
    //칼럼값에서 일부 일치여부를 확인하는 조건자키워드

    List<Product> findByNameLike(String name);
    List<Product> findByNameIsLike(String name);

    /*정렬과 페이징처리*/
    //쿼리문에서 정렬을 사용할때는 OrderBy를 사용.
    //Asc : 오름차순, Desc : 내림차순
    List<Product> findByNameOrderByNumberAsc(String name);
    List<Product> findByNameOrderByNumberDesc(String name);

    //And를 붙이지 않음
    List<Product> findByNameOrderByPriceAscStockDesc(String name);

    //매개변수를 활용한 쿼리정렬
    List<Product> findByName(String name, Sort sort);

    //페이징처리
    Page<Product> findByName(String name, Pageable pageable);

    //@Query 어노테이션을 사용하는 메서드
    @Query("SELECT p FROM Product AS p WHERE p.name = ?1")
    List<Product> findByName(String name);

    //@Query 어노테이션과 @Param 어노테이션을 사용한 메서드
    @Query("SELECT p FROM Product p WHERE p.name=:name")
    List<Product> findByNameParam(@Param("name") String name);

    @Query("SELECT p.name, p.price, p.stock FROM Product p WHERE p.name=:name")
    List<Object[]> findByNameParam2(@Param("name")String name);


}
