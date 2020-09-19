package by.s0mmelier.models;

import by.s0mmelier.collections.AlcoholCollection;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import lombok.experimental.PackagePrivate;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@PackagePrivate
@EqualsAndHashCode
@Entity
@Table(name = "c_alcohol")
public class Alcohol{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    long id;
    String name;
    @ManyToMany(fetch = FetchType.LAZY)
    List<Tag> tags;
    int cost;
    int percent;
    int volume;
    String manufacturer;
    String grade;
    String manufactureCountry;
    boolean hasOneLiter;
    boolean hasTwoLiters;
    boolean hasFiveLiters;
    String comment;
    String history;
    String recommendation;
    Date manufactureDate;
    Date developmentDate;
    Date manufactureDateInBelarus;
    long bitMask;
    long countOfLikes;

    @ManyToOne(fetch = FetchType.EAGER)
    //@OnDelete(action = OnDeleteAction.CASCADE)
    AlcoholCollection alcoholCollection;

    @OneToMany(fetch = FetchType.EAGER)
    List<Comment> comments;

    @ManyToMany
    @JsonIgnore
    @JoinTable(	name = "alcohol_likes",
            joinColumns = @JoinColumn(name = "alcohol_id"),
            inverseJoinColumns = @JoinColumn(name = "user_id"))
    List<User> likes = new ArrayList<>();
}
