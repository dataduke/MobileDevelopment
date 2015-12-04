package com.epages.epagesshopping;


import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class GetProductActivity extends Activity{

    private static final String TAG = GetProductActivity.class.getSimpleName();

    private TextView product_name;
    private EditText id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_getproduct_screen);

        id = (EditText) findViewById(R.id.product_input);
        final Button button = (Button) findViewById(R.id.product_button);
        product_name = (TextView) findViewById(R.id.product_name);

        button.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick (View v) {
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        try {
                            final ProductData productdata = BackendUserUtils.getUser(id.getText().toString());

                            runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    product_name.setText(productdata.name);
                                }
                            });
                        } catch (Exception e) {
                            Log.e(TAG, "getUser()", e );
                        }
                    }
                }).start();
            }
            });
    }
}

