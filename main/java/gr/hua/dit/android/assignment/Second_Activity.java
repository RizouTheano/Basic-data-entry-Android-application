package gr.hua.dit.android.assignment;



import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;


public class Second_Activity extends AppCompatActivity implements AdapterView.OnItemClickListener {

    static Spinner s1;
    String sp;
    String name;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second_);
        Toast.makeText(Second_Activity.this,"Are you ready to search?", Toast.LENGTH_SHORT).show();
        startSearch();
        populateSpinner();
        seeResults();
    }


    private void populateSpinner() { //method to create the dropdown list which contains the timestamps from database
        DbHelper h=firstActivity.helper;
        ArrayList<String> colRes = h.spinnerGetData();
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_spinner_item, colRes);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        s1 = findViewById(R.id.timestampSpinner);
        s1.setAdapter(adapter);

    }

    public String getSpinnerItem(Spinner spinner){
        String tmsText = spinner.getSelectedItem().toString();
        return tmsText;
    }


    protected void startSearch() { //if user clicks the button to start search
        EditText userIdSearch;
        Button searchB=findViewById(R.id.search_button);
        userIdSearch=(EditText)(findViewById(R.id.editTextUserIdSearch));
        searchB.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.O)
            @Override
            public void onClick(View v) {
                //passDataToVa();
                if(userIdSearch!=null) {
                    name = userIdSearch.getText().toString();
                    Toast.makeText(Second_Activity.this, name, Toast.LENGTH_SHORT).show();

                }else{

                    name="emptyUs";
                    Toast.makeText(Second_Activity.this, name, Toast.LENGTH_SHORT).show();

                }
                sp = getSpinnerItem(s1);
                Toast.makeText(Second_Activity.this, sp, Toast.LENGTH_SHORT).show();

                Toast.makeText(Second_Activity.this, "We got the data", Toast.LENGTH_SHORT).show();
            }

        });
    }


    protected void seeResults(){ //if user clicks the button to go to the second activity
        Button button3 = findViewById(R.id.resultButton);

        button3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent thirdIntent=new Intent(Second_Activity.this, Third_Activity.class); //explicitly telling that where it will run and which one to wake up

                thirdIntent.putExtra("userIdText", name);

                thirdIntent.putExtra("spinner", sp);
                startActivityForResult(thirdIntent,0);

            }
        });


    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        String text = parent.getItemAtPosition(position).toString();
        Toast.makeText(parent.getContext(),"You selected:"+ text, Toast.LENGTH_SHORT).show();
    }

}
