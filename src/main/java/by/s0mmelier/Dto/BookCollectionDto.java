package by.s0mmelier.Dto;

import by.s0mmelier.models.Book;
import by.s0mmelier.models.Image;
import by.s0mmelier.models.Theme;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.PackagePrivate;

import javax.persistence.*;
import java.util.List;

@Getter
@Setter
@PackagePrivate
@NoArgsConstructor
public class BookCollectionDto extends CollectionDto{
    String name;
    String description;
    long bitMask;
    Image image;
    List<Book> books;
    boolean blocked;
}
