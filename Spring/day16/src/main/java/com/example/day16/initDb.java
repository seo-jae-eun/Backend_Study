package com.example.day16;

import com.example.day16.many.Many;
import com.example.day16.many.ManyRepository;
import com.example.day16.one.OneRepository;
import com.example.day16.one.model.One;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class initDb {
    private final OneRepository oneRepository;
    private final ManyRepository manyRepository;

    @PostConstruct
    public void dataInsert() {
        for (int i = 1; i <= 10; i++) {
            One one1 = One.builder().str("one-"+i).build();
            oneRepository.save(one1);

            for (int j = 1; j <= 5; j++) {
                Many many1 = Many.builder().str("many-"+i+"-"+j).one(one1).build();
                manyRepository.save(many1);
            }
        }
    }
}
