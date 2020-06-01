package io.github.jaccastonguay.detailedtimer;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

public class ChosenWorkoutActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chosen_workout);

        Intent intent = getIntent();
        setTitle(intent.getStringExtra("workout"));

        ListView workoutListView = findViewById(R.id.ChosenExerciseListView);
        ArrayList<Exercise> exerciseArrayList = new ArrayList<>();

        List<Exercise> exercises = (List<Exercise>) intent.getSerializableExtra("workout1");
        for(Exercise exercise: exercises){
            Log.i("exercise passed", exercise.getName());
            Log.i(exercise.getName(), Integer.toString(exercise.getSeconds()));
            //Wrapping this poorly for now.
            exerciseArrayList.add(exercise);
        }

        ExerciseListAdapter adapter = new ExerciseListAdapter(this, R.layout.excercise_view_layout, exerciseArrayList);
        workoutListView.setAdapter(adapter);
    }
}
