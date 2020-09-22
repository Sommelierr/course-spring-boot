package by.s0mmelier.Dto;

import by.s0mmelier.models.Tag;
import lombok.*;
import lombok.experimental.PackagePrivate;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@PackagePrivate
public class AlcoholDto {
    String name;
    List<String> tags;
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
}
