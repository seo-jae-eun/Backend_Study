package com.example.day12.product;

import com.example.day12.product.model.Product;
import com.example.day12.product.model.request.ProductCreateReq;
import com.example.day12.product.model.response.ProductRes;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;

// 단위 테스트
// 통합 테스트

//@SpringBootTest // 단위 테스트에 속하지 않는다, 스프링 프로젝트 자체를 실행시켜버림 -> 통합 테스트
    // 모든 Bean을 등록시켜버림 , 단위 테스트 때는 달면 안된다.
@ExtendWith(MockitoExtension.class) // 단위 테스트
class ProductServiceTest {
    @Mock
    private ProductRepository productRepository;
    @InjectMocks
    private ProductService productService; // Mock Bean 객체가 들어감
    // 강사님의 메소드 이름 짓는 방식 : 클래스이름_메소드이름_성공 or 실패(예외)
    @Test
    void productService_create_success() {
        // given - 시나리오 지정
        ProductCreateReq request = ProductCreateReq.builder()
                .name("이름")
                .description("설명")
                .price(1000)
                .build();
        Product product = Product.builder()
                .idx(1L)
                .name("이름")
                .description("설명")
                .price(1000)
                .build();
        given(productRepository.save(any(Product.class))).willReturn(product);
        // when - 실제 테스트하려는 코드
        ProductRes response = productService.create(request);
        // then
        assertNotNull(response);
        assertEquals(1L, response.getIdx());
        assertEquals("이름", response.getName());
        assertEquals("설명", response.getDescription());
        assertEquals(1000, response.getPrice());
    }
    void productService_create_failed_email_validation() {

    }


}