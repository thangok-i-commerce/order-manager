package thangok.icommerce.ordermanager.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import thangok.icommerce.ordermanager.external.dto.PaymentRequestDTO;
import thangok.icommerce.ordermanager.external.dto.PaymentResponseDTO;

@RestController
@RequestMapping("/api/payment")
public class PaymentController {

    @PostMapping("/purchase")
    public PaymentResponseDTO purchase(@RequestBody final PaymentRequestDTO paymentRequestDTO) {
        return new PaymentResponseDTO();
    }

    @PostMapping("/refund")
    public PaymentResponseDTO refund(@RequestBody final PaymentRequestDTO paymentRequestDTO) {
        return new PaymentResponseDTO();
    }

}
