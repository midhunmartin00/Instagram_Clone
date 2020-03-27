package com.parse.starter.ui.home;

import android.content.Context;
import android.content.res.Resources;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;

import com.parse.starter.R;

import java.util.Objects;

public class HomeFragment extends Fragment {

    private HomeViewModel homeViewModel;
    LinearLayout linearLayout;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
//        homeViewModel =
//                ViewModelProviders.of(this).get(HomeViewModel.class);
        View root = inflater.inflate(R.layout.fragment_home, container, false);
//        final TextView textView = root.findViewById(R.id.text_home);
//        homeViewModel.getText().observe(getViewLifecycleOwner(), new Observer<String>() {
//            @Override
//            public void onChanged(@Nullable String s) {
//                textView.setText(s);
//            }
//        });
        try{
            linearLayout=root.findViewById(R.id.linLayout);
            DisplayMetrics metrics = Resources.getSystem().getDisplayMetrics();
            float px = 20 * (metrics.densityDpi / 160f);
            LayoutInflater layoutInflater= (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            View view=layoutInflater.inflate(R.layout.format_layout,null);
            ImageView imageView= view.findViewById(R.id.imageView5);

            TextView textView=view.findViewById(R.id.textView5);
            textView.setText("Sample Card");
            imageView.setImageDrawable(getResources().getDrawable(R.drawable.nature4,null));
            imageView.setMaxHeight(imageView.getWidth());
            imageView.setMinimumHeight(imageView.getWidth());
            CardView.LayoutParams layoutParams=new CardView.LayoutParams(CardView.LayoutParams.MATCH_PARENT,CardView.LayoutParams.WRAP_CONTENT);
            layoutParams.setMargins(0, (int) px,0,0);
            view.setLayoutParams(layoutParams);
            linearLayout.addView(view);

            View view1=layoutInflater.inflate(R.layout.format_layout,null);
            ImageView imageView1= view1.findViewById(R.id.imageView5);
            imageView1.setImageDrawable(getResources().getDrawable(R.drawable.nature5,null));
            imageView1.setMaxHeight(imageView1.getWidth());
            imageView1.setMinimumHeight(imageView1.getWidth());
            view1.setLayoutParams(layoutParams);
            linearLayout.addView(view1);

        }catch (Exception e){
            Log.i("error", Objects.requireNonNull(e.getMessage()));
            e.printStackTrace();
        }
        return root;
    }
}
