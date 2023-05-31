package sg.edu.rp.c346.id22024044.lesson_07_simpletodo;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.PopupMenu;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    Spinner spnToDoList;
    EditText etInput;
    Button btnAdd;
    Button btnDelete;
    Button btnClear;
    ListView lvToDo;
    ArrayList<String> toDoList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        spnToDoList = findViewById(R.id.spinnerToDoList);
        etInput = findViewById(R.id.editTextToDo);
        btnAdd = findViewById(R.id.buttonAdd);
        btnDelete = findViewById(R.id.buttonDelete);
        btnClear = findViewById(R.id.buttonClear);

        lvToDo = findViewById(R.id.listViewToDo);

        ArrayAdapter aaToDo = new ArrayAdapter<>(this,
                android.R.layout.simple_list_item_1, toDoList);

        lvToDo.setAdapter(aaToDo);

        ArrayAdapter<CharSequence> spinner = ArrayAdapter.createFromResource(this,
                R.array.spinnerItems, android.R.layout.simple_spinner_item);

        spinner.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        spnToDoList.setAdapter(spinner);

        spnToDoList.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                switch (position) {
                    case 0:
                        etInput.setHint("Type in a new task here");

                        btnAdd.setTextColor(getColor(R.color.black));
                        btnAdd.setClickable(true);

                        btnDelete.setTextColor(getColor(R.color.grey));
                        btnDelete.setClickable(false);
                        break;
                    case 1:
                        etInput.setHint("Type in index of task to be removed (starts at 0)");

                        btnAdd.setTextColor(getColor(R.color.grey));
                        btnAdd.setClickable(false);

                        btnDelete.setTextColor(getColor(R.color.black));
                        btnDelete.setClickable(true);
                        break;
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String toDoActivity = etInput.getText().toString();
                toDoList.add(toDoActivity);
                aaToDo.notifyDataSetChanged();
                etInput.setText(null);
            }
        });

        btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (toDoList.isEmpty()) {
                    Toast.makeText(MainActivity.this, "You don't have any task to remove", Toast.LENGTH_SHORT).show();
                } else {
                    int toDoPosition = Integer.parseInt(etInput.getText().toString());
                    if (toDoPosition >= toDoList.size()) {
                        Toast.makeText(MainActivity.this, "Wrong index number", Toast.LENGTH_SHORT).show();
                    } else {
                        toDoList.remove(toDoPosition);
                        aaToDo.notifyDataSetChanged();
                        etInput.setText(null);
                    }
                }
            }
        });

        btnClear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                toDoList.clear();
                aaToDo.notifyDataSetChanged();
            }
        });

    }

}