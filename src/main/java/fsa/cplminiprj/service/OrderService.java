package fsa.cplminiprj.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

//@Service
public class OrderService {
    // DI: Field Injection
//    @Autowired
    private UserService userService;
//    @Autowired
    private ProductService productService;
//    @Autowired
    private PaymentService paymentService;
//    @Autowired
    private NotificationService notificationService;

    // DI: Setter Injection
    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    public void setProductService(ProductService productService) {
        this.productService = productService;
    }

    public void setPaymentService(PaymentService paymentService) {
        this.paymentService = paymentService;
    }

    public void setNotificationService(NotificationService notificationService) {
        this.notificationService = notificationService;
    }

    // DI: Constructor Injection
    public OrderService(UserService userService, ProductService productService, PaymentService paymentService, NotificationService notificationService) {
        this.userService = userService;
        this.productService = productService;
        this.paymentService = paymentService;
        this.notificationService = notificationService;
    }

    public void order() {
        double price = productService.getProductPrice(1);
        int uid = userService.getUserId("user01");
        paymentService.pay(uid, price);
        notificationService.send("Order has been placed successfully!");
    }
}
