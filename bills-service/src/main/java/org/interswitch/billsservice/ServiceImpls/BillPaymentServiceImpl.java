package org.interswitch.billsservice.ServiceImpls;

import org.interswitch.billsservice.DTOs.PaymentDTO;
import org.interswitch.billsservice.Entities.BillCategory;
import org.interswitch.billsservice.Entities.Biller;
import org.interswitch.billsservice.Entities.Payment;
import org.interswitch.billsservice.Entities.Product;
import org.interswitch.billsservice.Repositories.BillCategoryRepository;
import org.interswitch.billsservice.Repositories.BillerRepository;
import org.interswitch.billsservice.Repositories.PaymentRepository;
import org.interswitch.billsservice.Repositories.ProductRepository;
import org.interswitch.billsservice.Services.BillPaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
public class BillPaymentServiceImpl implements BillPaymentService {

    @Autowired
    private BillCategoryRepository billCategoryRepository;

    @Autowired
    private BillerRepository billerRepository;

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private PaymentRepository paymentRepository;

//    @Autowired
//    private AccountRepository accountRepository;

    @Override
    public List<BillCategory> getAllBillCategories() {
        return billCategoryRepository.findAll();
    }

    @Override
    public List<Biller> getBillersByCategory(Long categoryId) {
        return billerRepository.findByBillCategoryId(categoryId);
    }

    @Override
    public List<Product> getProductsByBiller(Long billerId) {
        return productRepository.findByBillerId(billerId);
    }

    @Override
    public Payment submitPayment(PaymentDTO paymentDTO) {
//        Accounts account = accountRepository.findById(accountId)
//                .orElseThrow(() -> new RuntimeException("Account not found"));


        Product product = productRepository.findById(paymentDTO.getProductId())
                .orElseThrow(() -> new RuntimeException("Product not found"));

        Payment payment = new Payment();
//        payment.setAccount(account);
        payment.setProductId(product.getId());
        payment.setAmount(paymentDTO.getAmount());
        paymentRepository.save(payment);

        return payment;
    }
}
