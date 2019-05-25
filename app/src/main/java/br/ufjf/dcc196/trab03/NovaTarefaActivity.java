package br.ufjf.dcc196.trab03;

import android.app.Activity;
import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class NovaTarefaActivity extends AppCompatActivity {

    private Spinner spinner;
    private Button btnSubmitTask;
    private EditText etTitle;
    private EditText etDescription;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nova_tarefa);
        btnSubmitTask = findViewById(R.id.btnSubmitTask);
        etTitle = findViewById(R.id.etTitle);
        etDescription = findViewById(R.id.etDescription);
        final ContentValues valuesTask = new ContentValues();

        TarefasDBHelper helper = new TarefasDBHelper(getApplicationContext());
        final SQLiteDatabase db = helper.getWritableDatabase();

        spinner = findViewById(R.id.spinner);
        List<String> dificuldade = new ArrayList<>(Arrays.asList("Fácil","Medio", "Sem","Difícil","Muito Díficil"));
        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, dificuldade);
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(dataAdapter);

        btnSubmitTask.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                valuesTask.put(TarefasContract.Tarefas.COLLUMN_TITULO, String.valueOf(etTitle.getText()));
                valuesTask.put(TarefasContract.Tarefas.COLLUMN_DESCRICACAO, String.valueOf(etDescription.getText()));
                valuesTask.put(TarefasContract.Tarefas.COLLUMN_GRAU, String.valueOf(spinner.getSelectedItem()));
                db.insert(TarefasContract.Tarefas.TABLE_NAME, null, valuesTask);
                setResult(Activity.RESULT_OK);
                finish();
            }
        });


    }
}
