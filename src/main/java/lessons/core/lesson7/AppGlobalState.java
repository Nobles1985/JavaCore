package lessons.core.lesson7;

public final class AppGlobalState {

    private static AppGlobalState INSTANCE = new AppGlobalState();
    private String selectCity = null;
    private final String API_KEY = "8eff93e2-ccd1-44ce-87bd-4ef1c5e531ce";

    private AppGlobalState(){}

    public static AppGlobalState getInstance() {
        return INSTANCE;
    }

    public void setSelectCity(String selectCity) {
        this.selectCity = selectCity;
    }

    public String getSelectCity() {
        return selectCity;
    }

    public String getApiKey() {
        return API_KEY;
    }
}
