package ru.pp.mita.mvplistinterface;

/**
 * Created by mds on 4/22/2017.
 */

import android.location.Location;

import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;

import java.util.ArrayList;
import java.util.Collections;

@InjectViewState
public class WayPointListPresenterImpl extends MvpPresenter<WayPointsView> implements WayPointListPresenter{
    String LOG_TAG = "mimimita";
    ArrayList<WayPoints> wpList;

    public WayPointListPresenterImpl() {
        wpList = new ArrayList<WayPoints>();
        // wp list
        wpList.add(
                new WayPoints("Paris",new Location(""),2)
        );
        wpList.add(
                new WayPoints("Dakar",new Location(""),3)
        );
        wpList.add(
                new WayPoints("Bobruisk",new Location(""),4)
        );
        wpList.add(
                new WayPoints("Urupinsk",new Location(""),5)
        );
        wpList.add(
                new WayPoints("Zhmerinka",new Location(""),7)
        );

    }

    @Override
    protected void onFirstViewAttach() {
        super.onFirstViewAttach();
        getViewState().wpListView(wpList);
    }

    @Override
    public void close() {
        //tbd
    }

    @Override
    public void setEntryList(ArrayList<WayPoints> wpList) {
        this.wpList = wpList;
    }

    @Override
    public void addEntry() {
        if (wpList.size() > 0) {
            String lastName = wpList.get(wpList.size() - 1).strName;

            for (int i=0;i<wpList.size();i++) {
                if (wpList.get(i).strName.equals("<edit>")) {
                    return;
                }
            }
            if (! lastName.equals("<edit>")) {
                this.wpList.add(new WayPoints("<edit>", new Location(""), 1));
            }
        } else {
            this.wpList.add(new WayPoints("<edit>", new Location(""), 1));
        }
        getViewState().wpListView(wpList);
    }

    @Override
    public void upEntry(Integer id) {

        int pos = ID2Pos(id);
        if (pos != 0) {

            try {
                Collections.swap(this.wpList, pos, pos - 1);
            } catch (IndexOutOfBoundsException e) {
                //ignore
                return;
            }
            getViewState().wpListView(wpList);
        }
    }

    @Override
    public void downEntry(Integer id) {
        int pos = ID2Pos(id);
        if (pos < wpList.size()) {
            try {
                Collections.swap(this.wpList, pos, pos + 1);
            } catch (IndexOutOfBoundsException e) {
                //ignore
                return;
            }
            getViewState().wpListView(wpList);
        }
    }

    @Override
    public void deleteEntry(Integer id) {
        int pos = ID2Pos(id);
        this.wpList.remove(pos);
        getViewState().wpListView(wpList);
        //find an id inside Array List.
    }

    @Override
    public void editEntry(Integer id) {
// Intent to Editor
    }

    public int Pos2ID (int pos) {
        return wpList.get(pos).id;
    }

    public int ID2Pos (int id) {
        for (int i=0;i<wpList.size();i++) {
            if (wpList.get(i).id == id) {
                return i;
            }
        }
        return -1;
    }
}
