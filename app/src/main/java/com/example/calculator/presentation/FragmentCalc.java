package com.example.calculator.presentation;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.Observer;

import com.example.calculator.R;
import com.example.calculator.entity.Calculation;
import com.example.calculator.presentation.viewmodel.MyViewModel;

public class FragmentCalc extends Fragment {
    private EditText editTextCostObject;
    private EditText editTextMonthlyRent;
    private EditText editTextExpenses;
    private TextView tvPercentageOfIncome;
    private TextView tvNetProfit;
    private TextView tvPaybackPeriod;
    private MyViewModel myViewModel;
    private Context context;

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        this.context = context;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_layout, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        setClickListeners(view);
        LiveData<Calculation> liveData = myViewModel.getData();
        liveData.observe(getViewLifecycleOwner(), new Observer<Calculation>() {
            @Override
            public void onChanged(Calculation calculation) {
                if (calculation != null) {
                    setDataToTextView(calculation);
                }
            }

        });
//
    }

    private void setDataToTextView(Calculation c) {
        tvPercentageOfIncome.setText(c.getPercentageOfIncome());
        tvNetProfit.setText(c.getNetProfit());
        tvPaybackPeriod.setText(c.getPaybackPeriod());
    }

    private void setClickListeners(View view) {
//        textView = findViewById(R.id.textViewPercent);
        editTextCostObject = view.findViewById(R.id.editTextCostObject);
        editTextMonthlyRent = view.findViewById(R.id.editTextMonthlyRent);
        editTextExpenses = view.findViewById(R.id.editTextExpenses);

        editText2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (editText1.getText().length() != 0 && editText2.getText().length() != 0) {

                } else
                    Toast.makeText(context, "Введите данные", Toast.LENGTH_SHORT).show();
            }
        });

        button = findViewById(R.id.clearData);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                textView.setText("0.0%");
                editText1.setText("");
                editText2.setText("");
            }
        });
    }

}