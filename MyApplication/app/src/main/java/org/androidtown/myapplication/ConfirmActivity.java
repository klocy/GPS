package org.androidtown.myapplication;

import android.Manifest;
import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.location.LocationManager;
import android.location.SettingInjectorService;
import android.os.Build;
import android.os.Bundle;
import android.provider.Settings;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.Calendar;

//3-1
public class ConfirmActivity extends AppCompatActivity {

    TextView confrimeT, date, bn;
    Button change, cancel;
    Calendar cal = Calendar.getInstance();
    protected String bookingCode;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_confirm);



        //----------툴바-----------
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayShowCustomEnabled(true); //커스터마이징 하기 위해 필요
        actionBar.setDisplayShowTitleEnabled(false);
        //-----------------------

        Intent intent = getIntent();
        int time[] = intent.getIntArrayExtra("time"); //서버에서 시간가져오는것으로변경
        int start_h = intent.getIntExtra("start_hour", 0);
        int start_m = intent.getIntExtra("start_min", 0);
        int end_h = intent.getIntExtra("end_hour", 0);
        int end_m = intent.getIntExtra("end_min", 0);


        //TextView
        date = findViewById(R.id.date);
        confrimeT = findViewById(R.id.confrimeTime);
        bn = findViewById(R.id.bookingNum);

        //Button
        change = findViewById(R.id.change_booking);
        cancel = findViewById(R.id.cancel_booking);


        bookingCode = Create_BookingCode(time); //예약번호생성
        bn.setText(bookingCode);

        confrimeT.setText(time[0] + " : " + time[1] + " ~ " + time[2] + " : " + time[3]); //수정하기 00
        date.setText(cal.get(Calendar.YEAR) + "년 " + cal.get(Calendar.MONTH) + "월 " + cal.get(Calendar.DATE) + "일");

        change.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

            }
        });


        cancel.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

            }
        });

        //Button btn = (Button)findViewById(R.id.homeButton);
        /*btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(),MainActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);
            }
        });*/
    }


    protected String Create_BookingCode(int num[]) {
        String bn = "klocy";

        //코드만들기
        bn = bn + num[2] + num[3];

        //서버에저장하는것으로 변경
        SharedPreferences reserveTIme = getSharedPreferences("bookingCode", MODE_PRIVATE);
        SharedPreferences.Editor edi = reserveTIme.edit();
        edi.putString("code", bn);
        edi.commit();
        return bn;
    }


    public void copy(View v) {

        ClipboardManager clipboardManager = (ClipboardManager) getSystemService(CLIPBOARD_SERVICE);
        ClipData clipData = ClipData.newPlainText("Copy_Code", bookingCode); //클립보드에 ID라는 이름표로 id 값을 복사하여 저장
        clipboardManager.setPrimaryClip(clipData);


        AlertDialog.Builder toMain = new AlertDialog.Builder(this);
        toMain.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                Intent main = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(main);
                finish();
            }
        });
        toMain.setNegativeButton("No", null);
        toMain.setMessage("예약 번호가 복사되었습니다.\n메인화면으로 이동하시겠습니까?");
        toMain.show();

        //Toast.makeText(getApplicationContext(), "예약 번호를 복사했습니다.", Toast.LENGTH_LONG).show();
    }
}
