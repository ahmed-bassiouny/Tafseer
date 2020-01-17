package com.bassiouny.tafseer.model.repositories

import com.bassiouny.tafseer.model.local_data.QuranIndex
import com.bassiouny.tafseer.model.model.Sura
import com.google.gson.Gson

class QuranRepo {

    companion object instance {
    fun getIndex() : List<Sura>{
        return  Gson().fromJson(QuranIndex.quarnIndexJsonString, Array<Sura>::class.java).asList()
    }


    }
}