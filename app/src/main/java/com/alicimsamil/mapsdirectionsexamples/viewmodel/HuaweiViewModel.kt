package com.alicimsamil.mapsdirectionsexamples.viewmodel

import android.content.Context
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.alicimsamil.mapsdirectionsexamples.model.huawei.DirectionResponse
import com.alicimsamil.mapsdirectionsexamples.model.huawei.DirectionsRequest
import com.alicimsamil.mapsdirectionsexamples.service.huawei.HuaweiApiRetrofit
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.observers.DisposableSingleObserver
import io.reactivex.schedulers.Schedulers

class HuaweiViewModel : ViewModel() {

    val points=MutableLiveData<DirectionResponse>()
    val error=MutableLiveData<String>()
    private val disposable=CompositeDisposable()





    suspend fun getPoint(context:Context,directionRequest:DirectionsRequest,apiKey:String){


        disposable.add(

            HuaweiApiRetrofit().apiServices(context)
                               .getDirections(directionRequest,apiKey)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(object : DisposableSingleObserver<DirectionResponse>(){
                    override fun onSuccess(t: DirectionResponse) {
                        points.value=t
                    }

                    override fun onError(e: Throwable) {
                        error.value=e.localizedMessage
                    }


                })



        )








    }






}