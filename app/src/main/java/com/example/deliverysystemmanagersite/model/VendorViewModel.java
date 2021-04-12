package com.example.deliverysystemmanagersite.model;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class VendorViewModel extends ViewModel {

    private MutableLiveData<String> mText;

    public VendorViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("This is vendor fragment");
    }

    public LiveData<String> getText() {
        return mText;
    }
}