package org.example.emailcertservice.adapter.out;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface JpaEmailCertRepository extends JpaRepository<EmailCertEntity, Long> {
    Optional<EmailCertEntity> findByEmailAndUuid(String email, String emailUuid);
}
