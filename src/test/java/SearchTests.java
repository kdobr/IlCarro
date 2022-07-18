import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;


public class SearchTests extends BasicTest {

    @AfterMethod
    public void startNewSearch() throws InterruptedException {
        Thread.sleep(2000);
        carSearch.newSearch();
    }


    @Test
    public void searchCurrentMonth() throws InterruptedException{
        carSearch.searchCurrentMonth("Tel Aviv", "7/25/2022", "7/30/2022");
        carSearch.submit();
        //  Assert.assertTrue(carSearch.isListOfCarsAppeared());
    }

    @Test
    public void searchCurrentMonthOpposite() throws InterruptedException {
        carSearch.searchCurrentMonth("Tel Aviv", "7/26/2022", "7/23/2022");
        carSearch.submit();
        //  Assert.assertTrue(carSearch.isListOfCarsAppeared());
    }

    @Test (enabled=false)
    public void searchNextMonth() throws InterruptedException {
        carSearch.searchCurrentMonth("Tel Aviv", "8/03/2022", "08/30/2022");
        carSearch.submit();
        //  Assert.assertTrue(carSearch.isListOfCarsAppeared());
    }

    @Test
    public void searchNextMonthPlus2() throws InterruptedException {
        carSearch.searchCurrentMonth("Tel Aviv", "8/05/2022", "10/26/2022");
        carSearch.submit();
        //  Assert.assertTrue(carSearch.isListOfCarsAppeared());
    }
}
