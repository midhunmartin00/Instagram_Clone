package com.example.carddemo.ui.home;

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
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.cardview.widget.CardView;
import androidx.constraintlayout.widget.ConstraintLayout;
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
import java.util.Objects;

public class HomeFragment extends Fragment {

    private HomeViewModel homeViewModel;
    LinearLayout linearLayout;

    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
//        homeViewModel = ViewModelProviders.of(this).get(HomeViewModel.class);
        View root = inflater.inflate(R.layout.fragment_home,container,false);
//        final TextView textView = root.findViewById(R.id.text_home);
//        homeViewModel.getText().observe(getViewLifecycleOwner(), new Observer<String>() {
//            @Override
//            public void onChanged(@Nullable String s) {
//                textView.setText(s);
//            }
//        });
        linearLayout=root.findViewById(R.id.linLayout);
        ParseQuery<ParseObject> query=ParseQuery.getQuery("image");
        query.whereNotEqualTo("username", ParseUser.getCurrentUser().getUsername());
        query.orderByDescending("username");
        Log.i("success", "done: ");
        query.findInBackground(new FindCallback<ParseObject>() {
            @Override
            public void done(List<ParseObject> objects, ParseException e) {
                if(e==null){
                    if(objects.size()>0){
                        for(final ParseObject ob:objects){
                            ParseFile parseFile=ob.getParseFile("image");
                            parseFile.getDataInBackground(new GetDataCallback() {
                                @Override
                                public void done(byte[] data, ParseException e) {
                                    if(e==null && data!=null) {
                                        Bitmap bitmap = BitmapFactory.decodeByteArray(data, 0, data.length);
                                        try {
                                            DisplayMetrics metrics = Resources.getSystem().getDisplayMetrics();
                                            float px = 5 * (metrics.densityDpi / 160f);
                                            LayoutInflater layoutInflater = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                                            View view = layoutInflater.inflate(R.layout.format_layout, null);

                                            ImageView imageView = view.findViewById(R.id.imageView5);
                                            imageView.setImageBitmap(bitmap);
//                                            imageView.setImageDrawable(getResources().getDrawable(R.drawable.nature4,null));
                                            TextView textView = view.findViewById(R.id.textView5);
                                            textView.setText(ob.getString("username"));

                                            imageView.setMaxHeight(imageView.getWidth());
                                            imageView.setMinimumHeight(imageView.getWidth());
                                            ConstraintLayout.LayoutParams layoutParams = new ConstraintLayout.LayoutParams(ConstraintLayout.LayoutParams.MATCH_PARENT, ConstraintLayout.LayoutParams.WRAP_CONTENT);
                                            layoutParams.setMargins(0, (int) px, 0, 0);
                                            view.setLayoutParams(layoutParams);
                                            linearLayout.addView(view);
                                        }catch (Exception e1){
                                            Log.i("error", e.getMessage());
                                            e1.printStackTrace();
                                        }
                                    }else{
                                        e.printStackTrace();
                                        Log.i("error", e.getMessage());
                                    }
                                }
                            });
                        }
                    }
                }
            }
        });
        return root;
    }
}
