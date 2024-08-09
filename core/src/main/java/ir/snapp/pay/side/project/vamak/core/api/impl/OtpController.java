package ir.snapp.pay.side.project.vamak.core.api.impl;

import ir.snapp.pay.side.project.vamak.core.api.OtpMgmt;
import ir.snapp.pay.side.project.vamak.core.service.OtpService;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class OtpController implements OtpMgmt {

    private final OtpService service;

    public OtpController(OtpService service) {
        this.service = service;
    }

    @Override
    public void send(String username) {
        service.sendTo(username);
    }
}
