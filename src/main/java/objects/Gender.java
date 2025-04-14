package objects;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum Gender {

    MEN("men"),
    WOMEN("women"),
    KIDS("kids");

    String name;
}
