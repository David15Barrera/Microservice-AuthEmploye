package com.serviceAuth.authService.user.application.ports.input;

import com.serviceAuth.authService.user.application.usecase.confirmcode.ConfirmCodeUseDto;
import com.serviceAuth.authService.user.domain.model.UserEntityDomain;

public interface ConfirmationCodeInputPort {
    UserEntityDomain confirmCode(ConfirmCodeUseDto confirmCodeUseDto);

}
