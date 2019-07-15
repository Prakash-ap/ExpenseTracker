package Model;

public class Ex_Catg_model {
    private int id;
    private String ex_cat_type;

    public Ex_Catg_model(int id, String ex_cat_type) {
        this.id = id;
        this.ex_cat_type = ex_cat_type;
    }

    public Ex_Catg_model() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getEx_cat_type() {
        return ex_cat_type;
    }

    public void setEx_cat_type(String ex_cat_type) {
        this.ex_cat_type = ex_cat_type;
    }
}
