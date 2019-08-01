package service;

public interface MailService {
    void sendConfirmCode(String email, String code);
}
