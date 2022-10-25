package se.lexicon.spring_boot_rest_api.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class Violation {

    private String fieldName;
    private String message;
}
