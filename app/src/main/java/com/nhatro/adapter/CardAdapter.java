package com.nhatro.adapter;

import android.support.v7.widget.CardView;

/**
 * Created by CongHoang on 2/25/2018.
 */

public interface CardAdapter {
    int MAX_ELEVATION_FACTOR = 8;

    float getBaseElevation();

    CardView getCardViewAt(int position);

    int getCount();
}
