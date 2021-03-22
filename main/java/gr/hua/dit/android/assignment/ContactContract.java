package gr.hua.dit.android.assignment;

public class ContactContract {
    private int id;
    // private int id;
    private String userid;
    private float longitude;
    private float latitude;
    private static String dt;

    public ContactContract(){}

    public ContactContract(int id,String userid, float longitude, float latitude, String dt) {
        this.id = id;
        this.userid = userid;
        this.longitude = longitude;
        this.latitude = latitude;
        this.dt = dt;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUserid() {
        return userid;
    }

    public void setUserid(String userid) {
        this.userid = userid;
    }

    public float getLongitude() {
        return longitude;
    }

    public void setLongitude(float longitude) {
        this.longitude = longitude;
    }

    public float getLatitude() {
        return latitude;
    }

    public void setLatitude(float latitude) {
        this.latitude = latitude;
    }

    public static String getDt() {
        return dt;
    }

    public void setDt(String dt) {
        this.dt = dt;
    }
}
