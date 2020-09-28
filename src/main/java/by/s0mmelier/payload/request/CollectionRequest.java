package by.s0mmelier.payload.request;

import lombok.*;
import lombok.experimental.PackagePrivate;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.NotBlank;

@Getter
@Setter
public class CollectionRequest {

    @NotBlank
    private String name;

    private String description;

    private String theme;

    private MultipartFile image;
}
