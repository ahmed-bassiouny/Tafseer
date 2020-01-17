package com.bassiouny.tafseer.model.model

import com.google.gson.annotations.SerializedName

class AyaList {

    @SerializedName("ayahs")
    lateinit var list : List<Aya>
}