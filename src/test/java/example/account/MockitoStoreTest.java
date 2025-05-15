
package example.account;

import example.store.Product;
import example.store.Store;
import example.store.StoreImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class MockitoStoreTest {

    @Mock
    private AccountManager accountManager;

    @InjectMocks
    private StoreImpl store;

    private Product product;
    private Customer customer;

    @BeforeEach
    void setUp() {
        product = new Product();
        customer = new Customer();
    }

    @Test
    public void givenStoreWithInsufficientQuantity_whenBuy_thenThrowException() {
        product.setQuantity(0);
        assertThatThrownBy(() -> store.buy(product, customer))
                .isInstanceOf(RuntimeException.class)
                .hasMessageContaining("Product out of stock");
    }

    @Test
    public void givenStore_whenBuy_thenWithdrawCalledAndReturnsAnyStringExceptSuccess() {
        product.setQuantity(1);
        when(accountManager.withdraw(any(), anyInt())).thenReturn("failure");
        assertThatThrownBy(() -> store.buy(product, customer))
                .isInstanceOf(RuntimeException.class)
                .hasMessageContaining("Payment failure: failure");
    }

    @Test
    public void givenStore_whenBuy_thenProductQuantityDecreased() {
        product.setQuantity(1);
        when(accountManager.withdraw(any(), anyInt())).thenReturn("success");
        store.buy(product, customer);
        Assertions.assertEquals(0, product.getQuantity());
    }
}