package ru.pp.mita.mvplistinterface;

import com.arellomobile.mvp.MvpView;

import java.util.ArrayList;

/**
 * Created by mds on 4/22/2017.
 */

public interface WayPointsView extends MvpView {
    void wpListView(ArrayList<WayPoints> wp);
    void close();
}
