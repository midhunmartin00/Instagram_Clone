package com.example.carddemo.ui.notifications;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.example.carddemo.R;
import com.parse.FindCallback;
import com.parse.GetDataCallback;
import com.parse.ParseException;
import com.parse.ParseFile;
import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.parse.ParseUser;

import java.util.List;

public class NotificationsFragment extends Fragment {

//    private NotificationsViewModel notificationsViewModel;
    private LinearLayout linearLayout;
    private LayoutInflater layoutInflater;
    private View format;
    private int count=0;
    private ImageView imageView1;
    private ImageView imageView2;
    private ImageView imageView3;
    private CardView cardView;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
//        notificationsViewModel =
//                ViewModelProviders.of(this).get(NotificationsViewModel.class);
        final View root = inflater.inflate(R.layout.fragment_notifications, container, false);
        TextView textView=root.findViewById(R.id.Username);
        textView.setText(ParseUser.getCurrentUser().getUsername().toString());


//        final TextView textView = root.findViewById(R.id.text_notifications);
//        notificationsViewModel.getText().observe(getViewLifecycleOwner(), new Observer<String>() {
//            @Override
//            public void onChanged(@Nullable String s) {
//                textView.setText(s);
//            }
//        });
        linearLayout=root.findViewById(R.id.linearLayout);
        ParseQuery<ParseObject> query=ParseQuery.getQuery("image");
        query.whereEqualTo("username",ParseUser.getCurrentUser().getUsername());
        query.orderByDescending("createdAt");
        count=0;
        layoutInflater= (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        format=layoutInflater.inflate(R.layout.grid_layout,null);
        imageView1=format.findViewById(R.id.imageView2);
        imageView2=format.findViewById(R.id.imageView3);
        imageView3=format.findViewById(R.id.imageView4);
        query.findInBackground(new FindCallback<ParseObject>() {
            @Override
            public void done(List<ParseObject> objects, ParseException e) {
                if(e==null) {
                    if (objects.size() > 0) {
                        for (ParseObject ob : objects) {
                            ParseFile parseFile = ob.getParseFile("image");
                            try {
                                byte[] data=parseFile.getData();
                                Bitmap bitmap = BitmapFactory.decodeByteArray(data, 0, data.length);
                                count++;
                                if(count==4){
                                    linearLayout.addView(format);
                                    count=1;
                                    layoutInflater= (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                                    format=layoutInflater.inflate(R.layout.grid_layout,null);
                                    imageView1=format.findViewById(R.id.imageView2);
                                    imageView2=format.findViewById(R.id.imageView3);
                                    imageView3=format.findViewById(R.id.imageView4);
                                }
                                if(count==1) {
                                    imageView1.setImageBitmap(bitmap);
                                    Log.i("success", "done:1");
                                }
                                else if(count==2) {
                                    imageView2.setImageBitmap(bitmap);
                                    Log.i("success", "done:2");
                                }
                                else {
                                    imageView3.setImageBitmap(bitmap);
                                    Log.i("success", "done:3");
                                }

                            } catch (ParseException ex) {
                                ex.printStackTrace();
                            }

                            /*
                            parseFile.getDataInBackground(new GetDataCallback() {
                                @Override
                                public void done(byte[] data, ParseException e) {
                                    if(e==null && data!=null) {
                                        Bitmap bitmap = BitmapFactory.decodeByteArray(data, 0, data.length);
                                        count++;
                                        if(count==4){
                                            linearLayout.addView(format);
                                            count=1;
                                            layoutInflater= (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                                            format=layoutInflater.inflate(R.layout.grid_layout,null);
                                            imageView1=format.findViewById(R.id.imageView2);
                                            imageView2=format.findViewById(R.id.imageView3);
                                            imageView3=format.findViewById(R.id.imageView4);
                                        }
                                        try {
                                            if(count==1) {
                                                imageView1.setImageBitmap(bitmap);
                                                Log.i("success", "done:1");
                                                cardView=(CardView) format.findViewWithTag("5");
                                                cardView.setVisibility(View.INVISIBLE);
                                                cardView=(CardView) format.findViewWithTag("6");
                                                cardView.setVisibility(View.INVISIBLE);
                                                linearLayout.addView(format);
                                            }
                                            else if(count==2) {
                                                imageView2.setImageBitmap(bitmap);
                                                Log.i("success", "done:2");
                                            }
                                            else {
                                                imageView3.setImageBitmap(bitmap);
                                                Log.i("success", "done:3");
                                            }
                                        } catch (Exception e1) {
                                            Log.i("error", "done: " + e1.getMessage());
                                            Log.i("error", "count= "+ Integer.toString(count));
                                        }
                                    }
                                }
                            });

                             */
                        }
                            Log.i("count", Integer.toString(count));
                            DisplayMetrics metrics = Resources.getSystem().getDisplayMetrics();
                            float px = 5 * (metrics.densityDpi / 160f);
                            GridLayout.LayoutParams layoutParams=new GridLayout.LayoutParams();
                            layoutParams.setMargins(0, (int) px,0,0);
                            format.setLayoutParams(layoutParams);
                            if(count==1){
                                cardView=(CardView) format.findViewWithTag("5");
                                cardView.setVisibility(View.INVISIBLE);
                                cardView=(CardView) format.findViewWithTag("6");
                                cardView.setVisibility(View.INVISIBLE);
                                linearLayout.addView(format);
                            }
                            else if(count==2){
                                cardView=(CardView) format.findViewWithTag("6");
                                cardView.setVisibility(View.INVISIBLE);
                                linearLayout.addView(format);
                            }
                            else if(count==3){
                                linearLayout.addView(format);
                            }
                    }
                }
                else{
                    Log.i("error", e.getMessage());
                    Toast.makeText(getContext(), e.getMessage(), Toast.LENGTH_SHORT).show();
                }
            }
        });
        return root;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }
}
