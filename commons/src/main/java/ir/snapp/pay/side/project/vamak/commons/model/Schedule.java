package ir.snapp.pay.side.project.vamak.commons.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

import static jakarta.persistence.GenerationType.IDENTITY;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(
        schema = "vamak", name = "schedule",
        indexes = {
                @Index(name = "schedule.uidx.loan_id-and-priority", columnList = "loan_id, priority", unique = true)
        }
)
public class Schedule extends BaseEntity {

    @Id
    @GeneratedValue(strategy = IDENTITY)
    private long id;

    @Column(nullable = false, columnDefinition = "SMALLINT DEFAULT 1")
    private short priority;

    @ManyToOne
    @JoinColumn(nullable = false, name = "loan_id", referencedColumnName = "id", columnDefinition = "INT")
    private Loan loan;

    @ManyToOne
    @JoinColumn(nullable = false, name = "winner_id", referencedColumnName = "id", columnDefinition = "INT")
    private Member winner;

    @Column(nullable = false, name = "start_date", columnDefinition = "DATE DEFAULT CURRENT_DATE")
    private LocalDate startDate;
}
