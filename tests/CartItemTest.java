import csc4700.CartItem;
import csc4700.Item;
import csc4700.exceptions.InvalidCountException;
import org.junit.Test;
import static org.junit.Assert.*;

public class CartItemTest {


    @Test
    public void testNullCartItemCount() {

        CartItem itemOne = new CartItem(null);

        assertEquals(itemOne.getCount(), 0);
    }

    @Test
    public void testDecrementLessThanZero () {
        CartItem itemOne = new CartItem(new Item());

        try {
            itemOne.decrementCountByOne();
            fail("Error not raised");
        } catch (InvalidCountException e) {
            // Expected
        }
    }

}
