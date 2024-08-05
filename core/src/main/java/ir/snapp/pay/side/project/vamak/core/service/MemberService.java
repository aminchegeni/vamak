package ir.snapp.pay.side.project.vamak.core.service;

import ir.snapp.pay.side.project.vamak.commons.constraint.Unique;
import ir.snapp.pay.side.project.vamak.commons.dto.PersonalInfo;
import ir.snapp.pay.side.project.vamak.commons.dto.UserInfo;
import ir.snapp.pay.side.project.vamak.commons.dto.wrapper.Error;
import ir.snapp.pay.side.project.vamak.commons.exception.DoseNotExistException;
import ir.snapp.pay.side.project.vamak.commons.model.Member;
import ir.snapp.pay.side.project.vamak.core.repo.MemberRepository;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import static ir.snapp.pay.side.project.vamak.commons.dto.wrapper.Reason.NOT_EXISTENT;

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

    public void createMember(UserInfo info) {
        repo.save(Member.builder()
                .name(info.getName())
                .family(info.getFamily())
                .username(info.getUsername())
                .password(encoder.encode(info.getPassword()))
                .mobile(info.getMobile())
                .birthday(info.getBirthday())
                .build());
    }

    public void updateMember(PersonalInfo info) {
        int count = repo.updatePersonalState(info);
        if (count <= 0) {
            String username = SecurityContextHolder.getContext().getAuthentication().getName();
            throw new DoseNotExistException(
                    Error.builder()
                            .reason(NOT_EXISTENT)
                            .param("username")
                            .description("user dose not exist")
                            .values(new String[]{username})
                            .build());
        }
    }
}
