package com.example.kotlincoroutine2.main

import android.content.Context
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.bumptech.glide.Glide
import com.example.kotlincoroutine2.App
import com.example.kotlincoroutine2.R
import com.example.kotlincoroutine2.Utils.EndlessScrollListener
import com.example.kotlincoroutine2.model.Movie
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.row_item.view.*
import javax.inject.Inject

class MainActivity : AppCompatActivity(), MainContractor.View {


    @Inject
    @JvmField
    var presenter: MainContractor.Presenter? =null
    private var title = ""
    private var current_page = 1

    private val list= mutableListOf<Movie>()
    private var adapter:MovieAdapter? = null
    private lateinit var endlessScrollListener: EndlessScrollListener


    override fun onCreate(savedInstanceState: Bundle?) {
        App.getComponent().plus(MainModule(this)).inject(this)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        var layoutManager:LinearLayoutManager = LinearLayoutManager(this)
        recyclerView.layoutManager=layoutManager
        adapter= MovieAdapter(this,list)
        recyclerView.adapter=adapter

        presenter!!.onViewAttached(this)
        presenter!!.onLoadMoviesByTitle(title, 1)

        this.endlessScrollListener = object : EndlessScrollListener(recyclerView.layoutManager!!) {
            override fun onLoadMore(currentPage: Int, totalItemCount: Int) {
                if (totalItemCount > 1) {
                    presenter!!.onLoadMoviesByTitle(title, current_page)
                    current_page++
                }
            }

            override fun onScroll(firstVisibleItem: Int, dy: Int, scrollPosition: Int) {}
        }
        recyclerView.addOnScrollListener(endlessScrollListener)

    }

    override fun showMoreMovies(movies: List<Movie>) {
        list.clear()
        list.addAll(movies)
        adapter!!.notifyDataSetChanged()

        for(m in movies){
            Log.d("movieList", "" + m.title)
        }
    }

    override fun showToast(txt: String) {
    }

    class MovieAdapter(private val context:Context, private val list: List<Movie>): RecyclerView.Adapter<MovieAdapter.MyViewHolder>(){

        override fun onCreateViewHolder(viewGroup: ViewGroup, p1: Int): MovieAdapter.MyViewHolder {
            val view = LayoutInflater.from(viewGroup.context)
                .inflate(R.layout.row_item, viewGroup, false)

            return MyViewHolder(view)
        }

        override fun getItemCount(): Int {
            return list.size
        }

        override fun onBindViewHolder(myViewHolder: MovieAdapter.MyViewHolder, i: Int) {
            myViewHolder.bindItems(list[i])
        }

       inner class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
            fun bindItems(movie:Movie){
                Glide.with(context).load(movie.poster).into(itemView.poster)
                itemView.movieId.text="Id: "+movie.id.toString()
                itemView.title.text="Title: "+movie.title
            }
       }
    }
}
