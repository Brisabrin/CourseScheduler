package com.example.myapplication;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

//title, date, description
public class ClassDetailAdapter extends RecyclerView.Adapter<ClassDetailAdapter.ViewHolder> {

    private ArrayList classStuff;
    public ClassDetailAdapter(ArrayList classStuff) {
        this.classStuff = classStuff;
    }


    @Override
    public ClassDetailAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);

        // Inflate the custom layout
        View contactView = inflater.inflate(R.layout.class_list, parent, false);

        // Return a new holder instance
        ViewHolder viewHolder = new ViewHolder(contactView);
        return viewHolder;
    }

//    @Override
//    public void onBindViewHolder(ContactsAdapter.ViewHolder holder, int position) {
//        // Get the data model based on position
//        Contact contact = mContacts.get(position);
//
//        // Set item views based on your views and data model
//        TextView textView = holder.nameTextView;
//        textView.setText(contact.getName());
//        Button button = holder.messageButton;
//        button.setText(contact.isOnline() ? "Message" : "Offline");
//        button.setEnabled(contact.isOnline());
//    }
    public class ViewHolder extends RecyclerView.ViewHolder {
        //textview : id, title, date, instructor
        public TextView titleTextView, dateTextView, instructorTextView;

        // We also create a constructor that accepts the entire item row
        // and does the view lookups to find each subview
        public ViewHolder(View itemView) {
            // Stores the itemView in a public final member variable that can be used
            // to access the context from any ViewHolder instance.
            super(itemView);

            titleTextView = (TextView) itemView.findViewById(R.id.courseName);
            dateTextView = (TextView) itemView.findViewById(R.id.classTime);
            instructorTextView = (TextView) itemView.findViewById(R.id.instructor);


        }
    }




}




