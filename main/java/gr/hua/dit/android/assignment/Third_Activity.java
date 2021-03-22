package gr.hua.dit.android.assignment;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;
import static android.content.ContentValues.TAG;
import static gr.hua.dit.android.assignment.R.id.editTextUserIdSearch;

import java.util.ArrayList;

public class Third_Activity extends AppCompatActivity {
    String text;
    String tms;
    ArrayList<String> classData = new ArrayList<String>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_third_);
        Intent thirdIntent = getIntent();
        text=thirdIntent.getStringExtra("userIdText");
        tms=thirdIntent.getStringExtra("spinner");

            ArrayList search_field = get_search_field(text, tms); //return an arraylist with the fields from the db

            TextView textViewResults = (TextView) findViewById(R.id.textViewResults);
            ArrayList<String> colNames = new ArrayList<String>();
            colNames.add("id");
            colNames.add("user id");
            colNames.add("longitude");
            colNames.add("latitude");
            colNames.add("timestamp");
            setTextViewValues(search_field, textViewResults, colNames);


    }





    protected ArrayList get_search_field(String text,String tms) {


        ArrayList searchArrayList = null;

        if (!text.isEmpty() && tms!=" ") {
            //Toast.makeText(Third_Activity.this,"searching...",Toast.LENGTH_SHORT).show();

            searchArrayList = searchDb(text,tms);
        }else if(!text.isEmpty() && tms==" "){
            //Toast.makeText(Third_Activity.this,"searching only with user Id...",Toast.LENGTH_SHORT).show();
            searchArrayList=searchDb(text,text);
        }else if(text.isEmpty() && tms!=" "){
            //Toast.makeText(Third_Activity.this,"searching only with dt...",Toast.LENGTH_SHORT).show();
            searchArrayList=searchDb(text,tms);

        }else
            searchArrayList=null;
        return searchArrayList;

    }








    protected ArrayList searchDb(String text,String tms) {
        DbHelper dbHelper = firstActivity.helper; //creating a DbHelper instance
        DbHelper data = firstActivity.helper;
        if(!text.isEmpty() && tms!=" ") {
            if (data.readData(text, tms).isEmpty()) {
                Toast.makeText(Third_Activity.this, "Db return nothing!", Toast.LENGTH_SHORT).show();
            } else {
                classData.clear();
                try {
                    classData = data.readData(text, tms);
                } catch (Exception e) {
                    Toast.makeText(Third_Activity.this, "error on getting data to search", Toast.LENGTH_SHORT).show();
                    Log.e(TAG, "third activity, error getting data", e);
                }
                try {//closing connection, so we don't have memory leaking
                    data.close();
                    dbHelper.close();
                } catch (Exception e) {
                    e.getStackTrace();
                    Toast.makeText(Third_Activity.this, "error ", Toast.LENGTH_SHORT).show();

                }

            }
        }else if(!text.isEmpty() && tms==" "){
            if (data.readDataOnlyUserId(text).isEmpty()) {
                Toast.makeText(Third_Activity.this, "Db return nothing!", Toast.LENGTH_SHORT).show();
            } else {
                classData.clear();
                try {
                    classData = data.readDataOnlyUserId(text);
                } catch (Exception e) {
                    Toast.makeText(Third_Activity.this, "error on getting data to search", Toast.LENGTH_SHORT).show();
                    Log.e(TAG, "third activity, error getting data", e);
                }
                try {//closing connection, so we don't have memory leaking
                    data.close();
                    dbHelper.close();
                } catch (Exception e) {
                    e.getStackTrace();
                    Toast.makeText(Third_Activity.this, "error ", Toast.LENGTH_SHORT).show();

                }

            }
        }else if(text.isEmpty() && tms!=" "){
            if (data.readDataOnlyDt(tms).isEmpty()) {
                Toast.makeText(Third_Activity.this, "Db return nothing!", Toast.LENGTH_SHORT).show();
            } else {
                classData.clear();
                try {
                    classData = data.readDataOnlyDt(tms);
                } catch (Exception e) {
                    Toast.makeText(Third_Activity.this, "error on getting data to search", Toast.LENGTH_SHORT).show();
                    Log.e(TAG, "third activity, error getting data", e);
                }
                try {//closing connection, so we don't have memory leaking
                    data.close();
                    dbHelper.close();
                } catch (Exception e) {
                    e.getStackTrace();
                    Toast.makeText(Third_Activity.this, "error ", Toast.LENGTH_SHORT).show();

                }

            }
        }
        return classData;
    }

public void setTextViewValues(ArrayList<String> values, TextView text, ArrayList<String> column){
        String output="";//variable to hold all the values
        for(int i=0; i<values.size(); i++){
            //append all the values to a string
            String temp = column.get(i);
            output+=temp;
            output+=": ";
            output+=values.get(i);
            output+="\n";
        }
        text.setText(output);//Set the textView to the output string


}

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode==0){
            if(resultCode== RESULT_OK){


            }
            if(requestCode==RESULT_CANCELED){
                Toast.makeText(this,"An error occured",Toast.LENGTH_SHORT).show();
            }
        }
    }

     }




