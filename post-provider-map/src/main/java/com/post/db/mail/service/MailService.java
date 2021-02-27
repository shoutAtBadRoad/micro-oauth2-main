package com.post.db.mail.service;

public interface MailService {

    String getEmailCode(String mail);

    boolean testCode(String mail,String code);

}
