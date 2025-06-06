package com.projeto;

import java.util.List;
import java.util.Properties;
import javax.mail.*;
import javax.mail.internet.*;

public class EmailSender {
    private static final String REMETENTE = "gustavoho2005@gmail.com";
    private static final String SENHA = "ftpk okwp aups ezkz";

    public static void enviarEmails(List<Usuario> usuarios, Alerta alerta) {
        for (Usuario u : usuarios) {
            enviarEmail(u.getEmailUsuario(),
                        "Alerta de risco nível " + alerta.getNivelRisco(),
                        "Olá,\n\nFoi identificado um alerta na sua região:\n" +
                        alerta.getMensagemAlerta() + "\n\n" +
                        "Data/Hora: " + alerta.getDataHoraAlerta() + "\n\n" +
                        "Por favor, fique atento e tome as devidas precauções.\n\n" +
                        "Atenciosamente,\nMonitor de Alertas");
        }
    }

    public static void enviarEmail(String destinatario, String assunto, String corpo) {
        Properties props = new Properties();
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.port", "587");

        Session session = Session.getInstance(props,
            new javax.mail.Authenticator() {
                protected PasswordAuthentication getPasswordAuthentication() {
                    return new PasswordAuthentication(REMETENTE, SENHA);
                }
            });

        try {
            Message mensagem = new MimeMessage(session);
            mensagem.setFrom(new InternetAddress(REMETENTE));
            mensagem.setRecipients(Message.RecipientType.TO, InternetAddress.parse(destinatario));
            mensagem.setSubject(assunto);
            mensagem.setText(corpo);

            Transport.send(mensagem);

            System.out.println("Email enviado com sucesso para " + destinatario);
        } catch (MessagingException e) {
            throw new RuntimeException("Erro ao enviar e-mail: " + e.getMessage(), e);
        }
    }
}
