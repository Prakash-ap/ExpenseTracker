package Model;

public class In_Acc_Model {
    private int id;
    private String in_acc_type;

    public In_Acc_Model(int id, String in_acc_type) {
        this.id = id;
        this.in_acc_type = in_acc_type;
    }

    public In_Acc_Model() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getIn_acc_type() {
        return in_acc_type;
    }

    public void setIn_acc_type(String in_acc_type) {
        this.in_acc_type = in_acc_type;
    }
}
