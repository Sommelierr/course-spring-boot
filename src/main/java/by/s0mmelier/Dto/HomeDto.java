package by.s0mmelier.Dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.PackagePrivate;

import java.util.List;

@Getter
@Setter
@PackagePrivate
@NoArgsConstructor
public class HomeDto {
    List<String> tags;
    HomeBookDto book;
    HomeAlcoholDto alcohol;
    HomeCollectionDto collection;
}
