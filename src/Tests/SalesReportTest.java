package Tests;

import MainProgram.SalesReport;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/*The SalesReportTest Class is a JUnit Test for SalesReport Class methods*/

public class SalesReportTest {
    private SalesReport sales;

    @Before
    public void setUp() {
        sales = new SalesReport();
    }

    @Test
    public void testSetFoodQuantityWhenEmpty() {
        sales.setFoodQuantity("Burrito",28);
        Assert.assertEquals(28, sales.getFoodQuantity("Burrito"));
    }

    @Test
    public void testSetFoodQuantityWhenNotEmpty() {
        sales.setFoodQuantity("Burrito",28);
        sales.setFoodQuantity("Burrito",12);
        Assert.assertEquals(40, sales.getFoodQuantity("Burrito"));
    }

    @Test
    public void testSetFoodPriceWhenEmpty() {
        sales.setFoodPrice("Burrito", 27);
        Assert.assertEquals(27, sales.getFoodPrice("Burrito"), 0.01);
    }

    @Test
    public void testSetFoodPriceWhenNotEmpty() {

        sales.setFoodPrice("Burrito", 25);
        sales.setFoodPrice("Burrito", 26.5);
        Assert.assertEquals(51.5, sales.getFoodPrice("Burrito"), 0.01);
    }

    @Test
    public void testRecalculateTotalSalesForNewBurritoPrice() {
        sales.setFoodQuantity("Burrito", 3);
        sales.setFoodPrice("Burrito", 7);
        sales.setTotalSales(21);
        sales.recalculateTotalSales("Burrito", 5);

        Assert.assertEquals(15, sales.getTotalSales(), 0.01);

    }

    @Test
    public void testRecalculateTotalSalesWhenTheresSalesAlready() {
        sales.setFoodQuantity("Burrito", 3);
        sales.setFoodPrice("Burrito", 21);
        sales.setFoodQuantity("Fries", 3);
        sales.setFoodPrice("Fries", 12);
        sales.setFoodQuantity("Soda", 3);
        sales.setFoodPrice("Soda", 7.5);
        sales.setTotalSales(40.5);
        sales.recalculateTotalSales("Burrito", 5);

        Assert.assertEquals(34.5, sales.getTotalSales(), 0.01);
    }

    @Test
    public void testRecalculateTotalSalesWhenTheresMeals() {
        sales.setFoodQuantity("Burrito", 3);
        sales.setFoodPrice("Burrito", 21);
        sales.setFoodQuantity("Fries", 3);
        sales.setFoodPrice("Fries", 12);
        sales.setFoodQuantity("Soda", 3);
        sales.setFoodPrice("Soda", 7.5);
        sales.setTotalMeals(2);
        sales.setTotalSales(34.5);
        sales.recalculateTotalSales("Burrito", 5);

        Assert.assertEquals(28.5, sales.getTotalSales(), 0.01);
    }

    @After
    public void tearDown() {
        sales.setFoodPrice("Burrito", 0);
        sales.setFoodQuantity("Burrito",0);
        sales.setTotalMeals(0);
    }
}