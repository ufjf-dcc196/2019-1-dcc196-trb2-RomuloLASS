package br.ufjf.dcc196.trab03;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button btnRegister = findViewById(R.id.btnRegister);
        TarefasDBHelper helper = new TarefasDBHelper(getApplicationContext());
        SQLiteDatabase db = helper.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(TarefasContract.Tarefas.COLLUMN_TITULO, "Trabalho");
        values.put(TarefasContract.Tarefas.COLLUMN_DESCRICACAO, "Trabalho de Lab Android");
        values.put(TarefasContract.Tarefas.COLLUMN_GRAU, "5");
        values.put(TarefasContract.Tarefas.COLLUMN_DATAATUAL, "2019-05-23 20:56");
        values.put(TarefasContract.Tarefas.COLLUMN_DATALIMITE, "2019-06-32 20:56");

        db.insert(TarefasContract.Tarefas.TABLE_NAME, null, values);

        String[] campos = {
                TarefasContract.Tarefas.COLLUMN_TITULO,
                TarefasContract.Tarefas.COLLUMN_DESCRICACAO,
                TarefasContract.Tarefas.COLLUMN_GRAU,
                TarefasContract.Tarefas.COLLUMN_DATAATUAL,
                TarefasContract.Tarefas.COLLUMN_DATALIMITE,
        };

        Cursor cursor = db.query(TarefasContract.Tarefas.TABLE_NAME, campos, null, null, null, null, null);

        TarefasAdapter adapter = new TarefasAdapter(cursor);
        RecyclerView recyclerView = findViewById(R.id.rvTask);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));


    }
}
