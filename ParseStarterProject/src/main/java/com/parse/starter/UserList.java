package com.parse.starter;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Adapter;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.parse.FindCallback;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.parse.ParseUser;

import java.util.ArrayList;
import java.util.List;

public class UserList extends AppCompatActivity {

    ListView listView;
    ArrayList<String> arrayList=new ArrayList<String>();;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_list);
        setTitle("Instagram Clone");

        listView=findViewById(R.id.listview);
        final ArrayAdapter<String> adapter=new ArrayAdapter<String >(this,android.R.layout.simple_spinner_dropdown_item,arrayList);
        ParseQuery<ParseUser> query=ParseUser.getQuery();
        query.whereNotEqualTo("username",ParseUser.getCurrentUser().getUsername());
        query.addAscendingOrder("username");
        query.findInBackground(new FindCallback<ParseUser>() {
            @Override
            public void done(List<ParseUser> objects, ParseException e) {
                if(e==null){
                    if(objects.size()>0){
                        for(ParseUser ob:objects){
                            arrayList.add(ob.getUsername());
                        }
                        listView.setAdapter(adapter);
                    }
                }
                else{
                    e.printStackTrace();
                    Log.i("error", "done: ");
                }
            }
        });
    }
}
