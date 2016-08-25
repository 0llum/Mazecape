package com.ollum.mazecape.Fragments;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.content.res.ResourcesCompat;
import android.text.InputType;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.Toast;

import com.ollum.mazecape.Activities.MainActivity;
import com.ollum.mazecape.Arrays.Tiles;
import com.ollum.mazecape.R;

import java.util.ArrayList;
import java.util.List;

public class LevelEditorFragment extends Fragment implements AdapterView.OnItemClickListener, AdapterView.OnItemLongClickListener {

    public static int columns, rows;
    public static String[][] level;
    public static String scene;
    public static List<Integer> items;
    GridView gridViewLevelEditor;
    LevelEditorAdapter levelEditorAdapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_level_editor, container, false);

        MainActivity.title.setText(R.string.level_editor);

        gridViewLevelEditor = (GridView) view.findViewById(R.id.gridview_level_editor);
        gridViewLevelEditor.setOnItemClickListener(this);
        gridViewLevelEditor.setOnItemLongClickListener(this);

        columnDialog();

        return view;
    }

    public void columnDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        builder.setMessage("Select Columns (1..20");
        final EditText editText = new EditText(getContext());
        editText.setInputType(InputType.TYPE_CLASS_NUMBER);
        builder.setView(editText);
        builder.setPositiveButton("Next", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                MainActivity.soundPool.play(MainActivity.clickID, MainActivity.volumeSound, MainActivity.volumeSound, 1, 0, 1);
                if (editText.getText().toString().equals("") || Integer.parseInt(editText.getText().toString()) < 1 || Integer.parseInt(editText.getText().toString()) > 20) {
                    Toast.makeText(getContext(), "Please enter a number between 1 and 20", Toast.LENGTH_LONG).show();
                } else {
                    columns = Integer.parseInt(editText.getText().toString());
                    rowDialog();
                    dialog.dismiss();
                }
            }
        });
        builder.setCancelable(false);
        builder.create();
        builder.show();
    }

    public void rowDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        builder.setMessage("Select Rows (1..20");
        final EditText editText = new EditText(getContext());
        editText.setInputType(InputType.TYPE_CLASS_NUMBER);
        builder.setView(editText);
        builder.setPositiveButton("Next", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                MainActivity.soundPool.play(MainActivity.clickID, MainActivity.volumeSound, MainActivity.volumeSound, 1, 0, 1);
                if (editText.getText().toString().equals("") || Integer.parseInt(editText.getText().toString()) < 1 || Integer.parseInt(editText.getText().toString()) > 20) {
                    Toast.makeText(getContext(), "Please enter a number between 1 and 20", Toast.LENGTH_LONG).show();
                } else {
                    rows = Integer.parseInt(editText.getText().toString());
                    sceneDialog();
                    dialog.dismiss();
                }
            }
        });
        builder.setCancelable(false);
        builder.create();
        builder.show();
    }

    public void sceneDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        builder.setMessage("Select Scene ('f', 'c', 's' or 'd')");
        final EditText editText = new EditText(getContext());
        builder.setView(editText);
        builder.setPositiveButton("Next", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                MainActivity.soundPool.play(MainActivity.clickID, MainActivity.volumeSound, MainActivity.volumeSound, 1, 0, 1);
                if (editText.getText().toString().equals("f") || editText.getText().toString().equals("c") || editText.getText().toString().equals("s") || editText.getText().toString().equals("d")) {
                    scene = editText.getText().toString();
                    createGrid();
                    dialog.dismiss();
                } else {
                    Toast.makeText(getContext(), "Please enter 'f', 'c', 's' or 'd'", Toast.LENGTH_LONG).show();
                }
            }
        });
        builder.setCancelable(false);
        builder.create();
        builder.show();
    }

    public void createGrid() {
        gridViewLevelEditor.setNumColumns(columns);

        level = new String[rows][columns];

        items = new ArrayList<Integer>();

        for (int i = 0; i < rows; i++) {
            for (int k = 0; k < columns; k++) {
                level[i][k] = "xxx";
            }
        }

        for (int i = 0; i < level.length; i++) {
            for (int k = 0; k < level[i].length; k++) {
                items.add(getResources().getIdentifier(scene + level[i][k], "drawable", getContext().getPackageName()));
            }
        }

        levelEditorAdapter = new LevelEditorAdapter(getContext(), 0);
        gridViewLevelEditor.setAdapter(levelEditorAdapter);

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
        MainActivity.soundPool.play(MainActivity.clickID, MainActivity.volumeSound, MainActivity.volumeSound, 1, 0, 1);

        int row = position / columns;
        int column = position - (columns * row);

        if (!level[row][column].equals("xxx")) {
            for (int i = 0; i < Tiles.TILES.length; i++) {
                for (int k = 0; k < Tiles.TILES[i].length; k++) {
                    if (level[row][column].equals(Tiles.TILES[i][k])) {
                        if (k < Tiles.TILES[i].length - 1) {
                            k++;
                        } else {
                            k = 0;
                        }
                        level[row][column] = Tiles.TILES[i][k];
                        items.set(position, getResources().getIdentifier(scene + level[row][column], "drawable", getContext().getPackageName()));
                        levelEditorAdapter.notifyDataSetChanged();
                    }
                }
            }
        } else {
            level[row][column] = Tiles.TILES[0][0];
            items.set(position, getResources().getIdentifier(scene + level[row][column], "drawable", getContext().getPackageName()));
            levelEditorAdapter.notifyDataSetChanged();
        }

        /*for (int i = 0; i < level.length; i++) {
            System.out.print("{");
            for (int k = 0; k < level[i].length; k++) {
                System.out.print("\"" + level[i][k] + "\", ");
            }
            System.out.println("},");
        }
        System.out.println("---------------------------");*/
    }

    @Override
    public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
        MainActivity.soundPool.play(MainActivity.clickID, MainActivity.volumeSound, MainActivity.volumeSound, 1, 0, 1);

        int row = position / columns;
        int column = position - (columns * row);

        if (!level[row][column].equals("xxx")) {
            for (int i = 0; i < Tiles.TILES.length; i++) {
                for (int k = 0; k < Tiles.TILES[i].length; k++) {
                    if (level[row][column].equals(Tiles.TILES[i][k])) {
                        if (i < Tiles.TILES.length - 1) {
                            i++;
                        } else {
                            i = 0;
                        }
                        level[row][column] = Tiles.TILES[i][k];
                        items.set(position, getResources().getIdentifier(scene + level[row][column], "drawable", getContext().getPackageName()));
                        levelEditorAdapter.notifyDataSetChanged();
                    }
                }
            }
        }

        /*for (int i = 0; i < level.length; i++) {
            System.out.print("{");
            for (int k = 0; k < level[i].length; k++) {
                System.out.print("\"" + level[i][k] + "\", ");
            }
            System.out.println("},");
        }
        System.out.println("---------------------------");*/

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

    @Override
    public void onPause() {
        MainActivity.sendButton.setVisibility(View.GONE);
        MainActivity.stepsLayout.setVisibility(View.INVISIBLE);
        super.onPause();
    }

    @Override
    public void onResume() {
        MainActivity.stepsLayout.setVisibility(View.GONE);
        MainActivity.sendButton.setVisibility(View.VISIBLE);
        super.onResume();
    }

    public class LevelEditorAdapter extends BaseAdapter {
        private Context context;

        public LevelEditorAdapter(Context context, int center) {
            this.context = context;
        }

        @Override
        public int getCount() {
            return items.size();
        }

        @Override
        public Integer getItem(int i) {
            return items.get(i);
        }

        @Override
        public long getItemId(int i) {
            return items.get(i);
        }

        @Override
        public View getView(int position, View view, ViewGroup viewGroup) {

            View row = view;
            ViewHolder holder = null;

            if (row == null) {
                LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                row = inflater.inflate(R.layout.gridview_item, viewGroup, false);
                holder = new ViewHolder(row);
                row.setTag(holder);
            } else {
                holder = (ViewHolder) row.getTag();
            }

            //holder.imageView.setImageResource(items.get(position));
            holder.imageView.setImageDrawable(ResourcesCompat.getDrawable(context.getResources(), items.get(position), null));
            //holder.imageView.setImageBitmap(BitmapFactory.decodeResource(context.getResources(), items.get(position)));

            return row;
        }
    }

    class ViewHolder {
        ImageView imageView;

        ViewHolder(View view) {
            imageView = (ImageView) view.findViewById(R.id.picture);
        }
    }
}
