package com.example.tatvasoft_task;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import java.util.ArrayList;
import java.util.List;

public class MainViewModel extends ViewModel {
    private MutableLiveData<List<ItemArrayModel>> mItemList=new MutableLiveData();
    List<ItemArrayModel>  itemList=new ArrayList<>();
    MutableLiveData<Boolean>
}
