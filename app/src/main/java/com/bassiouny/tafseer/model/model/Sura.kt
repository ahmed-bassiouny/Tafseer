package com.bassiouny.tafseer.model.model

import android.os.Parcel
import android.os.Parcelable


data class Sura(var titleAr:String?,var count:Int,var place:String?,var index:String?) : Parcelable {



    constructor(source: Parcel) : this(
        source.readString(),
        source.readInt(),
        source.readString(),
        source.readString()
    )

    override fun describeContents() = 0

    override fun writeToParcel(dest: Parcel, flags: Int) = with(dest) {
        dest.writeString(titleAr)
        dest.writeInt(count)
        dest.writeString(place)
        dest.writeString(index)
    }

    companion object {
        @JvmField
        val CREATOR: Parcelable.Creator<Sura> = object : Parcelable.Creator<Sura> {
            override fun createFromParcel(source: Parcel): Sura = Sura(source)
            override fun newArray(size: Int): Array<Sura?> = arrayOfNulls(size)
        }
    }
}