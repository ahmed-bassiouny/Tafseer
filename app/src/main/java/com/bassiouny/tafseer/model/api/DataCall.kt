package com.bassiouny.tafseer.model.api

import com.bassiouny.tafseer.model.model.Aya
import com.bassiouny.tafseer.model.model.AyaList
import com.bassiouny.tafseer.model.model.Tafseer
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

object DataCall {
    fun getSuraDetails(index: String?, onSuccess: (List<Aya>) -> Unit, onFailure: (String?) -> Unit) {
        val responseCall =
            RetrofitConfig.httpApiInterface.getSuraDetails(index)
        responseCall.enqueue(object : Callback<BaseResponse<AyaList>?> {
            override fun onResponse(
                call: Call<BaseResponse<AyaList>?>,
                response: Response<BaseResponse<AyaList>?>
            ) {
                if (response.isSuccessful) {
                    if (response.body() != null && response.body()!!.code == 200) {
                        onSuccess(response.body()!!.t.list)
                    } else {
                        onFailure("Something wrong happened")
                    }
                } else {
                    onFailure(response.errorBody().toString())
                }
            }

            override fun onFailure(
                call: Call<BaseResponse<AyaList>?>,
                t: Throwable
            ) {
                onFailure(t.localizedMessage)
            }
        })
    }


    fun getTafseer(tafseerId:String,surahId:String,ayahId:String,onSuccess:(Tafseer?) -> Unit,onFailure:(String) -> Unit){
        val responseCall = RetrofitConfig.httpApiInterface.getTafseer(tafseerId,surahId,ayahId)
        responseCall.enqueue(object : Callback<Tafseer> {
            override fun onFailure(call: Call<Tafseer>, t: Throwable) {
                onFailure(t.localizedMessage)
            }

            override fun onResponse(call: Call<Tafseer>, response: Response<Tafseer>) {
                if (response.isSuccessful){
                    if (response.body() != null) {
                        onSuccess(response.body())
                    } else {
                        onFailure("Something wrong happened")
                    }
                }else{
                    onFailure(response.errorBody().toString())
                }
            }

        })
    }

    fun getAyaInfo(surahId:String,ayahId:String,onSuccess:(Aya?) -> Unit,onFailure:(String) -> Unit){
        val responseCall = RetrofitConfig.httpApiInterface.getAyaInfo(surahId,ayahId)
        responseCall.enqueue(object : Callback<BaseResponse<Aya>> {
            override fun onFailure(call: Call<BaseResponse<Aya>>, t: Throwable) {
                onFailure(t.localizedMessage)
            }

            override fun onResponse(call: Call<BaseResponse<Aya>>, response: Response<BaseResponse<Aya>>) {
                if (response.isSuccessful){
                    if (response.body() != null && response.body()?.code == 200) {
                        onSuccess(response.body()?.t)
                    } else {
                        onFailure("Something wrong happened")
                    }
                }else{
                    onFailure(response.errorBody().toString())
                }
            }

        })
    }
}