package com.bassiouny.tafseer.view.activities

import android.app.SearchManager
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.Menu
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SearchView
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.bassiouny.tafseer.R
import com.bassiouny.tafseer.model.model.Sura
import com.bassiouny.tafseer.supported_file.AdapterClick
import com.bassiouny.tafseer.supported_file.Toasty
import com.bassiouny.tafseer.view.adapter.SuraAdapter
import com.bassiouny.tafseer.view_model.HomeViewModel
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(), AdapterClick<Sura> {


    lateinit  var viewModel : HomeViewModel
    lateinit var adapter:SuraAdapter


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        viewModel = ViewModelProviders.of(this).get(HomeViewModel::class.java)
        adapter =  SuraAdapter( ArrayList(),this,this)
        recycler.adapter = adapter
            viewModel.quranList.observe(this,Observer<List<Sura>>{ items ->
            adapter.setList(items)
        })


    }


    override fun onClick(t:Sura,position: Int) {
        Intent(this, SuraDetailsActivity::class.java).run {
            putExtra("data", t)
            startActivity(this)
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.home_menu,menu)
        val searchItem = menu?.findItem(R.id.search)
        val searchView = searchItem?.actionView as SearchView

        searchView.queryHint = getString(R.string.search)

        searchView.setOnQueryTextListener(object :SearchView.OnQueryTextListener{
            override fun onQueryTextSubmit(query: String?): Boolean {
                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                Log.e("tag",newText)
                newText?.let {
                    viewModel.searchBy = it
                }
                return false
            }

        })
        return true
    }
}
