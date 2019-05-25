package br.ufjf.dcc196.trab03;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class TarefasActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tarefas);

        Bundle bundle = getIntent().getExtras();
        int posicao = (int) bundle.get("posicao");

        Button btnEdit = findViewById(R.id.btnEdit);
        Button btnDelete = findViewById(R.id.btnDelete);
        TextView txtTitulo = findViewById(R.id.txtTitulo);
        TextView txtDescricao = findViewById(R.id.txtDecricao);
        TextView txtDificuldade = findViewById(R.id.txtDificuldade);
        TextView txtEstatdo = findViewById(R.id.txtEstado);
        TextView txtDTAtual = findViewById(R.id.txtDTAtual);
        TextView txtDTLimite = findViewById(R.id.txtDTLimite);

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
        Cursor cursor = db.query(TarefasContract.Tarefas.TABLE_NAME, campos, null, null, null, null, null);

        int idTitulo = cursor.getColumnIndex(TarefasContract.Tarefas.COLLUMN_TITULO);
        int idDescricaco = cursor.getColumnIndex(TarefasContract.Tarefas.COLLUMN_DESCRICACAO);
        int idGrau = cursor.getColumnIndex(TarefasContract.Tarefas.COLLUMN_GRAU);
        int idEstado = cursor.getColumnIndex(TarefasContract.Tarefas.COLLUMN_ESTADO);
        int idDTAtual = cursor.getColumnIndex(TarefasContract.Tarefas.COLLUMN_DATAATUAL);
        int idDTLimite = cursor.getColumnIndex(TarefasContract.Tarefas.COLLUMN_DATALIMITE);
        cursor.moveToPosition(posicao);



        txtTitulo.setText(txtTitulo.getText() + cursor.getString(idTitulo));
        txtDescricao.setText(cursor.getString(idDescricaco));
        txtDificuldade.setText(cursor.getString(idGrau));
        txtEstatdo.setText(cursor.getString(idEstado));
        txtDTAtual.setText(cursor.getString(idDTAtual));
        txtDTLimite.setText(cursor.getString(idDTLimite));
    }
}
