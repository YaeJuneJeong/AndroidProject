package com.codesample.wdjmemo;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;

import com.codesample.wdjmemo.databinding.ActivityEditorBinding;

import java.time.LocalDate;
import java.util.List;

public class EditorActivity extends AppCompatActivity {
    private ActivityEditorBinding binding;
    private MemoDatabase db;
    int number = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding=ActivityEditorBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        Intent i = getIntent();
        number= i.getIntExtra("number",0);
        String title = i.getStringExtra("title");
        String memo = i.getStringExtra("memo");


        if(number>0)
        {
            if(title!=null) binding.editTextTitle.setText(title);
            if(memo!=null) binding.editTextMemo.setText(memo);
        }
        db=MemoDatabase.getInstance(getApplicationContext());
        binding.buttonSave.setOnClickListener(v->{
            save();
        });
        binding.buttonDelete.setOnClickListener(v->{
            delete();
        });
    }

    private void save(){
        String title=binding.editTextTitle.getText().toString();
        String memo=binding.editTextMemo.getText().toString();

        if(title.isEmpty()||memo.isEmpty()) return;

        Memo m = new Memo();
        m.number=0;
        m.title=title;
        m.memo=memo;
        m.time= LocalDate.now().toString();
        new SaveTask().execute(m);
    }
    private void delete(){
        String title=binding.editTextTitle.getText().toString();
        String memo=binding.editTextMemo.getText().toString();



        if(title.isEmpty()||memo.isEmpty())
            return;

        new DeleteTask().execute();
    }
    class SaveTask extends AsyncTask<Memo, Void,Long>{
        @Override
        protected void onPostExecute(Long along){

            super.onPostExecute(along);
            finish();
        }

        @Override
        protected Long doInBackground(Memo... memos) {
            if(number==0)
                return db.getMemoDao().addMemo(memos[0]);
            else
                return (long) db.getMemoDao().updateMemo(memos[0]);
        }


    }
    class DeleteTask extends AsyncTask<Memo, Void, List<Memo>> {



        @Override
        protected List<Memo> doInBackground(Memo... memos) {
            Memo memo= db.getMemoDao().getMemo(number);
            int result =  db.getMemoDao().delete(memo);
           if (result ==1)
            return db.getMemoDao().getMemos();
           else
               return null;

        }

        @Override
        protected void onPostExecute(List<Memo> memo) {

            super.onPostExecute(memo);
            finish();
        }

    }
}
