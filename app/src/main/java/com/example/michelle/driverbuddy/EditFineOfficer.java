package com.example.michelle.driverbuddy;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

public class EditFineOfficer extends AppCompatActivity {

    Button edit_fine_button;
    public void edit_fine_officer()
    {
        edit_fine_button=(Button)findViewById(R.id.edit_fine_button);
        edit_fine_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent nextActivity;
                nextActivity = new Intent(EditFineOfficer.this,WriteFine.class);
                startActivity(nextActivity);
            }
        });
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_fine_officer);
        //add back button
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        edit_fine_officer();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if(id == android.R.id.home){
            this.finish();
        }

        return super.onOptionsItemSelected(item);
    }
}
