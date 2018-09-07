package org.androidtown.myapplication;

import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class ShowBorrowActivity extends AppCompatActivity {
    final Context context = this;
    protected TextView viewCode ;
    protected String bookingCode;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_showborrow);

        //-----------툴바----------------
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayShowCustomEnabled(true); //커스터마이징 하기 위해 필요
        actionBar.setDisplayShowTitleEnabled(false);
        actionBar.setDisplayHomeAsUpEnabled(true); // 뒤로가기 버튼, 디폴트로 true만 해도 백버튼이 생김
        //actionBar.setHomeAsUpIndicator(R.drawable.ic_back); //뒤로가기 버튼을 본인이 만든 아이콘으로 하기 위해 필요
        //--------------------------------

        viewCode = findViewById(R.id.bookingCode);
        Button btn1 = (Button) findViewById(R.id.button_copy);
        Button btn2 = (Button) findViewById(R.id.button_change);
        Button btn3 = (Button) findViewById(R.id.button_cancel);

        bookingCode = ""; //서버에서 받아오기

        //---나중에 지우기----
        SharedPreferences pref = getSharedPreferences("Temp", Activity.MODE_PRIVATE);
        bookingCode = pref.getString("code", null);
        //-------------------

        viewCode.setText(bookingCode);

        btn1.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){ //복사버튼
                Toast.makeText(getApplicationContext(), "예약 번호를 복사했습니다.", Toast.LENGTH_LONG).show();
            }
        });

        btn2.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Intent intent = new Intent(getApplicationContext(), TimeActivity.class);
                startActivity(intent);
            }
        });

        btn3.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
               switch (v.getId()) {
                    case R.id.button_cancel:
                        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(context);

                        // 제목셋팅
                        alertDialogBuilder.setTitle("예약 삭제");

                        // AlertDialog 셋팅
                        alertDialogBuilder
                                .setMessage("예약을 삭제하시겠습니까?")
                                .setCancelable(false)
                                .setPositiveButton("예",
                                        new DialogInterface.OnClickListener() {
                                            public void onClick(
                                                    DialogInterface dialog, int id) {
                                                ShowBorrowActivity.this.finish();
                                            }
                                        })
                                .setNegativeButton("아니오",
                                        new DialogInterface.OnClickListener() {
                                            public void onClick(
                                                    DialogInterface dialog, int id) { // 다이얼로그를 취소
                                                dialog.cancel();
                                            }
                                        });

                        // 다이얼로그 생성
                        AlertDialog alertDialog = alertDialogBuilder.create();

                        // 다이얼로그 보여주기
                        alertDialog.show();
                        break;

                    default:
                        break;
                }
            }
        });
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case android.R.id.home:{
                finish();
                return true;
            }
        }
        return super.onOptionsItemSelected(item);
    }
}
