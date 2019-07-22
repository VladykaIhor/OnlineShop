package mate.academy.service;

import mate.academy.model.Code;

public interface MailService {
    void sendConfirmCode(Code code, String email);
}
