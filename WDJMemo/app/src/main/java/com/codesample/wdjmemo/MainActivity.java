package com.codesample.wdjmemo;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;


import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;

import com.codesample.wdjmemo.databinding.ActivityMainBinding;

import java.util.List;

public class MainActivity extends AppCompatActivity implements MemoAdapter.OnItemClick {
    private ActivityMainBinding binding;
    private MemoAdapter adapter;
    private MemoDatabase db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding=ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        db=MemoDatabase.getInstance(getApplicationContext());

        adapter = new MemoAdapter(this);
        binding.recyclerView.setAdapter(adapter);
        binding.recyclerView.setLayoutManager(new LinearLayoutManager(this));



        binding.floatingActionButton.setOnClickListener(v->
                {
                    Intent i =new Intent(this,EditorActivity.class);
                   startActivity(i);
                });

    }

    @Override
    protected void onStart() {
        super.onStart();
        QueryTask qt = new QueryTask();
        qt.execute();
    }


    @Override
    public void onItemClick(int position, Memo memo) {
        Intent i=new Intent(this, EditorActivity.class);
        i.putExtra("number", memo.number);
        i.putExtra("title", memo.title);
        i.putExtra("memo", memo.memo);
        startActivity(i);
    }

    class QueryTask extends AsyncTask<Void, Void, List<Memo>>{
        @Override
        protected void onPostExecute(List<Memo> memos) {
            super.onPostExecute(memos);
            adapter.setData(memos);
        }
        @Override
        protected List<Memo> doInBackground(Void... voids) {

            return db.getMemoDao().getMemos();
        }
    }
}
