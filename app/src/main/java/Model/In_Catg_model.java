package Model;

public class In_Catg_model {
    private int id;
    private String in_cat_type;

    public In_Catg_model(int id, String in_cat_type) {
        this.id = id;
        this.in_cat_type = in_cat_type;
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
}
