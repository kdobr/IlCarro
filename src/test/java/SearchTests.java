import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;


public class SearchTests extends BasicTest {

    @BeforeMethod
    public void startNewSearch() {
        carSearch.newSearch();
    }

    @Test(priority = 1)
    public void searchCurrentMonth() throws InterruptedException {
        carSearch.searchCar("Tel Aviv", "7/27/2022", "7/30/2022");
        carSearch.submit();
        Assert.assertTrue(carSearch.isCarListPresent());
    }

    @Test(priority = 2)
    public void searchCurrentMonthOpposite() throws InterruptedException {
        carSearch.searchCar("Haifa", "7/30/2022", "7/28/2022");
        carSearch.submit();
        Assert.assertTrue(carSearch.isCarListPresent());
    }

    @Test(priority = 3)
    public void searchNextMonth() throws InterruptedException {
        carSearch.searchCar("Tel Aviv", "8/03/2022", "08/30/2022");
        carSearch.submit();
        Assert.assertTrue(carSearch.isCarListPresent());
    }

    @Test(priority = 4)
    public void searchThisYear() throws InterruptedException {
        carSearch.searchCar("Tel Aviv", "8/05/2022", "10/26/2022");
        carSearch.submit();
        Assert.assertTrue(carSearch.isCarListPresent());
        Assert.assertTrue(carSearch.ifPositiveResultAppered());
    }

    @Test(priority = 5)
    public void searchAny() throws InterruptedException {
        carSearch.searchCar("Haifa", "8/05/2022", "02/26/2023");
        carSearch.submit();
        Assert.assertTrue(carSearch.isCarListPresent());
        Assert.assertTrue(carSearch.ifPositiveResultAppered());
    }

    @Test(priority = 6)
    public void searchInThePast() throws InterruptedException {
        carSearch.searchCarInThePast("Haifa", "02/02/2022", "02/26/2022");
        Assert.assertTrue(carSearch.earlyPickUpProhibitedText());
        Assert.assertTrue(carSearch.checkIfYallaInactive());
    }
}
