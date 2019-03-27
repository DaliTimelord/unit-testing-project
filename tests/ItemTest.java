import csc4700.Item;
import csc4700.ShoppingCart;
import org.junit.Test;

import static org.junit.Assert.*;

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

    @Test
    public void unequalHashTest() {
        // unequal determined by name string

        // create butter item
        Item butter = new Item();
        butter.setName("Butter");
        butter.setDescription("A dairy product with high butterfat content which is solid when chilled and at room temperature in some regions, and liquid when warmed.");
        butter.setCost(4);

        // create soda item
        Item soda = new Item();
        soda.setName("Soda");
        soda.setDescription("A drink that typically contains carbonated water, a sweetener, and a natural or artificial flavoring.");
        soda.setCost(2);

        assertNotEquals(butter.hashCode(), soda.hashCode());
    }

    @Test
    public void equalHashTest() {
        // create butter item
        Item butter = new Item();
        butter.setName("Butter");
        butter.setDescription("A dairy product with high butterfat content which is solid when chilled and at room temperature in some regions, and liquid when warmed.");
        butter.setCost(4);

        assertEquals(butter.hashCode(), "Butter".hashCode());
    }

}
