package ninja.farhood.materialtodolist;

import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

public class ToDoListActivity extends AppCompatActivity {

/* NOTE - It's good pracice to prefix class level variables with "m".
This ensures that while reading code, we can easily differentiate
between class and local variables. */

    private EditText mItemInput;
    private FloatingActionButton mAddButton;
    private ListView mDynamicListView;
    private List<String> mItemsList;
    private ArrayAdapter<String> mItemListAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_to_do_list);

        mItemInput = (EditText) findViewById(R.id.itemEditText);
        mAddButton = (FloatingActionButton) findViewById(R.id.add_item_button);
        mDynamicListView = (ListView) findViewById(R.id.itemListView);

        // Initialize items ArrayList and add a default item so that we always see one item when app is launched
        mItemsList = new ArrayList<>();
        mItemsList.add("Android ATC");

        // Initialize ArrayAdapter that holds the mapping of,
        // Strings in ArrayList, to, textView in listView items
        mItemListAdapter = new ArrayAdapter<>(
                ToDoListActivity.this,
                R.layout.list_individual_item,
                R.id.listItemText,
                mItemsList);

        // Setting the ArrayAdapter to the listView
        mDynamicListView.setAdapter(mItemListAdapter);

        // Add item to the listView on click floating action button (mAddButton)
        mAddButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                String todoItem = mItemInput.getText().toString();
                if ( todoItem.length() > 0) {

                    // Add editText's input to the item list
                    mItemsList.add(todoItem);

                    // Apply changes on the ArrayAdapter to refresh the listView
                    mItemListAdapter.notifyDataSetChanged();

                    // Clear the editText
                    mItemInput.setText("");

                }

            }

        });

        // Delete mItemInput on a long click on the ItemInput
        mDynamicListView.setOnItemLongClickListener(
                new AdapterView.OnItemLongClickListener() {
                    @Override
                    public boolean onItemLongClick(
                            AdapterView<?> parent,
                            View view,
                            int position,
                            long id)
                    {
                        // Remove the mItemInput from mItemsList
                        mItemsList.remove(position);
                        mItemListAdapter.notifyDataSetChanged();
                        return true;
                    }

                });

    }
}
