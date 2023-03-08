package org.palad.fakeshop.domain;

import org.hibernate.collection.internal.PersistentList;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long uid;

    private String title;

    private Long price;

    private String desc;

    @ManyToOne
    private List<Category> categories = new ArrayList<>();

}
