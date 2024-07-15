package org.example.memberservice.adapter.out.persistence;


import lombok.RequiredArgsConstructor;
import org.example.memberservice.application.port.out.ModifyMemberPort;
import org.example.memberservice.application.port.out.SignupMemberPort;
import org.example.memberservice.common.PersistenceAdapter;
import org.example.memberservice.domain.Member;

import java.util.Optional;

@PersistenceAdapter
@RequiredArgsConstructor
public class MemberPersistenceAdapter implements SignupMemberPort, ModifyMemberPort {
    private final JpaMemberRepository jpaMemberRepository;

    @Override
    public void verifyMember(Member member) {
        Optional<MemberEntity> result = jpaMemberRepository.findByEmail(member.getEmail());
        if(result.isPresent()) {
            MemberEntity entity = result.get();
            entity.verify();
            jpaMemberRepository.save(entity);
        }
    }

    @Override
    public void saveMember(Member member) {
        MemberEntity entity = MemberEntity.builder()
                .email(member.getEmail())
                .name(member.getName())
                .build();
        // DB 저장 코드
        jpaMemberRepository.save(entity);
    }

}
