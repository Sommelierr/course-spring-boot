package by.s0mmelier.models;

import by.s0mmelier.collections.BookCollection;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import lombok.experimental.PackagePrivate;

import javax.persistence.*;
import java.util.*;


@Getter
@Setter
@ToString
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
@PackagePrivate
@Entity
@Table(name = "c_book")
public class Book{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    long id;
    String name;
    @ManyToMany(fetch = FetchType.LAZY)
    List<Tag> tags;
    int cost;
    int countOfPages;
    int weight;
    String author;
    String genre;
    String publisher;
    boolean itSerial;
    boolean hasAudio;
    boolean hasFilm;
    String comment;
    String summary;
    String recommendation;
    Date publishingDateOnEnglish;
    Date publishingDateOnRussian;
    Date publishingDateOnJapan;
    long bitMask;
    long countOfLikes;

    @ManyToOne(fetch = FetchType.EAGER)
    BookCollection bookCollection;

    @ManyToMany
    @JsonIgnore
    @JoinTable(	name = "book_likes",
            joinColumns = @JoinColumn(name = "book_id"),
            inverseJoinColumns = @JoinColumn(name = "user_id"))
    List<User> likes = new ArrayList<>();
}
