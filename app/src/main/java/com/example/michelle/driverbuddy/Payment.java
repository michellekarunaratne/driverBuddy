package com.example.michelle.driverbuddy;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.widget.TextView;

import lk.payhere.androidsdk.PHConfigs;
import lk.payhere.androidsdk.PHConstants;
import lk.payhere.androidsdk.PHMainActivity;
import lk.payhere.androidsdk.PHResponse;
import lk.payhere.androidsdk.model.InitRequest;
import lk.payhere.androidsdk.model.Item;
import lk.payhere.androidsdk.model.StatusResponse;

public class Payment extends AppCompatActivity {

    private static final String TAG = "  ";
    private final static int PAYHERE_REQUEST = 11010;
    TextView message;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_payment);
        //add back button
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        //Bundle extras = getIntent().getExtras(); String val = extras.getString("fname");


        InitRequest req = new InitRequest();
        req.setMerchantId("1211879"); //  Merchant ID
        req.setMerchantSecret("DBuddy"); // Merchant secret
        req.setAmount(100.00); // Amount which the customer should pay
        req.setCurrency("LKR"); // Currency
        req.setOrderId("fine123"); // Unique ID for payment transaction
        req.setItemsDescription("xxx");  // Item title or Order/Invoice number fine name/fine name
        req.setCustom1("Custom message 1");
        req.setCustom2("Custom message 2");
        req.getCustomer().setFirstName("Deshani");
        req.getCustomer().setLastName("Vithanage");
        req.getCustomer().setEmail("deshanivithanagek@gmail.com ");
        req.getCustomer().setPhone("0112802456");
        req.getCustomer().getAddress().setAddress("xxxxxxxxxx");
        req.getCustomer().getAddress().setCity("xxxxxxxxxx");
        req.getCustomer().getAddress().setCountry("Sri Lanka");
        req.getCustomer().getDeliveryAddress().setAddress("xxxxxxxxxx");
        req.getCustomer().getDeliveryAddress().setCity("xxxxxxxxxx");
        req.getCustomer().getDeliveryAddress().setCountry("Sri Lanka");
        req.getItems().add(new Item(null, " ", 1));

        Intent intent = new Intent(this, PHMainActivity.class);
        intent.putExtra(PHConstants.INTENT_EXTRA_DATA, req);
        PHConfigs.setBaseUrl(PHConfigs.SANDBOX_URL);
        startActivityForResult(intent, PAYHERE_REQUEST);

        onActivityResult( 11010, 12345, intent);

    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        //TODO process response
        if (requestCode == PAYHERE_REQUEST && data != null && data.hasExtra(PHConstants.INTENT_EXTRA_RESULT)) {
            PHResponse<StatusResponse> response = (PHResponse<StatusResponse>) data.getSerializableExtra(PHConstants.INTENT_EXTRA_RESULT);
            String msg;
            if (response.isSuccess()) {
                msg = "Activity result:" + response.getData().toString();
                Log.d(TAG, msg);
            } else {
                msg = "Result:" + response.toString();
                Log.d(TAG, msg);
            }
            message.setText(msg);
        }
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
