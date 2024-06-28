package com.example.day08.payment;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.internal.LinkedTreeMap;
import com.google.gson.reflect.TypeToken;
import com.siot.IamportRestClient.IamportClient;
import com.siot.IamportRestClient.exception.IamportResponseException;
import com.siot.IamportRestClient.request.CancelData;
import com.siot.IamportRestClient.response.IamportResponse;
import com.siot.IamportRestClient.response.Payment;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.util.List;
import java.util.Map;

@Service
public class PaymentService {
    // 라이브러리 이용 o
    private final IamportClient iamportClient;

    public PaymentService(IamportClient iamportClient) {
        this.iamportClient = iamportClient;
    }

    public Boolean check(String impUid, IamportResponse<Payment> iamportResponse) {
        Double amount = iamportResponse.getResponse().getAmount().doubleValue();

        String customData = iamportResponse.getResponse().getCustomData();

        Gson gson = new Gson();
        Map<String, List> data = gson.fromJson(customData, Map.class);
        List list = data.get("list");
        Map<String, Double> products = (LinkedTreeMap<String, Double>) list.get(0);
        Double total = 0.0;
        for (String key: products.keySet()) {
            System.out.println(products.get(key));
            if(key.equals("1")) {
                total = total + products.get(key) * 10000.0;
            } else if(key.equals("2")) {
                total = total + products.get(key) * 70000.0;
            } else if(key.equals("9")) {
                total = total + products.get(key) * 100000.0;
            }
        }


        if(total.compareTo(amount) == 0) {
            return true;
        }

        return false;

    }

    public IamportResponse getPaymentInfo(String impUid) throws IamportResponseException, IOException {
        IamportResponse<Payment> response = iamportClient.paymentByImpUid(impUid);

        return response;
    }

    public IamportResponse refund(String impUid, IamportResponse<Payment> info) throws IamportResponseException, IOException {
        CancelData cancelData = new CancelData(impUid, true, info.getResponse().getAmount());
        return iamportClient.cancelPaymentByImpUid(cancelData);
    }


    // 라이브러리 이용 x
//    // 토큰 받기
//    public String getToken() {
//        // POST https://api.iamport.kr/users/getToken
//        // Headers
//        // "Content-Type", "application/json"
//        // "Accept", "application/json"
//        // Body
//        // {
//        //  "imp_key": "", // REST API Key
//        //  "imp_secret": "", // REST API Secret
//        //}
//        HttpHeaders headers = new HttpHeaders();
//        headers.add(HttpHeaders.CONTENT_TYPE,"application/json");
//        headers.add(HttpHeaders.ACCEPT,"application/json");
//
//        Gson gson = new Gson();
//        JsonObject jsonObject = new JsonObject();
//        jsonObject.addProperty("imp_key", "");
//        jsonObject.addProperty("imp_secret", "");
//        String jsonStr = gson.toJson(jsonObject);
//
//        HttpEntity<String> request = new HttpEntity<>(jsonStr, headers);
//        RestTemplate restTemplate = new RestTemplate();
//        ResponseEntity<String> response = restTemplate.exchange(
//                "https://api.iamport.kr/users/getToken",
//                HttpMethod.POST,
//                request,
//                String.class
//        );
//
//        Map<String, Object> result = gson.fromJson(response.getBody(), Map.class);
//        Map<String, Object> mapResponse = (Map<String, Object>) result.get("response");
//        return ""+mapResponse.get("access_token");
//    }
//
//    // 결제 정보 받기
//    public Map<String, Object> getPaymentInfo(String impUid, String accessToken) {
//        // GET https://api.iamport.kr/payments/{imp_uid}
//        // Headers
//        // "Authorization", "위에서 받은 토큰"
//        HttpHeaders headers2 = new HttpHeaders();
//        headers2.add(HttpHeaders.AUTHORIZATION, accessToken);
//
//        HttpEntity request2 = new HttpEntity<>(headers2);
//
//        RestTemplate restTemplate2 = new RestTemplate();
//        ResponseEntity<String> response2 = restTemplate2.exchange(
//                "https://api.iamport.kr/payments/" + impUid,
//                HttpMethod.GET,
//                request2,
//                String.class
//        );
//
//        Gson gson = new Gson();
//        Map<String, Object> result = gson.fromJson(response2.getBody(), Map.class);
//        Map<String, Object> mapResponse = (Map<String, Object>) result.get("response");
//        return mapResponse;
//    }
//
//    // 환불 처리
//    public void refund(String accessToken, String impUid, Double amount) {
//        // POST https://api.iamport.kr/payment.cancel
//        // Headers
//        //  "Content-Type", "application/json"
//        //  "Accept", "application/json"
//        //  "Authorization", "위에서 받은 토큰"
//        // Body
//        //  {
//        //      "reason": "취소사유",
//        //      "imp_uid" : impUid
//        //      "amount": "취소금액"
//        //  }
//
//        HttpHeaders headers = new HttpHeaders();
//        headers.add(HttpHeaders.CONTENT_TYPE,"application/json");
//        headers.add(HttpHeaders.ACCEPT,"application/json");
//        headers.add(HttpHeaders.AUTHORIZATION,accessToken);
//
//        Gson gson = new Gson();
//
//        JsonObject jsonObject = new JsonObject();
//        jsonObject.addProperty("reason", "금액 에러");
//        jsonObject.addProperty("imp_uid", impUid);
//        jsonObject.addProperty("amount", amount);
//        String jsonStr = gson.toJson(jsonObject);
//
//        HttpEntity<String> request = new HttpEntity<>(jsonStr, headers);
//        RestTemplate restTemplate = new RestTemplate();
//        ResponseEntity<String> response = restTemplate.exchange(
//                "https://api.iamport.kr/payments/cancel",
//                HttpMethod.POST,
//                request,
//                String.class
//        );
//        System.out.println(response);
//    }
//
//    public boolean amountCheck(Map<String, Object> paymentInfo) {
//        String customDataJson = (String) paymentInfo.get("custom_data"); // custom_data는 여기서 문자열로 저장되어 있음
//        Gson gson = new Gson();
//        java.lang.reflect.Type type = new TypeToken<Map<String, Object>>(){}.getType();
//        Map<String, Object> customDataMap = gson.fromJson(customDataJson, type);
//        List<Map<String, Object>> dataList = (List<Map<String, Object>>) customDataMap.get("list");
//        int realAmount = 0;
//        for (Map<String, Object> data : dataList) {
//            for (Map.Entry<String, Object> entry : data.entrySet()) {
//                String key = entry.getKey();
//                Double value = (Double)(entry.getValue());
//                if(key.equals("1")) {
//                    realAmount += 10000 * value.intValue();
//                }
//                else if(key.equals("2")) {
//                    realAmount += 70000 * value.intValue();
//                }
//                else if(key.equals("9")) {
//                    realAmount += 100000 * value.intValue();
//                }
//            }
//        }
//        Double amount = (Double) paymentInfo.get("amount");
//        return amount.intValue() == realAmount;
//    }
}
