package org.example.orderservice.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data   //@Getter @Setter @RequiredArgsConstructor @ToString @EqualsAndHashCode 포함
@AllArgsConstructor //모든 프라퍼티를 인자로 가지는 생성자 자동 생성
@NoArgsConstructor  //인자가 없는 생성자 자동 생성
public class OrderReqDetailDTO {
    private String productId;
    private int qty;
}
