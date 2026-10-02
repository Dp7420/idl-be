package in.org.dig.induslockbox.utils;

import in.org.dig.induslockbox.dto.EmailBodyDto;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import lombok.RequiredArgsConstructor;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class EmailService {

    private final JavaMailSender mailSender;

    public void sendMail(String email, EmailBodyDto body)
            throws MessagingException {

        String emailBody = body.getBody()
                .replace("{{userName}}", "456");

        String finalMailContent =
                "<html>" +
                        "<body style='font-family:Arial,sans-serif;'>" +

                        "<h2 style='color:blue;'>"
                        + body.getHeaderBody() +
                        "</h2>" +

                        "<p>"
                        + emailBody +
                        "</p>" +

                        "<br><hr>" +

                        "<footer style='color:gray;'>"
                        + body.getFooterBody() +
                        "</footer>" +

                        "</body>" +
                        "</html>";

        MimeMessage mimeMessage = mailSender.createMimeMessage();

        MimeMessageHelper helper =
                new MimeMessageHelper(mimeMessage, true);

        helper.setTo("digambarbulbule123@gmail.com");

        // Subject
        helper.setSubject(body.getSubject());

        // HTML Mail Content
        helper.setText(finalMailContent, true);

        // Send Mail
        mailSender.send(mimeMessage);

        System.out.println("Mail Sent Successfully");
    }
}