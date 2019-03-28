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

    @Test
    public void testIncreaseItem() {
        CartItem itemOne = new CartItem(new Item());

        itemOne.incrementCountByOne();

        assertEquals(itemOne.getCount(), 1);
    }

    @Test
    public void testDecreaseItem() {
        CartItem itemOne = new CartItem(new Item());

        itemOne.incrementCountByOne();
        itemOne.incrementCountByOne();
        itemOne.incrementCountByOne();
        itemOne.decrementCountByOne();

        assertEquals(itemOne.getCount(), 2);
    }

    @Test
    public void testSetCountItem() {
        CartItem itemOne = new CartItem(new Item());

        itemOne.setCount(35);

        assertEquals(itemOne.getCount(), 35);
    }

    @Test
    public void testModifyItem() {
        Item butter = new Item();
        butter.setName("Butter");
        butter.setDescription("A dairy product with high butterfat content which is solid when chilled and at room temperature in some regions, and liquid when warmed.");
        butter.setCost(4);

        CartItem itemOne = new CartItem(new Item());

        itemOne.setItem(butter);

        assertEquals(itemOne.getItem(), butter);
    }

    @Test
    public void testHashReflexItem() {
        Item butter = new Item();
        butter.setName("Butter");
        butter.setDescription("A dairy product with high butterfat content which is solid when chilled and at room temperature in some regions, and liquid when warmed.");
        butter.setCost(4);

        CartItem itemOne = new CartItem(new Item());

        itemOne.setItem(butter);

        assertEquals(itemOne.hashCode(), butter.hashCode());
    }

    @Test
    public void testNullEqualsCartItem() {
        Item butter = new Item();
        butter.setName("Butter");
        butter.setDescription("A dairy product with high butterfat content which is solid when chilled and at room temperature in some regions, and liquid when warmed.");
        butter.setCost(4);

        CartItem itemOne = new CartItem(new Item());

        itemOne.setItem(butter);

        assertFalse(itemOne.equals(null));
    }

    @Test
    public void testEqualsReflexCartItem() {
        Item butter = new Item();
        butter.setName("Butter");
        butter.setDescription("A dairy product with high butterfat content which is solid when chilled and at room temperature in some regions, and liquid when warmed.");
        butter.setCost(4);

        CartItem itemOne = new CartItem(new Item());

        itemOne.setItem(butter);

        assertTrue(itemOne.equals(itemOne));
    }

    @Test
    public void testUnequalsCartItem() {
        Item butter = new Item();
        butter.setName("Butter");
        butter.setDescription("A dairy product with high butterfat content which is solid when chilled and at room temperature in some regions, and liquid when warmed.");
        butter.setCost(4);

        CartItem itemOne = new CartItem(new Item());

        itemOne.setItem(butter);

        CartItem itemTwo = new CartItem(new Item());

        assertFalse(itemOne.equals(itemTwo));
	}

    public void unequalHashTestForCartItem() {
        Item berry = new Item();
        berry.setName("Berries");
        berry.setDescription("A berry is a small, pulpy, and often edible fruit.");
        berry.setCost(3);
        CartItem berries = new CartItem(berry);

        Item coffee = new Item();
        coffee.setName("Coffee");
        coffee.setDescription("A brewed drink prepared from roasted coffee beans, the seeds of berries from certain Coffea species.");
        coffee.setCost(7);
        CartItem cupsOfCoffee = new CartItem(coffee);

        assertNotEquals(berries.hashCode(), cupsOfCoffee.hashCode());

    }

    @Test
    public void equalHashTestForCartItem() {
        Item berry = new Item();
        berry.setName("Berries");
        berry.setDescription("A berry is a small, pulpy, and often edible fruit.");
        berry.setCost(3);
        CartItem berries = new CartItem(berry);

        assertEquals(berries.hashCode(), berries.hashCode());

    }

    @Test
    public void decrementTest() {

        // create item and set count
        CartItem itemOne = new CartItem(new Item());
        itemOne.setCount(5);
        itemOne.decrementCountByOne();

        // check count
        assertEquals(itemOne.getCount(), 4);
    }

}
