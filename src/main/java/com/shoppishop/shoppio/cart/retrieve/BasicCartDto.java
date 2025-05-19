package com.shoppishop.shoppio.cart.retrieve;

import lombok.Builder;

import java.sql.Timestamp;

@Builder
public record BasicCartDto(String cartId, Timestamp createdAt) {}
