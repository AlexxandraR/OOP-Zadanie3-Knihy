package oop.autor.web.bodies;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AuthorUpdateRequest {
    private String name;
    private String surname;
}
