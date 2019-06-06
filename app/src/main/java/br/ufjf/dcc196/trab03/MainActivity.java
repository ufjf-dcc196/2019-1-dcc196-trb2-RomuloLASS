package br.ufjf.dcc196.trab03;

import android.app.Activity;
import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {
    public TarefasAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button btnRegister = findViewById(R.id.btnRegister);
        TarefasDBHelper helper = new TarefasDBHelper(getApplicationContext());
        SQLiteDatabase db = helper.getWritableDatabase();

        String[] campos = {
                TarefasContract.Tarefas.COLLUMN_TITULO,
                TarefasContract.Tarefas.COLLUMN_DESCRICACAO,
                TarefasContract.Tarefas.COLLUMN_GRAU,
                TarefasContract.Tarefas.COLLUMN_DATAATUAL,
                TarefasContract.Tarefas.COLLUMN_DATALIMITE,
                TarefasContract.Tarefas.COLLUMN_ESTADO,
        };

        /*ContentValues valuesTask = new ContentValues();
        valuesTask.put(TarefasContract.Tarefas.COLLUMN_TITULO, "Trabalho 03");
        valuesTask.put(TarefasContract.Tarefas.COLLUMN_DESCRICACAO, "Trabalho do Laboratorio de Dispositiveis moveis");
        valuesTask.put(TarefasContract.Tarefas.COLLUMN_GRAU, "4");
        valuesTask.put(TarefasContract.Tarefas.COLLUMN_DATAATUAL, "2019-05-25 10:00");
        valuesTask.put(TarefasContract.Tarefas.COLLUMN_DATALIMITE, "2019-06-25 18:00");
        valuesTask.put(TarefasContract.Tarefas.COLLUMN_ESTADO, "A Fazer");

        db.insert(TarefasContract.Tarefas.TABLE_NAME, null, valuesTask);*/

        Cursor cursor = db.query(TarefasContract.Tarefas.TABLE_NAME, campos, null, null, null, null, null);

        adapter = new TarefasAdapter(cursor);
        RecyclerView recyclerView = findViewById(R.id.rvTask);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, NovaTarefaActivity.class);
                startActivity(intent);
            }
        });

        adapter.setOnTarefaClickListener(new TarefasAdapter.OnTarefasClickListener() {
            @Override
            public void onTarefaClickListener(View tarefaView, int position) {
                Intent intent = new Intent(MainActivity.this, TarefasActivity.class);
                intent.putExtra("posicao", position);
                startActivity(intent);
            }
        });
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        if(resultCode == Activity.RESULT_OK){
            adapter.notifyDataSetChanged();
            RecyclerView recyclerView = findViewById(R.id.rvTask);
            recyclerView.setAdapter(adapter);
            recyclerView.setLayoutManager(new LinearLayoutManager(this));
        }
    }
}