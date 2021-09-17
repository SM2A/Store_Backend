package org.acm.store.model;

import javax.persistence.*;

/**
 * Created by SM2A
 * Seyed Mohammad Amin Atyabi
 */

@Entity(name = "category")
@Table(name = "category")
@NamedQueries({
        @NamedQuery(name = "GET_CATEGORY_BY_NAME", query = Category.SEARCH_CATEGORY)
})
public class Category {

    public static final String SEARCH_CATEGORY = "FROM category c WHERE c.name = :name";

    @Id
    @GeneratedValue(strategy= GenerationType.TABLE)
    @Column(name = "ID")
    private long ID;
    @Column(name = "Name")
    private String name;

    public Category() {}

    public Category(String name) {
        this.name = name;
    }

    public long getID() {
        return ID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
