package com.bassiouny.tafseer.view_model

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.bassiouny.tafseer.model.api.DataCall
import com.bassiouny.tafseer.model.model.Aya
import kotlin.properties.Delegates

class SuraDetailsViewModel : ViewModel() {

    var index by Delegates.observable("1") { _, oldValue, newValue ->
        if (oldValue != newValue) {
            DataCall.getSuraDetails(
                newValue,
                onSuccess = { data.value = it },
                onFailure = { error.value = it}
            )
        }
    }

    val data = MutableLiveData<List<Aya>>()

    val error = MutableLiveData<String?>()

}