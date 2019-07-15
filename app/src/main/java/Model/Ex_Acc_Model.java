package Model;

public class Ex_Acc_Model {
    private int id;
    private String ex_acc_type;

    public Ex_Acc_Model(int id, String ex_acc_type) {
        this.id = id;
        this.ex_acc_type = ex_acc_type;
    }

    public Ex_Acc_Model() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getEx_acc_type() {
        return ex_acc_type;
    }

    public void setEx_acc_type(String ex_acc_type) {
        this.ex_acc_type = ex_acc_type;
    }
}
