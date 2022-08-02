import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;


public class SearchTests extends BasicTest {

    @BeforeMethod(alwaysRun = true)
    public void startNewSearch() {
        carSearch.newSearch();
    }

    @Test(priority = 1, groups = {"web"})
    public void searchCurrentMonth() throws InterruptedException {
        carSearch.searchCar("Tel Aviv", "9/28/2022", "9/30/2022");
        carSearch.submit();
        Assert.assertTrue(carSearch.isCarListPresent());
    }

    @Test(priority = 2)
    public void searchCurrentMonthOpposite() throws InterruptedException {
        carSearch.searchCar("Haifa", "9/30/2022", "9/28/2022");
        carSearch.submit();
        Assert.assertTrue(carSearch.isCarListPresent());
    }

    @Test(priority = 3)
    public void searchNextMonth() throws InterruptedException {
        carSearch.searchCar("Tel Aviv", "10/03/2022", "10/30/2022");
        carSearch.submit();
        Assert.assertTrue(carSearch.isCarListPresent());
    }

    @Test(priority = 4)
    public void searchThisYear() throws InterruptedException {
        carSearch.searchCar("Tel Aviv", "11/05/2022", "12/26/2022");
        carSearch.submit();
        Assert.assertTrue(carSearch.isCarListPresent());
        Assert.assertTrue(carSearch.ifPositiveResultAppered());
    }

    @Test(priority = 5)
    public void searchAny() throws InterruptedException {
        carSearch.searchCar("Haifa", "10/05/2022", "05/25/2023");
        carSearch.submit();
        Assert.assertTrue(carSearch.isCarListPresent());
        Assert.assertTrue(carSearch.ifPositiveResultAppered());
    }

    @Test(priority = 6, groups = {"web"})
    public void searchInThePast() throws InterruptedException {
        carSearch.searchCarInThePast("Haifa", "02/02/2022", "02/26/2022");
        Assert.assertTrue(carSearch.earlyPickUpProhibitedText());
        Assert.assertTrue(carSearch.checkIfYallaInactive());
    }
}
