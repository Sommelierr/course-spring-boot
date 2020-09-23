package by.s0mmelier.Dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.PackagePrivate;

@Getter
@Setter
@PackagePrivate
@NoArgsConstructor
public class HomeBookDto {
    long id;
    String name;
    long userId;
    long collectionId;
}
