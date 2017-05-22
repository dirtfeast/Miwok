package com.example.android.miwok;

import android.app.Activity;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by DanW on 5/21/2017.
 */

public class WordAdapter extends ArrayAdapter<Word> {

    // @param context    The current context. Used to inflate the layout file.
    // @param miwokWords A List of Word objects to display in a list
    public WordAdapter(Activity context, ArrayList<Word> miwokWords) {
        // Here, we initialize the ArrayAdapter's internal storage for the context and the list.
        // the second argument is used when the ArrayAdapter is populating a single TextView.
        // Because this is a custom adapter for two TextViews and an ImageView, the adapter is not
        // going to use this second argument, so it can be any value. Here, we used 0.

        // Call on constructor for superclass ArrayAdapter
        // ArrayAdapter(Context context, int resource, List<T> objects)
        super(context, 0, miwokWords);
    }

    @NonNull
    @Override
    // Custom override of the getView() method
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        // Check if the existing view is being recycled, otherwise inflate the view
        // Important, for example, when you start app and there are no recyclable views yet
        View listItemView = convertView;
        if (listItemView == null) {
            listItemView = LayoutInflater.from(getContext()).inflate(R.layout.list_item, parent, false);
        }

        // Get the Word object located at this position in the list
        // getItem() is a method within ArrayAdapter superclass
        Word currentWord = getItem(position);

        // Find the TextView in the list_item.xml layout with the ID word_miwok
        TextView miwokTextView = (TextView) listItemView.findViewById(R.id.word_miwok);
        // Get the Miwok word from the current Word object and
        // set this text on the name TextView
        miwokTextView.setText(currentWord.getmMiwokWord());

        // Find the TextView in the list_item.xml layout with the ID word_englis
        TextView englishTextView = (TextView) listItemView.findViewById(R.id.word_english);
        // Get the version number from the current AndroidFlavor object and
        // set this text on the number TextView
        englishTextView.setText(currentWord.getmEnglishWord());

        // Find the ImageView in the list_item.xml layout with the ID list_item_icon
//        ImageView iconView = (ImageView) listItemView.findViewById(R.id.list_item_icon);

        // Get the image resource ID from the current Word object and
        // set the image to iconView
//        iconView.setImageResource(currentWord.getImageResourceId());

        // Return the whole list item layout (containing 2 TextViews and an ImageView)
        // so that it can be shown in the ListView
        return listItemView;

    } // Close method getView()

} // Close class WordAdapter
