package io.github.jaccastonguay.detailedtimer;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;

public class WorkoutListAdapter extends ArrayAdapter<WorkOut> {
    private static final String TAG = "WorkoutListAdapter";

    private Context mContext;
    private int mResource;

    public WorkoutListAdapter(@NonNull Context context, int resource, @NonNull ArrayList<WorkOut> objects) {
        super(context, resource, objects);
        this.mContext = context;
        this.mResource = resource;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        String name = getItem(position).getName();
        String time = getItem(position).getTime();
        WorkOut workOut = new WorkOut(name, time);

        LayoutInflater inflater = LayoutInflater.from(mContext);
        convertView = inflater.inflate(mResource, parent, false);

        //Setting views for each list item. ConvertView is the view we are going to be.
        TextView nameTextView = (TextView) convertView.findViewById(R.id.nameTextView);
        TextView timeTextView = (TextView) convertView.findViewById(R.id.timeTextView);

        nameTextView.setText(workOut.getName());
        timeTextView.setText(workOut.getTime());
        return convertView;
    }
}
