package org.example.productservice.adapter.in.web;


import lombok.RequiredArgsConstructor;
import org.example.productservice.application.port.in.RegisterProductCommand;
import org.example.productservice.application.port.in.RegisterProductUseCase;
import org.example.productservice.common.WebAdapter;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@WebAdapter
@RestController
@RequiredArgsConstructor
public class RegisterProductController {

    private final RegisterProductUseCase useCase;

    @RequestMapping(method = RequestMethod.POST, value = "/product/register")
    public ResponseEntity register(@RequestBody RegisterProductRequest request) {
        RegisterProductCommand command = RegisterProductCommand.builder()
                .name(request.getName())
                .price(request.getPrice())
                .build();
        return ResponseEntity.ok().body(useCase.registerProduct(command));
    }
}
