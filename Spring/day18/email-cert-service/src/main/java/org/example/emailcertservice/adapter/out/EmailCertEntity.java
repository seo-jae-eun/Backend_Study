package org.example.emailcertservice.adapter.out;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "email_cert")
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class EmailCertEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idx;

    private String email;
    private String uuid;
}
