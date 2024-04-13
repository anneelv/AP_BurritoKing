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
    public void setUp() throws Exception {
        sales = new SalesReport();
    }

    @Test
    public void testSetFoodQuantityWhenEmpty() {
        sales.setFoodQuantity("MainProgram.Burrito",28);
        Assert.assertEquals(28, sales.getFoodQuantity("MainProgram.Burrito"));
    }

    @Test
    public void testSetFoodQuantityWhenNotEmpty() {
        sales.setFoodQuantity("MainProgram.Burrito",28);
        sales.setFoodQuantity("MainProgram.Burrito",12);
        Assert.assertEquals(40, sales.getFoodQuantity("MainProgram.Burrito"));
    }

    @Test
    public void testSetFoodPriceWhenEmpty() {
        sales.setFoodPrice("MainProgram.Burrito", 27);
        Assert.assertEquals(27, sales.getFoodPrice("MainProgram.Burrito"), 0.01);
    }

    @Test
    public void testSetFoodPriceWhenNotEmpty() {

        sales.setFoodPrice("MainProgram.Burrito", 25);
        sales.setFoodPrice("MainProgram.Burrito", 26.5);
        Assert.assertEquals(51.5, sales.getFoodPrice("MainProgram.Burrito"), 0.01);
    }

    @Test
    public void testRecalculateTotalSalesForNewBurritoPrice() {
        sales.setFoodQuantity("MainProgram.Burrito", 3);
        sales.setFoodPrice("MainProgram.Burrito", 7);
        sales.setTotalSales(21);
        sales.recalculateTotalSales("MainProgram.Burrito", 5);

        Assert.assertEquals(15, sales.getTotalSales(), 0.01);

    }

    @Test
    public void testRecalculateTotalSalesWhenTheresSalesAlready() {
        sales.setFoodQuantity("MainProgram.Burrito", 3);
        sales.setFoodPrice("MainProgram.Burrito", 21);
        sales.setFoodQuantity("MainProgram.Fries", 3);
        sales.setFoodPrice("MainProgram.Fries", 12);
        sales.setFoodQuantity("MainProgram.Soda", 3);
        sales.setFoodPrice("MainProgram.Soda", 7.5);
        sales.setTotalSales(40.5);
        sales.recalculateTotalSales("MainProgram.Burrito", 5);

        Assert.assertEquals(34.5, sales.getTotalSales(), 0.01);
    }

    @Test
    public void testRecalculateTotalSalesWhenTheresMeals() {
        sales.setFoodQuantity("MainProgram.Burrito", 3);
        sales.setFoodPrice("MainProgram.Burrito", 21);
        sales.setFoodQuantity("MainProgram.Fries", 3);
        sales.setFoodPrice("MainProgram.Fries", 12);
        sales.setFoodQuantity("MainProgram.Soda", 3);
        sales.setFoodPrice("MainProgram.Soda", 7.5);
        sales.setTotalMeals(2);
        sales.setTotalSales(34.5);
        sales.recalculateTotalSales("MainProgram.Burrito", 5);

        Assert.assertEquals(28.5, sales.getTotalSales(), 0.01);
    }

    @After
    public void tearDown() throws Exception {
        sales.setFoodPrice("MainProgram.Burrito", 0);
        sales.setFoodQuantity("MainProgram.Burrito",0);
        sales.setTotalMeals(0);
    }
}