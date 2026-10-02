package in.org.dig.induslockbox.repository;

import in.org.dig.induslockbox.entity.EmailFormats;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

public interface EmailFormatRepository extends JpaRepository<EmailFormats, Long> {

    Optional<EmailFormats> findByTemplateId(String templateId);


    @Query("SELECT e.templateId, e.templateName FROM EmailFormats e")
    java.util.List<Object[]> findAllTemplateIdAndTemplateName();

    default Map<String, String> getAllTemplateIdAndTemplateName() {
        return findAllTemplateIdAndTemplateName()
                .stream()
                .collect(Collectors.toMap(
                        data -> (String) data[0],
                        data -> (String) data[1]
                ));
    }
}
