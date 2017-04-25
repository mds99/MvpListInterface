package ru.pp.mita.mvplistinterface;

import android.location.Location;
import android.view.View;

import java.util.UUID;

/**
 * Created by mds on 4/22/2017.
 */

public class WayPoints {
    int id;
    String strName;
    Location location;
    Integer iHours2go;

    public WayPoints(String name,Location loc,Integer hours2go) {
        id=(int) UUID.randomUUID().getMostSignificantBits();
        strName = name;
        location = loc;
        iHours2go = hours2go;
    }
}
