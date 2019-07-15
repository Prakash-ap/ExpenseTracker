package Model;

public class In_Chart_Catg {

    private int id;
    private String in_chartcat_type;
    private String on_chart_amount;

    public In_Chart_Catg(int id, String in_chartcat_type, String on_chart_amount) {
        this.id = id;
        this.in_chartcat_type = in_chartcat_type;
        this.on_chart_amount = on_chart_amount;
    }

    public In_Chart_Catg() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getIn_chartcat_type() {
        return in_chartcat_type;
    }

    public void setIn_chartcat_type(String in_chartcat_type) {
        this.in_chartcat_type = in_chartcat_type;
    }

    public String getOn_chart_amount() {
        return on_chart_amount;
    }

    public void setOn_chart_amount(String on_chart_amount) {
        this.on_chart_amount = on_chart_amount;
    }
}
