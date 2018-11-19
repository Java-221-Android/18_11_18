package com.sheygam.java_221_18_11_18_cw;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.text.method.MovementMethod;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements MyAdapter.RowClickListener, View.OnClickListener {
    private RecyclerView myList;
    private MyAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        myList = findViewById(R.id.myList);
        RecyclerView.LayoutManager manager = new LinearLayoutManager(this);
//        RecyclerView.LayoutManager manager = new GridLayoutManager(this,2,LinearLayoutManager.VERTICAL,true);
        adapter = new MyAdapter();
        adapter.setListener(this);
        RecyclerView.ItemDecoration decoration = new DividerItemDecoration(this,DividerItemDecoration.VERTICAL);
        myList.setLayoutManager(manager);
        myList.setAdapter(adapter);
        myList.addItemDecoration(decoration);
        findViewById(R.id.add_btn).setOnClickListener(this);
        findViewById(R.id.remove_btn).setOnClickListener(this);

        ItemTouchHelper helper = new ItemTouchHelper(new MyTouchCallback());
        helper.attachToRecyclerView(myList);
    }

    @Override
    public void onRowClick(View view, int pos) {
        Toast.makeText(this, "Click " + pos, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onRowLongClick(View view, int pos) {
        Toast.makeText(this, "Long click " + pos, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onClick(View v) {
        if(v.getId() == R.id.add_btn){
            adapter.add();
        }else if(v.getId() == R.id.remove_btn){
            adapter.remove();
        }

    }

    private class MyTouchCallback extends ItemTouchHelper.Callback {
        @Override
        public int getMovementFlags(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder) {
            return makeMovementFlags(ItemTouchHelper.DOWN|ItemTouchHelper.UP|ItemTouchHelper.LEFT|ItemTouchHelper.RIGHT,ItemTouchHelper.END|ItemTouchHelper.START);
        }

        @Override
        public boolean onMove(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, RecyclerView.ViewHolder target) {
            adapter.move(viewHolder.getAdapterPosition(),target.getAdapterPosition());
            return true;
        }

        @Override
        public void onSwiped(RecyclerView.ViewHolder viewHolder, int direction) {
            adapter.remove(viewHolder.getAdapterPosition());
        }

        @Override
        public boolean isItemViewSwipeEnabled() {
            return true;
        }

        @Override
        public boolean isLongPressDragEnabled() {
            return true;
        }
    }
}
