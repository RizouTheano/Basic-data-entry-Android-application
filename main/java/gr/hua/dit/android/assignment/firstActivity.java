package gr.hua.dit.android.assignment;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Build;
import android.os.Bundle;
import android.text.InputType;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.ArrayList;

import static android.content.ContentValues.TAG;

public class firstActivity extends AppCompatActivity {
    public static ArrayList<String> tms = new ArrayList<String>();
    public static DbHelper helper;
    Button button1 = null;
    Button button2 = null;
    float longitude;
    int id;
    float latitude;
    String userId;
    String dt;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        helper = new DbHelper(firstActivity.this);
        click_Submit_Button();

        click_Go_To_Second();
    }




    protected void click_Submit_Button() {        //When the user clicks the submit button, pass to variables what the user wrote and save given data to database

        button1 = findViewById(R.id.insertButton);

        button1.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.O)
            @Override
            public void onClick(View v) {
                String idOnly = ((EditText) findViewById(R.id.editTextId)).getText().toString();
                String usIdOnly = ((EditText) findViewById(R.id.editTextTextUserId)).getText().toString();

                boolean check=true;
                try {    //this try-catch makes sure that the user has everything filled up and if so, the it submits into the db
                    passDataToVariables();//passing to variables what the user wrote

                }catch(Exception e){
                    check=false;

                }

                    if (check == true) {
                        long insertId = save_data_to_database(id, userId, longitude, latitude, dt);//saving to database the data that the user wrote
                        tms.add(dt);
                        if (insertId >= 0) { //if it was inserted, notify user
                            Toast.makeText(firstActivity.this, "inserted", Toast.LENGTH_SHORT).show();
                        }
                    } else {
                        Toast.makeText(firstActivity.this, "Unable to submit! Please fill all the fields!", Toast.LENGTH_SHORT).show();
                    }




            }
        });


    }

    protected boolean passDataToVariables() { //method to pass the user input to variables
        boolean checkEmpty = false;
        //id

        String id1 = ((EditText) findViewById(R.id.editTextId)).getText().toString();
        if (id1.isEmpty()) { //Check if the EditText is empty, if the user did not write anything, then notify the user
            ((EditText) findViewById(R.id.editTextId)).setError("Missing id");
        }
        //user id
        userId = ((EditText) findViewById(R.id.editTextTextUserId)).getText().toString();

        if (userId.isEmpty()) { //Check if the EditText is empty, if the user did not write anything, then notify the user
            ((EditText) findViewById(R.id.editTextTextUserId)).setError("Missing User Id");
        }

        //longitude
        String longitude1 = ((EditText) findViewById(R.id.editTextTextlongitude)).getText().toString();
        if (longitude1.isEmpty()) { //Check if the EditText is empty, if the user did not write anything, then notify the user
            ((EditText) findViewById(R.id.editTextTextlongitude)).setError("Missing Longitude");
        }

        //latitude
        String latitude1 = ((EditText) findViewById(R.id.editTextTextlatitude)).getText().toString();
        if (latitude1.isEmpty()) { //Check if the EditText is empty, if the user did not write anything, then notify the user
            ((EditText) findViewById(R.id.editTextTextlatitude)).setError("Missing Latitude");
        }

        //dt
        dt = ((EditText) findViewById(R.id.editTextTexttimestamp)).getText().toString();
        if (dt.isEmpty()) { //Check if the EditText is empty, if the user did not write anything, then notify the user
            ((EditText) findViewById(R.id.editTextTexttimestamp)).setError("Missing Timestamp");
        }

        longitude = Float.parseFloat(longitude1); //convert string to int
        latitude = Float.parseFloat(latitude1);
        id = Integer.parseInt(id1);
        if (!id1.isEmpty() && !userId.isEmpty() && !longitude1.isEmpty() && !latitude1.isEmpty() && !dt.isEmpty()) {
            checkEmpty = true;
        } else {
            checkEmpty = false;
        }
        return  checkEmpty;
    }





    protected long save_data_to_database (int id,String userId, float longitude, float latitude, String dt){
        ContactContract row = new ContactContract(id,userId, longitude, latitude, dt); //creating an object every time that the user wants to save data
        SQLiteDatabase database=helper.getWritableDatabase();
        long insertId=helper.CreateEntry(row); //when an entry is creating, an insert id is returned
        database.close();
        Toast.makeText(firstActivity.this, "Data saved in database! insert id:" +insertId,Toast.LENGTH_SHORT).show();
        return insertId;
    }





    protected void click_Go_To_Second(){ //if user clicks the button to go to the second activity
        button2=findViewById(R.id.Activity2);
        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(firstActivity.this, Second_Activity.class); //explicitly telling that where it will run and which one to wake up
                startActivityForResult(intent,0);
                Toast.makeText(firstActivity.this, "Heading to second activity!",Toast.LENGTH_SHORT).show();

            }
        });


    }



}