package com.example.rozerpayintigration;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.razorpay.Checkout;
import com.razorpay.PaymentResultListener;

import org.json.JSONException;
import org.json.JSONObject;

public class MainActivity extends AppCompatActivity implements PaymentResultListener {

    EditText Amount;
    TextView Pay_btn,moreBtn;

    // rzp_test_OBdaQAEgNi70uo

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        Amount = findViewById(R.id.Amount);
        Pay_btn = findViewById(R.id.Pay_btn);

        moreBtn = findViewById(R.id.more_btn);



        moreBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this,NextActivity.class);
                startActivity(intent);
            }
        });







        Pay_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                startPayment();

            }
        });


    }
    public  void startPayment(){


        String samount = Amount.getText().toString();

        int amount = Math.round(Float.parseFloat(samount) * 100);


        // rounding off the amount.
        //int amount = Math.round(Float.parseFloat(samount) * 100);
        // initialize Razorpay account.
        Checkout checkout = new Checkout();

        // set your id as below
        checkout.setKeyID("rzp_test_OBdaQAEgNi70uo");

        // set image
        checkout.setImage(R.drawable.ic_launcher_background);

        // initialize json object
        JSONObject object = new JSONObject();
        try {
            // to put name
            object.put("name", "Geeks for Geeks");

            // put description
            object.put("description", "");

            // to set theme color
            object.put("theme.color", "");

            // put the currency
            object.put("currency", "INR");

            // put amount
            object.put("amount",amount);

            // put mobile number
            object.put("prefill.contact", "");

            // put email
            object.put("prefill.email", "");

            // open razorpay to checkout activity
            checkout.open(MainActivity.this, object);
        } catch (JSONException e) {
            Toast.makeText(this, "Error in Payment:"+ e.getMessage(), Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onPaymentSuccess(String s) {
        Toast.makeText(this, "Payment Success!!"+s, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onPaymentError(int i, String s) {
        Toast.makeText(this, "Payment Error"+s, Toast.LENGTH_SHORT).show();
    }
}