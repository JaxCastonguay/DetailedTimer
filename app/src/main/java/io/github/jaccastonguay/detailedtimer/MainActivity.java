package io.github.jaccastonguay.detailedtimer;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ListView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ListView workoutListView = findViewById(R.id.ExerciseListView);
        ArrayList<WorkOut> workOutList = new ArrayList<>();
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

        WorkoutListAdapter adapter = new WorkoutListAdapter(this, R.layout.excercise_view_layout, workOutList);
        workoutListView.setAdapter(adapter);
    }
}
