package com.serviceAuth.authService.user.application.ports.output.notification;

import com.serviceAuth.authService.user.domain.model.UserEmployeeEntityDomain;

public interface ConfirmationRegisterUseNotificationPort {
    void notifyConfirmRegister(UserEmployeeEntityDomain userEmployee);
}
