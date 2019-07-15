package Model;

public class WeekModel {
    private String date;
    private String weektotalincome;
    private String weektotalexpense;

    public  WeekModel(){

    }


    public WeekModel(String date, String weektotalincome, String weektotalexpense) {
        this.date = date;
        this.weektotalincome = weektotalincome;
        this.weektotalexpense = weektotalexpense;
    }

    public String getWeektotalincome() {
        return weektotalincome;
    }

    public void setWeektotalincome(String weektotalincome) {
        this.weektotalincome = weektotalincome;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getWeektotalexpense() {
        return weektotalexpense;
    }

    public void setWeektotalexpense(String weektotalexpense) {
        this.weektotalexpense = weektotalexpense;
    }
}
