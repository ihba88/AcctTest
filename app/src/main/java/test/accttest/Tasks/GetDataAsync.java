package test.accttest.Tasks;

import android.os.AsyncTask;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import test.accttest.Listeners.AsyncListener;
import test.accttest.model.TitleDataModel;

/**
 * Created by Abhishek on 29/11/18.
 */

public class GetDataAsync extends AsyncTask {
    String api;
    ArrayList<TitleDataModel> titleDataModels;
    AsyncListener listener;

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        api="https://jsonplaceholder.typicode.com/albums";
    }

    @Override
    protected Object doInBackground(Object[] objects) {
        OkHttpClient client = new OkHttpClient();
        this.listener = (AsyncListener) objects[0];
        Request request = new Request.Builder()
                .url(api)
                .get()
                .build();

        try {
            Response response = client.newCall(request).execute();
            Type listType = new TypeToken<List<TitleDataModel>>(){}.getType();
            titleDataModels = new Gson().fromJson(response.body().string(),listType);
            //Log.v("response:",titleDataModels.toString());
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;

    }

    @Override
    protected void onPostExecute(Object o) {
        super.onPostExecute(o);
        listener.onAsyncCompleted(titleDataModels);
    }
}
