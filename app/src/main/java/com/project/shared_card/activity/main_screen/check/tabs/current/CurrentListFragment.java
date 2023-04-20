package com.project.shared_card.activity.main_screen.check.tabs.current;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.project.shared_card.R;
import com.project.shared_card.activity.main_screen.check.PopupMenu;


public class CurrentListFragment extends Fragment {
    Button buttonSort;
    RecyclerView list;
    PopupMenu popupMenu;
    public CurrentListFragment() {
    }

    public static CurrentListFragment newInstance() {
        CurrentListFragment fragment = new CurrentListFragment();
//        Bundle args = new Bundle();
//        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) { }
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        list = view.findViewById(R.id.list_product);
        buttonSort = view.findViewById(R.id.button_sort);
        popupMenu = new PopupMenu(getContext(),buttonSort);

        Adapter adapter = new Adapter(getContext());
        list.setAdapter(adapter);
        buttonSort.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                popupMenu.popupMenu();
            }
        });
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_current_list, container, false);
    }
}