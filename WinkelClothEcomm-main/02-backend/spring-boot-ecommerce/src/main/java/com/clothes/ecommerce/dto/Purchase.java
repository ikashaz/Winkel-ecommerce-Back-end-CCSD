package com.clothes.ecommerce.dto;

import com.clothes.ecommerce.entity.Address;
import com.clothes.ecommerce.entity.Customer;
import com.clothes.ecommerce.entity.Order;
import com.clothes.ecommerce.entity.OrderItem;
import lombok.Data;

import java.util.Set;

@Data
public class Purchase {

    private Customer customer;
    private Address shippingAddress;
    private Address billingAddress;
    private Order order;
    private Set<OrderItem> orderItems;
}
