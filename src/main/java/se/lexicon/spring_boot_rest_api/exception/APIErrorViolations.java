package se.lexicon.spring_boot_rest_api.exception;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class APIErrorViolations extends APIError {
    private List<Violation> Violation;

    public APIErrorViolations(Integer statusCode, String statusText, String message, List<se.lexicon.spring_boot_rest_api.exception.Violation> violation) {
        super(statusCode, statusText, message);
        Violation = violation;
    }

}
