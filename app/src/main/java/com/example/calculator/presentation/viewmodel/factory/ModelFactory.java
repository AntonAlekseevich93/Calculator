package com.example.calculator.presentation.viewmodel.factory;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import com.example.calculator.interactor.UseCase;
import com.example.calculator.presentation.viewmodel.MyViewModel;

public class ModelFactory extends ViewModelProvider.NewInstanceFactory {
    private UseCase useCase;


    public ModelFactory(@NonNull UseCase useCase) {
        super();
        this.useCase = useCase;
    }

    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        if (modelClass == MyViewModel.class) {
            return (T) new MyViewModel(useCase);
        }
        return null;
    }
}
