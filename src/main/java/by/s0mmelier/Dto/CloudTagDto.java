package by.s0mmelier.Dto;

import by.s0mmelier.models.Tag;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.PackagePrivate;

@Getter
@Setter
@PackagePrivate
@NoArgsConstructor
public class CloudTagDto {
    String text;
    int width;
    String link;

    public CloudTagDto(Tag tag){
        this.text = tag.getName();
        this.width = 400;
        this.link = "http://localhost:4200/find/" + tag.getName();
    }
}
