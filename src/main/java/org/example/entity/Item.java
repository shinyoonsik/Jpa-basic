package org.example.entity;

import javax.persistence.*;

@Entity
@Table(name = "ITEM")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn
public abstract class Item extends BaseEntity{ // Item만 사용할 일이 있느냐 / 없느냐에 따라 class OR abstract class
    @Id
    @GeneratedValue
    @Column(name = "ITEM_ID")
    private Long id;
    private int price;
    private String name;
    private int stockQuantity;

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getStockQuantity() {
        return stockQuantity;
    }

    public void setStockQuantity(int stockQuantity) {
        this.stockQuantity = stockQuantity;
    }
}
