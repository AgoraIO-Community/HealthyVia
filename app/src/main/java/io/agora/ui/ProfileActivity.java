package io.agora.ui;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import java.util.ArrayList;
import java.util.List;

import io.agora.largegroupcall.R;

public class ProfileActivity extends AppCompatActivity {

    private Spinner spinner;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        spinner = (Spinner) findViewById(R.id.sp_car_name);

        List<String> list = new ArrayList<String>();
        list.add("Select");
        list.add("Male");
        list.add("Female");

        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(ProfileActivity.this,
                R.layout.spinner_item, R.id.spinner_text, list);
        spinner.setAdapter(dataAdapter);

    }
}
