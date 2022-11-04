package com.springboot.relationship.data.entity;


import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@ToString(callSuper = true) //부모클래스의 필드를 포함
@EqualsAndHashCode(callSuper = true)
@Table(name = "product")
//테이블어노테이션 클래스의 이름과 테이블의 이름을 다르게 지정해야하는경우사용
//명시하지않으면 이름이 동일하다는 의미.
public class Product extends BaseEntity{

    @Id //테이블의 기본값으로 사용됨. 엔티티에 필수로들어가야함.
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long number;

    @Column(nullable = false) //컬럼값에 null처리가 가능한지 명시
    private String name;

    @Column(nullable = false)
    private Integer price;

    @Column(nullable = false)
    private Integer stock;

    @OneToOne(mappedBy = "product")
    @ToString.Exclude
    private ProductDetail productDetail;

    @ManyToOne
    @JoinColumn(name = "provider_id")
    @ToString.Exclude
    private Provider provider;

    @ManyToMany
    @ToString.Exclude
    private List<Producer> producers = new ArrayList<>();

    public void addProducer(Producer producer){
        this.producers.add(producer);
    }

    //BaseEntity클래스에서
//    private LocalDateTime createdAt;
//
//    private LocalDateTime updatedAt;

}
