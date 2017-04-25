package ru.pp.mita.mvplistinterface;

import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by mds on 4/22/2017.
 */

public class WayPointsListAdapter extends RecyclerView.Adapter<WayPointViewHolder> {
    private ArrayList<WayPoints> mDataset;
    public WayPointListPresenterImpl presenter;

    // Provide a reference to the views for each data item
    // Complex data items may need more than one view per item, and
    // you provide access to all the views for a data item in a view holder

    // Provide a suitable constructor (depends on the kind of dataset)
    public WayPointsListAdapter(WayPointListPresenterImpl presenter, ArrayList<WayPoints> myDataset) {
        this.presenter = presenter;
        mDataset = myDataset;
    }

    // Create new views (invoked by the layout manager)
    @Override
    public WayPointViewHolder onCreateViewHolder(ViewGroup parent,
                                                 int viewType) {
        // create a new view
        View v =  LayoutInflater.from(parent.getContext()).inflate(R.layout.post_item, parent, false);
        // set the view's size, margins, paddings and layout parameters

        WayPointViewHolder vh = new WayPointViewHolder(presenter, v);
        return vh;
    }

    // Replace the contents of a view (invoked by the layout manager)
    @Override
    public void onBindViewHolder(WayPointViewHolder holder, int position) {
        // - get element from your dataset at this position
        // - replace the contents of the view with that element
        holder.Show(mDataset.get(position));

    }

    // Return the size of your dataset (invoked by the layout manager)
    @Override
    public int getItemCount() {
        return mDataset.size();
    }
}

class WayPointViewHolder extends RecyclerView.ViewHolder {
    public WayPointListPresenterImpl presenter;

    // each data item is just a string in this case
    private TextView wpName,wpHours;
    private Button btnUp;
    private Button btnDown;
    private Button btnDelete;
    private Button btnEdit;

    String LOG_TAG = "mimimita";

    private int id;


    public WayPointViewHolder(WayPointListPresenterImpl presenter, View v) {
        super(v);
        this.presenter = presenter;
        wpName = (TextView) v.findViewById(R.id.wpName);
        wpHours = (TextView) v.findViewById(R.id.wpHours);
        btnUp = (Button) v.findViewById(R.id.wp_btnUp);
        btnDown = (Button) v.findViewById(R.id.wp_btnDown);
        btnDelete = (Button) v.findViewById(R.id.wp_btnDel);
        btnEdit = (Button) v.findViewById(R.id.wp_btnEdit);

        btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d(LOG_TAG,"btnDelete for "+id);
                WayPointViewHolder.this.presenter.deleteEntry(id);
            }
        });
        btnUp.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Log.d(LOG_TAG,"btnUp for "+id);
                WayPointViewHolder.this.presenter.upEntry(id);
                Log.d(LOG_TAG,"pos:"+WayPointViewHolder.this.presenter.ID2Pos(id));
            }
        });

        btnDown.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Log.d(LOG_TAG,"btnDown for "+id);
                WayPointViewHolder.this.presenter.downEntry(id);
                Log.d(LOG_TAG,"pos:"+WayPointViewHolder.this.presenter.ID2Pos(id));
            }
        });

    }

    public void Show (WayPoints Entry) {

        id = Entry.id;
        int pos = this.presenter.ID2Pos(id);

        wpName.setText(Entry.strName);
        wpHours.setText(Entry.iHours2go.toString());
        // disable up if we're first
        if (pos == 0) {
            btnUp.setEnabled(false);
        }
        if (pos == this.presenter.wpList.size()-1) {
            btnDown.setEnabled(false);
        }

            /*
            btnGo.setText("Run to " + input);
            btnEdit.setText("Edit " + input);
            */
    }
}
