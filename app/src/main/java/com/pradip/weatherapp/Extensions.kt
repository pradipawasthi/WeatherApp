package com.pradip.weatherapp

import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable

/**
 *  Created by Pradip Awasthi on 2019-09-14.
 */


operator fun CompositeDisposable.plus(disposable: Disposable) {
    this.add(disposable)
}