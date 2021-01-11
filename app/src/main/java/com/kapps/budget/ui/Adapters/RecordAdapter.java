package com.kapps.budget.ui.Adapters;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.graphics.Canvas;
import android.support.v7.util.SortedList;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Collections;
import java.util.List;
import java.util.Locale;


public class RecordAdapter extends RecyclerView.Adapter<NoteCardAdapter.NoteCardViewHolder> {

    private List<Note> NoteList;
    SortedList<Note> sortednotes;
    Activity activity;
    Context context;
    float screenwidth;

    public RecordAdapter(Context context, Activity activity) {
        this.context = context;
        this.activity = activity;
    }

    public class NoteCardViewHolder extends RecyclerView.ViewHolder {
        LinearLayout notelayout;
        TextView Id_txt;
        TextView date_txt;
        EditText title_txt;
        LinedEditText note_txt;
        Button save_btn;
        Button delete_btn;
        Canvas canvas;
        CardView notecard;

        public NoteCardViewHolder(View view) {
            super(view);
            notelayout = view.findViewById(R.id.note_layout);
            Id_txt = view.findViewById(R.id.id_txt);
            date_txt = view.findViewById(R.id.date_txt);
            title_txt = view.findViewById(R.id.title_txt);
            note_txt = view.findViewById(R.id.note_txt);
            save_btn = view.findViewById(R.id.save_btn);
            delete_btn = view.findViewById(R.id.delete_btn);
            notecard = view.findViewById(R.id.note_card);


            save_btn.setEnabled(false);

        }

    }

    @android.support.annotation.NonNull
    @Override
    public NoteCardViewHolder onCreateViewHolder(@android.support.annotation.NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.notecard, parent, false);
        return new NoteCardViewHolder(view);

    }

    Note current;

    @SuppressLint("ClickableViewAccessibility")
    @Override
    public void onBindViewHolder(@android.support.annotation.NonNull NoteCardViewHolder holder, int position) {
        /**assign every element's values in NoteList to current list that holds every note values for holder position*/
        current = NoteList.get(position);
        /**fold values to Notecard to display*/
        holder.note_txt.setText(current.Text);
        holder.title_txt.setText(current.Title);
        holder.Id_txt.setText(Integer.toString(current.ID));

        String Date = Dateformat(current.Date);
        holder.date_txt.setText(Date);
        holder.notelayout.setTag(current);


        /**Create a single Textwatcher for both edittexts text changing**/
        final TextWatcher textWatcher = new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                /**ALWAYS PUT LONG MULTI-DOT NOTATIONS VALUES INTO A SIMPLE VARIABLE NAME**/
                String x = holder.title_txt.getText().toString();
                /**check if title is changed or note text is changed, enable and set onclicklistener on save button*/
                if (!x.equals(current.Title) || !holder.note_txt.getText().toString().equals(current.Text)) {
                    holder.save_btn.setEnabled(true);
                    holder.save_btn.setClickable(true);
                    holder.save_btn.setOnClickListener(v -> {
                        Calendar calendar = Calendar.getInstance(Locale.getDefault());
                        /*UPDATE column and save input to DB*/
                        Main4Activity.NotesDB.NoteDAO().Save(holder.title_txt.getText().toString(), holder.note_txt.getText().toString(), calendar.getTimeInMillis(), NoteList.get(position).ID);
                        holder.save_btn.setEnabled(false);
                        holder.save_btn.setClickable(false);
                        /*to keep VIEW data sync and updated with MODEL data*/
                        NoteList = Main4Activity.NotesDB.NoteDAO().getNotes();
                        if (NoteList != null)
                            current = NoteList.get(position);
                    });
                }
                /*if both titles and both texts remain the same after text changed,disable save button*/
                else if (x.equals(current.Title) && holder.note_txt.getText().toString().equals(current.Text)) {
                    holder.save_btn.setEnabled(false);
                    holder.save_btn.setClickable(false);
                }

            }
        };
        /**hook the custom textwatcher**/
        holder.note_txt.addTextChangedListener(textWatcher);
        holder.title_txt.addTextChangedListener(textWatcher);

        OnSwipeListener onSwipeListener = new OnSwipeListener(context) {
            {
                setCustomDraggedView(true, holder.notecard);
                setAnimated(true);
                setAnimationDelay(100);
                setDragHorizontal(true);
                setVelocityThreshold(5);
                setDragSnapBack(true);
                setOnlyRight(true);
            }

            @Override
            public void onSwipeRight(float distance) {
                super.onSwipeRight(distance);
                if (isExitScreenOnSwipe()) {
                    Main4Activity.NotesDB.NoteDAO().Delete(NoteList.get(position).ID);
                    reloadDB();
                }
            }

            @Override
            public void onSwipeDown(float distance) {
                super.onSwipeDown(distance);
            }

        };
        holder.note_txt.setOnTouchListener(onSwipeListener);

        holder.title_txt.setOnTouchListener(onSwipeListener);
        ;

        holder.notecard.setOnTouchListener(onSwipeListener);

        holder.delete_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Main4Activity.NotesDB.NoteDAO().Delete(NoteList.get(position).ID);
                reloadDB();
            }
        });

    }


    @Override
    public int getItemCount() {
        return NoteList.size();
    }


    public void reloadDB() {
        if (Main4Activity.NotesDB.NoteDAO() != null)
            NoteList = Main4Activity.NotesDB.NoteDAO().getNotes();
        else {
            Main4Activity.NotesDB.NoteDAO().CreateNote("", "", 1000000000);
            NoteList = Main4Activity.NotesDB.NoteDAO().getNotes();
            NoteList = SortList(NoteList);
        }

        notifyDataSetChanged();
    }

    public String Dateformat(Long timestamp) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.getDefault());
        java.util.Date currenTimeZone = new java.util.Date(timestamp);
        return sdf.format(currenTimeZone);
    }

    public static List<Note> SortList(List<Note> Notes) {

        //** implementing lambda expression
        Collections.sort(Notes, (p1, p2) -> {
            return Integer.compare(p1.ID, p2.ID);
        });

        return Notes;
    }
}
