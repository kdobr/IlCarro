public class ApplicationManager {

    private HelperBase userBase;
    private HelperUserLogin userLogin;
    private HelperUserReg userReg;

    public ApplicationManager() {
        this.userLogin = new HelperUserLogin();
        this.userReg = new HelperUserReg();
        this.userBase = new HelperBase();
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
}
