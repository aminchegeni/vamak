package ir.snapp.pay.side.project.vamak.commons.model;

import ir.snapp.pay.side.project.vamak.commons.PayType;
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
        schema = "vamak", name = "installment",
        indexes = {
                @Index(name = "installment.uidx.schedule_id-and-payer_id", columnList = "schedule_id, payer_id", unique = true)
        }
)
public class Installment extends BaseEntity {

    @Id
    @GeneratedValue(strategy = IDENTITY)
    private long id;

    @ManyToOne
    @JoinColumn(
            nullable = false, name = "schedule_id", referencedColumnName = "id", columnDefinition = "BIGINT",
            foreignKey = @ForeignKey(name = "installment.fk.schedule_id-to-schedule.id")
    )
    private Schedule schedule;

    @ManyToOne
    @JoinColumn(
            nullable = false, name = "payer_id", referencedColumnName = "id", columnDefinition = "BIGINT",
            foreignKey = @ForeignKey(name = "installment.fk.payer_id-to-member.id")
    )
    private Member payer;

    @Convert(converter = Converters._PayType.class)
    @Column(nullable = false, name = "pay_type", columnDefinition = "CHAR(1) DEFAULT 'U'")
    private PayType payType;

    @Column(nullable = false, name = "pay_date", columnDefinition = "DATE DEFAULT CURRENT_DATE")
    private LocalDate payDate;

    @Column(nullable = false, length = 50, columnDefinition = "NVARCHAR(50) DEFAULT '0000000000'")
    private String voucher;

    @Column(nullable = false, columnDefinition = "BOOLEAN DEFAULT FALSE")
    private boolean verified;
}
