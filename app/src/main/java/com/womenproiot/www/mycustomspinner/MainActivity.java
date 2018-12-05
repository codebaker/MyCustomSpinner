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

        /*
         *db에서 모임 title과 seq를 불러와서 Spinner에 뿌려준다.
         */
        ArrayList<MeetupDto> spinnerList = LinkDAO.getInstance(this).selectMeetupList();
        //화면에서 spinner 객체를 가져오고
        Spinner mySpinner = (Spinner) findViewById(R.id.spinner);
        //커스터마이징한 어댑터와 아이템 레이아웃 사용
        MySpinner adapter = new MySpinner(this, R.layout.item_spinner, spinnerList);
        mySpinner.setAdapter(adapter);
        //이벤트 리스너를 단다
        mySpinner.setOnItemSelectedListener(this);
    }


    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
        //스피너에서 아이템중 하나를 선택하면 호출된다
        //view는 item_spinner의 가장 바깥 View
        ConstraintLayout layout = (ConstraintLayout)view;
        //layout에서 gone으로 설정된 seq를 불러온다.
        TextView seq = (TextView)layout.getViewById(R.id.itemSpinnerLayout);
        //seq(모임 테이블의 pk)를 들고 db에서 참석자들의 위치를 가져온다.
        ArrayList<AttendeeDto> list = LinkDAO.getInstance(this).selectAttendee(seq.getText().toString());

        //db에서 가져온 참석자 리스트를 리사이클러뷰에 뿌려준다.
        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        //리스트 형식으로 보여줄꺼니까 LinearLayoutManager를 선택한다.
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
