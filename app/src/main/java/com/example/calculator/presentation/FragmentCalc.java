public class FragmentCalc extends Fragment{
    private EditText editText1;
    private EditText editText2;
    private TextView tvPercentageOfIncome;
    private TextView tvNetProfit;
    private TextView tvPaybackPeriod;

    @Override
    public void onCreate(Bundle savedInstanceState){
    
    }
    
    @Override
    public void onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
    }
    
    @Override
    public void onActivityCreated(Bundle savedInstanceState){
        textView = findViewById(R.id.textViewPercent);
        editText1 = findViewById(R.id.editTextTextPersonName);
        editText2 = findViewById(R.id.editTextTextPersonName2);
        LiveData<Calculation> liveData = myViewModel.getData();
        liveData.observe(this, new Observer<Calculation>() {
            @Override
            onChanged(Calculation c) {
                if(c != null) {
                    setDataToTextView(c);
                }
            }
        })
        editText2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (editText1.getText().length() != 0 && editText2.getText().length() != 0) {
                    presenter.getPercent(editText1.getText().toString(), editText2.getText().toString());
                } else
                    Toast.makeText(getApplicationContext(), "Введите данные", Toast.LENGTH_SHORT).show();
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
    
    private void setDataToTextView(Calculation c) {
        tvPercentageOfIncome.setText(c.getPercentageOfIncome);
        tvNetProfit.setText(c.getNetProfit);
        tvPaybackPeriod.setText(c.getPayBackPeriod);
    }
    
    
}