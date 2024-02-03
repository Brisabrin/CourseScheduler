package com.example.myapplication;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.TextView;
import android.content.Context;


//title, date, description
public class ClassDetailAdapter extends RecyclerView.Adapter<ClassDetailAdapter.ViewHolder> {

    private ArrayList<ClassDetails> classStuff;
    private final Context context;
    private OnItemClickListener onItemClickListener;
    public interface OnItemClickListener {
        void onItemClick(String classId);
    }

    public ClassDetailAdapter(Context context, ArrayList<ClassDetails> classStuff,OnItemClickListener onItemClickListener) {
        this.context = context;
        this.classStuff = classStuff;
        this.onItemClickListener = onItemClickListener;
    }

    @Override
    public ClassDetailAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.class_list, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ClassDetailAdapter.ViewHolder holder, int position) {
        // Get the data model based on position
        ClassDetails classDetails = classStuff.get(position);

        // Set item views based on your views and data model
        TextView titleView = holder.titleTextView;
        titleView.setText(classDetails.title);
        TextView dateView = holder.dateTextView;
        dateView.setText(classDetails.datetime);
        TextView instructorView = holder.instructorTextView;
        instructorView.setText(classDetails.instructor);

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (onItemClickListener != null) {
                    onItemClickListener.onItemClick(classDetails.getId());
                }
            }
        });
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        //textview : id, title, date, instructor
        public TextView titleTextView, dateTextView, instructorTextView;

        // We also create a constructor that accepts the entire item row
        // and does the view lookups to find each subview
        public ViewHolder(View itemView) {
            // Stores the itemView in a public final member variable that can be used
            // to access the context from any ViewHolder instance.
            super(itemView);

            titleTextView = (TextView) itemView.findViewById(R.id.idCourseName);
            dateTextView = (TextView) itemView.findViewById(R.id.idCourseTime);
            instructorTextView = (TextView) itemView.findViewById(R.id.idInstructor);


        }
    }
    @Override
    public int getItemCount() {
        return classStuff.size();
    }

}




