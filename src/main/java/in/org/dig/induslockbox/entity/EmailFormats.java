package in.org.dig.induslockbox.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "email_formats")
public class EmailFormats {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "email_format_id")
    private Long emailFormatId;

    @Column(unique = true)
    private String templateId;

    private String templateName;

    @Column(columnDefinition = "TEXT")
    private String templateHeader;

    @Column(columnDefinition = "TEXT")
    private String templateBody;

    @Column(columnDefinition = "TEXT")
    private String templateFooter;
}
