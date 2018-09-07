package org.androidtown.myapplication;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.Toolbar;

public class MainActivity extends AppCompatActivity {

    protected  boolean account, booking, borrow ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //서버에서 확인하는것으로변경
        account=true;
        booking=true;
        borrow=true;


        ImageButton btn1 = (ImageButton) findViewById(R.id.create);
        ImageButton btn2 = (ImageButton) findViewById(R.id.book);
        ImageButton btn3 = (ImageButton) findViewById(R.id.rental);


        btn1.setOnClickListener(new View.OnClickListener(){ //계정생성
            @Override
            public void onClick(View v){ //지갑생성

                account=!account;//핸드폰에 등록된 계정정보가 있는 경우 true, 추후변경하기

                if(account){ //지갑정보가 있는경우
                Intent  intent = new Intent(getApplicationContext(), ShowWalletActivity.class);
                startActivity(intent);
                }else { //지갑정보가 없는경우
                Intent intent = new Intent(getApplicationContext(),WalletActivity.class);
                startActivity(intent);
                }
        }});

        btn2.setOnClickListener(new View.OnClickListener(){ //예약
            @Override
            public void onClick(View v){ //자전거 예약

                booking=false; //서버에 요청해서 예약내역 확인하는것으로 변경


                if(booking){ //예약내역이 있는경우
                    Intent  intent = new Intent(getApplicationContext(), ConfirmActivity.class);
                    startActivity(intent);
                }else { //예약내역이 없는 경우
                    Intent intent = new Intent(getApplicationContext(),TimeActivity.class);//날짜선택화면엑티비티로 변경하기
                    startActivity(intent);
                }
            }
        });

        btn3.setOnClickListener(new View.OnClickListener(){ //대여

            @Override
            public void onClick(View v){ //자전거 대여

               borrow = !borrow; //추후에 서버에서 대여 확인하는것으로 변경

                if(borrow) { //대여중인 경우
                    Intent intent = new Intent(getApplicationContext(), Borrow2Activity.class);
                    startActivity(intent);
                }else { //대여하지 않는경우
                    Intent intent = new Intent(getApplicationContext(), BorrowActivity.class);
                    startActivity(intent);
                }
            }
        });



        }
    }
