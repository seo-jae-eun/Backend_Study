package org.example.day17.adapter.out.persistence;


import lombok.RequiredArgsConstructor;
import org.example.day17.application.port.out.LoginMemberPort;
import org.example.day17.application.port.out.SignupMemberPort;
import org.example.day17.common.PersistenceAdapter;
import org.example.day17.domain.Member;
import java.util.Optional;

@PersistenceAdapter
@RequiredArgsConstructor
public class MemberPersistenceAdapter implements SignupMemberPort, LoginMemberPort {
    private final JpaMemberRepository jpaMemberRepository;
    @Override
    public void saveMember(Member member) {
        MemberEntity entity = MemberEntity.builder()
                .email(member.getEmail())
                .name(member.getName())
                .build();
        // DB 저장 코드
        jpaMemberRepository.save(entity);
    }

    @Override
    public Member loginMember(Member member) {

        Optional<MemberEntity> result = jpaMemberRepository.findByEmail(member.getEmail());
        if(result.isPresent()) {
            MemberEntity entity = result.get();
            Member member1 = Member.builder()
                    .idx(entity.getIdx())
                    .email(entity.getEmail())
                    .name(entity.getName())
                    .build();

            return member1;
        }

        return null;
    }
}
