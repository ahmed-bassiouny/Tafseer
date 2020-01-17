package com.bassiouny.tafseer.view_model

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.bassiouny.tafseer.model.api.DataCall
import com.bassiouny.tafseer.model.model.Aya
import com.bassiouny.tafseer.model.model.Sura
import com.bassiouny.tafseer.model.model.Tafseer

class TafseerViewModel : ViewModel() {

    var data = MutableLiveData<Tafseer>()
    var error = MutableLiveData<String>()
    var loading = MutableLiveData<Boolean>()
    var sura = MutableLiveData<Sura>()
    var aya = MutableLiveData<Aya>()


    fun getTafseer() {
        loading.postValue(true)
        DataCall.getTafseer("1", sura.value?.index.toString(), aya.value?.numberInSurah.toString(), {
            loading.postValue(false)
            it?.let {
                data.postValue(it)
            }
        }, {
            loading.postValue(false)
            error.postValue(it)
        })
    }

    fun next() {
        if (aya.value?.numberInSurah == sura.value?.count)
            return
        loading.postValue(true)
        DataCall.getAyaInfo(sura.value?.index.toString(),(aya.value?.numberInSurah?.plus(1)).toString(),{
            it?.let {
                aya.value = it
                getTafseer()
            }
        },{
            loading.postValue(false)
            error.postValue(it)
        })
    }

    fun previous() {
        if (aya.value?.numberInSurah == 1)
            return
        loading.postValue(true)
        DataCall.getAyaInfo(sura.value?.index.toString(),(aya.value?.numberInSurah?.minus(1)).toString(),{
        it?.let {
            aya.value = it
            getTafseer()
        }
        },{
            loading.postValue(false)
            error.postValue(it)
        })
    }
}