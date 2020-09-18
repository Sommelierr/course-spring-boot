package by.s0mmelier.models;

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
@Table(name = "c_comment")
public class Comment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    long id;

    String content;

    @ManyToOne(fetch = FetchType.EAGER)
    Alcohol alcohol;

    @ManyToOne(fetch = FetchType.EAGER)
    Book book;

    @ManyToOne(fetch = FetchType.EAGER)
    Mark mark;

}
