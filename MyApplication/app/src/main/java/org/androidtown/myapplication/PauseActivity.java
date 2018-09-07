package org.androidtown.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

public class PauseActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pause);


        ImageButton btn = (ImageButton) findViewById(R.id.restart);

        btn.setOnClickListener(new View.OnClickListener(){ //자전거 반납
            @Override
            public void onClick(View v){
                Intent intent = new Intent(getApplicationContext(), Borrow2Activity.class);
                startActivity(intent);
                finish();
            }
        });


    }

}

