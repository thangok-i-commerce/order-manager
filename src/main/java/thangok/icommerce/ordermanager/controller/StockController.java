package thangok.icommerce.ordermanager.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import thangok.icommerce.ordermanager.external.dto.StockRequestDTO;
import thangok.icommerce.ordermanager.external.dto.StockResponseDTO;

@RestController
@RequestMapping("/api/stock")
public class StockController {

    @PostMapping("/export")
    public StockResponseDTO exportStock(@RequestBody final StockRequestDTO stockRequestDTO) {
        return new StockResponseDTO();
    }

    @PostMapping("/import")
    public StockResponseDTO importStock(@RequestBody final StockRequestDTO stockRequestDTO) {
        return new StockResponseDTO();
    }

}
