package by.s0mmelier.payload.request;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class AlcoholRequest {
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
    String manufactureDate;
    String developmentDate;
    String manufactureDateInBelarus;
}
