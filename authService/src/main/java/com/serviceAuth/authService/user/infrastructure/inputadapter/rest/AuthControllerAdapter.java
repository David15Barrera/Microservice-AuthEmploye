package com.serviceAuth.authService.user.infrastructure.inputadapter.rest;

import com.serviceAuth.authService.common.infrastructure.anotation.WebAdapter;
import com.serviceAuth.authService.user.application.ports.input.AuthenticatingUserInputPort;
import com.serviceAuth.authService.user.application.ports.input.ConfirmationCodeInputPort;
import com.serviceAuth.authService.user.application.usecase.authentication.AuthUserDto;
import com.serviceAuth.authService.user.application.usecase.confirmcode.ConfirmCodeUseDto;
import com.serviceAuth.authService.user.domain.model.UserEntityDomain;
import com.serviceAuth.authService.user.infrastructure.inputadapter.dto.ConfirmUserDto;
import com.serviceAuth.authService.user.infrastructure.inputadapter.dto.TokenDto;
import com.serviceAuth.authService.user.infrastructure.inputadapter.dto.UserResponseDto;
import com.serviceAuth.authService.user.infrastructure.inputadapter.dto.UserSignIn;
import com.serviceAuth.authService.user.infrastructure.inputadapter.mapper.UserEmployeeMapper;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("v1/auth")
@WebAdapter
@RequiredArgsConstructor
public class AuthControllerAdapter {

    private final ConfirmationCodeInputPort confirmationCodeInputPort;
    private final UserEmployeeMapper userEmployeeMapper;
    private final AuthenticatingUserInputPort authenticatingUserInputPort;

    @PutMapping("/sign-up")
    @Transactional
    public ResponseEntity<TokenDto> confirmCodeUserSingUp(@RequestBody @Valid ConfirmUserDto confirmUserDto) {

        ConfirmCodeUseDto objectAdapter = confirmUserDto.toDomain();

        UserEntityDomain userEntityDomain = this.confirmationCodeInputPort.confirmCode(objectAdapter);

        UserResponseDto userResponseDto = this.userEmployeeMapper.toResponseDto(userEntityDomain);

        TokenDto tokenDto = TokenDto.builder().token(userEntityDomain.getToken()).user(userResponseDto).build();

        return ResponseEntity.status(HttpStatus.OK).body(tokenDto);
    }

    @PostMapping("/sign-in")
    @Transactional
    public ResponseEntity<TokenDto> signIn(@RequestBody @Valid UserSignIn userSignIn) {

        AuthUserDto objectAdapter = userSignIn.toDomain();

        UserEntityDomain userEntityDomain = authenticatingUserInputPort.authenticationUser(objectAdapter);

        UserResponseDto userResponseDto = this.userEmployeeMapper.toResponseDto(userEntityDomain);

        TokenDto tokenDto = TokenDto.builder().token(userEntityDomain.getToken()).user(userResponseDto).build();

        return ResponseEntity.status(HttpStatus.OK).body(tokenDto);

    }

}
