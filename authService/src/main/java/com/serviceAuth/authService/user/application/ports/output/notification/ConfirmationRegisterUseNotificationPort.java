package com.serviceAuth.authService.user.application.ports.output.notification;


import com.serviceAuth.authService.user.domain.model.UserEmployee;

public interface ConfirmationRegisterUseNotificationPort {
    void notifyConfirmRegister(UserEmployee userEmployee);
}
