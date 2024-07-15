package org.example.memberservice.application.port.out;

public interface SignupMemberNotificationPort {
    void sendSignupMessage(String message);
}
