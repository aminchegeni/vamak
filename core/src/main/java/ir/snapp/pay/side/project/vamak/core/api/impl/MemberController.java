package ir.snapp.pay.side.project.vamak.core.api.impl;

import ir.snapp.pay.side.project.vamak.commons.dto.PersonalInfo;
import ir.snapp.pay.side.project.vamak.commons.dto.UserInfo;
import ir.snapp.pay.side.project.vamak.core.api.MemberMgmt;
import ir.snapp.pay.side.project.vamak.core.service.MemberService;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MemberController implements MemberMgmt {

    private final MemberService service;

    public MemberController(MemberService service) {
        this.service = service;
    }

    @Override
    public void add(UserInfo info) {
        service.createMember(info);
    }

    @Override
    public void update(PersonalInfo info) {
        service.updateMember(info);
    }
}
