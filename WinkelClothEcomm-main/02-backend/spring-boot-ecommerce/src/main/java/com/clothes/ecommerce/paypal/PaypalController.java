package com.clothes.ecommerce.paypal;

import com.clothes.ecommerce.paypal.*; // Adjusted import for PayPalService

import com.paypal.api.payments.Links; // Adjusted import for Links
import com.paypal.api.payments.Payment;
import com.paypal.base.rest.PayPalRESTException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/paypal")

public class PaypalController {
    @Autowired
    private PaypalService paypalService;

    private static final String SUCCESS_URL = "http://localhost:4200/paypal/success"; // Adjusted URL
    private static final String CANCEL_URL = "http://localhost:4200/paypal/cancel"; // Adjusted URL

    @PostMapping("/pay")
    public String payment(@RequestParam("sum") double sum) {
        try {
            Payment payment = paypalService.createPayment(
                    sum,
                    "USD",
                    "paypal",
                    "sale",
                    "payment description",
                    CANCEL_URL,
                    SUCCESS_URL);
            for (Links links : payment.getLinks()) {
                if (links.getRel().equals("approval_url")) {
                    return "redirect:" + links.getHref();
                }
            }

        } catch (PayPalRESTException e) {
            e.printStackTrace();
        }
        return "redirect:/";
    }

    @GetMapping("/success")
    public String successPay(@RequestParam("paymentId") String paymentId, @RequestParam("PayerID") String payerId) {
        try {
            Payment payment = paypalService.executePayment(paymentId, payerId);
            if (payment.getState().equals("approved")) {
                return "success";
            }
        } catch (PayPalRESTException e) {
            e.printStackTrace();
        }
        return "redirect:/";
    }

    @GetMapping("/cancel")
    public String cancelPay() {
        return "cancel";
    }
}