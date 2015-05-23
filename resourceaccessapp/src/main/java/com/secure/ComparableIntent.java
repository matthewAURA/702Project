package com.secure;

import android.content.Intent;

/**
* Created by matthewdyer on 23/05/15.
*/
public class ComparableIntent implements Comparable<ComparableIntent>{
    public Intent getIntent() {
        return _intent;
    }

    private Intent _intent;

    public ComparableIntent(Intent intent){
        _intent = intent;
    }

    @Override
    public int compareTo(ComparableIntent another) {
        return 0;
    }
}
