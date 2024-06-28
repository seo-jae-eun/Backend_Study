package com.example.day08.payment;


import com.siot.IamportRestClient.exception.IamportResponseException;
import com.siot.IamportRestClient.response.IamportResponse;
import com.siot.IamportRestClient.response.Payment;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.Map;

@RestController
@RequestMapping("/payment")
public class PaymentController {
    private final PaymentService paymentService;

    public PaymentController(PaymentService paymentService) {
        this.paymentService = paymentService;
    }

    @RequestMapping(method = RequestMethod.GET, value = "/check")
    public ResponseEntity<String> check(String impUid) {
        System.out.println(impUid); // 2개 찍힘

        // 라이브러리 사용 o
        try {
            IamportResponse<Payment> info = paymentService.getPaymentInfo(impUid);
            Boolean result = paymentService.check(impUid, info);

            if(result) {
                return ResponseEntity.ok("ok");
            } else {
                paymentService.refund(impUid, info);
                return ResponseEntity.ok("error");
            }

        } catch (IamportResponseException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        // 라이브러리 사용 x
//        // 토큰 받기
//        String accessToken = paymentService.getToken();
//        // 결제 정보 받기
//        Map<String, Object> paymentInfo = paymentService.getPaymentInfo(impUid, accessToken);
//        Double amount = (Double) paymentInfo.get("amount");
//
//        // 실제 상품의 금액과 결제 금액을 비교
//        //  실제 상품의 금액은 info에서 custom_data 상품 번호로 가격을 DB에서 조회하고 수량으로 계산
//        //  실제 결제 금액은 info에서 amount로 확인
//        //  두 값이 일치하면 주문 테이블에 정보를 저장
//        //      주문 성공이라고 출력
//        //  일치하지 않으면 환불
//        //      결제에 문제가 발생했습니다. 자동으로 환불처리 됩니다. 출력
//        if(paymentService.amountCheck(paymentInfo)) {
//            System.out.println("주문 성공");
//        } else {
//            System.out.println("결제에 문제가 발생했습니다. 자동으로 환불처리 됩니다.");
//            // 환불처리
//            paymentService.refund(accessToken, impUid, amount);
//        }

//        return ResponseEntity.ok("ok");
    }
}
