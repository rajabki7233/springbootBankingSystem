import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.assertEquals;

class AccountServiceTest {

    @Test
    void shouldAddDepositToBalance() {
        BigDecimal balance = new BigDecimal("5000.00");
        BigDecimal deposit = new BigDecimal("2000.00");

        BigDecimal result = balance.add(deposit);

        assertEquals(new BigDecimal("7000.00"), result);
    }
}