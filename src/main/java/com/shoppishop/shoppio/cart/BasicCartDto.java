package com.shoppishop.shoppio.cart;

import lombok.Builder;

import java.sql.Timestamp;

@Builder
record BasicCartDto(String cartId, Timestamp createdAt) {}
