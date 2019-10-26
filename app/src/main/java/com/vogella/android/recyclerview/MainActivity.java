package com.vogella.android.recyclerview;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.GridLayout;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements PersonAdapter.ItemClicked {

    RecyclerView recyclerView;
    RecyclerView.Adapter myAdapter;
    RecyclerView.LayoutManager layoutManager;

    ArrayList<Person> people;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = findViewById(R.id.list);
        recyclerView.setHasFixedSize(true); // making sure recycler isn't changing based on size of items in the list

        layoutManager = new LinearLayoutManager(this);
       //layoutManager = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
       // layoutManager = new GridLayoutManager(this, 2, GridLayoutManager.VERTICAL, false);


        recyclerView.setLayoutManager(layoutManager);

        Button btnAdd = findViewById(R.id.btnAdd);

        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                people.add(new Person("Susan", "Peters", "plane"));
                myAdapter.notifyDataSetChanged();

            }
        });

        people = new ArrayList<Person>();
        people.add(new Person("John", "Rambo", "bus"));
        people.add(new Person("Chuck", "Norris", "bus"));
        people.add(new Person("Peter", "Jennings", "plane"));
        people.add(new Person("Tom", "Cruise", "plane"));
        people.add(new Person("John", "Rambo", "bus"));
        people.add(new Person("Chuck", "Norris", "bus"));
        people.add(new Person("Peter", "Jennings", "plane"));
        people.add(new Person("Tom", "Cruise", "plane"));
        people.add(new Person("John", "Rambo", "bus"));
        people.add(new Person("Chuck", "Norris", "bus"));
        people.add(new Person("Peter", "Jennings", "plane"));
        people.add(new Person("Tom", "Cruise", "plane"));
        people.add(new Person("Tom", "Cruise", "plane"));

        // setting the adapter takes context, and ArrayList as parameters
        myAdapter = new PersonAdapter(this, people);

        // setting the adapter on the recyclerView
        recyclerView.setAdapter(myAdapter);


    }

    @Override
    public void onItemClicked(int index) {

        Toast.makeText(this, "Surname: " + people.get(index).getSurname(), Toast.LENGTH_SHORT).show();
    }
}
