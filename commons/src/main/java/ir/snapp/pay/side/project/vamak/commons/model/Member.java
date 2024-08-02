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
        schema = "vamak", name = "member",
        indexes = {
                @Index(name = "member.uidx.username", columnList = "username", unique = true)
        }
)
public class Member extends BaseEntity {

    @Id
    @GeneratedValue(strategy = IDENTITY)
    private int id;

    @Column(length = 30, columnDefinition = "NVARCHAR(30) DEFAULT NULL")
    private String name;

    @Column(length = 50, columnDefinition = "NVARCHAR(50) DEFAULT NULL")
    private String family;

    @Column(/*unique = true, */nullable = false, updatable = false, length = 40, columnDefinition = "NVARCHAR(40)")
    private String username;

    @Column(/*unique = true, */nullable = false, columnDefinition = "NVARCHAR(255)")
    private String password;

    @Column(length = 11, columnDefinition = "CHAR(11) DEFAULT NULL")
    private String mobile;

    @Column(columnDefinition = "DATE DEFAULT NULL")
    private LocalDate birthday;

    @Column(nullable = false, columnDefinition = "BOOLEAN DEFAULT FALSE")
    private boolean inactive;
}
