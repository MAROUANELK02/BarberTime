package com.barbertime.barbertime_backend.security.Email;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.WriterException;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

@Service
@AllArgsConstructor
@Slf4j
public class EmailSenderService {
    private JavaMailSender mailSender;

    public void sendEmail(String toEmail,String subject,String body) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom("marouanelk02@gmail.com");
        message.setTo(toEmail);
        message.setSubject(subject);
        message.setText(body);

        mailSender.send(message);

        log.info("Mail sent successfully...");
    }

    public void generateQRCodeImage(String barcodeText, int width, int height, String filePath)
            throws WriterException, IOException {
        QRCodeWriter qrCodeWriter = new QRCodeWriter();
        BitMatrix bitMatrix = qrCodeWriter.encode(barcodeText, BarcodeFormat.QR_CODE, width, height);

        Path path = Paths.get(filePath);
        MatrixToImageWriter.writeToPath(bitMatrix, "PNG", path);
    }

    public void sendEmailWithAttachment(String toEmail, String subject, String body, String attachmentPath) {
        try {
            MimeMessage message = mailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(message, true);

            // Définir l'expéditeur comme "BarberTime"
            helper.setFrom("BarberTime Plateforme <noreply@barbertime.com>");
            helper.setTo(toEmail);
            helper.setSubject(subject);

            // Ajouter un message personnalisé en français
            String customBody = "Cher client,\n\n" + body + "\n\nCordialement,\nL'équipe BarberTime";
            helper.setText(customBody);

            FileSystemResource file = new FileSystemResource(new File(attachmentPath));
            helper.addAttachment("QRCode.png", file);

            mailSender.send(message);

            log.info("Mail sent successfully...");
        } catch (MessagingException e) {
            log.error("Failed to send email with attachment", e);
        }
    }
}
