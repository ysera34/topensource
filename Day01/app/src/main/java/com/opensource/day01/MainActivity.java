package com.opensource.day01;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


//        case1
//        Fragment1 fragment1 = (Fragment1) getSupportFragmentManager().findFragmentById(R.id.fragment1);

//        case2
        final Fragment1 fragment1 = new Fragment1();
        final Fragment2 fragment2 = new Fragment2();

        Button button1 = (Button) findViewById(R.id.button1);
        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getSupportFragmentManager().beginTransaction()
                        .replace(R.id.fragment_container, Fragment1.newInstance())
                        .commit();
            }
        });
        Button button2 = (Button) findViewById(R.id.button2);
        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getSupportFragmentManager().beginTransaction()
                        .replace(R.id.fragment_container, Fragment2.newInstance())
                        .commit();
            }
        });
    }

    public void onChangeFragment(int position) {
        if (position == 0) {
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.fragment_container, Fragment1.newInstance())
                    .commit();
        } else {
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.fragment_container, Fragment2.newInstance())
                    .commit();
        }
    }
}
