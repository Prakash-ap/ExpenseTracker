package Model;

public class Ex_Chart_Catg {

    private int id;
    private String ex_chartcat_type;
    private String ex_chart_amount;

    public Ex_Chart_Catg(int id, String ex_chartcat_type, String ex_chart_amount) {
        this.id = id;
        this.ex_chartcat_type = ex_chartcat_type;
        this.ex_chart_amount = ex_chart_amount;
    }

    public Ex_Chart_Catg() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getEx_chartcat_type() {
        return ex_chartcat_type;
    }

    public void setEx_chartcat_type(String ex_chartcat_type) {
        this.ex_chartcat_type = ex_chartcat_type;
    }

    public String getEx_chart_amount() {
        return ex_chart_amount;
    }

    public void setEx_chart_amount(String ex_chart_amount) {
        this.ex_chart_amount = ex_chart_amount;
    }
}


