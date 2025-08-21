package com.serviceAuth.authService.infrastructure;


import com.serviceAuth.authService.common.infrastructure.anotation.CurrentUser;
import com.serviceAuth.authService.user.infrastructure.outputadapter.persistence.entity.UserDBEntity;
import com.serviceAuth.authService.user.infrastructure.outputadapter.persistence.repository.UserDBRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.MethodParameter;
import org.springframework.lang.NonNull;
import org.springframework.lang.Nullable;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.List;

@Configuration
@RequiredArgsConstructor
public class WebConfig implements WebMvcConfigurer {

    private final UserDBRepository userDBRepository;

    @Override
    public void addArgumentResolvers(@NonNull List<HandlerMethodArgumentResolver> resolvers) {
        resolvers.add(new CurrentUserArgumentResolver(userDBRepository));
    }
}

@RequiredArgsConstructor
class CurrentUserArgumentResolver implements HandlerMethodArgumentResolver {

    private final UserDBRepository userDBRepository;

    @Override
    public boolean supportsParameter(@NonNull MethodParameter parameter) {
        return parameter.hasParameterAnnotation(CurrentUser.class);
    }

    @Override
    public Object resolveArgument(@NonNull MethodParameter parameter, @Nullable ModelAndViewContainer mavContainer,
                                  @NonNull NativeWebRequest webRequest, @Nullable WebDataBinderFactory binderFactory) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String subject = authentication.getName();
        Class<?> dtoType = parameter.getParameterType();
        if (dtoType.equals(UserDBEntity.class)) {
            throw new IllegalArgumentException("La clase UserEntity no puede ser utilizada como argumento");
        }
        return userDBRepository.findUnknownById(Long.parseLong(subject), dtoType).orElseThrow();
    }
}