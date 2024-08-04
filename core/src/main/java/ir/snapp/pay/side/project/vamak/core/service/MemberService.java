package ir.snapp.pay.side.project.vamak.core.service;

import ir.snapp.pay.side.project.vamak.commons.constraint.Unique;
import ir.snapp.pay.side.project.vamak.core.repo.MemberRepository;
import org.springframework.stereotype.Service;

@Service("checker.username")
public class MemberService implements Unique.Checker<String> {

    private final MemberRepository repo;

    public MemberService(MemberRepository repo) {
        this.repo = repo;
    }

    @Override
    public boolean isExist(String username) {
        return repo.existsByUsername(username);
    }
}
