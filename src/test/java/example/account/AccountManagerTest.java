package example.account;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;

public class AccountManagerTest {
    Customer customer = new Customer();
    AccountManager accountManager = new AccountManagerImpl();

    @Test
    public void givenCustomer_whenDeposit_thenBalanceUpdated() {
        accountManager.deposit(customer, 1000);
        assertThat(customer.getBalance()).isEqualTo(1000);
    }

    @Test
    public void givenCustomer_whenDepositWithNegativeAmount_thenBalanceNotUpdated() {
        Assertions.assertEquals(0, customer.getBalance());
        assertThatThrownBy(() -> accountManager.deposit(customer, -1000))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("amount must be positive");
    }

    @Test
    public void givenCustomer_whenWithdrawWithSufficientBalance_thenBalanceUpdated() {
        customer.setBalance(1000);
        String result = accountManager.withdraw(customer, 500);
        Assertions.assertEquals("success", result);
        assertThat(customer.getBalance()).isEqualTo(500);
    }

    @Test
    public void givenCustomer_whenWithdrawWithInsufficientBalanceAndNotCreditAllowed_thenReturnError() {
        customer.setBalance(500);
        customer.setCreditAllowed(false);
        String result = accountManager.withdraw(customer, 1000);
        Assertions.assertEquals("insufficient account balance", result);
        Assertions.assertEquals(500, customer.getBalance());
    }

    @Test
    public void
    givenCustomer_whenWithdrawWithInsufficientBalanceLessThanMaxCreditAndNotVipCustomer_thenBalanceUpdated() {
        customer.setBalance(500);
        customer.setCreditAllowed(true);
        customer.setVip(false);
        String result = accountManager.withdraw(customer, 1000);
        Assertions.assertEquals("success", result);
        Assertions.assertEquals(-500, customer.getBalance());
    }

    @Test
    public void
    givenCustomer_whenWithdrawWithInsufficientBalanceGreaterThanMaxCreditAndVipCustomer_thenBalanceUpdated() {
        customer.setBalance(500);
        customer.setCreditAllowed(true);
        customer.setVip(true);
        String result = accountManager.withdraw(customer, 2000);
        Assertions.assertEquals("success", result);
        Assertions.assertEquals(-1500, customer.getBalance());
    }

    @Test
    public void
    givenCustomer_whenWithdrawWithInsufficientBalanceLessThanMaxCreditAndVipCustomer_thenBalanceUpdated() {
        customer.setBalance(500);
        customer.setCreditAllowed(true);
        customer.setVip(true);
        String result = accountManager.withdraw(customer, 1000);
        Assertions.assertEquals("success", result);
        Assertions.assertEquals(-500, customer.getBalance());
    }

    @Test
    public void
    givenCustomer_whenWithdrawWithInsufficientBalanceGreaterThanMaxCreditAndNotVipCustomer_thenReturnError() {
        customer.setBalance(500);
        customer.setCreditAllowed(true);
        customer.setVip(false);
        String result = accountManager.withdraw(customer, 2000);
        Assertions.assertEquals("maximum credit exceeded", result);
        Assertions.assertEquals(500, customer.getBalance());
    }

}
