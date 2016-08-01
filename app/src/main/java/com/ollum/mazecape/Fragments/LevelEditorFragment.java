package com.ollum.mazecape.Fragments;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.ImageView;

import com.ollum.mazecape.Arrays.LevelArrays;
import com.ollum.mazecape.Classes.Item;
import com.ollum.mazecape.R;

import java.util.ArrayList;
import java.util.List;

public class LevelEditorFragment extends Fragment implements AdapterView.OnItemClickListener, AdapterView.OnItemLongClickListener {

    GridView gridViewLevelEditor;
    int columns, rows;
    String[][] level;
    String scene;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_level_editor, container, false);

        gridViewLevelEditor = (GridView) view.findViewById(R.id.gridview_level_editor);
        gridViewLevelEditor.setOnItemClickListener(this);
        gridViewLevelEditor.setOnItemLongClickListener(this);

        columnDialog();

        return view;
    }

    public void columnDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        builder.setMessage("Select Columns");
        final EditText editText = new EditText(getContext());
        builder.setView(editText);
        builder.setPositiveButton("Next", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                columns = Integer.parseInt(editText.getText().toString());
                rowDialog();
                dialog.dismiss();
            }
        });
        builder.create();
        builder.show();
    }

    public void rowDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        builder.setMessage("Select Rows");
        final EditText editText = new EditText(getContext());
        builder.setView(editText);
        builder.setPositiveButton("Next", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                rows = Integer.parseInt(editText.getText().toString());
                sceneDialog();
                dialog.dismiss();
            }
        });
        builder.create();
        builder.show();
    }

    public void sceneDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        builder.setMessage("Select Scene");
        final EditText editText = new EditText(getContext());
        builder.setView(editText);
        builder.setPositiveButton("Next", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                scene = editText.getText().toString();
                createGrid();
                dialog.dismiss();
            }
        });
        builder.create();
        builder.show();
    }

    public void createGrid() {
        gridViewLevelEditor.setNumColumns(columns);

        level = new String[rows][columns];

        for (int i = 0; i < rows; i++) {
            for (int k = 0; k < columns; k++) {
                level[i][k] = "xxx";
            }
        }

        gridViewLevelEditor.setAdapter(new LevelEditorAdapter(getContext(), 0));

        for (int i = 0; i < level.length; i++) {
            System.out.print("{");
            for (int k = 0; k < level[i].length; k++) {
                System.out.print("\"" + level[i][k] + "\", ");
            }
            System.out.println("},");
        }
        System.out.println("---------------------------");
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        int row = position / columns;
        int column = position - (columns * row);
        //int i;

        if (!level[row][column].equals("xxx")) {
            for (int i = 0; i < LevelArrays.TILES.length; i++) {
                for (int k = 0; k < LevelArrays.TILES[i].length; k++) {
                    if (level[row][column].equals(LevelArrays.TILES[i][k])) {
                        if (k < LevelArrays.TILES[i].length - 1) {
                            k++;
                        } else {
                            k = 0;
                        }
                        level[row][column] = LevelArrays.TILES[i][k];
                        gridViewLevelEditor.setAdapter(new LevelEditorAdapter(getContext(), 0));
                    }
                }
            }
        } else {
            level[row][column] = LevelArrays.TILES[0][0];
            gridViewLevelEditor.setAdapter(new LevelEditorAdapter(getContext(), 0));
        }

        for (int i = 0; i < level.length; i++) {
            System.out.print("{");
            for (int k = 0; k < level[i].length; k++) {
                System.out.print("\"" + level[i][k] + "\", ");
            }
            System.out.println("},");
        }
        System.out.println("---------------------------");
    }

    @Override
    public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
        int row = position / columns;
        int column = position - (columns * row);

        if (!level[row][column].equals("xxx")) {
            for (int i = 0; i < LevelArrays.TILES.length; i++) {
                for (int k = 0; k < LevelArrays.TILES[i].length; k++) {
                    if (level[row][column].equals(LevelArrays.TILES[i][k])) {
                        if (i < LevelArrays.TILES.length - 1) {
                            i++;
                        } else {
                            i = 0;
                        }
                        level[row][column] = LevelArrays.TILES[i][k];
                        gridViewLevelEditor.setAdapter(new LevelEditorAdapter(getContext(), 0));
                    }
                }
            }
        }

        for (int i = 0; i < level.length; i++) {
            System.out.print("{");
            for (int k = 0; k < level[i].length; k++) {
                System.out.print("\"" + level[i][k] + "\", ");
            }
            System.out.println("},");
        }
        System.out.println("---------------------------");

        return true;
    }

    public int getIndex(String[] array, String resource) {
        for (int i = 0; i < array.length; i++) {
            if (array[i].equals(resource)) {
                return i;
            }
        }
        return -1;
    }

    public boolean contains(String[] array, String resource) {
        for (String element : array) {
            if (element.equals(resource)) {
                return true;
            }
        }
        return false;
    }

    public class LevelEditorAdapter extends BaseAdapter {
        private List<Item> items = new ArrayList<Item>();
        private LayoutInflater inflater;

        public LevelEditorAdapter(Context context, int center) {
            inflater = LayoutInflater.from(context);

            for (int i = 0; i < rows; i++) {
                for (int k = 0; k < columns; k++) {
                    items.add(new Item("" + i, context.getResources().getIdentifier(scene + level[i][k], "drawable", context.getPackageName())));
                }
            }
        }

        @Override
        public int getCount() {
            return items.size();
        }

        @Override
        public Object getItem(int i) {
            return items.get(i);
        }

        @Override
        public long getItemId(int i) {
            return items.get(i).drawableId;
        }

        @Override
        public View getView(int i, View view, ViewGroup viewGroup) {
            View v = view;
            ImageView picture;

            if (v == null) {
                v = inflater.inflate(R.layout.gridview_item, viewGroup, false);
                v.setTag(R.id.picture, v.findViewById(R.id.picture));
                v.setTag(R.id.text, v.findViewById(R.id.text));
            }

            picture = (ImageView) v.getTag(R.id.picture);

            Item item = (Item) getItem(i);

            picture.setImageResource(item.drawableId);

            return v;
        }
    }
}
