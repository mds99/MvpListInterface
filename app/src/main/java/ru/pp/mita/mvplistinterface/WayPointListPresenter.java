package ru.pp.mita.mvplistinterface;

import java.util.ArrayList;

/**
 * Created by mds on 4/22/2017.
 */

public interface WayPointListPresenter {
    void close();
    void setEntryList(ArrayList<WayPoints> wpList);
    void addEntry();
    void upEntry(Integer id);
    void downEntry(Integer id);
    void deleteEntry(Integer id);
    void editEntry(Integer id);
}
