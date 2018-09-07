package org.androidtown.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class WalletActivity extends AppCompatActivity {

    protected int c_wallet = 0;
    String wallet ;
    EditText input ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wallet);

        //-------------------툴바--------------------
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayShowCustomEnabled(true); //커스터마이징 하기 위해 필요
        actionBar.setDisplayShowTitleEnabled(false);
        actionBar.setDisplayHomeAsUpEnabled(true); // 뒤로가기 버튼, 디폴트로 true만 해도 백버튼이 생김
        //actionBar.setHomeAsUpIndicator(R.drawable.ic_back); //뒤로가기 버튼을 본인이 만든 아이콘으로 하기 위해 필요
        //------------------------------------------

        input = findViewById(R.id.walletInput);

    }

    public void onButton2Clicked(View v) {

        wallet = input.getText().toString(); //사용자의 입력값

        //서버에 입력값을 보내 오류확인
        c_wallet= 0;

        switch (c_wallet){
            case 0 : //계정생성
                Toast.makeText(getApplicationContext(), "지갑 계정을 생성 완료했습니다.", Toast.LENGTH_LONG).show();
                Intent intent = new Intent(getApplicationContext(),ShowWalletActivity.class);
                startActivity(intent);
                finish();
                break;
            case 1 : //등록된지갑
                Toast.makeText(getApplicationContext(), "이미 등록된 지갑입니다.", Toast.LENGTH_LONG).show();
                break;
            case 2 :  //존재하지 않은 지갑
                Toast.makeText(getApplicationContext(), "유효하지 않은 지갑입니다.", Toast.LENGTH_LONG).show();
                break;
        }

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
