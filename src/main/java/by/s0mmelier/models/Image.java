package by.s0mmelier.models;

import by.s0mmelier.collections.BookCollection;

import javax.persistence.*;

@Entity
@Table(name = "c_image")
public class Image {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String name;
    private String url;
    private String imageId;

    @OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private BookCollection bookCollection;

    public Image() {
    }

    public Image(String name, String url, String imageId) {
        this.name = name;
        this.url = url;
        this.imageId = imageId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getImageId() {
        return imageId;
    }

    public void setImageId(String imageId) {
        this.imageId = imageId;
    }

    public BookCollection getBookCollection() {
        return bookCollection;
    }

    public void setBookCollection(BookCollection bookCollection) {
        this.bookCollection = bookCollection;
    }
}
