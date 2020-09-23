package by.s0mmelier.Dto;

import lombok.*;
import lombok.experimental.PackagePrivate;
import java.util.Date;
import java.util.List;
@Getter
@Setter
@NoArgsConstructor
@PackagePrivate
public class BookDto {
    String name;
    List<String> tags;
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
}
