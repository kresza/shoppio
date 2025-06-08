package com.shoppishop.shoppio.cart.create;

import com.shoppishop.shoppio.cart.TestStubs;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(MockitoExtension.class)
class CodeGeneratorTest implements TestStubs {

    @Test
    void shouldGenerateCartIdCode() {
        // given / when
        String generatedCartIdCode = CodeGenerator.generateCartIdCode(CART_ID);

        // then
        assertThat(generatedCartIdCode).isNotBlank().isEqualTo(CART_ID_2);
    }
}
