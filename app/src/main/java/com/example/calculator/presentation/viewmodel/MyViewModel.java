public class MyViewModel extends ViewModel{
private MutableLiveData <Calculation> mutableLiveData; 
private UseCase useCase;

    public LiveData<Calculation> getData() {
        if(mutableLiveData == null) {
            mutableLiveData = new MutableLiveData();
        }
        
        return mutableLiveData;
    }
    
    public void calculate(String costObject, String monthlyRent, String expenses){
        mutableLiveData.setValue(useCase.calculate(costObject, monthlyRent, expenses));
    }

}