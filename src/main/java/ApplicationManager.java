

public class ApplicationManager {

    private final HelperSearch search;
    private final HelperBase userBase;
    private final HelperUserLogin userLogin;
    private final HelperUserReg userReg;
    private final HelperUserCar userCar;


    public ApplicationManager() {
        userLogin = new HelperUserLogin();
        userReg = new HelperUserReg();
        userBase = new HelperBase();
        userCar = new HelperUserCar();
        search = new HelperSearch();
    }

    public HelperUserLogin getUserLogin() {
        return userLogin;
    }

    public HelperUserReg getUserReg() {
        return userReg;
    }

    public HelperBase getUserBase() {
        return userBase;
    }

    public HelperUserCar getUserCar() {
        return userCar;
    }

    public HelperSearch getSearch() {
        return search;
    }
}
