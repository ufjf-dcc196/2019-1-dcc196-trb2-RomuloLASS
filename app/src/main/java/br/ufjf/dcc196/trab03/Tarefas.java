package br.ufjf.dcc196.trab03;

public class Tarefas {
    private long id;
    private String titulo;
    private String descricao;
    private String grau;
    private String dataHoraLimite;
    private String dataHoraAtual;

    public Tarefas(){}

    public Tarefas(long id, String titulo, String descricao, String grau){
        this.id = id;
        this.titulo = titulo;
        this.descricao = descricao;
        this.grau = grau;
    }

    @Override
    public String toString() {
        return super.toString();
    }
}
