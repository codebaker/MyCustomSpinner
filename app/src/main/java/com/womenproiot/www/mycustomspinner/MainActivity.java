package com.womenproiot.www.mycustomspinner;

import android.support.constraint.ConstraintLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.Spinner;
import android.widget.TabHost;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements OnItemSelectedListener{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Spinner mySpinner = (Spinner) findViewById(R.id.spinner);
        ArrayList<MeetupDto> spinnerList = LinkDAO.getInstance(this).selectMeetupList();
        MySpinner adapter = new MySpinner(this, R.layout.row, spinnerList);
        mySpinner.setAdapter(adapter);
        mySpinner.setOnItemSelectedListener(this);
    }


    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
        ConstraintLayout layout = (ConstraintLayout)view;
        TextView seq = (TextView)layout.getViewById(R.id.textViewSeq);
        ArrayList<AttendeeDto> list = LinkDAO.getInstance(this).selectAttenddee(seq.getText().toString());

        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        AttendeeRecyclerAdapter adapter = new AttendeeRecyclerAdapter(list);
        recyclerView.setAdapter(adapter);

        Toast.makeText(this,seq.getText().toString(),Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }
}
