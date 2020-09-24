package by.s0mmelier.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.PackagePrivate;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
@Getter
@Setter
@NoArgsConstructor
@PackagePrivate
@EqualsAndHashCode
@Entity
@Table(name = "c_tag")
public class Tag {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String name;

    @ManyToMany(mappedBy = "tags")
            @JsonIgnore
    List<Book> books = new ArrayList<>();

    @ManyToMany(mappedBy = "tags")
            @JsonIgnore
    List<Alcohol> alcohols = new ArrayList<>();

    public Tag(String name) {
        this.name = name;
    }
}
