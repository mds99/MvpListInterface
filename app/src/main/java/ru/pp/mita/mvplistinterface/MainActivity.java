package ru.pp.mita.mvplistinterface;

import android.location.Location;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.arellomobile.mvp.MvpAppCompatActivity;
import com.arellomobile.mvp.presenter.InjectPresenter;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends MvpAppCompatActivity implements WayPointsView {
    final String LOG_TAG = "mimimita";
    TextView txtView;
    Button btnAdd;

    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private LinearLayoutManager mLayoutManager;

    @InjectPresenter
    public WayPointListPresenterImpl presenter; //link to presenter impl


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);



        btnAdd = (Button) findViewById(R.id.btnAdd);
        mRecyclerView = (RecyclerView) findViewById(R.id.wpList);

        mLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(mLayoutManager);

//        mAdapter = new WayPointsListAdapter();
//        mRecyclerView.setAdapter(mAdapter);

        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                presenter.addEntry();
            }
        });

    }

    @Override
    public void wpListView(ArrayList<WayPoints> wp) {
        mAdapter = new WayPointsListAdapter(presenter, wp);
        mRecyclerView.setAdapter(mAdapter);
    }

    @Override
    public void close() {
        Log.d(LOG_TAG,"close for WayPointsView");
    }
}

