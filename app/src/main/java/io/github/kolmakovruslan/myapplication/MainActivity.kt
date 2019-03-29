package io.github.kolmakovruslan.myapplication

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import kotlinx.coroutines.*
import okhttp3.OkHttpClient
import okhttp3.Request
import kotlin.coroutines.CoroutineContext

class MainActivity : AppCompatActivity(), CoroutineScope {

    val adapter = RepoAdapter()

    private val httpClient = OkHttpClient.Builder().build()

    private val rootJob = Job()
    override val coroutineContext: CoroutineContext
        get() = Dispatchers.Main + rootJob

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main_layout)
        val repoList = findViewById<RecyclerView>(R.id.repoList)
        repoList.layoutManager = LinearLayoutManager(this)
        repoList.adapter = adapter
        loadData()
    }

    private fun loadData() = launch {
        val request = Request.Builder()
            .url("https://api.github.com/orgs/google/repos")
            .build()
        val response: String = withContext(Dispatchers.IO) {
            httpClient.newCall(request).execute().body()!!.string()
        }
        val type = object : TypeToken<ArrayList<Repo>>() {}
        val repos = Gson().fromJson<ArrayList<Repo>>(response, type.type)
        adapter.data.clear()
        adapter.data.addAll(repos)
        adapter.notifyDataSetChanged()
    }

    override fun onDestroy() {
        rootJob.cancel()
        super.onDestroy()
    }
}