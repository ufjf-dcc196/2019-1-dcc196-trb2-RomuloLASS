package br.ufjf.dcc196.trab03;

import android.app.Activity;
import android.content.ContentValues;
import android.database.Cursor;
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

public class EditaTarefaActivity extends AppCompatActivity {
    private Spinner spinnerDificuldade;
    private Spinner spinnerEstado;
    private Button btnSubmit;
    private EditText etTitle;
    private EditText etDescription;
    private EditText etTime;
    private EditText etDate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edita_tarefa);

        Bundle bundle = getIntent().getExtras();
        final int posicao = (int) bundle.get("posicao");

        btnSubmit = findViewById(R.id.btnSubmit2);
        etTitle = findViewById(R.id.etEditTitle);
        etDescription = findViewById(R.id.etEditDescription);
        etTime = findViewById(R.id.etEditTime);
        etDate = findViewById(R.id.etEditDate);
        final ContentValues valuesTask = new ContentValues();

        TarefasDBHelper helper = new TarefasDBHelper(getApplicationContext());
        final SQLiteDatabase db = helper.getWritableDatabase();

        final String[] campos = {
                TarefasContract.Tarefas.COLLUMN_TITULO,
                TarefasContract.Tarefas.COLLUMN_DESCRICACAO,
                TarefasContract.Tarefas.COLLUMN_GRAU,
                TarefasContract.Tarefas.COLLUMN_DATAATUAL,
                TarefasContract.Tarefas.COLLUMN_DATALIMITE,
                TarefasContract.Tarefas.COLLUMN_ESTADO,
        };
        final Cursor cursor = db.query(TarefasContract.Tarefas.TABLE_NAME, campos, null, null, null, null, null);

        int idTitulo = cursor.getColumnIndex(TarefasContract.Tarefas.COLLUMN_TITULO);
        int idDescricaco = cursor.getColumnIndex(TarefasContract.Tarefas.COLLUMN_DESCRICACAO);
        int idGrau = cursor.getColumnIndex(TarefasContract.Tarefas.COLLUMN_GRAU);
        int idEstado = cursor.getColumnIndex(TarefasContract.Tarefas.COLLUMN_ESTADO);
        int idDTAtual = cursor.getColumnIndex(TarefasContract.Tarefas.COLLUMN_DATAATUAL);
        int idDTLimite = cursor.getColumnIndex(TarefasContract.Tarefas.COLLUMN_DATALIMITE);
        cursor.moveToPosition(posicao);

        etTitle.setText(cursor.getString(idTitulo));
        etDescription.setText(cursor.getString(idDescricaco));
        //et.setText(cursor.getString(idGrau));
        //txtEstatdo.setText(cursor.getString(idEstado));

        spinnerDificuldade = findViewById(R.id.spinner3);
        List<String> dificuldade = new ArrayList<>(Arrays.asList("Fácil","Medio", "Sem","Difícil","Muito Díficil"));
        ArrayAdapter<String> dificuldadeAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, dificuldade);
        dificuldadeAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerDificuldade.setAdapter(dificuldadeAdapter);

        spinnerEstado = findViewById(R.id.spinner4);
        List<String> estado = new ArrayList<>(Arrays.asList("A Fazer","Em Execução", "Bloqueda","Concluida"));
        ArrayAdapter<String> estadoAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, estado);
        estadoAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerEstado.setAdapter(estadoAdapter);

        final SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
        final Date date = new Date();

        btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String dt = etDate.getText() + " " + etTime.getText();
                valuesTask.put(TarefasContract.Tarefas.COLLUMN_TITULO, String.valueOf(etTitle.getText()));
                valuesTask.put(TarefasContract.Tarefas.COLLUMN_DESCRICACAO, String.valueOf(etDescription.getText()));
                valuesTask.put(TarefasContract.Tarefas.COLLUMN_GRAU, String.valueOf(spinnerDificuldade.getSelectedItem()));
                valuesTask.put(TarefasContract.Tarefas.COLLUMN_ESTADO, String.valueOf(spinnerEstado.getSelectedItem()));
                valuesTask.put(TarefasContract.Tarefas.COLLUMN_DATAATUAL, dateFormat.format(date));
                valuesTask.put(TarefasContract.Tarefas.COLLUMN_DATALIMITE, dt);
                db.update(TarefasContract.Tarefas.TABLE_NAME, valuesTask, TarefasContract.Tarefas._ID, campos);
                setResult(Activity.RESULT_OK);
                finish();
            }
        });
    }
}
