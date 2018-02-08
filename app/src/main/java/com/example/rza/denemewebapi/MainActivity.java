package com.example.rza.denemewebapi;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import java.util.List;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button getstudents = (Button) findViewById(R.id.button);
        Button createstudetns = (Button) findViewById(R.id.button2);
        final EditText fname = (EditText) findViewById(R.id.txtfname);
        final EditText lname = (EditText) findViewById(R.id.txtlname);
        final EditText department = (EditText) findViewById(R.id.txtdpt);

        getstudents.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showStudents();
                Toast.makeText(getApplicationContext(),"Check Android Monitor For The Results",Toast.LENGTH_SHORT).show();
            }
        });

        createstudetns.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!fname.getText().toString().isEmpty() && !lname.getText().toString().isEmpty() && !department.getText().toString().isEmpty()){
                    Students students = new Students(
                            fname.getText().toString(),
                            lname.getText().toString(),
                            department.getText().toString()
                    );
                    createStudents(students);
                    fname.setText("");
                    lname.setText("");
                    department.setText("");
                }
                else
                {
                    Toast.makeText(getApplicationContext(),"Please Fill The Fields",Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private void createStudents(Students students) {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Api.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        Api api = retrofit.create(Api.class);

        Call<Students> call = api.createStudents(students);

        call.enqueue(new Callback<Students>() {
            @Override
            public void onResponse(Call<Students> call, Response<Students> response) {
                Toast.makeText(getApplicationContext(),"Succesfully Created " + response.body().getFirstName(),Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onFailure(Call<Students> call, Throwable t) {
                Toast.makeText(getApplicationContext(),t.getMessage(),Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void showStudents() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Api.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        Api api = retrofit.create(Api.class);

        Call<List<Students>> call = api.getStudents();

        call.enqueue(new Callback<List<Students>>() {
            @Override
            public void onResponse(Call<List<Students>> call, Response<List<Students>> response) {
                List<Students> students = response.body();
                //Show's on the ANDROID MONITOR
                for (Students st : students){
                    Log.d("FirstName",st.getFirstName());
                    Log.d("LastName",st.getLastName());
                    Log.d("Department",st.getDepartment());
                }
            }

            @Override
            public void onFailure(Call<List<Students>> call, Throwable t) {
                Toast.makeText(getApplicationContext(),t.getMessage(),Toast.LENGTH_SHORT).show();
            }
        });
    }
}
