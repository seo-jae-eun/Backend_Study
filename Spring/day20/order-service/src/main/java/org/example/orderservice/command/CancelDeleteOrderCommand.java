package org.example.orderservice.command;

import lombok.Builder;
import lombok.Value;
import org.axonframework.modelling.command.TargetAggregateIdentifier;

@Value      //Equivalent to @Getter @FieldDefaults(makeFinal=true, level=AccessLevel.PRIVATE) @AllArgsConstructor @ToString @EqualsAndHashCode.
@Builder    //객체 생성시 {Class명}.builder() 이용함. 한번 생성된 객체값이 변경 안되게 강제함으로써 객체값 변조를 방지
public class CancelDeleteOrderCommand {
    @TargetAggregateIdentifier  //orderId가 Command메시지를 구별하는 유일한 key라는 것을 나타냄
    String orderId;
    boolean isCompensation;
}
