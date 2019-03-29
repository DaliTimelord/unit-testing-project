import csc4700.CartItem;
import csc4700.Item;
import csc4700.ShoppingCart;
import java.util.List;
import org.junit.Test;
import static org.junit.Assert.*;

public class ShoppingCartTest {

    @Test
    public void nullShoppingCart() {
        ShoppingCart cart = new ShoppingCart();
        List<CartItem> cartList = cart.getCartItems();
        assertEquals(cartList.size(), 0);
    }

    @Test
    public void checkCartSize() {
        ShoppingCart cart = new ShoppingCart();

        // create array of products -- match individual Item objects in array
        Item[] products = new Item[3];

        // create butter item
        Item butter = new Item();
        butter.setName("Butter");
        butter.setDescription("A dairy product with high butterfat content which is solid when chilled and at room temperature in some regions, and liquid when warmed.");
        butter.setCost(3);
        cart.addItem(butter);

        // create soda item
        Item soda = new Item();
        soda.setName("Soda");
        soda.setDescription("A drink that typically contains carbonated water, a sweetener, and a natural or artificial flavoring.");
        soda.setCost(2);
        cart.addItem(soda);
        products[1] = soda;

        // create lettuce
        Item lettuce = new Item();
        lettuce.setName("Lettuce");
        lettuce.setDescription("A leafy herbaceous annual or biennial plant in the family Asteraceae grown for its leaves which are used as a salad green.");
        lettuce.setCost(5);
        cart.addItem(lettuce);
        products[2] = lettuce;

        // check size of list versus array
        List<CartItem> cartList = cart.getCartItems();
        assertEquals(cartList.size(), products.length);
    }

    @Test
    public void findCartItem() {
        ShoppingCart cart = new ShoppingCart();

        // create array of products -- match individual Item objects in array

        // create butter item
        Item butter = new Item();
        butter.setName("Butter");
        butter.setDescription("A dairy product with high butterfat content which is solid when chilled and at room temperature in some regions, and liquid when warmed.");
        butter.setCost(3);
        cart.addItem(butter);

        // create soda item
        Item soda = new Item();
        soda.setName("Soda");
        soda.setDescription("A drink that typically contains carbonated water, a sweetener, and a natural or artificial flavoring.");
        soda.setCost(2);
        cart.addItem(soda);

        // create lettuce
        Item lettuce = new Item();
        lettuce.setName("Lettuce");
        lettuce.setDescription("A leafy herbaceous annual or biennial plant in the family Asteraceae grown for its leaves which are used as a salad green.");
        lettuce.setCost(5);
        cart.addItem(lettuce);

        // check size of list versus array
        assertEquals(cart.findCartItem(lettuce).getItem().hashCode(), lettuce.hashCode());
    }


    @Test
    public void testRemoveItem() {
        ShoppingCart cart = new ShoppingCart();

        // create lettuce
        Item lettuce = new Item();
        lettuce.setName("Lettuce");
        lettuce.setDescription("A leafy herbaceous annual or biennial plant in the family Asteraceae grown for its leaves which are used as a salad green.");
        lettuce.setCost(5);
        cart.addItem(lettuce);
        cart.addItem(lettuce);

        cart.deleteItem(lettuce);
        assertEquals(cart.findCartItem(lettuce).getItem().hashCode(), lettuce.hashCode());
    }

    @Test
    public void testRemoveLastItem() {
        ShoppingCart cart = new ShoppingCart();

        // create lettuce
        Item lettuce = new Item();
        lettuce.setName("Lettuce");
        lettuce.setDescription("A leafy herbaceous annual or biennial plant in the family Asteraceae grown for its leaves which are used as a salad green.");
        lettuce.setCost(5);
        cart.addItem(lettuce);

        cart.deleteItem(lettuce);
        assertEquals(cart.findCartItem(lettuce), null);
    }


    @Test
    public void removeNullItem() {
        ShoppingCart cart = new ShoppingCart();

        // create lettuce
        Item lettuce = new Item();
        lettuce.setName("Lettuce");
        lettuce.setDescription("A leafy herbaceous annual or biennial plant in the family Asteraceae grown for its leaves which are used as a salad green.");
        lettuce.setCost(5);
        cart.addItem(lettuce);

        try {
            cart.deleteItem(null);
        } catch (NullPointerException e) {
            // Expected
        }
    }

    @Test
    public void removeEmptyItem() {
        ShoppingCart cart = new ShoppingCart();

        // create lettuce
        Item lettuce = new Item();
        lettuce.setName("Lettuce");
        lettuce.setDescription("A leafy herbaceous annual or biennial plant in the family Asteraceae grown for its leaves which are used as a salad green.");
        lettuce.setCost(5);

        cart.deleteItem(lettuce);

        assertNull(cart.findCartItem(lettuce));

    }
}
