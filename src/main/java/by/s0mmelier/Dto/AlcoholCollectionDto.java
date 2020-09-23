package by.s0mmelier.Dto;

import by.s0mmelier.models.Alcohol;
import by.s0mmelier.models.Image;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.PackagePrivate;

import java.util.List;

@Getter
@Setter
@PackagePrivate
@NoArgsConstructor
public class AlcoholCollectionDto extends CollectionDto {
    String name;
    String description;
    long bitMask;
    Image image;
    List<Alcohol> alcohols;
    boolean blocked;
}