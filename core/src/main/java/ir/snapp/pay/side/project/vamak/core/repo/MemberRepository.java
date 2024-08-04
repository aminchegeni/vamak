package ir.snapp.pay.side.project.vamak.core.repo;

import ir.snapp.pay.side.project.vamak.commons.dto.PersonalInfo;
import ir.snapp.pay.side.project.vamak.commons.model.Member;
import ir.snapp.pay.side.project.vamak.core.dto.MemberDetails;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Repository
@Transactional(readOnly = true)
public interface MemberRepository extends JpaRepository<Member, Integer> {

    boolean existsByUsername(String username);

    Optional<MemberDetails> findByUsername(String username);

    @Transactional
    @Modifying
    @Query("""
            UPDATE Member m
                SET m.name     = COALESCE(:#{#info.name}, m.name),
                    m.family   = COALESCE(:#{#info.family}, m.family),
                    m.mobile   = COALESCE(:#{#info.mobile}, m.mobile),
                    m.birthday = COALESCE(:#{#info.birthday}, m.birthday)
            WHERE m.inactive = FALSE
              AND m.username = ?#{principal?.username}
            """)
    int updatePersonalState(@Param("info") PersonalInfo info);
}
