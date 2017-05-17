package com.example.bruhshua.parking.Model;

import android.content.Context;
import android.util.Log;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.lang.reflect.Array;
import java.util.ArrayList;

/**
 * Created by bruhshua on 4/24/17.
 */

public class Singleton {

    private static Singleton singleton = null;
    private Context mAppContext;

    private Student student;
    private ArrayList<ParkingLotProbabilityPOJO> POJOS;


    private FirebaseDatabase database;
    private DatabaseReference myRef;

    private Singleton(Context appContext) {
        mAppContext = appContext;
        setUp();
        queryDatabase();
    }

    public static Singleton getInstance(Context c) {
        if(singleton == null){
            singleton = new Singleton(c.getApplicationContext());
        }
        return singleton;
    }


    //Initialize student data here
    //Reference from fragments like "Singleton.getInstance(getContext).getStudent()"
    private void setUp() {
        student = new Student("jcan1995","cantero.joshua@gmail.com","password","Joshua","Cantero");
        Course course1 = new Course("History 130","History","Catalog","Section","History class.","Online Class",0,"9:45","10:45","August 21","December 6");
        Course course2 = new Course("CS 436","Computer Science","Catalog","Section","CS class.","ACD 211",0,"1:00","1:45","August 21","December 6");

        student.addCourse(course1);
        student.addCourse(course2);

    }

    private void queryDatabase() {

        database = FirebaseDatabase.getInstance();
        myRef = database.getReference("Day");
        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                Log.d("Data","Data:" + dataSnapshot.getValue());
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });


//todo:Add buttons to each class, grab time for that class, subtract 30 min, grab data based off that time.
//Create method to query db in buttons for each listviewitem.

    }


    public Student getStudent() {
        return student;
    }

    public ArrayList<ParkingLotProbabilityPOJO> getProbabilities(){
        return POJOS;
    }

}
