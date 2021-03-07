package com.febrian.newsapp

import android.R
import android.app.SearchManager
import android.content.Context
import android.content.Intent
import android.net.ConnectivityManager
import android.os.Bundle
import android.view.MenuItem
import android.view.View
import android.widget.SearchView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.febrian.newsapp.databinding.ActivityListBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


class ListActivity : AppCompatActivity() {
    private lateinit var binding: ActivityListBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityListBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        if (isConnect()) {
            val searchManager = getSystemService(Context.SEARCH_SERVICE) as SearchManager
            binding.search.setSearchableInfo(searchManager.getSearchableInfo(componentName))
            showData()
        } else {
            binding.progressBar.visibility = View.GONE
            Toast.makeText(applicationContext, "No Internet Connection!", Toast.LENGTH_LONG).show()
        }
    }

    private fun showData() {
        val listData = ArrayList<DataItem>()
       val service = getServiceAPI()

        val client = service.getListArticles(getCategoryName(), Constant.API_KEY)
        binding.progressBar.visibility = View.VISIBLE
        client.enqueue(object : Callback<ResponseUser> {
            override fun onResponse(call: Call<ResponseUser>, response: Response<ResponseUser>) {
                if (response.isSuccessful) {
                    binding.progressBar.visibility = View.GONE
                    val data = response.body()?.data as List<DataItem>
                    listData.addAll(data)
                    showRecycleView(listData)
                }
            }

            override fun onFailure(call: Call<ResponseUser>, t: Throwable) {
                binding.progressBar.visibility = View.GONE
            }
        })
    }

    private fun getServiceAPI(): ApiService{
        val retrofit = Retrofit.Builder()
            .baseUrl(Constant.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        val service = retrofit.create(ApiService::class.java)
        return service
    }

    private fun showRecycleView(listData: ArrayList<DataItem>) {
        binding.search.setOnQueryTextListener(object : SearchView.OnQueryTextListener,
            androidx.appcompat.widget.SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                Toast.makeText(applicationContext, query, Toast.LENGTH_LONG).show()
                if (!query.equals(null) || !query.equals("")) {
                    val filter = listData.filter { it.title?.contains(query.toString()) == true }
                    show(filter as ArrayList<DataItem>)
                } else {
                    show(listData)
                }
                return true
            }

            override fun onQueryTextChange(query: String?): Boolean {

                if (!query.equals(null) || !query.equals("")) {
                    val filter = listData.filter { it.title?.contains(query.toString()) == true }
                    show(filter as ArrayList<DataItem>)
                } else {
                    show(listData)
                }
                return true
            }
        })

        show(listData)
    }

    fun show(listData: ArrayList<DataItem>) {
        val adapter = ListAdapter()
        adapter.setList(listData)
        binding.rv.hasFixedSize()
        binding.rv.layoutManager = LinearLayoutManager(applicationContext)
        binding.rv.adapter = adapter
    }

    private fun getCategoryName(): String {
        val category = intent.getStringExtra(Constant.CATEGORY)
        return category.toString()
    }

    private fun isConnect(): Boolean {
        val connect: ConnectivityManager =
            getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        return connect.activeNetworkInfo != null && connect.activeNetworkInfo!!.isConnected
    }

    override fun onNavigateUp(): Boolean {
        finish()
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.home -> {
                // app icon in action bar clicked; go home
                val intent = Intent(this, MainActivity::class.java)
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
                startActivity(intent)
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }
}