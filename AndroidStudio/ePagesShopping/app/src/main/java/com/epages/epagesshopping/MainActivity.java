package com.epages.epagesshopping;

import android.app.Activity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends Activity {
    private TextView message;
    private Button doneNext;
    private EditText input;
    private boolean firstClick;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        message = (TextView) findViewById(R.id.message);
        doneNext = (Button) findViewById(R.id.done_next);
        input = (EditText) findViewById(R.id.manual_input);

        firstClick = true;
        message.setText(R.string.welcome);
        doneNext.setText(R.string.next);

        doneNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (firstClick){
                    message.setText(getString(R.string.start_shopping, input.getText()));
                    input.setVisibility(View.VISIBLE);
                    doneNext.setText(R.string.done);
                    firstClick = false;
                } else {
                    finish();
                }
            }
        });

        //make button visible only if input is filled in
        input.addTextChangedListener(new TextWatcher(){

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count){}

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after){}

            @Override
            public void afterTextChanged(Editable s){
                doneNext.setEnabled(s.length() > 0 );
            }
        });


        doneNext.setEnabled(false);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item){
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings){
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
