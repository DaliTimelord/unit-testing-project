import csc4700.Backup;
import csc4700.Item;
import csc4700.ShoppingCart;
import csc4700.exceptions.SerializedFormatException;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.*;

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

    @After
    public void deleteTempFile() {
        File f = new File(this.filename);
        f.delete();
    }

}
