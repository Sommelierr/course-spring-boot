package by.s0mmelier.models;

import by.s0mmelier.collections.BookCollection;
import lombok.*;
import lombok.experimental.PackagePrivate;

import javax.persistence.*;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@PackagePrivate
@EqualsAndHashCode
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


    public Image(String name, String url, String imageId) {
        this.name = name;
        this.url = url;
        this.imageId = imageId;
    }
}
