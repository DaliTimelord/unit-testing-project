import csc4700.Backup;
import csc4700.Item;
import csc4700.ShoppingCart;
import csc4700.CartItem;
import csc4700.exceptions.SerializedFormatException;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.*;
import java.text.DateFormat;
import java.util.List;

import static org.junit.Assert.*;

public class BackupTest {

    private String filename;
    private Backup test;
    private File tempFile;

    @Before
    public void createTempFile() throws IOException {
        tempFile = File.createTempFile("BackupDemoTests","");
        this.filename = tempFile.getAbsolutePath();

        BufferedWriter bw = new BufferedWriter(new FileWriter(this.filename));
        bw.write("Test Data");
        bw.close();
    }

    @Test(expected = NullPointerException.class)
    public void nullSerializeShoppingCartInputTest() {
        test = new Backup();
        String result = test.serializeShoppingCart(null);
    }

    @Test
    public void emptyCartTest() {
        test = new Backup();
        ShoppingCart cart = new ShoppingCart();
        assertEquals(test.serializeShoppingCart(cart), "");
    }

    @Test
    public void serializeCart() {
        test = new Backup();
        ShoppingCart cart = new ShoppingCart();

        // create butter item
        Item butter = new Item();
        butter.setName("Butter");
        butter.setDescription("A dairy product with high butterfat content which is solid when chilled and at room temperature in some regions, and liquid when warmed.");
        butter.setCost(4);
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

        // generate cart String
        String result = test.serializeShoppingCart(cart);

        // save expected String
        String expectedResult = "Butter,4,A dairy product with high butterfat content which is solid when chilled and at room temperature in some regions, and liquid when warmed.,1" + Backup.LINE_SEPARATOR + "Soda,2,A drink that typically contains carbonated water, a sweetener, and a natural or artificial flavoring.,1" + Backup.LINE_SEPARATOR + "Lettuce,5,A leafy herbaceous annual or biennial plant in the family Asteraceae grown for its leaves which are used as a salad green.,1" + Backup.LINE_SEPARATOR;

        // compare cart String and expected String
        assertEquals(result, expectedResult);
    }

    @Test
    public void nullStringTest() {

        test = new Backup();

        try {
            test.deserializeShoppingCart(null);
        } catch (NullPointerException | SerializedFormatException e) {
            // Expected
        }
    }

    @Test
    public void badFileNameTest() throws IOException {

        // set up test file
        BufferedWriter bw = new BufferedWriter(new FileWriter(this.filename));
        bw.write("Test Data");

        // create cart
        ShoppingCart cart = new ShoppingCart();

        // create butter item
        Item butter = new Item();
        butter.setName("Butter");
        butter.setDescription("A dairy product with high butterfat content which is solid when chilled and at room temperature in some regions, and liquid when warmed.");
        butter.setCost(4);
        cart.addItem(butter);

        // test backup
        test = new Backup();
        test.saveShoppingCart(cart, tempFile);

        // compare file with original text
        BufferedReader br = new BufferedReader(new FileReader(tempFile));

        assertNotEquals(br.readLine(), "Test Data");
        br.close();
        bw.close();

    }

    @Test
    public void shoppingCartNotFound() {

        test = new Backup();

        try {
            test.loadShoppingCart(new File("somefile.txt"));
        } catch (IOException | SerializedFormatException e) {
            // Expected
        }
    }

    @Test
    public void deserializeTest() {

        // serialized string
        String cartString = "Butter" + Backup.FIELD_SEPARATOR + "4" + Backup.FIELD_SEPARATOR +
                "A dairy product with high butterfat content which is solid" + Backup.FIELD_SEPARATOR + "1" + Backup.LINE_SEPARATOR
                + "Soda" + Backup.FIELD_SEPARATOR + "2" + Backup.FIELD_SEPARATOR + "A drink that typically contains carbonated water " +
                "and a natural or artificial flavoring" + Backup.FIELD_SEPARATOR + "2" + Backup.LINE_SEPARATOR;

        // run method
        test = new Backup();
        ShoppingCart cart = null;

        try {
            cart = test.deserializeShoppingCart(cartString);
        } catch (SerializedFormatException e) {
            // not supposed to happen
        }

        // create shopping cart to test deserialized method input
        ShoppingCart demo = new ShoppingCart();

        // create butter item
        Item butter = new Item();
        butter.setName("Butter");
        butter.setDescription("A dairy product with high butterfat content which is solid");
        butter.setCost(4);
        demo.addItem(butter);

        // create soda item
        Item soda = new Item();
        soda.setName("Soda");
        soda.setDescription("A drink that typically contains carbonated water and a natural or artificial flavoring");
        soda.setCost(2);
        demo.addItem(soda);
        demo.findCartItem(soda).setCount(2);

        // go through carts to match items
        List<CartItem> demoList = demo.getCartItems();
        List<CartItem> cartList = cart.getCartItems();

        if (demoList.size() != cartList.size()) {
            fail("The carts are of different sizes");
        }


        for (int i = 0; i < demoList.size() && i < cartList.size(); i++) {

            if (demoList.get(i).hashCode() != cartList.get(i).hashCode()) {
                fail("Repeated items");
            }

            if (demoList.get(i).getCount() != cartList.get(i).getCount()) {
                fail("Not the same item quantity");
            }

            if (!demoList.get(i).getItem().getDescription().equals(cartList.get(i).getItem().getDescription())) {
                fail("Not the same description");
            }

            if (demoList.get(i).getItem().getCost() != demoList.get(i).getItem().getCost()) {
                fail("Not the same cost");
            }

        }

    }

    @After
    public void deleteTempFile() {
        File f = new File(this.filename);
        f.delete();
    }

}
