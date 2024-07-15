package org.example.emailcertservice.adapter.out;

import lombok.RequiredArgsConstructor;
import org.example.emailcertservice.application.port.out.EmailCertPort;
import org.example.emailcertservice.common.PersistenceAdapter;
import org.example.emailcertservice.domain.EmailCert;
import org.springframework.stereotype.Component;

import java.util.Optional;

@PersistenceAdapter
@RequiredArgsConstructor
public class EmailCertPersistenceAdapter implements EmailCertPort {
    private final JpaEmailCertRepository jpaEmailCertRepository;

    @Override
    public Boolean findEmailCert(EmailCert emailCert) {
        Optional<EmailCertEntity> result = jpaEmailCertRepository.findByEmailAndUuid(emailCert.getEmail(), emailCert.getUuid());
        if(result.isPresent()) {
            return true;
        }
        return false;
    }

    @Override
    public void saveEmailCert(EmailCert emailCert) {
        EmailCertEntity entity = EmailCertEntity.builder()
                .email(emailCert.getEmail())
                .uuid(emailCert.getUuid())
                .build();
        jpaEmailCertRepository.save(entity);

    }

}
