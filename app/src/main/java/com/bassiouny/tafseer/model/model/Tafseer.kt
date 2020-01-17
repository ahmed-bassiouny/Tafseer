package com.bassiouny.tafseer.model.model

import com.google.gson.annotations.SerializedName

class Tafseer {

    @SerializedName("tafseer_name")
    var tafseerName: String? = null
        get() {
            field?.let {
                return it
            }
            return ""
        }
    var text: String? = null
        get() {
            field?.let {
                return it
            }
            return ""
        }
}