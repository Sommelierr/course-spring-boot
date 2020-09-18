package by.s0mmelier.collections;

import by.s0mmelier.models.Book;
import by.s0mmelier.models.Image;
import by.s0mmelier.models.Theme;
import by.s0mmelier.models.User;
import lombok.*;
import lombok.experimental.PackagePrivate;

import javax.persistence.*;
import java.util.List;

@Getter
@Setter
@EqualsAndHashCode
@ToString
@NoArgsConstructor
@AllArgsConstructor
@PackagePrivate
@Entity
@Table(name = "c_book_collection")
public class BookCollection extends Collection{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    long id;
    String name;
    String description;
    long bitMask;
    @ManyToOne(fetch = FetchType.LAZY)
    Theme theme;

    @OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    Image image;

    @OneToMany(fetch = FetchType.EAGER, orphanRemoval = true)
    @JoinColumn(name = "c_book_id") // need c_book_collection_id
    List<Book> books;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    //@JoinColumn(name = "c_user_id")
    //@OnDelete(action = OnDeleteAction.CASCADE)
    User user;
}
