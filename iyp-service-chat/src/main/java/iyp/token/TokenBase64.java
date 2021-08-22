package iyp.token;

public interface TokenBase64 {

    String encode(String user);

    Token decode(String secret);
}
