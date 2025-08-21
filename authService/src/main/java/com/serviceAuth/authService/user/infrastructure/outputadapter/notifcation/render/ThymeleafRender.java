package com.serviceAuth.authService.user.infrastructure.outputadapter.notifcation.render;

import com.serviceAuth.authService.user.application.ports.output.render.RenderTemplate;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.thymeleaf.context.Context;
import org.thymeleaf.spring6.SpringTemplateEngine;

import java.util.Map;

@Service
@RequiredArgsConstructor
public class ThymeleafRender implements RenderTemplate {

    private final SpringTemplateEngine templateEngine;

    @Override
    public String renderTemplate(String templateName, Map<String, Object> variables) {
        Context context = new Context();
        context.setVariables(variables);

        return templateEngine.process(templateName, context);
    }
}
