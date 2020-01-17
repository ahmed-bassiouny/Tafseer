package com.bassiouny.tafseer.supported_file

import android.content.Context
import android.widget.Toast

fun Context.Toasty(txt:String){
    Toast.makeText(this,txt,Toast.LENGTH_SHORT)
}