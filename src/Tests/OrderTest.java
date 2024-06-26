package Tests;

import org.junit.*;
import MainProgram.*;
import static org.junit.Assert.*;

/*The OrderTest Class is a JUnit Test for Order Class methods*/

public class OrderTest{

    private SalesReport sales;
    private Validation validation;
    private Order order;

    @Before
    public void setUp() throws Exception {
        sales = new SalesReport();
        validation = new Validation();
        order = new Order(sales, validation);
    }

    @Test
    public void FriesCookingTimeWithoutRemaining(){
        order.setRemainingFriesQuantity(0);
        Assert.assertEquals(8,order.friesCookingTime(3, 5,8));
        Assert.assertEquals(0,order.friesCookingTime(0, 5,8));
    }

    @Test
    public void FriesCookingTimeWithRemaining() {
        order.setRemainingFriesQuantity(5);
        Assert.assertEquals(8,order.friesCookingTime(7, 5,8));
    }

    @Test
    public void BurritoCookingTimeWithVarietyOfQuantity() {
        Assert.assertEquals(9, order.burritoCookingTime(2,2,9));
        Assert.assertEquals(27, order.burritoCookingTime(5,2,9));
        Assert.assertEquals(0, order.burritoCookingTime(0,2,9));
    }

    @Test
    public void waitingTimeWithMoreBurrito() {
        Assert.assertEquals(18, order.calculateWaitingTime(18,8));
    }

    @Test
    public void waitingTimeWithMoreFries() {
        Assert.assertEquals(24, order.calculateWaitingTime(18,24));
    }

    @Test
    public void NoChangeReturnedWhenTheMoneyIsNotEnough() {
        order.setTotalPrice(23);
        assertFalse(order.moneyChange(20));
    }

    @Test
    public void NoChangeWithExactMoney() {
        order.setTotalPrice(23);
        assertTrue(order.moneyChange(23));
    }

    @Test
    public void ChangeReturnedWithMoneyOver() {
        order.setTotalPrice(23);
        assertTrue(order.moneyChange(26));
    }

    @Test
    public void TotalPriceOfAMealSetTest() {
        order.setTotalPrice(0);
        order.orderMeal(2);
        Assert.assertEquals(21.0, order.getTotalPrice(), 0.01);
    }

    @Test
    public void TotalPriceFor3Burrito2Fries1SodaAnd1Meal() {
        Burrito burrito = new Burrito("MainProgram.Burrito");
        Fries fries = new Fries("MainProgram.Fries");
        Soda soda = new Soda("MainProgram.Soda");

        order.setTotalPrice(0);
        burrito.setOrderQuantity(burrito.getOrderQuantity() + 3);
        order.addMenuToArray(burrito, 3);
        burrito.setOrderQuantity(fries.getOrderQuantity() + 2);
        order.addMenuToArray(fries, 2);
        burrito.setOrderQuantity(soda.getOrderQuantity() + 1);
        order.addMenuToArray(soda, 1);
        order.orderMeal(1);

        Assert.assertEquals(42,order.getTotalPrice(), 0.01);
    }

}