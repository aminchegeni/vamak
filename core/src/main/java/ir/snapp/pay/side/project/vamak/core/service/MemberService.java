package ir.snapp.pay.side.project.vamak.core.service;

import ir.snapp.pay.side.project.vamak.commons.constraint.Unique;
import ir.snapp.pay.side.project.vamak.commons.dto.UserInfo;
import ir.snapp.pay.side.project.vamak.commons.model.Member;
import ir.snapp.pay.side.project.vamak.core.repo.MemberRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service("checker.username")
public class MemberService implements Unique.Checker<String> {

    private final MemberRepository repo;

    private final PasswordEncoder encoder;

    public MemberService(MemberRepository repo, PasswordEncoder encoder) {
        this.repo = repo;
        this.encoder = encoder;
    }

    @Override
    public boolean isExist(String username) {
        return repo.existsByUsername(username);
    }

    public void createMember(UserInfo userInfo) {
        repo.save(Member.builder()
                .name(userInfo.getName())
                .family(userInfo.getFamily())
                .username(userInfo.getUsername())
                .password(encoder.encode(userInfo.getPassword()))
                .mobile(userInfo.getMobile())
                .birthday(userInfo.getBirthday())
                .build());
    }
}
