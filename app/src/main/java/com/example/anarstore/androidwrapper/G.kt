package com.example.anarstore.androidwrapper

import android.app.Application
import com.example.anarstore.R
import com.parse.Parse

class G : Application()  {

    override fun onCreate() {
        super.onCreate()


        Parse.initialize(
            Parse.Configuration.Builder(applicationContext)
                .server(getString(R.string.back4app_server_url))
                .clientKey(getString(R.string.back4app_client_key))
                .applicationId(getString(R.string.back4app_app_id))
                .build()
        )

    }


}