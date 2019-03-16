package com.example.bytecamp_raw.Activity.Fragment;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.bytecamp_raw.R;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import static android.content.Context.MODE_PRIVATE;

/**
 * Created by venkat on 23/2/19.
 */
public class ProfileFragment extends Fragment {
    TextView nameSetter;
    private DatabaseReference mDatabase;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.profile_fragment, container, false);
        SharedPreferences prefs =getActivity().getSharedPreferences("MY_PREFS_NAME", MODE_PRIVATE);
        String name = prefs.getString("name",null);
        nameSetter = view.findViewById(R.id.name_setter);
        nameSetter.setText(name);
        mDatabase = FirebaseDatabase.getInstance().getReference();


        return view;
    }
}
