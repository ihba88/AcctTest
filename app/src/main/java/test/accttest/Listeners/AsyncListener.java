package test.accttest.Listeners;

import java.util.ArrayList;

import test.accttest.model.TitleDataModel;

/**
 * Created by Abhishek on 29/11/18.
 */

public interface AsyncListener {
    public void onAsyncCompleted(ArrayList<TitleDataModel> titleDataModels);
}
