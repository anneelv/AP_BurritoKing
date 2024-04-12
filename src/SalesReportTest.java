import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class SalesReportTest {
    private SalesReport sales;

    @Before
    public void setUp() throws Exception {
        sales = new SalesReport();
    }

    @Test
    public void testSetFoodQuantityWhenEmpty() {
        sales.setFoodQuantity("Burrito",28);
        assertEquals(28, sales.getFoodQuantity("Burrito"));
    }

    @Test
    public void testSetFoodQuantityWhenNotEmpty() {
        sales.setFoodQuantity("Burrito",28);
        sales.setFoodQuantity("Burrito",12);
        assertEquals(40, sales.getFoodQuantity("Burrito"));
    }

    @Test
    public void testSetFoodPriceWhenEmpty() {
        sales.setFoodPrice("Burrito", 27);
        assertEquals(27, sales.getFoodPrice("Burrito"), 0.01);
    }

    @Test
    public void testSetFoodPriceWhenNotEmpty() {

        sales.setFoodPrice("Burrito", 25);
        sales.setFoodPrice("Burrito", 26.5);
        assertEquals(51.5, sales.getFoodPrice("Burrito"), 0.01);
    }

    @Test
    public void testRecalculateTotalSalesForNewBurritoPrice() {

    }

    @Test
    public void testRecalculateTotalSalesWhenTheresSalesAlready() {

    }

    @After
    public void tearDown() throws Exception {
        sales.setFoodPrice("Burrito", 0);
        sales.setFoodQuantity("Burrito",0);
    }
}