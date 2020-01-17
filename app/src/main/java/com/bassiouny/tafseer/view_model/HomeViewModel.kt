package com.bassiouny.tafseer.view_model

import android.content.Context
import android.content.Intent
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.bassiouny.tafseer.model.model.Sura
import com.bassiouny.tafseer.model.repositories.QuranRepo
import com.bassiouny.tafseer.view.activities.SuraDetailsActivity
import kotlin.properties.Delegates

class HomeViewModel : ViewModel() {
    lateinit var list : List<Sura>

    val quranList by lazy {
        list = QuranRepo.getIndex()
        MutableLiveData<List<Sura>>(list)
    }

    var searchBy by Delegates.observable("") { _, oldValue, newValue ->
        if (oldValue != newValue) {
            quranList.postValue(list.filter { s -> s.titleAr!!.contains(newValue)})
        }
    }


}