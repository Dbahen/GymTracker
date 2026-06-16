package com.dbahen.GymTracker.service;

import com.resend.Resend;
import com.resend.core.exception.ResendException;
import com.resend.services.emails.model.CreateEmailOptions;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class EmailService {

    @Value("${resend.api.key}")
    private String resendApiKey;

    public void sendPasswordResetEmail(String toEmail, String resetLink) {
        Resend resend = new Resend(resendApiKey);

        CreateEmailOptions params = CreateEmailOptions.builder()
                .from("GymTracker <onboarding@resend.dev")
                .to(toEmail)
                .subject("Restablecer contraseña - GymTracker")
                .html("<h2>Restablecer contraseña</h2>" +
                        "<p>Haz clic en el siguiente enlace para restablecer tu contraseña:</p>" +
                        "<a href=\"" + resetLink + "\">Restablecer contraseña</a>" +
                        "<p>Este enlace expira en 1 hora.</p>")
                .build();

        try {
            resend.emails().send(params);
        } catch (ResendException e) {
            throw new RuntimeException("Error enviando email: " + e.getMessage());
        }
    }
}
