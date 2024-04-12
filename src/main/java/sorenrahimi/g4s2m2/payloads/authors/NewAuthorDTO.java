package sorenrahimi.g4s2m2.payloads.authors;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record NewAuthorDTO(
        @NotEmpty(message = "Il nome è obbligatorio")
        @Size(min = 3, max = 30, message = "Nome deve avere" +
                "minimo 3 caratteri, massimo 30")
        String name,
        @NotEmpty(message = "il cognome è obbligatorio")
        String surname,
        @NotEmpty(message = "L'email è obbligatoria")
        @Email(message = "L'email inserita non è valida")
        String email,
        @NotNull(message = "la data di nascita è obbligatoria")
        String dateOfBirth
) {
}
