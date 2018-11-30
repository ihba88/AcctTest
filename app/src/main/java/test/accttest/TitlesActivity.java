package test.accttest;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;
import java.util.Collections;

import test.accttest.Listeners.AsyncListener;
import test.accttest.Tasks.GetDataAsync;
import test.accttest.model.TitleDataModel;

public class TitlesActivity extends AppCompatActivity{
    private ArrayList<String> titleList;
    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.titles_layout);
        mRecyclerView = (RecyclerView) findViewById(R.id.rlView);

        mRecyclerView.setHasFixedSize(true);

        // use a linear layout manager
        mLayoutManager = new LinearLayoutManager(this);


    }

    @Override
    protected void onResume() {
        super.onResume();
        AsyncListener listener = new AsyncListener() {
            @Override
            public void onAsyncCompleted(ArrayList<TitleDataModel> titleDataModels) {
                titleList = new ArrayList<String>();
                for(int i=0;i<titleDataModels.size();i++){
                    titleList.add(titleDataModels.get(i).getTitle());
                }
                Collections.sort(titleList);
                setUi();
            }
        };
        new GetDataAsync().execute(listener);
    }
    public void setUi(){
        mRecyclerView.setLayoutManager(mLayoutManager);

        // specify an adapter (see also next example)
        mAdapter = new TitleAdapter(titleList);
        mRecyclerView.setAdapter(mAdapter);
    }
}
