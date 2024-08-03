package ir.snapp.pay.side.project.vamak.commons.dto.wrapper;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serial;
import java.io.Serializable;
import java.util.Comparator;

import static com.fasterxml.jackson.annotation.JsonInclude.Include.NON_NULL;
import static ir.snapp.pay.side.project.vamak.commons.dto.wrapper.Reason.UNKNOWN;
import static lombok.Builder.Default;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(NON_NULL)
public class Error implements Serializable, Comparable<Error> {

    @Serial
    private static final long serialVersionUID = -3045731012099931205L;

    @Default
    private Reason reason = UNKNOWN;

    private String param;

    @Default
    private String description = "";

    @Default
    private String[] values = new String[0];

    @Override
    public int compareTo(Error o) {
        return Comparator.comparing(Error::getReason)
                .thenComparing(Error::getParam)
                .thenComparing(Error::getDescription)
                .compare(this, o);
    }
}
