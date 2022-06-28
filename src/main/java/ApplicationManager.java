public class ApplicationManager {

    private HelperUserLogin userLogin;
    private HelperUserReg userReg;

    public ApplicationManager() {
        this.userLogin = new HelperUserLogin();
        this.userReg = new HelperUserReg();
    }

    public HelperUserLogin getUserLogin() {
        return userLogin;
    }

    public HelperUserReg getUserReg() {
        return userReg;
    }
}
