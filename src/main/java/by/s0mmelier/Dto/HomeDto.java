package by.s0mmelier.Dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.PackagePrivate;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@PackagePrivate
@NoArgsConstructor
public class HomeDto {
    List<CloudTagDto> tags = new ArrayList<>();
    FindBookDto book;
    FindAlcoholDto alcohol;
    HomeCollectionDto collection;
}
