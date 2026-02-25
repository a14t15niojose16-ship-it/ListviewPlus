package com.listviewplus;

import android.content.Context;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.view.View;
import android.widget.AdapterView;

import com.google.appinventor.components.annotations.*;
import com.google.appinventor.components.common.*;
import com.google.appinventor.components.runtime.*;

import java.util.ArrayList;

@DesignerComponent(
        version = 1,
        description = "Listview+ Avançado",
        category = ComponentCategory.EXTENSION,
        nonVisible = false,
        iconName = "")

@SimpleObject(external = true)

public class ListviewPlus extends AndroidViewComponent {

    private Context context;
    private ListView listView;
    private ArrayAdapter<String> adapter;
    private ArrayList<String> items;

    public ListviewPlus(ComponentContainer container) {
        super(container);
        context = container.$context();
        listView = new ListView(context);
        items = new ArrayList<>();
        adapter = new ArrayAdapter<>(context,
                android.R.layout.simple_list_item_1,
                items);

        listView.setAdapter(adapter);

        listView.setOnItemClickListener((parent, view, position, id) -> {
            ItemClick(items.get(position), position + 1);
        });
    }

    @Override
    public View getView() {
        return listView;
    }

    @SimpleFunction(description = "Adicionar Item")
    public void AddItem(String text) {
        items.add(text);
        adapter.notifyDataSetChanged();
    }

    @SimpleEvent(description = "Evento ao clicar")
    public void ItemClick(String text, int position) {
        EventDispatcher.dispatchEvent(this, "ItemClick", text, position);
    }
}
