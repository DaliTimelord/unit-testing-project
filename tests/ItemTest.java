import csc4700.ShoppingCart;
import org.junit.Test;

public class ItemTest {

    @Test
    public void addNullItem() {
        ShoppingCart cart = new ShoppingCart();

        try {
            cart.addItem(null);
        } catch (NullPointerException e) {
            // Expected
        }
    }

}
