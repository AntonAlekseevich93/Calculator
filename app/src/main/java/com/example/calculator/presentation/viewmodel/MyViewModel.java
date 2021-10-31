package com.example.calculator.presentation.viewmodel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.calculator.entity.Calculation;
import com.example.calculator.interactor.UseCase;

public class MyViewModel extends ViewModel {
private MutableLiveData<Calculation> liveDataFirstFragment;
private MutableLiveData<Calculation> liveDataSecondFragment;
private UseCase useCase;


    public MyViewModel(UseCase useCase) {
        this.useCase = useCase;
    }

    public LiveData<Calculation> getDataForFirstFragment() {
        if(liveDataFirstFragment == null) {
            liveDataFirstFragment = new MutableLiveData();
        }
        return liveDataFirstFragment;
    }

    public LiveData<Calculation> getDataForSecondFragment() {
        if(liveDataSecondFragment == null) {
            liveDataSecondFragment = new MutableLiveData();
        }
        return liveDataSecondFragment;
    }
    
    public void calculateForFirstFragment(String costObject, String monthlyRent, String expenses){
        liveDataFirstFragment.setValue(useCase.calculate(costObject, monthlyRent, expenses));
    }
    public void calculateForSecondFragment(String costObject, String monthlyRent, String expenses){
        liveDataSecondFragment.setValue(useCase.calculate(costObject, monthlyRent, expenses));
    }
}