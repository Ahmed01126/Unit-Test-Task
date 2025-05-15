package example.account;

import example.store.Product;
import example.store.Store;
import example.store.StoreImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;

public class StoreTest {
    private Store store;
    private Product product;
    private Customer customer;

    @BeforeEach
    void setUp() {
        store = new StoreImpl(new AccountManagerImpl());
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
        store = new StoreImpl(new AccountManagerImpl() {
            @Override
            public String withdraw(Customer customer, int amount) {
                return "failure";
            }
        });
        product.setQuantity(1);
        assertThatThrownBy(() -> store.buy(product, customer))
                .isInstanceOf(RuntimeException.class)
                .hasMessageContaining("Payment failure: failure");
    }

    @Test
    public void givenStore_whenBuy_thenProductQuantityDecreased() {
        store = new StoreImpl(new AccountManagerImpl() {
            @Override
            public String withdraw(Customer customer, int amount) {
                return "success";
            }
        });
        product.setQuantity(1);
        store.buy(product, customer);
        Assertions.assertEquals(0, product.getQuantity());
    }


}
