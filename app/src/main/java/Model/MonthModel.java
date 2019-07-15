package Model;

public class MonthModel {

    private String month;
    private String monthtotalincome;
    private String monthtotalexpense;

    public MonthModel() {


    }

    public MonthModel(String month, String monthtotalincome, String monthtotalexpense) {

        this.month = month;
        this.monthtotalincome = monthtotalincome;
        this.monthtotalexpense = monthtotalexpense;
    }

    public String getMonth() {
        return month;
    }

    public void setMonth(String month) {
        this.month = month;
    }

    public String getMonthtotalincome() {
        return monthtotalincome;
    }

    public void setMonthtotalincome(String monthtotalincome) {
        this.monthtotalincome = monthtotalincome;
    }

    public String getMonthtotalexpense() {
        return monthtotalexpense;
    }

    public void setMonthtotalexpense(String monthtotalexpense) {
        this.monthtotalexpense = monthtotalexpense;
    }
}