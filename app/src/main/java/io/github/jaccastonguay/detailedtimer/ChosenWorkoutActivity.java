package io.github.jaccastonguay.detailedtimer;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ListView;

import java.util.ArrayList;

public class ChosenWorkoutActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chosen_workout);

        Intent intent = getIntent();
        setTitle(intent.getStringExtra("workout"));

        ListView workoutListView = findViewById(R.id.ChosenExerciseListView);
        ArrayList<WorkOut> workOutList = new ArrayList<>();
//        workOutList.add(new WorkOut("leg circles", "1:00"));
//        workOutList.add(new WorkOut("opposite leg circles", "1:00"));
//        workOutList.add(new WorkOut("drunken Mountain Climbers", "37:30"));
//        workOutList.add(new WorkOut("rest", "0:30"));
//        workOutList.add(new WorkOut("Planks", "1:00"));
//        workOutList.add(new WorkOut("scissors", "1:00"));
//        workOutList.add(new WorkOut("starfish crunch", "0:30"));
//        workOutList.add(new WorkOut("rest", "0:30"));
//        workOutList.add(new WorkOut("Russian V tuck", ":30"));

        WorkoutListAdapter adapter = new WorkoutListAdapter(this, R.layout.excercise_view_layout, workOutList);
        workoutListView.setAdapter(adapter);
    }
}
