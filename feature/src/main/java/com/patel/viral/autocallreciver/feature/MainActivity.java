package com.patel.viral.autocallreciver.feature;


import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    Button bStart,bEnd,bSave;
    SharedPreferences sf;
    EditText editText;
    public static final String sharedtel = "sharedtel";
    public static String monNum;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        bStart = (Button) findViewById(R.id.bStart);
        bEnd = (Button) findViewById(R.id.bEnd);
        bSave = (Button) findViewById(R.id.bSave);
        editText = (EditText) findViewById(R.id.editText);
        bStart.setOnClickListener(this);
        bEnd.setOnClickListener(this);
        bSave.setOnClickListener(this);
    }


    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.bStart) {//start
            startService(new Intent(this, simple.class));

        }else if (v.getId() == R.id.bEnd) {//destroy
            stopService(new Intent(this, simple.class));

        }else if (v.getId() == R.id.bSave) {
            if (!editText.getText().toString().isEmpty()){
                SharedPreferences.Editor editor = sf.edit();
                editor.putString(sharedtel,editText.getText().toString());
                editor.commit();
                monNum = editText.getText().toString();
                Toast.makeText(this, "numero enregistrer", Toast.LENGTH_LONG).show();

            }
        }
    }


    @Override
    public void onStart() {
        super.onStart();
        if (sf.contains(sharedtel)) {
            //prendre les truc de shared pref
            monNum = sf.getString(sharedtel, null);
        }
    }

}