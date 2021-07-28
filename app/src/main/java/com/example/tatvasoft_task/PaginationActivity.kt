package com.example.tatvasoft_task

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class PaginationActivity : AppCompatActivity() {
    lateinit var activityMainBinding: ActivityPaginationBinding
    lateinit var mainViewModel: MainViewModel3
    private lateinit var linearLayoutManager: LinearLayoutManager
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_pagination)
        activityMainBinding = DataBindingUtil.setContentView(this, R.layout.activity_pagination)
        mainViewModel = MainViewModel3()
        activityMainBinding.viewmodel = mainViewModel
        activityMainBinding.lifecycleOwner

        activityMainBinding.rvItems.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)
                if (dy > 0) {
                    val visibleItemCount: Int = linearLayoutManager.getChildCount()
                    val totalItemCount: Int = linearLayoutManager.getItemCount()
                    val pastVisiblesItems: Int = linearLayoutManager.findFirstVisibleItemPosition()
                    if (mIsLoading) if (visibleItemCount + pastVisiblesItems >= totalItemCount) {
                        mIsLoading = false
                        llLoadMore.setVisibility(View.VISIBLE)
                        page = page + 1
                        rvArticles.scrollTo(0, llLoadMore.getY() as Int)
                        getAllArticles()
                    }
                }
            }
        })
    }

}