package ir.snapp.pay.side.project.vamak.commons.model;

import ir.snapp.pay.side.project.vamak.commons.LoanModel;
import ir.snapp.pay.side.project.vamak.commons.ReceiveType;
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
        schema = "vamak", name = "loan",
        indexes = {
                @Index(name = "loan.uidx.name", columnList = "name", unique = true),
                @Index(name = "loan.idx.owner", columnList = "owner_id")
        }
)
public class Loan extends BaseEntity {

    @Id
    @GeneratedValue(strategy = IDENTITY)
    private int id;

    @Column(/*unique = true, */nullable = false, length = 100)
    private String name;

    @Convert(converter = Converters._LoanModel.class)
    @Column(nullable = false, columnDefinition = "CHAR(1) DEFAULT 'N'")
    private LoanModel model;

    @Column(nullable = false, columnDefinition = "DOUBLE DEFAULT 0.0")
    private double amount;

    @ManyToOne
    @JoinColumn(
            nullable = false, name = "owner_id", referencedColumnName = "id", columnDefinition = "INT",
            foreignKey = @ForeignKey(name = "loan.fk.owner_id-to-member.id")
    )
    private Member owner;

    @Column(nullable = false, name = "num_of_members", columnDefinition = "SMALLINT DEFAULT 0")
    private short numOfMembers;

    @Convert(converter = Converters._ReceiveType.class)
    @Column(nullable = false, name = "receive_type", columnDefinition = "CHAR(1) DEFAULT 'O'")
    private ReceiveType receiveType;
}
