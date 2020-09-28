package by.s0mmelier.models;

import by.s0mmelier.collections.AlcoholCollection;
import by.s0mmelier.collections.BookCollection;
import by.s0mmelier.collections.MarkCollection;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;
import lombok.experimental.PackagePrivate;

import javax.persistence.*;
import java.util.List;

@Getter
@Setter
@PackagePrivate
@ToString
@EqualsAndHashCode
@NoArgsConstructor
@JsonIgnoreProperties({"hibernateLazyInitializer"})
@Entity
@Table(name = "c_theme")
public class Theme {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Integer id;

    String name;

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    List<BookCollection> bookCollections;

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    List<AlcoholCollection> alcoholCollections;

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    List<MarkCollection> markCollections;
}
