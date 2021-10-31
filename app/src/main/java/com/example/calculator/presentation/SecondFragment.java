package com.example.calculator.presentation;

import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.example.calculator.R;
import com.example.calculator.entity.Calculation;
import com.example.calculator.presentation.viewmodel.MyViewModel;

public class SecondFragment extends Fragment {
    private EditText editTextCostObject;
    private EditText editTextMonthlyRent;
    private EditText editTextExpenses;
    private TextView tvPercentageOfIncome;
    private TextView tvNetProfit;
    private TextView tvPaybackPeriod;
    private MyViewModel myViewModel;
    private Context context;
    private Button buttonClearData;
    private Button btnAddSecondFragment;
    private Button btnCalculate;
    private FragmentManager fragmentManager;


    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        this.context = context;

    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        fragmentManager = getParentFragmentManager();
        myViewModel = new ViewModelProvider(requireActivity()).get(MyViewModel.class);
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_layout, container, false);
    }


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);


        initializeViews(view);
        setClickListeners();
        btnAddSecondFragment.setVisibility(View.GONE);
        LiveData<Calculation> liveData = myViewModel.getDataForSecondFragment();
        liveData.observe(getViewLifecycleOwner(), new Observer<Calculation>() {
            @Override
            public void onChanged(Calculation calculation) {
                setDataToTextView(calculation);
            }
        });
    }

    private void setDataToTextView(Calculation c) {
        tvPercentageOfIncome.setText(c.getPercentageOfIncome());
        tvNetProfit.setText(c.getNetProfit());
        tvPaybackPeriod.setText(c.getPaybackPeriod());
    }

    private void setClickListeners() {

        editTextExpenses.setOnClickListener(v -> {
            calculate();
        });

        btnCalculate.setOnClickListener(v ->{
            calculate();
        });

        buttonClearData.setOnClickListener(v -> {
            clearViews();
        });

        buttonClearData.setOnLongClickListener(v -> {
            clearViews();
            editTextExpenses.setText("");
            return true;
        });


    }


    private void initializeViews(View view) {
        tvPercentageOfIncome = view.findViewById(R.id.textViewPercent);
//        tvPercentageOfIncome.setAutoSizeTextTypeWithDefaults(TextView.AUTO_SIZE_TEXT_TYPE_UNIFORM);
        tvNetProfit = view.findViewById(R.id.tvNetProfit);
        tvPaybackPeriod = view.findViewById(R.id.tvPaybackPeriod);
        editTextCostObject = view.findViewById(R.id.editTextCostObject);
        editTextMonthlyRent = view.findViewById(R.id.editTextMonthlyRent);
        editTextExpenses = view.findViewById(R.id.editTextExpenses);
        buttonClearData = view.findViewById(R.id.buttonClearData);
        btnAddSecondFragment = view.findViewById(R.id.btnAddFragment);
        btnCalculate = view.findViewById(R.id.buttonCalculate);
    }

    private void clearViews() {
        tvPercentageOfIncome.setText("0.0%");
        tvNetProfit.setText("");
        tvPaybackPeriod.setText("");
        editTextMonthlyRent.setText("");
        editTextCostObject.setText("");
    }

    private void calculate(){
        String expenses = "";
        if (editTextExpenses.getText().length() == 0) expenses = "0";
        else expenses = editTextExpenses.getText().toString();
        if (editTextCostObject.getText().length() != 0 && editTextMonthlyRent.getText().length() != 0) {
            myViewModel.calculateForSecondFragment(editTextCostObject.getText().toString(),
                    editTextMonthlyRent.getText().toString(), expenses);
        } else
            Toast.makeText(context, "Введите данные", Toast.LENGTH_SHORT).show();
    }
}
