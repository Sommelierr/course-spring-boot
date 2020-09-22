package by.s0mmelier.collections;

import by.s0mmelier.models.Alcohol;
import by.s0mmelier.models.Image;
import by.s0mmelier.models.Theme;
import by.s0mmelier.models.User;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import lombok.experimental.PackagePrivate;

import javax.persistence.*;
import java.util.List;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@PackagePrivate
@EqualsAndHashCode
@Entity
@Table(name = "c_alcohol_collection")
public class AlcoholCollection extends Collection{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    long id;
    String name;
    String description;
    long bitMask;
    long countOfAlcohols;
    @ManyToOne(fetch = FetchType.LAZY)
    Theme theme;

    @OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    Image image;

    @OneToMany(fetch = FetchType.LAZY, orphanRemoval = true)
            @JoinColumn(name = "alcohol_collection_id")
            @JsonIgnore
    List<Alcohol> alcohols;

    @ManyToOne(fetch = FetchType.LAZY)
            @JsonIgnore
    User user;
}
