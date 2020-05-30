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

        //Temp
        workOutList.add(new WorkOut("Abs", "7:30"));
        workOutList.add(new WorkOut("Biceps", "27:30"));
        workOutList.add(new WorkOut("Triceps", "37:30"));
        workOutList.add(new WorkOut("Back", "30:00"));
        workOutList.add(new WorkOut("Legs", "22:00"));
        workOutList.add(new WorkOut("Cardio", "17:00"));
        workOutList.add(new WorkOut("Abs", "7:30"));
        workOutList.add(new WorkOut("Biceps", "27:30"));
        workOutList.add(new WorkOut("Triceps", "37:30"));
        workOutList.add(new WorkOut("Back", "30:00"));
        workOutList.add(new WorkOut("Legs", "22:00"));
        workOutList.add(new WorkOut("Cardio", "17:00"));

        //Get Workouts
        //Open DB
        SQLiteDatabase sqLiteDatabase = this.openOrCreateDatabase("WorkoutDB", MODE_PRIVATE, null);
        sqLiteDatabase.execSQL("CREATE TABLE IF NOT EXISTS Workout ( workoutid INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, name VARCHAR NOT NULL)");
        sqLiteDatabase.execSQL("CREATE TABLE IF NOT EXISTS Exercise ( exerciseid INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, name VARCHAR NOT NULL," +
                " seconds INTEGER NOT NULL, workoutid INTEGER NOT NULL, FOREIGN KEY (workoutid) REFERENCES Workout (workoutid))");

        //sqLiteDatabase.execSQL("INSERT INTO Workout (name) VALUES ('Abs')");
//        sqLiteDatabase.execSQL("INSERT INTO Workout (name) VALUES ('Abs')");
//        sqLiteDatabase.execSQL("INSERT INTO Workout (name) VALUES ('Biceps')");
//        sqLiteDatabase.execSQL("INSERT INTO Workout (name) VALUES ('Triceps')");
//        sqLiteDatabase.execSQL("INSERT INTO Workout (name) VALUES ('Back')");
//        sqLiteDatabase.execSQL("INSERT INTO Workout (name) VALUES ('Legs')");
//        sqLiteDatabase.execSQL("INSERT INTO Workout (name) VALUES ('Cardio')");
//        sqLiteDatabase.execSQL("INSERT INTO Workout (name) VALUES ('Abs2')");
//        sqLiteDatabase.execSQL("INSERT INTO Workout (name) VALUES ('Biceps2')");
//        sqLiteDatabase.execSQL("INSERT INTO Workout (name) VALUES ('Triceps2')");
//        sqLiteDatabase.execSQL("INSERT INTO Workout (name) VALUES ('Back2')");
//        sqLiteDatabase.execSQL("INSERT INTO Workout (name) VALUES ('Legs2')");
//        sqLiteDatabase.execSQL("DELETE FROM Exercise WHERE name = 'Legs2'");
//        sqLiteDatabase.execSQL("INSERT INTO Exercise (name, seconds, workoutid) VALUES ('crunches', 30, 1)");
//        sqLiteDatabase.execSQL("INSERT INTO Exercise (name, seconds, workoutid) VALUES ('planks', 30, 1)");
//        sqLiteDatabase.execSQL("INSERT INTO Exercise (name, seconds, workoutid) VALUES ('sit ups', 30, 1)");
//        sqLiteDatabase.execSQL("INSERT INTO Exercise (name, seconds, workoutid) VALUES ('mountain climbers', 30, 1)");
//        sqLiteDatabase.execSQL("INSERT INTO Exercise (name, seconds, workoutid) VALUES ('scissors', 30, 1)");
//        sqLiteDatabase.execSQL("INSERT INTO Exercise (name, seconds, workoutid) VALUES ('Hammer Curls', 40, 3)");
//        sqLiteDatabase.execSQL("INSERT INTO Exercise (name, seconds, workoutid) VALUES ('Preacher Curls', 30, 3)");




        //Get Workouts
        Cursor c = sqLiteDatabase.rawQuery("SELECT * FROM Exercise", null);
        int nameIndex = c.getColumnIndex("name");
        int idIndex = c.getColumnIndex("workoutid");
        int exerciseId = c.getColumnIndex("exerciseid");
        c.moveToFirst();
        while (!c.isAfterLast()){
            Log.i("name", c.getString(nameIndex));
            Log.i("FK", c.getString(idIndex));
            Log.i("PK", c.getString(exerciseId));
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
}
