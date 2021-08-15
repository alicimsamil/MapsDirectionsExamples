package com.alicimsamil.mapsdirectionsexamples.viewmodel

import android.content.Context
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.alicimsamil.mapsdirectionsexamples.model.google.DirectionResponses
import com.alicimsamil.mapsdirectionsexamples.service.google.GoogleApiRetrofit
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.observers.DisposableSingleObserver
import io.reactivex.schedulers.Schedulers


class GoogleViewModel : ViewModel() {

    val points=MutableLiveData<DirectionResponses>()
    val error=MutableLiveData<String>()
    private val disposable=CompositeDisposable()



    suspend fun getData(context: Context,originPoint: String, destinationPoint:String, apiKey:String){
        disposable.add(

            GoogleApiRetrofit().apiServices(context)
                .getDirection(originPoint,destinationPoint,apiKey)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(object : DisposableSingleObserver<DirectionResponses>(){
                    override fun onSuccess(t: DirectionResponses) {
                        points.value=t
                    }

                    override fun onError(e: Throwable) {
                        error.value=e.localizedMessage
                    }

                })

        )

    }





}