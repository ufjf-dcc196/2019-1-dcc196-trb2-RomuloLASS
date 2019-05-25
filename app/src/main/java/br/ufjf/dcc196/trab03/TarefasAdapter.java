package br.ufjf.dcc196.trab03;

import android.content.Context;
import android.database.Cursor;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class TarefasAdapter extends RecyclerView.Adapter<TarefasAdapter.TarefasViewHolder> {
    private Cursor cursor;
    public OnTarefasClickListener listener;

    public TarefasAdapter(Cursor c){
        this.cursor = c;
    }

    public void setCursor(Cursor cursor) {
        this.cursor = cursor;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public TarefasViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        Context context = viewGroup.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.tarefas_layout, viewGroup, false);
        TarefasViewHolder viewHolder = new TarefasViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull TarefasViewHolder tarefasViewHolder, int i) {
        int idTitulo = cursor.getColumnIndex(TarefasContract.Tarefas.COLLUMN_TITULO);
        int idGrau = cursor.getColumnIndex(TarefasContract.Tarefas.COLLUMN_GRAU);
        cursor.moveToPosition(i);
        tarefasViewHolder.txtTitulo.setText(cursor.getString(idTitulo));
        tarefasViewHolder.txtGrau.setText(cursor.getString(idGrau));
    }

    @Override
    public int getItemCount() {
        return cursor.getCount();
    }

    public class TarefasViewHolder extends RecyclerView.ViewHolder {
        public TextView txtTitulo;
        public TextView txtGrau;
        public TarefasViewHolder(final View itemView) {
            super(itemView);
            txtTitulo = itemView.findViewById(R.id.txtTitulo);
            txtGrau = itemView.findViewById(R.id.txtLevel);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int position = getAdapterPosition();
                    if (position != RecyclerView.NO_POSITION){
                        listener.onTarefaClickListener(v, position);
                    }
                }
            });
        }
    }

    public interface OnTarefasClickListener {
        void onTarefaClickListener(View tarefaView, int position);
    }

    public void setOnTarefaClickListener(OnTarefasClickListener listener){
        this.listener = listener;
    }
}
