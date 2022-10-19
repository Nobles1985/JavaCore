package lessons.core.lesson8;

public final class AppGlobalState {

    private static AppGlobalState INSTANCE = new AppGlobalState();
    private String selectCity = null;
    private final String API_KEY = "8eff93e2-ccd1-44ce-87bd-4ef1c5e531ce";
    private final String fileDb = "sqlbase.db";

    private AppGlobalState(){}

    public static AppGlobalState getInstance() {
        return INSTANCE;
    }

    public String getFileDb() {
        return fileDb;
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
