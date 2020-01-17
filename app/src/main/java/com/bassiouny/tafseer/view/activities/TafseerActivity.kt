package com.bassiouny.tafseer.view.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.bassiouny.tafseer.R
import com.bassiouny.tafseer.model.model.Aya
import com.bassiouny.tafseer.model.model.Sura
import com.bassiouny.tafseer.supported_file.Toasty
import com.bassiouny.tafseer.view_model.SuraDetailsViewModel
import com.bassiouny.tafseer.view_model.TafseerViewModel
import kotlinx.android.synthetic.main.activity_tafseer.*


class TafseerActivity : AppCompatActivity() {

    lateinit var viewModel: TafseerViewModel


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_tafseer)
        viewModel = ViewModelProviders.of(this).get(TafseerViewModel::class.java)

        intent?.also {
            viewModel.sura.value = it.getParcelableExtra("sura")
            viewModel.aya.value = it.getParcelableExtra("aya")

        }
        //set back button
        supportActionBar?.also {
            it.setDisplayHomeAsUpEnabled(true)
        }
        // set observer
        setObserve()

        // add onclick listener
        onCliCk()

        // fetch Data
        viewModel.getTafseer()
    }

    private fun onCliCk(){
        ivNext.setOnClickListener {
            viewModel.next()
        }

        ivPrevious.setOnClickListener {
            viewModel.previous()
        }
    }

    private fun setObserve(){
        viewModel.data.observe(this, Observer {
            tvTafseer.text = it.text
            tvTafseerName.text = it.tafseerName
            linear.visibility = View.VISIBLE
        })

        viewModel.error.observe(this, Observer {
            Toasty(it)
        })

        viewModel.loading.observe(this, Observer {
            if (it){
                startLoading()
            }else{
                stopLoading()
            }
        })

        viewModel.sura.observe(this, Observer {
            it?.let {
                title = it.titleAr
            }
        })

        viewModel.aya.observe(this, Observer {
            it?.let {
                tvSuraName.text = it.text
                tvAyaNumber.text = it.numberInSurah.toString()
            }
        })
    }

    private fun startLoading(){
        progress.visibility = View.VISIBLE
    }

    private fun stopLoading(){
        progress.visibility = View.GONE
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return super.onSupportNavigateUp()
    }
}
