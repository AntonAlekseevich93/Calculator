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
import androidx.lifecycle.LiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.example.calculator.R;
import com.example.calculator.entity.Calculation;
import com.example.calculator.interactor.UseCase;
import com.example.calculator.presentation.viewmodel.MyViewModel;
import com.example.calculator.presentation.viewmodel.factory.ModelFactory;

public class FragmentCalc extends Fragment {
    private EditText editTextCostObject;
    private EditText editTextMonthlyRent;
    private EditText editTextExpenses;
    private TextView tvPercentageOfIncome;
    private TextView tvNetProfit;
    private TextView tvPaybackPeriod;
    private MyViewModel myViewModel;
    private Context context;
    private Button buttonClearData;
     // обратить внимание - каждый раз создается новый UseCase

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        this.context = context;

    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // обратить внимание - каждый раз создается новый UseCase
        myViewModel = new ViewModelProvider(requireActivity()).get(MyViewModel.class);
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_layout, container, false);
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
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

    @RequiresApi(api = Build.VERSION_CODES.O)
    private void setClickListeners(View view) {
        tvPercentageOfIncome = view.findViewById(R.id.textViewPercent);
        tvPercentageOfIncome.setAutoSizeTextTypeWithDefaults(TextView.AUTO_SIZE_TEXT_TYPE_UNIFORM);
        tvNetProfit = view.findViewById(R.id.tvNetProfit);
        tvPaybackPeriod = view.findViewById(R.id.tvPaybackPeriod);
        editTextCostObject = view.findViewById(R.id.editTextCostObject);
        editTextMonthlyRent = view.findViewById(R.id.editTextMonthlyRent);
        editTextExpenses = view.findViewById(R.id.editTextExpenses);
        buttonClearData = view.findViewById(R.id.buttonClearData);

        editTextExpenses.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (editTextCostObject.getText().length() != 0 && editTextMonthlyRent.getText().length() != 0 && editTextExpenses.getText().length() != 0) {
                    myViewModel.calculate(editTextCostObject.getText().toString(), editTextMonthlyRent.getText().toString(), editTextExpenses.getText().toString());
                } else
                    Toast.makeText(context, "Введите данные", Toast.LENGTH_SHORT).show();
            }
        });
//

        buttonClearData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tvPercentageOfIncome.setText("0.0%");
                tvNetProfit.setText("");
                tvPaybackPeriod.setText("");
                editTextExpenses.setText("0");
                editTextMonthlyRent.setText("");
                editTextCostObject.setText("");
            }
        });
    }

}