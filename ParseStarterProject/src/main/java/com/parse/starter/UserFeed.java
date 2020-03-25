package com.parse.starter;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.provider.ContactsContract;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.parse.FindCallback;
import com.parse.GetDataCallback;
import com.parse.ParseException;
import com.parse.ParseFile;
import com.parse.ParseObject;
import com.parse.ParseQuery;

import java.util.List;

public class UserFeed extends AppCompatActivity {

    LinearLayout linearLayout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_feed);
        Intent intent=getIntent();
        String s=intent.getStringExtra("username");
        setTitle(s+"'s photos");
        linearLayout=findViewById(R.id.linlayout);

        ParseQuery<ParseObject> query=ParseQuery.getQuery("image");
        query.whereEqualTo("username",s);
        query.orderByDescending("createdAt");
        query.findInBackground(new FindCallback<ParseObject>() {
            @Override
            public void done(List<ParseObject> objects, ParseException e) {
                if(e==null){
                    if(objects.size()>0){
                        for(ParseObject ob:objects){
                            ParseFile parseFile=ob.getParseFile("image");
                            parseFile.getDataInBackground(new GetDataCallback() {
                                @Override
                                public void done(byte[] data, ParseException e) {
                                    if(e==null && data!=null) {
                                        Bitmap bitmap = BitmapFactory.decodeByteArray(data, 0, data.length);
                                        ImageView imageView=new ImageView(getApplicationContext());
                                        imageView.setLayoutParams(new ViewGroup.LayoutParams(
                                                ViewGroup.LayoutParams.MATCH_PARENT,ViewGroup.LayoutParams.WRAP_CONTENT
                                        ));
                                        imageView.setImageBitmap(bitmap);
                                        linearLayout.addView(imageView);
                                    }else{
                                        e.printStackTrace();
                                        Log.i("error", e.getMessage());
                                    }
                                }
                            });
                        }
                    }
                    else {
                        Toast.makeText(UserFeed.this, "Your feed is Empty", Toast.LENGTH_SHORT).show();
                    }
                }
                else {
                    e.printStackTrace();
                    Log.i("error", e.getMessage());
                }
            }
        });
    }
}
