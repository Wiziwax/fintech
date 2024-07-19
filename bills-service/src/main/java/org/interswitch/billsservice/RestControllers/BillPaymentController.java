package org.interswitch.billsservice.RestControllers;

import org.interswitch.billsservice.DTOs.PaymentDTO;
import org.interswitch.billsservice.Entities.BillCategory;
import org.interswitch.billsservice.Entities.Biller;
import org.interswitch.billsservice.Entities.Payment;
import org.interswitch.billsservice.Entities.Product;
import org.interswitch.billsservice.Services.BillPaymentService;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;


@RestController
@RequestMapping("/bills")
public class BillPaymentController {

    private final BillPaymentService billPaymentService;

    public BillPaymentController(BillPaymentService billPaymentService) {
        this.billPaymentService = billPaymentService;
    }

    @GetMapping("/categories")
    public List<BillCategory> getAllBillCategories() {
        return billPaymentService.getAllBillCategories();
    }

    @GetMapping("/billers")
    public List<Biller> getBillersByCategory(@RequestParam Long categoryId) {
        return billPaymentService.getBillersByCategory(categoryId);
    }

    @GetMapping("/products")
    public List<Product> getProductsByBiller(@RequestParam Long billerId) {
        return billPaymentService.getProductsByBiller(billerId);
    }

    @PostMapping("/pay")
    public Payment submitPayment(@RequestBody PaymentDTO paymentDTO) {
        return billPaymentService.submitPayment(paymentDTO);
    }
}
