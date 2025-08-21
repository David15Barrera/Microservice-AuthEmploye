package com.serviceAuth.authService.user.application.ports.output.render;

import java.util.Map;

public interface RenderTemplate {
    String renderTemplate(String templateName, Map<String, Object> variables);
}
