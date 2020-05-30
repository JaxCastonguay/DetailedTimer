package io.github.jaccastonguay.detailedtimer;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        setTitle("Choose a workout");

        ListView workoutListView = findViewById(R.id.ExerciseListView);
        final ArrayList<WorkOut> workOutList = new ArrayList<>();
        final ArrayList<Exercise> exerciseList = new ArrayList<>();

        //Workouts and Exercises
        //Open DB
        SQLiteDatabase sqLiteDatabase = this.openOrCreateDatabase("WorkoutDB", MODE_PRIVATE, null);
        sqLiteDatabase.execSQL("CREATE TABLE IF NOT EXISTS Workout ( workoutid INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, name VARCHAR NOT NULL)");
        sqLiteDatabase.execSQL("CREATE TABLE IF NOT EXISTS Exercise ( exerciseid INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, name VARCHAR NOT NULL," +
                " seconds INTEGER NOT NULL, workoutid INTEGER NOT NULL, FOREIGN KEY (workoutid) REFERENCES Workout (workoutid))");

        //Get Exercises
        Cursor c = sqLiteDatabase.rawQuery("SELECT * FROM Exercise", null);
        int nameIndex = c.getColumnIndex("name");
        int idIndex = c.getColumnIndex("exerciseid");
        int secondsId = c.getColumnIndex("seconds");
        int workoutId = c.getColumnIndex("workoutid");
        c.moveToFirst();
        while (!c.isAfterLast()){
            Log.i("name", c.getString(nameIndex));
            Log.i("PK", c.getString(idIndex));
            Log.i("seconds", Integer.toString(c.getInt(secondsId)));
            Log.i("FK", c.getString(workoutId));
            exerciseList.add(new Exercise(c.getString(nameIndex), c.getInt(secondsId), c.getInt(workoutId)));
            c.moveToNext();
        }

        //Get Workouts
        c = sqLiteDatabase.rawQuery("SELECT * FROM Workout", null);
        nameIndex = c.getColumnIndex("name");
        idIndex = c.getColumnIndex("workoutid");
        c.moveToFirst();
        while (!c.isAfterLast()){
            Log.i("name", c.getString(nameIndex));
            Log.i("PK", c.getString(idIndex));
            int totalSeconds = 0;

            for(Exercise e : exerciseList){
                if(e.getWorkoutid() == c.getInt(idIndex)){
                    totalSeconds+=e.getSeconds();
                }
            }

            workOutList.add(new WorkOut(c.getString(nameIndex), ConvertToMinuteFormat(totalSeconds), c.getInt(idIndex)));
            c.moveToNext();
        }

        //Practice safe DBing
        c.close();
        sqLiteDatabase.close();

        WorkoutListAdapter adapter = new WorkoutListAdapter(this, R.layout.excercise_view_layout, workOutList);
        workoutListView.setAdapter(adapter);

        workoutListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent = new Intent(getApplicationContext(), ChosenWorkoutActivity.class);
                intent.putExtra("workout", workOutList.get(i).getName());

                startActivity(intent);
            }
        });
    }

    private String ConvertToMinuteFormat(int seconds){

        int minutes = (int) Math.floor((seconds/60.0));
        int remainingSecond = seconds % 60;
        String stringSeconds = Integer.toString(remainingSecond);
        if(stringSeconds.equals("0")){
            stringSeconds="00";
        }
        return minutes + ":" + stringSeconds;
    }
}
