package Database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;

import Model.Ex_Acc_Model;
import Model.Ex_Catg_model;
import Model.ExpenseModel;
import Model.In_Acc_Model;
import Model.In_Catg_model;
import Model.In_Chart_Catg;

public class DatabaseHandler extends SQLiteOpenHelper {
    private static final String DATABASE_NAME="expenseManager";
    private static final int DATABASE_VERSION=9;
    private static final String TABLE_NAME="expenses";
    private static final String IN_CATG_TABLE_NAME="in_category_table";
    private static final String EX_CATG_TABLE_NAME="ex_category_table";
    private static final String IN_ACC_TABLE_NAME="in_account_table";
    private static final String EX_ACC_TABLE_NAME="ex_account_table";
    private static final String IN_CAT_TABLE_NAME="in_cat_table";
    private static final String EX_CAT_TABLE_NAME="ex_cat_table";
    private static final String KEY_ID="id";
    private static final String KEY_DATE="date";
    private static final String KEY_ACCOUNT="account";
    private static final String KEY_CATEGORY="category";
    private static final String KEY_AMOUNT="amount";
    private static final String KEY_DESCRIPTION="description";
    private static final String KEY_TYPE="type";
    private static final String KEY_WEEK_OF_MONTH="week";
    private static final String KEY_YEAR="year";
    private static final String KEY_MONTH="month";



    private static final String IN_KEY_CATEGORY="in_category";
    private static final String EX_KEY_CATEGORY="ex_category";
    private static final String IN_KEY_ACCOUNT="in_account";
    private static final String EX_KEY_ACCOUNT="ex_account";
    private static final String IN_KEY_CAT="in_chart_catg";
    private static final String IN_KEY_AMOUNT="in_chart_amount";





    public DatabaseHandler(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_EXPENSE_TABLE=" CREATE TABLE "+TABLE_NAME + "("+KEY_ID+ " INTEGER PRIMARY KEY AUTOINCREMENT,"+KEY_DATE+ " TEXT,"
                + KEY_ACCOUNT+" TEXT,"+KEY_CATEGORY+ " TEXT,"+ KEY_AMOUNT + " TEXT,"+KEY_DESCRIPTION+" TEXT,"+KEY_TYPE+" TEXT,"+KEY_WEEK_OF_MONTH+" TEXT,"+KEY_MONTH+" TEXT,"+KEY_YEAR+ " TEXT" +")";


        String CREATE_IN_CAT_TABNAME=" CREATE TABLE "+IN_CATG_TABLE_NAME + "("+KEY_ID+ " INTEGER PRIMARY KEY AUTOINCREMENT,"+IN_KEY_CATEGORY+ " TEXT" +")";
        String CREATE_EX_CAT_TABNAME=" CREATE TABLE "+EX_CATG_TABLE_NAME + "("+KEY_ID+ " INTEGER PRIMARY KEY AUTOINCREMENT,"+EX_KEY_CATEGORY+ " TEXT" +")";
        String CREATE_IN_ACC_TABNAME=" CREATE TABLE "+IN_ACC_TABLE_NAME + "("+KEY_ID+ " INTEGER PRIMARY KEY AUTOINCREMENT,"+IN_KEY_ACCOUNT+ " TEXT" +")";
        String CREATE_EX_ACC_TABNAME=" CREATE TABLE "+EX_ACC_TABLE_NAME + "("+KEY_ID+ " INTEGER PRIMARY KEY AUTOINCREMENT,"+EX_KEY_ACCOUNT+ " TEXT" +")";
        String CREATE__IN_CATCAHRT_TABNAME=" CREATE TABLE "+IN_CAT_TABLE_NAME + "("+KEY_ID+ " INTEGER PRIMARY KEY AUTOINCREMENT,"+IN_KEY_CAT+ " TEXT," + IN_KEY_AMOUNT+" TEXT "+")";
       // String CREATE_EX_CATCHART_TABNAME=" CREATE TABLE "+EX_CAT_TABLE_NAME + "("+KEY_ID+ " INTEGER PRIMARY KEY AUTOINCREMENT,"+EX_KEY_ACCOUNT+ " TEXT" +")";



        db.execSQL(CREATE_EXPENSE_TABLE);
        db.execSQL(CREATE_IN_CAT_TABNAME);
        db.execSQL(CREATE_EX_CAT_TABNAME);
        db.execSQL(CREATE_IN_ACC_TABNAME);
        db.execSQL(CREATE_EX_ACC_TABNAME);
        db.execSQL(CREATE__IN_CATCAHRT_TABNAME);


    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(" DROP TABLE IF EXISTS "+TABLE_NAME);
        db.execSQL(" DROP TABLE IF EXISTS "+IN_CATG_TABLE_NAME);
        db.execSQL(" DROP TABLE IF EXISTS "+EX_CATG_TABLE_NAME);
        db.execSQL(" DROP TABLE IF EXISTS "+IN_ACC_TABLE_NAME);
        db.execSQL(" DROP TABLE IF EXISTS "+EX_ACC_TABLE_NAME);
        db.execSQL(" DROP TABLE IF EXISTS "+IN_CATG_TABLE_NAME);

        onCreate(db);
    }



    public void addExpense(ExpenseModel expenseModel){
        SQLiteDatabase db=this.getWritableDatabase();

        ContentValues contentValues=new ContentValues();
        contentValues.put(KEY_ACCOUNT,expenseModel.getExpenseaccount());
        contentValues.put(KEY_CATEGORY,expenseModel.getExpensecategory());
        contentValues.put(KEY_AMOUNT,expenseModel.getExpenseAmount());
        contentValues.put(KEY_DESCRIPTION,expenseModel.getExpensecontent());
       // SimpleDateFormat dateFormat=new SimpleDateFormat("yyyy-MM-dd hh:mm a");
        contentValues.put(KEY_DATE,expenseModel.getExpensedate());
        contentValues.put(KEY_TYPE,expenseModel.getExpenseType());
        contentValues.put(KEY_WEEK_OF_MONTH,expenseModel.getExpenseWeek());
        contentValues.put(KEY_MONTH,expenseModel.getExpenseMonth());
        contentValues.put(KEY_YEAR,expenseModel.getExpenseYear());

        db.insert(TABLE_NAME,null,contentValues);
        db.close();
    }

    public void addChartCat(In_Chart_Catg in_chart_catg){
        SQLiteDatabase db=this.getWritableDatabase();

        ContentValues contentValues=new ContentValues();

        contentValues.put(IN_KEY_CAT,in_chart_catg.getIn_chartcat_type());
        contentValues.put(IN_KEY_AMOUNT,in_chart_catg.getOn_chart_amount());


        db.insert(IN_CAT_TABLE_NAME,null,contentValues);
        db.close();
    }

    public void addInCatg(In_Catg_model in_catg_model){
        SQLiteDatabase db=this.getWritableDatabase();

        ContentValues contentValues1=new ContentValues();

        contentValues1.put(IN_KEY_CATEGORY,in_catg_model.getIn_cat_type());


        db.insert(IN_CATG_TABLE_NAME,null,contentValues1);
        db.close();
    }

    public void addInAcc(In_Acc_Model in_acc_model){
        SQLiteDatabase db=this.getWritableDatabase();

        ContentValues contentValues1=new ContentValues();

        contentValues1.put(IN_KEY_ACCOUNT,in_acc_model.getIn_acc_type());


        db.insert(IN_ACC_TABLE_NAME,null,contentValues1);
        db.close();
    }
    public void addExCatg(Ex_Catg_model ex_cat_model){
        SQLiteDatabase db=this.getWritableDatabase();

        ContentValues contentValues1=new ContentValues();

        contentValues1.put(EX_KEY_CATEGORY,ex_cat_model.getEx_cat_type());


        db.insert(EX_CATG_TABLE_NAME,null,contentValues1);
        db.close();
    }


    public void addExAcc(Ex_Acc_Model ex_acc_model){
        SQLiteDatabase db=this.getWritableDatabase();

        ContentValues contentValues1=new ContentValues();

        contentValues1.put(EX_KEY_ACCOUNT,ex_acc_model.getEx_acc_type());


        db.insert(EX_ACC_TABLE_NAME,null,contentValues1);
        db.close();
    }



    public ExpenseModel getExpenses(int id){
        SQLiteDatabase db=this.getReadableDatabase();

        Cursor cursor=db.query(TABLE_NAME,new String[]{KEY_ID,KEY_DATE,KEY_ACCOUNT,KEY_CATEGORY,KEY_AMOUNT,KEY_DESCRIPTION,KEY_TYPE,KEY_WEEK_OF_MONTH,KEY_MONTH,KEY_YEAR},
                KEY_ID+ "=?",new String[]{String.valueOf(id)},null,null,null,null);
        if(cursor!=null)
            cursor.moveToFirst();

        ExpenseModel expenseModel=new ExpenseModel(Integer.parseInt(cursor.getString(0)),cursor.getString(1),
                cursor.getString(2),cursor.getString(3),cursor.getString(4),cursor.getString(5),cursor.getString(6),cursor.getString(7),cursor.getString(8),cursor.getString(9));

        return expenseModel;


    }

    public ExpenseModel getWeekExpenses(String month){
        SQLiteDatabase db=this.getReadableDatabase();

        Cursor cursor=db.query(TABLE_NAME,new String[]{KEY_ID,KEY_DATE,KEY_ACCOUNT,KEY_CATEGORY,KEY_AMOUNT,KEY_DESCRIPTION,KEY_TYPE,KEY_WEEK_OF_MONTH,KEY_MONTH,KEY_YEAR},
                KEY_ID+ "=?",new String[]{String.valueOf(month)},null,null,null,null);
        if(cursor!=null)
            cursor.moveToFirst();

        ExpenseModel expenseModel=new ExpenseModel(Integer.parseInt(cursor.getString(0)),cursor.getString(1),
                cursor.getString(2),cursor.getString(3),cursor.getString(4),cursor.getString(5),cursor.getString(6),cursor.getString(7),cursor.getString(8),cursor.getString(9));

        return expenseModel;


    }


  /*  public ExpenseModel getExpenseAmount(int id){
        SQLiteDatabase db=this.getReadableDatabase();

        Cursor cursor=db.query(TABLE_NAME,new String[]{KEY_ID,KEY_AMOUNT,KEY_TYPE},
                KEY_ID+ "=?",new String[]{String.valueOf(id)},null,null,null,null);
        if(cursor!=null)
            cursor.moveToFirst();

        ExpenseModel expenseModelamount=new ExpenseModel(Integer.parseInt(cursor.getString(0)),cursor.getString(1),
                cursor.getString(2),cursor.getString(3),cursor.getString(4),cursor.getString(5),cursor.getString(6));

        return expenseModelamount;


    }*/

    public ArrayList<ExpenseModel> getAllExpenses(){
        ArrayList<ExpenseModel>expenseModelList=new ArrayList<ExpenseModel>();

        String selecAllQuery=" SELECT * FROM "+TABLE_NAME;
        SQLiteDatabase db=this.getWritableDatabase();

        Cursor cursor=db.rawQuery(selecAllQuery,null);

        if(cursor.moveToFirst()){
            do{
                ExpenseModel expenseModel=new ExpenseModel();
                expenseModel.setId(Integer.parseInt(cursor.getString(0)));
                expenseModel.setExpensedate(cursor.getString(1));
                expenseModel.setExpenseaccount(cursor.getString(2));
                expenseModel.setExpensecategory(cursor.getString(3));
                expenseModel.setExpenseAmount(cursor.getString(4));
                expenseModel.setExpensecontent(cursor.getString(5));
                expenseModel.setExpenseType(cursor.getString(6));
                expenseModel.setExpenseWeek(cursor.getString(7));
                expenseModel.setExpenseMonth(cursor.getString(8));
                expenseModel.setExpenseYear(cursor.getString(9));

                expenseModelList.add(expenseModel);

            }while (cursor.moveToNext());

        }
        return expenseModelList;
    }

    public ArrayList<In_Catg_model>getAllInType(){
        ArrayList<In_Catg_model>in_catg_models=new ArrayList<>();

        String selectAll=" SELECT * FROM "+IN_CATG_TABLE_NAME;
        SQLiteDatabase db=this.getReadableDatabase();
        Cursor cursor=db.rawQuery(selectAll,null);

        if(cursor.moveToFirst()){
            do{
                In_Catg_model in_catg_model=new In_Catg_model();

                in_catg_model.setId(Integer.parseInt(cursor.getString(0)));
                in_catg_model.setIn_cat_type(cursor.getString(1));

                in_catg_models.add(in_catg_model);

            }while (cursor.moveToNext());

        }
        return in_catg_models;


    }

    public ArrayList<Ex_Catg_model>getAllExCatType(){
        ArrayList<Ex_Catg_model>ex_catg_models=new ArrayList<>();

        String selectAll=" SELECT * FROM "+EX_CATG_TABLE_NAME;
        SQLiteDatabase db=this.getReadableDatabase();
        Cursor cursor=db.rawQuery(selectAll,null);

        if(cursor.moveToFirst()){
            do{
                Ex_Catg_model ex_catg_model=new Ex_Catg_model();

                ex_catg_model.setId(Integer.parseInt(cursor.getString(0)));
                ex_catg_model.setEx_cat_type(cursor.getString(1));

                ex_catg_models.add(ex_catg_model);

            }while (cursor.moveToNext());

        }
        return ex_catg_models;


    }

    public ArrayList<In_Acc_Model>getAllInAccType(){
        ArrayList<In_Acc_Model>in_acc_models=new ArrayList<>();

        String selectAll=" SELECT * FROM "+IN_ACC_TABLE_NAME;
        SQLiteDatabase db=this.getReadableDatabase();
        Cursor cursor=db.rawQuery(selectAll,null);

        if(cursor.moveToFirst()){
            do{
                In_Acc_Model in_acc_model=new In_Acc_Model();


                in_acc_model.setId(Integer.parseInt(cursor.getString(0)));
                in_acc_model.setIn_acc_type(cursor.getString(1));



                in_acc_models.add(in_acc_model);

            }while (cursor.moveToNext());

        }
        return in_acc_models;


    }

    public ArrayList<Ex_Acc_Model>getAllExAccType(){
        ArrayList<Ex_Acc_Model>ex_acc_models=new ArrayList<>();

        String selectAll=" SELECT * FROM "+EX_ACC_TABLE_NAME;
        SQLiteDatabase db=this.getReadableDatabase();
        Cursor cursor=db.rawQuery(selectAll,null);

        if(cursor.moveToFirst()){
            do{
                Ex_Acc_Model ex_acc_model=new Ex_Acc_Model();

                ex_acc_model.setId(Integer.parseInt(cursor.getString(0)));
                ex_acc_model.setEx_acc_type(cursor.getString(1));

                ex_acc_models.add(ex_acc_model);

            }while (cursor.moveToNext());

        }
        return ex_acc_models;


    }
    public ArrayList<In_Chart_Catg>getAllInChartCat(){
        ArrayList<In_Chart_Catg>in_chart_catgArrayList=new ArrayList<>();

        String selectAll=" SELECT * FROM "+IN_CAT_TABLE_NAME;
        SQLiteDatabase db=this.getReadableDatabase();
        Cursor cursor=db.rawQuery(selectAll,null);

        if(cursor.moveToFirst()){
            do{
                In_Chart_Catg in_chart_catg=new In_Chart_Catg();

                in_chart_catg.setId(Integer.parseInt(cursor.getString(0)));
                in_chart_catg.setIn_chartcat_type(cursor.getString(1));
                in_chart_catg.setOn_chart_amount(cursor.getString(2));

                in_chart_catgArrayList.add(in_chart_catg);

            }while (cursor.moveToNext());

        }
        return in_chart_catgArrayList;


    }


    public int updateExpense(ExpenseModel expenseModel){
        SQLiteDatabase db=this.getWritableDatabase();

        ContentValues contentValues=new ContentValues();
        contentValues.put(KEY_DATE,expenseModel.getExpensedate());
        contentValues.put(KEY_ACCOUNT,expenseModel.getExpenseaccount());
        contentValues.put(KEY_CATEGORY,expenseModel.getExpensecategory());
        contentValues.put(KEY_AMOUNT,expenseModel.getExpenseAmount());
        contentValues.put(KEY_DESCRIPTION,expenseModel.getExpensecontent());
        contentValues.put(KEY_TYPE,expenseModel.getExpenseType());
        contentValues.put(KEY_WEEK_OF_MONTH,expenseModel.getExpenseWeek());
        contentValues.put(KEY_MONTH,expenseModel.getExpenseMonth());
        contentValues.put(KEY_YEAR,expenseModel.getExpenseYear());

        return db.update(TABLE_NAME,contentValues,KEY_ID+"=?",new String[]{String.valueOf(expenseModel.getId())});

    }

    public int updateCharIncome(In_Chart_Catg in_chart_catg){
        SQLiteDatabase db=this.getWritableDatabase();

        ContentValues contentValues=new ContentValues();
        contentValues.put(IN_KEY_AMOUNT,in_chart_catg.getOn_chart_amount());


        return db.update(IN_CAT_TABLE_NAME,contentValues,KEY_ID+"=?",new String[]{String.valueOf(in_chart_catg.getId())});

    }

    public void deleteExpense(ExpenseModel expenseModel){
        SQLiteDatabase db=this.getWritableDatabase();
        db.delete(TABLE_NAME,KEY_ID +"=?",new String[]{String.valueOf(expenseModel.getId())});
        db.close();
    }

    public int getExpenseCount(){
        String countQuery=" SELECT * FROM " +TABLE_NAME;
        SQLiteDatabase db=this.getReadableDatabase();
        Cursor cursor=db.rawQuery(countQuery,null);
        cursor.close();

        return cursor.getCount();
    }

}
