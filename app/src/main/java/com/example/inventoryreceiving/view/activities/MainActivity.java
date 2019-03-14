package com.example.inventoryreceiving.view.activities;

import android.content.Intent;
import android.content.IntentFilter;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.inventoryreceiving.R;
import com.example.inventoryreceiving.model.Product;
import com.example.inventoryreceiving.model.datasource.MyBroadcastReceiver;

public class MainActivity extends AppCompatActivity {

    public static final String SEND_BROADCAST = "example.broadcastReceivers.SEND_BROADCAST";
    MyBroadcastReceiver myBroadcastReceiver;
    IntentFilter intentFilter;

    EditText productName;
    EditText productID;
    EditText inventoryCount;
    EditText description;
    Button sendInfo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        bindFields();

    }

    private void bindFields() {
        productID = findViewById(R.id.etProductIdDisplay);
        productName = findViewById(R.id.etProductNameDisplay);
        inventoryCount = findViewById(R.id.etProductInventoryCountDisplay);
        description = findViewById(R.id.etProductDescriptionDisplay);
        sendInfo = findViewById(R.id.btnSentInfo);
    }

    public void onClick(View view){
        String id = productID.getText().toString();
        String name = productName.getText().toString();
        String count = inventoryCount.getText().toString();
        String productDescription = description.getText().toString();
        Product product = new Product(Integer.valueOf(id),name,count,productDescription);

        Bundle bundle = new Bundle();
        bundle.putParcelable("Product", product);

        Intent intent = new Intent(SEND_BROADCAST);//create intent to send the broadcast
        intent.putExtras(bundle);//insert our message into the intent
        sendBroadcast(intent, "inventoryManagemen");//send broadcast with permissions requirement

    }
}
