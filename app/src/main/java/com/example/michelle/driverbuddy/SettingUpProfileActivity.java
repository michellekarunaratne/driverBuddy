package com.example.michelle.driverbuddy;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class SettingUpProfileActivity extends AppCompatActivity {

    public Button btn_done;

    public void SignOut()
    {
        btn_done=(Button) findViewById(R.id.settingUpDone_btn);
        btn_done.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent done= new Intent(SettingUpProfileActivity.this, Logging.class);
                startActivity(done);
                finish();
            }
        });



    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting_up_profile);
        SignOut();
    }
}
