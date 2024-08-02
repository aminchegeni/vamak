package ir.snapp.pay.side.project.vamak.commons.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import static jakarta.persistence.GenerationType.IDENTITY;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(
        schema = "vamak", name = "account",
        indexes = {
                @Index(name = "account.uidx.pan", columnList = "pan", unique = true),
                @Index(name = "account.uidx.iban", columnList = "iban", unique = true)
        }
)
public class Account extends BaseEntity {

    @Id
    @GeneratedValue(strategy = IDENTITY)
    private int id;

    @ManyToOne
    @JoinColumn(
            nullable = false, name = "member_id", referencedColumnName = "id", columnDefinition = "INT",
            foreignKey = @ForeignKey(name = "account.fk.member_id-to-member.id")
    )
    private Member member;

    @Column(/*unique = true, */nullable = false, length = 16, columnDefinition = "CHAR(16)")
    private String pan;

    @Column(/*unique = true, */nullable = false, length = 26, columnDefinition = "CHAR(26)")
    private String iban;

    @Column(nullable = false, columnDefinition = "BOOLEAN DEFAULT FALSE")
    private boolean disabled;
}
