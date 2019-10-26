package com.vogella.android.recyclerview;

import android.content.ClipData;
import android.content.Context;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import org.w3c.dom.Text;

import java.util.ArrayList;

/* Adapter: is a bridge between UI component and data source
that helps us to fill data in UI component. It holds the data and
sends the data to an Adapter View, then view can take the data
from the adapter view and show the data on different views like
ListView, GridView, Spinner, etc.
*/

// class PersonAdapter extends RecyclerView.Adapter and uses the specific ViewHolder inside
// our class called ViewHolder, so we call it by -> PersonAdapter.ViewHolder
public class PersonAdapter extends RecyclerView.Adapter<PersonAdapter.ViewHolder> {

    // we pass in an ArrayList of Person objects
    private ArrayList<Person> people;

    // Interface
    ItemClicked activity;

    // interface that tells main activity which item in the list was clicked
    public interface ItemClicked {

        void onItemClicked(int index);
    }

    // constructor that receives context (activity that will be using this adapter)
    public PersonAdapter (Context context, ArrayList<Person> list) {

        // set people variable to list passed in, so now we have a link to the list
        // passed in and now we can use the objects in that list
        people = list;

        activity = (ItemClicked) context; // refers to main activity converted to an ItemClicked
    }

    // Inner class that has access to the outer class methods,
    // will represent every item that we will place in our view (list_items.xml)
    public class ViewHolder extends RecyclerView.ViewHolder {

        // we need to define every item we have in our list_items
        ImageView ivPref;
        TextView tvName, tvSurname;

        // itemView refers to the list_items view
        public ViewHolder(@NonNull final View itemView) {
            super(itemView);
            tvName = itemView.findViewById(R.id.tvName);
            tvSurname = itemView.findViewById(R.id.tvSurname);
            ivPref = itemView.findViewById(R.id.ivPref);

            // set the onClick listener on this constructor
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    activity.onItemClicked(people.indexOf((Person) view.getTag()));

                }
            });
        }
    }

    // This method is called right when the adapter is created and is used
    // to initialize your ViewHolder(s)
    @NonNull
    @Override
    public PersonAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {

        // gets a connection to the layout
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.list_items, viewGroup, false);

        // passes in view, so we know exactly in what view the items in our viewHolder are linked up to
        return new ViewHolder(v);
    }

    // This method is called for each ViewHolder to bind it to the adapter.
    // This is where we will pass our data to our ViewHolder
    // Where we set our items for every item in the ArrayList
    @Override
    public void onBindViewHolder(@NonNull PersonAdapter.ViewHolder viewHolder, int i) {
        // this method runs for every item currently in our ArrayList
        viewHolder.itemView.setTag(people.get(i));

        // referring to Person class
        viewHolder.tvName.setText(people.get(i).getName());
        viewHolder.tvSurname.setText(people.get(i).getSurname());

        if (people.get(i).getPreference().equals("bus")) {
            viewHolder.ivPref.setImageResource(R.drawable.bus);
        }
        else {
            viewHolder.ivPref.setImageResource(R.drawable.plane);

        }
    }

    // This method returns the size of the collection that contains the items we want
    // to display
    @Override
    public int getItemCount() {
        return people.size();
    }
}
