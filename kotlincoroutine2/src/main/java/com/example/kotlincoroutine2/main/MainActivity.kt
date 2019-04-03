package com.example.kotlincoroutine2.main

import android.content.Context
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.text.Editable
import android.text.TextWatcher
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
    var presenter: MainContractor.Presenter? = null
    private var title = ""
    private var current_page = 1

    private val list = mutableListOf<Movie>()
    private var adapter: MovieAdapter? = null
    private lateinit var endlessScrollListener: EndlessScrollListener
    private lateinit var scrollListener: RecyclerView.OnScrollListener
    private lateinit var linearLayoutManager: LinearLayoutManager
    private val lastVisibleItemPosition: Int
        get() = linearLayoutManager.findLastVisibleItemPosition()

    override fun onCreate(savedInstanceState: Bundle?) {
        App.getComponent().plus(MainModule(this)).inject(this)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        linearLayoutManager = LinearLayoutManager(this)
        recyclerView.layoutManager = linearLayoutManager
        adapter = MovieAdapter(this, list)
        recyclerView.adapter = adapter

        presenter!!.onViewAttached(this)
        presenter!!.onLoadMoviesByTitle(title, 1, true)
        edSearch.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(charSequence: CharSequence, i: Int, i1: Int, i2: Int) {}

            override fun onTextChanged(charSequence: CharSequence, i: Int, i1: Int, i2: Int) {

                if (charSequence.isNotEmpty()) {
                    cancel.visibility = View.VISIBLE
                } else {
                    cancel.visibility = View.GONE
                }
            }

            override fun afterTextChanged(editable: Editable) {}
        })

        cancel.setOnClickListener {
            edSearch.setText("")
            presenter!!.onLoadMoviesByTitle(title, 1, false)
        }

        btnSearch.setOnClickListener {
            presenter!!.onLoadMoviesByTitle(edSearch.text.toString(), current_page, false)
        }

        scrollListener = object : RecyclerView.OnScrollListener() {
            override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
                super.onScrollStateChanged(recyclerView, newState)
                val totalItemCount = recyclerView.layoutManager!!.itemCount
                if (totalItemCount == lastVisibleItemPosition + 1) {
                    Log.d("MyTAG", "Load new list")
                    current_page++
                    presenter!!.onLoadMoviesByTitle(title, current_page, true)
                }
            }
        }
        recyclerView.addOnScrollListener(scrollListener)

    }

    override fun showMoreMovies(movies: List<Movie>, isReload: Boolean) {
        if(!isReload){
            list.clear()
        }
        list.addAll(movies)
        adapter!!.notifyDataSetChanged()
    }

    override fun showToast(txt: String) {
    }

    class MovieAdapter(private val context: Context, private val list: List<Movie>) :
        RecyclerView.Adapter<MovieAdapter.MyViewHolder>() {

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

        inner class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
            fun bindItems(movie: Movie) {
                Glide.with(context).load(movie.poster).into(itemView.poster)
                itemView.movieId.text = "Id: " + movie.id.toString()
                itemView.title.text = "Title: " + movie.title
            }
        }
    }
}
