import csc4700.Backup;
import csc4700.Item;
import csc4700.ShoppingCart;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import static org.junit.Assert.*;

public class BackupTest {

    private String filename;
    private Backup test;

    @Before
    public void createTempFile() throws IOException {
        File tempFile = File.createTempFile("BackupDemoTests","");
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
        String expectedResult = "Butter,4,A dairy product with high butterfat content which is solid when chilled and at room temperature in some regions, and liquid when warmed.,1\r\nSoda,2,A drink that typically contains carbonated water, a sweetener, and a natural or artificial flavoring.,1\r\nLettuce,5,A leafy herbaceous annual or biennial plant in the family Asteraceae grown for its leaves which are used as a salad green.,1\r\n";

        // compare cart String and expected String
        assertEquals(result, expectedResult);
    }

    @After
    public void deleteTempFile() {
        File f = new File(this.filename);
        f.delete();
    }

}
