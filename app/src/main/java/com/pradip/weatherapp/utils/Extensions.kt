package com.pradip.weatherapp.utils

import android.view.View

/**
 *  Created by Pradip Awasthi on 2019-09-15.
 */


fun View.setVisibleState(visible: Boolean) {
    this.visibility = if (visible) View.VISIBLE else View.GONE
}