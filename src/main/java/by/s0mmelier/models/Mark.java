package by.s0mmelier.models;

import by.s0mmelier.collections.MarkCollection;
import lombok.*;
import lombok.experimental.PackagePrivate;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@PackagePrivate
@EqualsAndHashCode
@Entity
@Table(name = "c_mark")
public class Mark {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    long id;
    String name;
    @ManyToMany(fetch = FetchType.LAZY)
    List<Tag> tags;
    int cost;
    int length;
    int width;
    String author;
    String manufacturer;
    String theme;
    boolean hasRadColor;
    boolean hasBlueColor;
    boolean hasWhiteColor;
    String comment;
    String history;
    String recommendation;
    Date manufactureDate;
    Date developmentDate;
    Date appearedInJapan;
    long bitMask;
    long countOfLikes;

    @ManyToOne(fetch = FetchType.EAGER)
    @OnDelete(action = OnDeleteAction.CASCADE)
    MarkCollection markCollection;

    @OneToMany(fetch = FetchType.EAGER)
    List<Comment> comments;
}
