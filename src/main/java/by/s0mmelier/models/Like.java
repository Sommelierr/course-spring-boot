package by.s0mmelier.models;

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
@Entity(name = "c_like")
public class Like {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    long id;

    boolean status;

    long bookId;

    @ManyToMany(fetch = FetchType.LAZY)
    List<User> users;
}
