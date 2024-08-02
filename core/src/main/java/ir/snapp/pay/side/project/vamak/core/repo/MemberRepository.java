package ir.snapp.pay.side.project.vamak.core.repo;

import ir.snapp.pay.side.project.vamak.commons.model.Member;
import ir.snapp.pay.side.project.vamak.core.dto.MemberDetails;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Repository
@Transactional(readOnly = true)
public interface MemberRepository extends JpaRepository<Member, Integer> {

    Optional<MemberDetails> findByUsername(String username);
}
