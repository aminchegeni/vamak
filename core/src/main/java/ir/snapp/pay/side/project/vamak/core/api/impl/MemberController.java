package ir.snapp.pay.side.project.vamak.core.api.impl;

import ir.snapp.pay.side.project.vamak.commons.dto.UserInfo;
import ir.snapp.pay.side.project.vamak.commons.model.Member;
import ir.snapp.pay.side.project.vamak.core.api.MemberMgmt;
import ir.snapp.pay.side.project.vamak.core.repo.MemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MemberController implements MemberMgmt {

    @Autowired
    private MemberRepository repo;

    @Autowired
    private PasswordEncoder encoder;

    @Override
    public void add(UserInfo userInfo) {
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
