package br.ufjf.dcc196.trab03;

import android.app.LauncherActivity;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class TarefasAdapter extends RecyclerView.Adapter<TarefasAdapter.TarefasViewHolder> {
    private Cursor cursor;

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
        View view = inflater.inflate(R.layout.)
        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull TarefasViewHolder tarefasViewHolder, int i) {

    }

    @Override
    public int getItemCount() {
        return cursor.getCount();
    }

    public class TarefasViewHolder extends RecyclerView.ViewHolder {
        public TarefasViewHolder(final View itemView) {
            super(itemView);
        }
    }
}
