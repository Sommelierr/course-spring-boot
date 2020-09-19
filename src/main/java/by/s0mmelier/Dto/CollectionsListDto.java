package by.s0mmelier.Dto;

import by.s0mmelier.collections.BookCollection;
import by.s0mmelier.collections.AlcoholCollection;
import by.s0mmelier.collections.MarkCollection;
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
public class CollectionsListDto {
    List<BookCollection> bookCollections = new ArrayList<>();
    List<AlcoholCollection> alcoholCollections = new ArrayList<>();
    List<MarkCollection> markCollections = new ArrayList<>();
    boolean blocked;
}
