package org.androidtown.myapplication;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

public class Borrow2Activity extends AppCompatActivity {

    public  static final int Return_Time = 1;
    int end_hour;
    int end_minute;
    int time[]={0,0,0,0};
    TextView endT;
    String h , m;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_borrow2);

        //----------툴바------------
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayShowCustomEnabled(true); //커스터마이징 하기 위해 필요
        actionBar.setDisplayShowTitleEnabled(false);
        //actionBar.setDisplayHomeAsUpEnabled(true); // 뒤로가기 버튼, 디폴트로 true만 해도 백버튼이 생김
        //actionBar.setHomeAsUpIndicator(R.drawable.ic_back); //뒤로가기 버튼을 본인이 만든 아이콘으로 하기 위해 필요
        //------------------------


        ImageButton btn4 =(ImageButton) findViewById(R.id.button4);//reutrn botton
        ImageButton btn5 = (ImageButton) findViewById(R.id.button5); //pause botton
        ImageButton btn6 = (ImageButton) findViewById(R.id.button6); //extend botton


        //서버에서 예약시간 가져오기
        SharedPreferences pref = getSharedPreferences("Temp", Activity.MODE_PRIVATE);
        end_hour = pref.getInt("hour", 0);
        end_minute = pref.getInt("minute", 0);


        h = String.valueOf(end_hour);
        m = String.valueOf(end_minute);
        if (end_hour < 10) h = 0 + h;
        if (end_minute < 10) m = 0 + m;

        endT = findViewById(R.id.end);
        endT.setText(h + " : " + m);

        btn4.setOnClickListener(new View.OnClickListener() { //자전거 반납
            @Override
            public void onClick(View v) { //반납
                Intent intent = new Intent(getApplicationContext(), ReturnActivity.class);
                startActivity(intent);
                finish();
            }
        });

        btn5.setOnClickListener(new View.OnClickListener() { //일시정지
            @Override
            public void onClick(View v) { //일시정지


                Intent intent = new Intent(getApplicationContext(), PauseActivity.class);
                startActivity(intent);
            }
        });


        btn6.setOnClickListener(new View.OnClickListener() { //연장
            @Override
            public void onClick(View v) { //연장
                Intent intent = new Intent(getApplicationContext(), ExtendActivity.class);
                startActivityForResult(intent, Return_Time);

            }
        });


    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data){
        if(requestCode==Return_Time){
            if(resultCode==RESULT_OK){
                int etime[] = data.getIntArrayExtra("extend_time");
                end_hour = etime[2];
                end_minute = etime[3];
                h = String.valueOf(end_hour);
                m = String.valueOf(end_minute);

             if (end_hour < 10) h = 0 + h;
             if (end_minute < 10) m = 0 + m;

            endT.setText(h + " : " + m);

        }}
    }


}
