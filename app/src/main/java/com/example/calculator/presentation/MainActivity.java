package com.example.calculator.presentation;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.ViewModelProvider;

import com.example.calculator.R;
import com.example.calculator.interactor.UseCase;
import com.example.calculator.presentation.viewmodel.MyViewModel;
import com.example.calculator.presentation.viewmodel.factory.ModelFactory;

public class MainActivity extends AppCompatActivity {
    private MyViewModel myViewModel;
    private UseCase useCase;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);//Отключаем темную тему
        useCase = new UseCase();
        myViewModel = new ViewModelProvider(this, new ModelFactory(useCase)).get(MyViewModel.class);
        FragmentManager fragmentManager = getSupportFragmentManager();
        if (savedInstanceState == null) {

            fragmentManager.beginTransaction()
                    .add(R.id.containerFrameLayout, new FragmentCalc())
                    .commit();
        }
    }


}