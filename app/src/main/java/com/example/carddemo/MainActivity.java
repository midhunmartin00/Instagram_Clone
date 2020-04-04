package com.example.carddemo;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.constraintlayout.widget.ConstraintSet;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.util.Log;
import android.util.TypedValue;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import com.parse.LogInCallback;
import com.parse.Parse;
import com.parse.ParseACL;
import com.parse.ParseAnalytics;
import com.parse.ParseUser;
import com.parse.SignUpCallback;

import java.text.ParseException;
import java.util.Objects;

public class MainActivity extends AppCompatActivity implements View.OnKeyListener,View.OnClickListener {

        EditText username,password;
        TextView textView;
        Button button;
        ConstraintLayout constraintLayout;
        ImageView imageView;
        Boolean signup=true;

        public void startnewactivity(){
        Intent intent=new Intent(getApplicationContext(),buttonActivity.class);
        startActivity(intent);
        finish();
        }
    public void signup(View view){
        if(signup) {
            if (username.getText().toString().equals("") || password.getText().toString().equals(""))
                Toast.makeText(this, "Username or password missing", Toast.LENGTH_SHORT).show();
            else {
                ParseUser user = new ParseUser();
                user.setUsername(username.getText().toString());
                user.setPassword(password.getText().toString());
                user.signUpInBackground(new SignUpCallback() {
                    @Override
                    public void done(com.parse.ParseException e) {
                        if (e == null) {
                            Log.i("result", "Success");
                            startnewactivity();

                        } else {
                            e.printStackTrace();
                            Toast.makeText(MainActivity.this, e.getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    }
                });
            }
        }else {
            ParseUser.logInInBackground(username.getText().toString(), password.getText().toString(), new LogInCallback() {
                @Override
                public void done(ParseUser user, com.parse.ParseException e) {
                    if(user!=null){
                        Log.i("success", "done: ");
                        startnewactivity();
                    }
                    else {
                        Toast.makeText(MainActivity.this, e.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                }
            });
        }
    }
        @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
//        Parse.enableLocalDatastore(this);
//    ParseUser.enableAutomaticUser();

            username=findViewById(R.id.username);
            password=findViewById(R.id.password);
            button=findViewById(R.id.button3);
            textView=findViewById(R.id.textView);
            imageView=findViewById(R.id.logoimage);
            constraintLayout=findViewById(R.id.constraintLayout);
            imageView.setOnClickListener(this);
            constraintLayout.setOnClickListener(this);

            if(ParseUser.getCurrentUser()!=null)
                startnewactivity();

            password.setOnKeyListener(this);
            textView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if(signup) {
                        signup = false;
                        button.setText("Login");
                        textView.setText("or, Sign up");
                    }
                    else{
                        signup = true;
                        button.setText("Sign Up");
                        textView.setText("or, login");
                    }
                }
            });
            ParseAnalytics.trackAppOpenedInBackground(getIntent());
    }
    @Override
    public boolean onKey(View view, int i, KeyEvent keyEvent) {
        if(i==KeyEvent.KEYCODE_ENTER && keyEvent.getAction()== KeyEvent.ACTION_DOWN){
            signup(view);
        }
        return false;
    }

    @Override
    public void onClick(View view) {
        InputMethodManager inputMethodManager=(InputMethodManager) getSystemService(INPUT_METHOD_SERVICE);
        inputMethodManager.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(),0);
    }
}
