package ir.snapp.pay.side.project.vamak.commons.dto;

import ir.snapp.pay.side.project.vamak.commons.constraint.Unique;
import jakarta.persistence.Column;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDate;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserInfo implements Serializable {

    @Serial
    private static final long serialVersionUID = -8748254555304285087L;

    @Size(min = 1, max = 30)
    private String name;

    @Size(min = 1, max = 50)
    private String family;

    @Unique(checker = "checker.username")
    @Pattern(regexp = "^[A-Z0-9a-z-._]{5,40}$")
    private String username;

    @NotNull
    private String password;

    @Pattern(regexp = "^0[0-9]{10}$")
    private String mobile;

    @Past
    private LocalDate birthday;
}
