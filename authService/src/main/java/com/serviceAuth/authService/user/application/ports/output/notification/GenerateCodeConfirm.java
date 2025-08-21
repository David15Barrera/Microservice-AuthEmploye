package com.serviceAuth.authService.user.application.ports.output.notification;

public interface GenerateCodeConfirm {
    String generateConfirmCode(String key);
}
