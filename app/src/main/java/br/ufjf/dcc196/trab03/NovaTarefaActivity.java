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

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

public class NovaTarefaActivity extends AppCompatActivity {

    private Spinner spinnerDificuldade;
    private Spinner spinnerEstado;
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

        spinnerDificuldade = findViewById(R.id.spinner);
        List<String> dificuldade = new ArrayList<>(Arrays.asList("Fácil","Medio", "Sem","Difícil","Muito Díficil"));
        ArrayAdapter<String> dificuldadeAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, dificuldade);
        dificuldadeAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerDificuldade.setAdapter(dificuldadeAdapter);

        spinnerEstado = findViewById(R.id.spinner2);
        List<String> estado = new ArrayList<>(Arrays.asList("A Fazer","Em Execução", "Bloqueda","Concluida"));
        ArrayAdapter<String> estadoAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, estado);
        estadoAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerEstado.setAdapter(estadoAdapter);


        final SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        final Date date = new Date();

        btnSubmitTask.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                valuesTask.put(TarefasContract.Tarefas.COLLUMN_TITULO, String.valueOf(etTitle.getText()));
                valuesTask.put(TarefasContract.Tarefas.COLLUMN_DESCRICACAO, String.valueOf(etDescription.getText()));
                valuesTask.put(TarefasContract.Tarefas.COLLUMN_GRAU, String.valueOf(spinnerDificuldade.getSelectedItem()));
                valuesTask.put(TarefasContract.Tarefas.COLLUMN_ESTADO, String.valueOf(spinnerEstado.getSelectedItem()));
                valuesTask.put(TarefasContract.Tarefas.COLLUMN_DATAATUAL, dateFormat.format(date));
                db.insert(TarefasContract.Tarefas.TABLE_NAME, null, valuesTask);
                setResult(Activity.RESULT_OK);
                finish();
            }
        });


    }
}
