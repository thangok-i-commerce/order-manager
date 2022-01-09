package thangok.icommerce.ordermanager.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import thangok.icommerce.ordermanager.aop.io.LogIO;
import thangok.icommerce.ordermanager.external.dto.PaymentRequestDTO;
import thangok.icommerce.ordermanager.external.dto.PaymentResponseDTO;

@RestController
@RequestMapping("/api/payment")
public class PaymentController {

    @LogIO
    @PostMapping("/purchase")
    public PaymentResponseDTO purchase(@RequestBody final PaymentRequestDTO paymentRequestDTO) {
        PaymentResponseDTO result = new PaymentResponseDTO();
        result.setIsSuccess(false);
        return result;
    }

    @LogIO
    @PostMapping("/refund")
    public PaymentResponseDTO refund(@RequestBody final PaymentRequestDTO paymentRequestDTO) {
        PaymentResponseDTO result = new PaymentResponseDTO();
        result.setIsSuccess(true);
        return result;
    }

}
