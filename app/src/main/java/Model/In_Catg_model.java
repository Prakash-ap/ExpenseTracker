package Model;

public class In_Catg_model {
    private int id;
    private String in_cat_type;
    private String in_cat_amount;

    public In_Catg_model(int id, String in_cat_type, String in_cat_amount) {
        this.id = id;
        this.in_cat_type = in_cat_type;
        this.in_cat_amount = in_cat_amount;
    }

    public In_Catg_model() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getIn_cat_type() {
        return in_cat_type;
    }

    public void setIn_cat_type(String in_cat_type) {
        this.in_cat_type = in_cat_type;
    }

    public String getIn_cat_amount() {
        return in_cat_amount;
    }

    public void setIn_cat_amount(String in_cat_amount) {
        this.in_cat_amount = in_cat_amount;
    }
}
