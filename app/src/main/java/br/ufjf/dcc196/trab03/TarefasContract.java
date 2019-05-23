package br.ufjf.dcc196.trab03;

import android.provider.BaseColumns;

public class TarefasContract {

    public static class Tarefas implements BaseColumns {
        //Colunas da tabela
        public static final String TABLE_NAME = "tarefas";
        public static final String COLLUMN_TITULO = "titulo";
        public static final String COLLUMN_DESCRICACAO = "descricacao";
        public static final String COLLUMN_GRAU = "grau";
        /*
        public static final String COLLUMN_DATALIMITE = "datalimite";
        public static final String COLLUMN_HORALIMITE = "horalimite";
        public static final String COLLUMN_DATAATUAL = "dataatual";
        public static final String COLLUMN_HORAATUAL = "horaatual";
        public static final String COLLUMN TAG = "tag";
        */
        //Criação da tabela
        public static final String CREATE_TABLE = String.format("CREATE TABLE %s (%s INTEGER PRIMARY KEY AUTOINCREMENT, %s TEXT, %s TEXT, %s TEXT)", TABLE_NAME, _ID, COLLUMN_TITULO, COLLUMN_DESCRICACAO, COLLUMN_GRAU);
        //Deletar a tabela
        public static final String DROP_TABLE = String.format("DROP TABLE %s", TABLE_NAME);
    }
}
