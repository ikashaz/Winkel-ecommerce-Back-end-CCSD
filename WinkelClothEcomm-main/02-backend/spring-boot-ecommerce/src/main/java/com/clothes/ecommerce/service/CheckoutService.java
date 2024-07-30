package com.clothes.ecommerce.service;

import com.clothes.ecommerce.dto.Purchase;
import com.clothes.ecommerce.dto.PurchaseResponse;

public interface CheckoutService {

    PurchaseResponse placeOrder(Purchase purchase);
}
