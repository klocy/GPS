package org.androidtown.myapplication;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.media.Image;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Calendar;

public class ExtendActivity extends AppCompatActivity {

    Calendar cal= Calendar.getInstance();
    int hour = cal.get(Calendar.HOUR_OF_DAY);//현재 시
    int minute = cal.get(Calendar.MINUTE);//현재 분

    int time[] = {hour,minute,hour,minute}; //시작시, 시작분, 종료시, 종료분


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_extend);


        ImageButton btn = (ImageButton) findViewById(R.id.extend);

        ImageButton hourUp = (ImageButton) findViewById(R.id.hourUp_e);
        ImageButton hourDown = (ImageButton) findViewById(R.id.hourDown_e);
        ImageButton minuteUp = (ImageButton) findViewById(R.id.minuteUp_e);
        ImageButton minuteDown = (ImageButton) findViewById(R.id.minuteDown_e);

        final TextView eHour = (TextView) findViewById(R.id.extend_hour);
        final TextView eMinute = (TextView) findViewById(R.id.extend_minute);

        SharedPreferences pref = getSharedPreferences("Temp", Activity.MODE_PRIVATE);
       time[0]= pref.getInt("hour",0);
        time[1]=pref.getInt("hour",0);
        time[2] = pref.getInt("hour",0);
        time[3] = pref.getInt("minute",0);
        String h = String.valueOf(time[2]);
        String m = String.valueOf(time[3]);

        if(time[2]<10) h = 0+h;
        if(time[3]<10) m = 0+m;

        eHour.setText(h);
        eMinute.setText(m);


        hourUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                time[2]=setTime(eHour,1);
            }
        });

        hourDown.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                time[2]=setTime(eHour,-1);
            }
        });

        minuteUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                time[3]=setTime(eMinute,5);
            }
        });

        minuteDown.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                time[3]=setTime(eMinute,-5);
            }
        });

        btn.setOnClickListener(new View.OnClickListener(){ //자전거 반납
            @Override
            public void onClick(View v){
                Intent intent = new Intent(getApplicationContext(), Borrow2Activity.class);

                if(errorCheck(time))  {
                    intent.putExtra("extend_time",time);
                    //intent.putExtra("cal",cal);
                    SharedPreferences reserveTIme = getSharedPreferences("reserveTime", MODE_PRIVATE);
                    SharedPreferences.Editor edi = reserveTIme.edit();
                    edi.putInt("hour",time[2]);
                    edi.putInt("minute",time[3]);
                    edi.commit();
                    setResult(RESULT_OK,intent);
                    finish();
                }/*else{
                    setResult(RESULT_CANCELED,intent);
                  //  finish();
                }*/
            }
        });


    }

    public  int setTime(TextView text, int num){
        int time = Integer.valueOf((String)text.getText());
        int x=60;
        String t;

        if(num==1||num==-1) x= 24;

        time = (time+num) % x;

        if(time<0) time+=x;

        t = String.valueOf(time);

        if(time <10) t = "0"+t;

        text.setText(t);

        return time;
    }

    public  boolean errorCheck(int num[]){
        double eth = 10000; //eth 잔고, db에서 가져오기
        double price = 0.01; // 단위 : eth / 5분
        double totalP = 0; //총요금
        int stime = num[0]*60+num[1];
        int etime = num[2]*60+num[3];
        int usingTime = etime-stime;
        boolean check = false;


        totalP = (usingTime/5.0)*price;


        if(usingTime<=0){
            Toast.makeText(getApplicationContext(), "잘못된 예약시간입니다.", Toast.LENGTH_LONG).show();

        }else if(totalP>eth){
            //잔액부족팝업, 충전화면 전환여부
            AlertDialog.Builder bid = new AlertDialog.Builder(this);
            bid.setTitle("잔액 부족");
            bid.setMessage("사용자 계정에 이더가 부족합니다.\n마이닝 하시겠습니까?");
            bid.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                    //충전화면으로 전환하는 인텐트
                }
            });
            bid.setNegativeButton("No",null);
            bid.show();

        }else check =true;


        return check;
    }


}
