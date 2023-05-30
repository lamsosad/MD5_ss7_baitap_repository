package lam.ss7.model.entity;

import javax.persistence.*;


@Entity
public class Blog {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String title;
    private String content;
    @ManyToOne
    private Catalog catalog;

    public Blog() {
    }

    public Blog(Long id, String title, String content) {
        this.id = id;
        this.title = title;
        this.content = content;
    }

    public Blog(Long id, String title, String content, Catalog catalog) {
        this.id = id;
        this.title = title;
        this.content = content;
        this.catalog = catalog;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Catalog getCatalog() {
        return catalog;
    }

    public void setCatalog(Catalog catalog) {
        this.catalog = catalog;
    }
}