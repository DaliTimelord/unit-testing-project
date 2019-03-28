package csc4700;

import csc4700.exceptions.SerializedFormatException;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.*;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import static junit.framework.TestCase.assertEquals;

public class BackupTest {

    private Backup backupUnderTest;

    @BeforeEach
    public void setUp() {
        backupUnderTest = new Backup();
    }

    @Test
    public void testSerializeShoppingCart() {
        // Setup
        final ShoppingCart cart = null;
        final String expectedResult = "Test result 01";

        // Running the actual test
        final String result = backupUnderTest.serializeShoppingCart(cart);

        // The assertion
        assertEquals(expectedResult, result);
    }

    @Test
    public void testDeserializeShoppingCart() throws Exception {
        // Setup
        final String s = "String test data";
        final ShoppingCart expectedResult = null;

        // The testing
        final ShoppingCart result = backupUnderTest.deserializeShoppingCart(s);

        // The assertion
        assertEquals(expectedResult, result);
    }

    @Test
    public void testDeserializeShoppingCart_ThrowsSerializedFormatException() throws Exception {
        // Setup
        final String s = "s";
        final ShoppingCart expectedResult = null;

        // The testing
        assertThrows(SerializedFormatException.class, () -> {
            backupUnderTest.deserializeShoppingCart(s);
        });
    }

    @Test
    public void testSaveShoppingCart() throws Exception {
        // Setup
        final ShoppingCart saveMe = null;
        final File location = null;

        // Run the test
        backupUnderTest.saveShoppingCart(saveMe, location);
    }

    @Test
    public void testSaveShoppingCart_ThrowsIOException() throws Exception {
        // Setup
        final ShoppingCart saveMe = null;
        final File location = null;

        // The testing
        assertThrows(IOException.class, () -> {
            backupUnderTest.saveShoppingCart(saveMe, location);
        });
    }

    @Test
    public void testLoadShoppingCart() throws Exception {
        // Setup
        final File location = null;
        final ShoppingCart expectedResult = null;

        // The testing
        final ShoppingCart result = backupUnderTest.loadShoppingCart(location);

        // The assertion
        assertEquals(expectedResult, result);
    }

    @Test
    public void testLoadShoppingCart_ThrowsIOException() throws Exception {
        // Setup
        final File location = null;
        final ShoppingCart expectedResult = null;

        // The testing
        assertThrows(IOException.class, () -> {
            backupUnderTest.loadShoppingCart(location);
        });
    }

    @Test
    public void testLoadShoppingCart_ThrowsSerializedFormatException() throws Exception {
        // Setup
        final File location = null;
        final ShoppingCart expectedResult = null;

        // The testing
        assertThrows(SerializedFormatException.class, () -> {
            backupUnderTest.loadShoppingCart(location);
        });
    }

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
        Assert.assertEquals(test.serializeShoppingCart(cart), "");
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
        Assert.assertEquals(result, expectedResult);
    }

    @After
    public void deleteTempFile() {
        File f = new File(this.filename);
        f.delete();
    }
}
