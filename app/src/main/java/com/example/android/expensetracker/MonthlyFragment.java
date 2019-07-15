package com.example.android.expensetracker;

import android.content.Context;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import Adapter.MonthlyRecyclerAdapter;
import Adapter.WeekRecyclerAdapter;
import Database.DatabaseHandler;
import Model.ExpenseModel;
import Model.MonthModel;
import Model.WeekModel;


public class MonthlyFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    DatabaseHandler db;
    private RecyclerView recyclerView;
    private MonthlyRecyclerAdapter recyclerAdapter;
    private ExpenseModel expenseModel;
    private ArrayList<ExpenseModel> expenseModelArrayList;
    String month;
    String currentmonth;
    int curryear;
    int year;
    String in, ex, to;
    String in1, ex1, to1;
    long tempjanincome = 0;
    long tempjanexpense = 0;
    long tempfebincome = 0;
    long tempfebexpense = 0;
    long tempmarexpense = 0;
    long tempmarincome = 0;
    long tempaprexpense = 0;
    long tempaprincome = 0;
    long tempmayexpense = 0;
    long tempmayincome = 0;
    long tempjunexpense = 0;
    long tempjunincome = 0;
    long tempjulexpense = 0;
    long tempjulincome = 0;
    long tempaugexpense = 0;
    long tempaugincome = 0;
    long tempsepexpense = 0;
    long tempsepincome = 0;
    long tempoctexpense = 0;
    long tempoctincome = 0;
    long tempnovexpense = 0;
    long tempnovincome = 0;
    long tempdecexpense = 0;
    long tempdecincome = 0;

    MonthModel monthModel;
    private ArrayList<MonthModel>monthModelArrayList;
    long toinco,toexp;
    String janmonth;
    String febmonth,marmonth,aprmonth,maymonth,junmonth,julymonth,augmonth,sepmonth,octmonth,novmonth,decmonth;


    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_monthly, container, false);
        db=new DatabaseHandler(getContext());

        recyclerView=view.findViewById(R.id.monthlyrecycler);
        monthModel=new MonthModel();
        monthModelArrayList=new ArrayList<>();
        expenseModelArrayList=new ArrayList<>();
        expenseModelArrayList=db.getAllExpenses();
        Calendar calendar=Calendar.getInstance();
        curryear = calendar.get(Calendar.YEAR);
        currentmonth=new SimpleDateFormat("MMMM").format(calendar.getTime());

   //     Stream<ExpenseModel>expenseModelStream=expenseModelArrayList.stream().filter(p->p.getExpenseMonth().equals("July"));

        for (int j = 0; j < expenseModelArrayList.size(); j++) {

            expenseModel = expenseModelArrayList.get(j);

            month = expenseModel.getExpenseMonth();

            year = Integer.parseInt(expenseModel.getExpenseYear());

            if (year == curryear) {
                janmonth="January";febmonth="February";marmonth="March";aprmonth="April";maymonth="May";junmonth="June";
                julymonth="July";augmonth="August";sepmonth="September";octmonth="October";novmonth="November";decmonth="December";


                if (month.equals("January") && expenseModel.getExpenseType().equals("income")) {
                  // janmonth=month;


                    in = expenseModel.getExpenseAmount();
                    if (in.equals("")) {
                        tempjanincome=0;

                    } else {


                        tempjanincome += Long.parseLong(in);


                    }
                } else if (month.equals("January") && expenseModel.getExpenseType().equals("expenses")) {
                    //janmonth=month;
                    ex = expenseModel.getExpenseAmount();

                    if (ex.equals("")) {
                        tempjanexpense=0;

                    } else {
                        tempjanexpense += Long.parseLong(ex);


                    }
                }
                else if (month.equals("February") && expenseModel.getExpenseType().equals("income")) {
                   // febmonth=month;
                    in = expenseModel.getExpenseAmount();
                    if (in.equals("")) {
                        tempfebincome=0;

                    } else {


                        tempfebincome += Long.parseLong(in);
                        //  List<Long>income1=new ArrayList<Long>(Arrays.asList(tempincome));

                    }
                }
                else if (month.equals("February") && expenseModel.getExpenseType().equals("expenses")) {
                    //febmonth=month;
                    ex = expenseModel.getExpenseAmount();
                    if (ex.equals("")) {
                        tempfebexpense=0;

                    } else {
                        tempfebexpense += Long.parseLong(ex);
                        //  List<Long>income1=new ArrayList<Long>(Arrays.asList(tempexpense));


                    }
                }


                else if (month.equals("March") && expenseModel.getExpenseType().equals("income")) {
                    //marmonth=month;
                    in = expenseModel.getExpenseAmount();
                    if (in.equals("")) {
                        tempmarincome=0;

                    } else {


                        tempmarincome += Long.parseLong(in);
                        //  List<Long>income1=new ArrayList<Long>(Arrays.asList(tempincome));

                    }
                }
                else if (month.equals("March") && expenseModel.getExpenseType().equals("expenses")) {
                    //marmonth=month;
                    ex = expenseModel.getExpenseAmount();
                    if (ex.equals("")) {
                        tempmarexpense=0;

                    } else {
                        tempmarexpense += Long.parseLong(ex);
                        //  List<Long>income1=new ArrayList<Long>(Arrays.asList(tempexpense));

                    }
                }
                else if (month.equals("April") && expenseModel.getExpenseType().equals("income")) {
                   // aprmonth=month;
                    in = expenseModel.getExpenseAmount();
                    if (in.equals("")) {
                        tempaprincome=0;

                    } else {


                        tempaprincome += Long.parseLong(in);
                        //  List<Long>income1=new ArrayList<Long>(Arrays.asList(tempincome));

                    }
                }
                else if (month.equals("April") && expenseModel.getExpenseType().equals("expenses")) {
                    //aprmonth=month;
                    ex = expenseModel.getExpenseAmount();
                    if (ex.equals("")) {
                        tempaprexpense=0;

                    } else {
                        tempaprexpense += Long.parseLong(ex);
                        //  List<Long>income1=new ArrayList<Long>(Arrays.asList(tempexpense));

                    }
                }
                else if (month.equals("May") && expenseModel.getExpenseType().equals("income")) {
                    //maymonth=month;
                    in = expenseModel.getExpenseAmount();
                    if (in.equals("")) {
                        tempmayincome=0;

                    } else {


                        tempmayincome += Long.parseLong(in);
                        //  List<Long>income1=new ArrayList<Long>(Arrays.asList(tempincome));

                    }
                }
                else if (month.equals("May") && expenseModel.getExpenseType().equals("expenses")) {
                    //maymonth=month;
                    ex = expenseModel.getExpenseAmount();
                    if (ex.equals("")) {
                        tempmayexpense=0;

                    } else {
                        tempmayexpense += Long.parseLong(ex);
                        //  List<Long>income1=new ArrayList<Long>(Arrays.asList(tempexpense));

                    }
                }
                else if (month.equals("June") && expenseModel.getExpenseType().equals("income")) {
                    //junmonth=month;
                    in = expenseModel.getExpenseAmount();
                    if (in.equals("")) {
                        tempjunincome=0;

                    } else {


                        tempjunincome += Long.parseLong(in);
                        //  List<Long>income1=new ArrayList<Long>(Arrays.asList(tempincome));

                    }
                }
                else if (month.equals("June") && expenseModel.getExpenseType().equals("expenses")) {
                    //junmonth=month;
                    ex = expenseModel.getExpenseAmount();
                    if (ex.equals("")) {
                        tempjunexpense=0;

                    } else {
                        tempjunexpense += Long.parseLong(ex);
                        //  List<Long>income1=new ArrayList<Long>(Arrays.asList(tempexpense));

                    }
                }
                else if (month.equals("July") && expenseModel.getExpenseType().equals("income")) {
                    //julymonth=month;
                    in = expenseModel.getExpenseAmount();
                    if (in.equals("")) {
                        tempjulincome=0;

                    } else {


                        tempjulincome += Long.parseLong(in);
                        //  List<Long>income1=new ArrayList<Long>(Arrays.asList(tempincome));

                    }
                }
                else if (month.equals("July") && expenseModel.getExpenseType().equals("expenses")) {
                    //julymonth=month;
                    ex = expenseModel.getExpenseAmount();
                    if (ex.equals("")) {
                        tempjulexpense=0;

                    } else {
                        tempjulexpense += Long.parseLong(ex);
                        //  List<Long>income1=new ArrayList<Long>(Arrays.asList(tempexpense));

                    }
                }
                else if (month.equals("August") && expenseModel.getExpenseType().equals("income")) {
                    //augmonth=month;
                    in = expenseModel.getExpenseAmount();
                    if (in.equals("")) {
                        tempaugincome=0;

                    } else {


                        tempaugincome += Long.parseLong(in);
                        //  List<Long>income1=new ArrayList<Long>(Arrays.asList(tempincome));

                    }
                }
                else if (month.equals("August") && expenseModel.getExpenseType().equals("expenses")) {
                    //augmonth=month;
                    ex = expenseModel.getExpenseAmount();
                    if (ex.equals("")) {
                        tempaugexpense=0;

                    } else {
                        tempaugexpense += Long.parseLong(ex);
                        //  List<Long>income1=new ArrayList<Long>(Arrays.asList(tempexpense));

                    }
                }
                else if (month.equals("September") && expenseModel.getExpenseType().equals("income")) {
                    //sepmonth=month;
                    in = expenseModel.getExpenseAmount();
                    if (in.equals("")) {
                        tempsepincome=0;

                    } else {


                        tempsepincome += Long.parseLong(in);
                        //  List<Long>income1=new ArrayList<Long>(Arrays.asList(tempincome));

                    }
                }
                else if (month.equals("September") && expenseModel.getExpenseType().equals("expenses")) {
                    //sepmonth=month;
                    ex = expenseModel.getExpenseAmount();
                    if (ex.equals("")) {
                        tempsepexpense=0;

                    } else {
                        tempsepexpense += Long.parseLong(ex);
                        //  List<Long>income1=new ArrayList<Long>(Arrays.asList(tempexpense));

                    }
                }
                else if (month.equals("October") && expenseModel.getExpenseType().equals("income")) {
                    //octmonth=month;
                    in = expenseModel.getExpenseAmount();
                    if (in.equals("")) {
                        tempoctincome=0;

                    } else {


                        tempoctincome += Long.parseLong(in);
                        //  List<Long>income1=new ArrayList<Long>(Arrays.asList(tempincome));

                    }
                }
                else if (month.equals("October") && expenseModel.getExpenseType().equals("expenses")) {
                    //octmonth=month;
                    ex = expenseModel.getExpenseAmount();
                    if (ex.equals("")) {
                        tempoctexpense=0;

                    } else {
                        tempoctexpense += Long.parseLong(ex);
                        //  List<Long>income1=new ArrayList<Long>(Arrays.asList(tempexpense));

                    }
                }
                else if (month.equals("November") && expenseModel.getExpenseType().equals("income")) {
                    //novmonth=month;
                    in = expenseModel.getExpenseAmount();
                    if (in.equals("")) {
                        tempnovincome=0;

                    } else {


                        tempnovincome += Long.parseLong(in);
                        //  List<Long>income1=new ArrayList<Long>(Arrays.asList(tempincome));

                    }
                }
                else if (month.equals("November") && expenseModel.getExpenseType().equals("expenses")) {
                    //novmonth=month;
                    ex = expenseModel.getExpenseAmount();
                    if (ex.equals("")) {
                        tempnovexpense=0;

                    } else {
                        tempnovexpense += Long.parseLong(ex);
                        //  List<Long>income1=new ArrayList<Long>(Arrays.asList(tempexpense));

                    }
                }
                else if (month.equals("December") && expenseModel.getExpenseType().equals("income")) {
                    //decmonth=month;
                    in = expenseModel.getExpenseAmount();
                    if (in.equals("")) {
                        tempdecincome=0;

                    } else {


                        tempdecincome += Long.parseLong(in);
                        //  List<Long>income1=new ArrayList<Long>(Arrays.asList(tempincome));

                    }
                }
                else if (month.equals("December") && expenseModel.getExpenseType().equals("expenses")) {
                   //decmonth=month;
                    ex = expenseModel.getExpenseAmount();
                    if (ex.equals("")) {
                        tempdecexpense=0;

                    } else {
                        tempdecexpense += Long.parseLong(ex);
                        //  List<Long>income1=new ArrayList<Long>(Arrays.asList(tempexpense));

                    }
                }

            } else {

                Toast.makeText(getContext(), "Not this year", Toast.LENGTH_SHORT).show();

            }
        }
        monthModel=new MonthModel();
        monthModel.setMonth(janmonth);
        monthModel.setMonthtotalexpense(String.valueOf("$"+tempjanexpense));
        monthModel.setMonthtotalincome(String.valueOf("$"+tempjanincome));
        monthModelArrayList.add(monthModel);


        monthModel=new MonthModel();
        monthModel.setMonth(febmonth);
        monthModel.setMonthtotalexpense(String.valueOf("$"+tempfebexpense));
        monthModel.setMonthtotalincome(String.valueOf("$"+tempfebincome));
        monthModelArrayList.add(monthModel);

        monthModel=new MonthModel();
        monthModel.setMonth(marmonth);
        monthModel.setMonthtotalexpense(String.valueOf("$"+tempmarexpense));
        monthModel.setMonthtotalincome(String.valueOf("$"+tempmarincome));
        monthModelArrayList.add(monthModel);

        monthModel=new MonthModel();
        monthModel.setMonth(aprmonth);
        monthModel.setMonthtotalexpense(String.valueOf("$"+tempaprexpense));
        monthModel.setMonthtotalincome(String.valueOf("$"+tempaprincome));
        monthModelArrayList.add(monthModel);

        monthModel=new MonthModel();
        monthModel.setMonth(maymonth);
        monthModel.setMonthtotalexpense(String.valueOf("$"+tempmayexpense));
        monthModel.setMonthtotalincome(String.valueOf("$"+tempmayincome));
        monthModelArrayList.add(monthModel);

        monthModel=new MonthModel();
        monthModel.setMonth(junmonth);
        monthModel.setMonthtotalexpense(String.valueOf("$"+tempjunexpense));
        monthModel.setMonthtotalincome(String.valueOf("$"+tempjunincome));
        monthModelArrayList.add(monthModel);

        monthModel=new MonthModel();
        monthModel.setMonth(julymonth);
        monthModel.setMonthtotalexpense(String.valueOf("$"+tempjulexpense));
        monthModel.setMonthtotalincome(String.valueOf("$"+tempjulincome));
        monthModelArrayList.add(monthModel);

        monthModel=new MonthModel();
        monthModel.setMonth(augmonth);
        monthModel.setMonthtotalexpense(String.valueOf("$"+tempaugexpense));
        monthModel.setMonthtotalincome(String.valueOf("$"+tempaugincome));
        monthModelArrayList.add(monthModel);

        monthModel=new MonthModel();
        monthModel.setMonth(sepmonth);
        monthModel.setMonthtotalexpense(String.valueOf("$"+tempsepexpense));
        monthModel.setMonthtotalincome(String.valueOf("$"+tempsepincome));
        monthModelArrayList.add(monthModel);

        monthModel=new MonthModel();
        monthModel.setMonth(octmonth);
        monthModel.setMonthtotalexpense(String.valueOf("$"+tempoctexpense));
        monthModel.setMonthtotalincome(String.valueOf("$"+tempoctincome));
        monthModelArrayList.add(monthModel);

        monthModel=new MonthModel();
        monthModel.setMonth(novmonth);
        monthModel.setMonthtotalexpense(String.valueOf("$"+tempnovexpense));
        monthModel.setMonthtotalincome(String.valueOf("$"+tempnovincome));
        monthModelArrayList.add(monthModel);

        monthModel=new MonthModel();
        monthModel.setMonth(decmonth);
        monthModel.setMonthtotalexpense(String.valueOf("$"+tempdecincome));
        monthModel.setMonthtotalincome(String.valueOf("$"+tempdecexpense));
        monthModelArrayList.add(monthModel);


      /*  Collection<MonthModel> filtered = Collections2.filter(list,
                Predicates.containsPattern("How"));
        print(filtered);*/
      Stream<ExpenseModel>expenseModelStream=expenseModelArrayList.stream().filter(p->p.getExpenseMonth().equals("July"));
     /* monthModelArrayList.add(expenseModelStream);

       monthModelArrayList = expenseModelArrayList.stream().filter(article -> article.getExpenseMonth().contains("July")).collect(Collectors.toList());

*/
        recyclerAdapter = new MonthlyRecyclerAdapter(monthModelArrayList, getContext());
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(recyclerAdapter);


        return view;
    }


}
