package by.s0mmelier.payload.request;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class BookRequest {
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
    String publishingDateOnEnglish;
    String publishingDateOnRussian;
    String publishingDateOnJapan;
}
