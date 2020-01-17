package com.bassiouny.tafseer.view.activities

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.bassiouny.tafseer.R
import com.bassiouny.tafseer.model.model.Aya
import com.bassiouny.tafseer.model.model.Sura
import com.bassiouny.tafseer.supported_file.AdapterClick
import com.bassiouny.tafseer.supported_file.Toasty
import com.bassiouny.tafseer.view.adapter.AyaAdapter
import com.bassiouny.tafseer.view.adapter.SuraAdapter
import com.bassiouny.tafseer.view_model.SuraDetailsViewModel
import kotlinx.android.synthetic.main.activity_sura_details.*

class SuraDetailsActivity : AppCompatActivity(), AdapterClick<Aya> {

    lateinit var viewModel: SuraDetailsViewModel
    lateinit var item: Sura

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sura_details)
        viewModel = ViewModelProviders.of(this).get(SuraDetailsViewModel::class.java)
        item = intent.getParcelableExtra("data") as Sura

        // set title
        title = item.titleAr

        //set back button
        supportActionBar?.also {
            it.setDisplayHomeAsUpEnabled(true)
        }



        // set index and start loading
        viewModel.index = item.index.toString()
        startLoading()

        viewModel.data.observe(this, Observer {
            stopLoading()
            it?.let {
                recycler.adapter = AyaAdapter(it,this)
            }
        })

        viewModel.error.observe(this, Observer{
            stopLoading()
            it?.let {
                Toasty(it)
            }
        })
    }

    fun startLoading(){
        progress.visibility = View.VISIBLE
    }

    fun stopLoading(){
        progress.visibility = View.GONE
    }

    override fun onClick(t: Aya, position: Int) {
        Intent(this,TafseerActivity::class.java).run {
            putExtra("sura",item)
            putExtra("aya",t)
            startActivity(this)
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return super.onSupportNavigateUp()
    }
}
