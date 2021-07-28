package com.example.tatvasoft_task

import android.util.Log
import android.view.View
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import okhttp3.ResponseBody
import org.json.JSONArray
import org.json.JSONObject
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.create

class MainViewModel3(
    var mloading: MutableLiveData<Boolean>,
    var itemList: MutableLiveData<ItemArrayModel>
) : ViewModel() {
    var numberString: MutableLiveData<String> = MutableLiveData();
    var progress: MutableLiveData<Integer> = MutableLiveData();
    fun onSubmitClick(view: View) {
        if (numberString.value.toString().trim().isNotEmpty()) {

        }
    }

    private fun getAllItems(page: Int) {
        if (page != 1) {
            progress.value
        }

        val apiInterface: ApiInterface? = ApiClient.client?.create()
        //ApiInterface.class)
        val call: Call<ResponseBody> = apiInterface?.callForItems(page) as Call<ResponseBody>

        call.enqueue(Callback<ResponseBody>() {
            override fun onResponse(
                call: Call<ResponseBody>,
                response: Response<ResponseBody>
            ) {
                progress.value = 8

                try {
                    val body = response.body()!!.string()
                    val jsonObject1 = JSONObject(body)
                    var jsonArray: JSONArray? = null
                    jsonArray = jsonObject1.optJSONArray("data")
                    if (jsonArray != null && jsonArray.length() > 0) {
                        for (i in 0 until jsonArray.length()) {
                            val jsonObject = jsonArray.getJSONObject(i)
                            val user_email = jsonObject.optString("email")
                            val first_Name = jsonObject.optString("first_name")
                            val last_Name = jsonObject.optString("last_name")


                            val itemsArray = ItemArrayModel()
                            itemsArray.email(user_email)
                            itemsArray.firstName(first_Name)
                            itemsArray.lastName(last_Name)
                            itemList.add(itemsArray)
                        }
                        articlesAdapter.setData(articlesList)
                    } else {
                        if (llLoadMore.getVisibility() == View.GONE) {
                            rlNoDataFound.setVisibility(View.VISIBLE)
                            // rvArticles.setVisibility(View.GONE);
                        }
                    }
                } catch (e: Exception) {
                    Log.e("OkHttp", "onResponse: " + e.message)
                    Log.e("OkHttp", "onResponse: " + e.message)
                    rlNoDataFound.setVisibility(View.VISIBLE)
                    //  rvArticles.setVisibility(View.GONE);
                    llLoadMore.setVisibility(View.GONE)
                }
            }

            fun onFailure(call: Call<*>?, t: Throwable) {
                mIsLoading = false
                llLoadMore.setVisibility(View.GONE)
            }
        })
    }
}