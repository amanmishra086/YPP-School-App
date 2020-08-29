package my.awesome.yppschoolapp.retrofit;

import java.util.ArrayList;

public class LoginData {

    private ArrayList<Result> result;

    public LoginData(ArrayList<Result> result) {
        this.result = result;
    }



    public ArrayList<Result> getResult() {
        return result;
    }

}
