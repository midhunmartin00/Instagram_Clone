package com.example.carddemo.ui.notifications;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;

import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;

import android.util.DisplayMetrics;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.example.carddemo.R;
import com.parse.FindCallback;
import com.parse.ParseException;
import com.parse.ParseFile;
import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.parse.ParseUser;

import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link firstFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class firstFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters

    private LinearLayout linearLayout;
    private LayoutInflater layoutInflater;
    private View format;
    private int count=0;
    private ImageView imageView1;
    private ImageView imageView2;
    private ImageView imageView3;
    private ImageView imageView;
    private int temp=0;

    public firstFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
//     * @param param1 Parameter 1.
//     * @param param2 Parameter 2.
     * @return A new instance of fragment firstFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static firstFragment newInstance() {
        firstFragment fragment = new firstFragment();
//        Bundle args = new Bundle();
//        args.putString(ARG_PARAM1, param1);
//        args.putString(ARG_PARAM2, param2);
//        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        if (getArguments() != null) {
//            mParam1 = getArguments().getString(ARG_PARAM1);
//            mParam2 = getArguments().getString(ARG_PARAM2);
//        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View root=inflater.inflate(R.layout.fragment_first, container, false);

        linearLayout=root.findViewById(R.id.linearlayout);

        ParseQuery<ParseObject> query=ParseQuery.getQuery("image");
        query.whereEqualTo("username", ParseUser.getCurrentUser().getUsername());
        query.orderByAscending("createdAt");
        count=0;
        layoutInflater= (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        format= layoutInflater.inflate(R.layout.new_format,null);
        imageView1=format.findViewById(R.id.imageView1);
        imageView2=format.findViewById(R.id.imageView2);
        imageView3=format.findViewById(R.id.imageView3);
        query.findInBackground(new FindCallback<ParseObject>() {
            @SuppressLint("CutPasteId")
            @Override
            public void done(List<ParseObject> objects, ParseException e) {
                if(e==null) {
                    if (objects.size() > 0) {
                        int j=0;
                        for (ParseObject ob : objects) {
                            ParseFile parseFile = ob.getParseFile("image");
                            Log.i("file", parseFile.toString());
                            try {
                                assert parseFile != null;
                                byte[] data= parseFile.getData();
                                Bitmap bitmap= BitmapFactory.decodeByteArray(data,0,data.length);
                                Log.i("bitmap", bitmap.toString());
//                                InputStream in= parseFile.getDataStream();
//                                BufferedInputStream bufferedInputStream=new BufferedInputStream(in);
//                                Bitmap bitmap=BitmapFactory.decodeStream(bufferedInputStream);
                                count++;
                                if (count == 4) {

                                    DisplayMetrics metrics = Resources.getSystem().getDisplayMetrics();
                                    float px = 1 * (metrics.densityDpi / 160f);
                                    ConstraintLayout.LayoutParams layoutParams=new ConstraintLayout.LayoutParams(ConstraintLayout.LayoutParams.MATCH_PARENT,ConstraintLayout.LayoutParams.WRAP_CONTENT);
                                    layoutParams.setMargins(0, (int) px,0,0);
                                    format.setLayoutParams(layoutParams);
                                    linearLayout.addView(format);

                                    count = 1;
                                    layoutInflater = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                                    format = layoutInflater.inflate(R.layout.new_format, null);
                                    imageView1 = format.findViewById(R.id.imageView1);
                                    imageView2 = format.findViewById(R.id.imageView2);
                                    imageView3 = format.findViewById(R.id.imageView3);
                                }
                                if (count == 1) {
                                    imageView1.setImageBitmap(bitmap);
                                    Log.i("success", "done:1");
                                } else if (count == 2) {
                                    imageView2.setImageBitmap(bitmap);
                                    Log.i("success", "done:2");
                                } else {
                                    imageView3.setImageBitmap(bitmap);
                                    Log.i("success", "done:3");
                                }

                            } catch (Exception ex) {
//                                ex.printStackTrace();
                                Log.i("data error", ex.getMessage());
//                                Toast.makeText(getActivity(), ex.getMessage(), Toast.LENGTH_SHORT).show();
                            }
                        }
                        Log.i("count", Integer.toString(count));
                        try {

                            DisplayMetrics metrics = Resources.getSystem().getDisplayMetrics();
                            float px = 1 * (metrics.densityDpi / 160f);
                            ConstraintLayout.LayoutParams layoutParams = new ConstraintLayout.LayoutParams(ConstraintLayout.LayoutParams.MATCH_PARENT, ConstraintLayout.LayoutParams.WRAP_CONTENT);
                            layoutParams.setMargins(0, (int) px, 0, 0);
                            format.setLayoutParams(layoutParams);
                            if (count == 1) {
                                imageView = (ImageView) format.findViewById(R.id.imageView2);
                                imageView.setVisibility(View.INVISIBLE);
                                imageView = (ImageView) format.findViewById(R.id.imageView3);
                                imageView.setVisibility(View.INVISIBLE);
                                linearLayout.addView(format);
                            } else if (count == 2) {
                                imageView = (ImageView) format.findViewById(R.id.imageView3);
                                imageView.setVisibility(View.INVISIBLE);
                                linearLayout.addView(format);
                            } else if (count == 3) {
                                linearLayout.addView(format);
                            }
                        }catch (Exception e3){
                            Log.i("error3", e3.getMessage());
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
}
