package org.acm.store.model.category;

import javax.persistence.*;

/**
 * Created by SM2A
 * Seyed Mohammad Amin Atyabi
 */

@Entity(name = "category")
@Table(name = "category" , uniqueConstraints = {
        @UniqueConstraint(columnNames = "name")
})
@NamedQueries({
        @NamedQuery(name = Category.SEARCH_CATEGORY, query = Category.SEARCH_CATEGORY_Q)
})
public class Category {

    public static final String SEARCH_CATEGORY = "SEARCH_CATEGORY";
    public static final String SEARCH_CATEGORY_Q = "FROM category c WHERE c.name = :name";

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
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
