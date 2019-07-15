package Model;

import android.support.annotation.NonNull;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class ExpenseModel implements Comparable<ExpenseModel> {
    private int id;
    private String Expensedate;
    private String Expenseaccount;
    private String Expensecategory;
    private String ExpenseAmount;
    private String Expensecontent;
    private String ExpenseType;

    private String ExpenseWeek;
    private String ExpenseMonth;
    private String ExpenseYear;



    public ExpenseModel(String Expensedate, String Expenseaccount, String Expensecategory, String ExpenseAmount, String Expensecontent, String expenseYear) {
        this.Expensedate = Expensedate;
        this.Expenseaccount = Expenseaccount;
        this.Expensecategory = Expensecategory;
        this.ExpenseAmount = ExpenseAmount;
        this.Expensecontent = Expensecontent;
        ExpenseYear = expenseYear;
    }

    public ExpenseModel(int id, String expensedate, String expenseaccount, String expensecategory, String expenseAmount, String expensecontent, String expenseType, String expenseWeek, String expenseMonth, String expenseYear) {
        this.id = id;
        Expensedate = expensedate;
        Expenseaccount = expenseaccount;
        Expensecategory = expensecategory;
        ExpenseAmount = expenseAmount;
        Expensecontent = expensecontent;
        ExpenseType = expenseType;
        ExpenseWeek = expenseWeek;
        ExpenseMonth = expenseMonth;
        ExpenseYear = expenseYear;

    }

    public ExpenseModel() {
    }



    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getExpensedate() {
        return Expensedate;
    }

    public void setExpensedate(String Expensedate) {
        this.Expensedate = Expensedate;
    }

    public String getExpenseaccount() {
        return Expenseaccount;
    }

    public void setExpenseaccount(String Expenseaccount) {
        this.Expenseaccount = Expenseaccount;
    }

    public String getExpensecategory() {
        return Expensecategory;
    }

    public void setExpensecategory(String Expensecategory) {
        this.Expensecategory = Expensecategory;
    }

    public String getExpenseAmount() {
        return ExpenseAmount;
    }

    public void setExpenseAmount(String ExpenseAmount) {
        this.ExpenseAmount = ExpenseAmount;
    }

    public String getExpensecontent() {
        return Expensecontent;
    }

    public void setExpensecontent(String Expensecontent) {
        this.Expensecontent = Expensecontent;
    }

    public String getExpenseType() {
        return ExpenseType;
    }

    public void setExpenseType(String expenseType) {
        ExpenseType = expenseType;
    }

    @Override
    public int compareTo(@NonNull ExpenseModel o) {

        try {
            Date date1=new SimpleDateFormat("MM/dd/yyyy").parse(Expensedate);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        return getExpensedate().compareTo(o.getExpensedate());
    }


    public String getExpenseWeek() {
        return ExpenseWeek;
    }

    public void setExpenseWeek(String expenseWeek) {
        ExpenseWeek = expenseWeek;
    }

    public String getExpenseMonth() {
        return ExpenseMonth;
    }

    public void setExpenseMonth(String expenseMonth) {
        ExpenseMonth = expenseMonth;
    }

    public String getExpenseYear() {
        return ExpenseYear;
    }

    public void setExpenseYear(String expenseYear) {
        ExpenseYear = expenseYear;
    }
}
