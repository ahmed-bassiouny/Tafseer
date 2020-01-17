package com.bassiouny.tafseer.supported_file

import android.app.Application
import com.bassiouny.tafseer.model.api.RetrofitConfig

class MyApp: Application() {

    override fun onCreate() {
        super.onCreate()
        RetrofitConfig.initRetrofitConfig();
    }
}