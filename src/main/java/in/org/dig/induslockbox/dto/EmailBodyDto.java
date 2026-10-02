package in.org.dig.induslockbox.dto;

import lombok.Data;

@Data
public class EmailBodyDto {
    private String subject;
    private String body;
    private String ccPerson;
    private String bccPerson;
    private String textMessage;
    private String headerBody;
    private String footerBody;

}
