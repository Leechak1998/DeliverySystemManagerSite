package com.example.deliverysystemmanagersite.model;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class SiteViewModel extends ViewModel {

    private MutableLiveData<String> mText;

    public SiteViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("This is site fragment");
    }

    public LiveData<String> getText() {
        return mText;
    }
}
