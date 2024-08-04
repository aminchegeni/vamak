package ir.snapp.pay.side.project.vamak.commons.dto;

import ir.snapp.pay.side.project.vamak.commons.constraint.Unique;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serial;
import java.io.Serializable;

@Data
@Builder(builderMethodName = "userinfo")
@NoArgsConstructor
@AllArgsConstructor
public class UserInfo extends PersonalInfo implements Serializable {

    @Serial
    private static final long serialVersionUID = -8748254555304285087L;

    @Unique(checker = "checker.username")
    @Pattern(regexp = "^[A-Z0-9a-z-._@]{5,40}$")
    private String username;

    @NotNull
    private String password;
}
