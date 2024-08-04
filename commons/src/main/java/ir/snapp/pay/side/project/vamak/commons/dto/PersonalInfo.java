package ir.snapp.pay.side.project.vamak.commons.dto;

import jakarta.validation.constraints.Past;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDate;

@Data
@SuperBuilder(builderMethodName = "personal")
@NoArgsConstructor
@AllArgsConstructor
public class PersonalInfo implements Serializable {

    @Serial
    private static final long serialVersionUID = -8176421453530335700L;

    @Size(min = 1, max = 30)
    protected String name;

    @Size(min = 1, max = 50)
    protected String family;

    @Pattern(regexp = "^0[0-9]{10}$")
    protected String mobile;

    @Past
    protected LocalDate birthday;
}
