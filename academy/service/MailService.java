package mate.academy.service;

import mate.academy.model.Code;
import mate.academy.model.User;

public interface MailService {
    void sendConfirmCode(String email, String code);
}
