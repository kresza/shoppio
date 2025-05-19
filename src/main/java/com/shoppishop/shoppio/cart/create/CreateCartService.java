package com.shoppishop.shoppio.cart.create;

import com.shoppishop.shoppio.models.BaseResponse;
import org.springframework.stereotype.Service;

@Service
public class CreateCartService {

    public BaseResponse createCart(){
        return BaseResponse.builder().build();
    }

}
