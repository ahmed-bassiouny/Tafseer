package com.bassiouny.tafseer.model.model

import android.os.Parcel
import android.os.Parcelable
import com.google.gson.annotations.SerializedName

data class Aya(var text: String = "", @SerializedName(value = "numberInSurah",alternate = ["number"]) var numberInSurah: Int = 0) : Parcelable {
    constructor(source: Parcel) : this(
        source.readString(),
        source.readInt()
    )



    override fun describeContents() = 0

    override fun writeToParcel(dest: Parcel, flags: Int) = with(dest) {
        writeString(text)
        writeInt(numberInSurah)
    }

    companion object {
        @JvmField
        val CREATOR: Parcelable.Creator<Aya> = object : Parcelable.Creator<Aya> {
            override fun createFromParcel(source: Parcel): Aya = Aya(source)
            override fun newArray(size: Int): Array<Aya?> = arrayOfNulls(size)
        }
    }
}