package io.github.kolmakovruslan.myapplication

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView

class RepoAdapter : RecyclerView.Adapter<RepoAdapter.ViewHolder>() {

    val data = mutableListOf<Repo>()

    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): ViewHolder {
        val inflater = LayoutInflater.from(p0.context)
        val view = inflater.inflate(R.layout.repo_layout, p0, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int = data.size

    override fun onBindViewHolder(p0: ViewHolder, p1: Int) {
        p0.itemView.findViewById<TextView>(R.id.repoName).text = data[p1].name
    }

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view)
}