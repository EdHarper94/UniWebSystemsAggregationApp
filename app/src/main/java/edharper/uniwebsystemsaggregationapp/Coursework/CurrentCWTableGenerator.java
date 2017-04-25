package edharper.uniwebsystemsaggregationapp.Coursework;

import android.content.Context;
import android.view.Gravity;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import java.text.DateFormat;
import java.util.ArrayList;

import edharper.uniwebsystemsaggregationapp.R;

import static android.view.Gravity.CENTER;

/**
 * @file CurrentCWTableGenerator.java
 * @author Ed Harper
 * @date 19/03/2017
 *
 * Generates Table Layout for passed Currents Courseworks
 */

public class CurrentCWTableGenerator {

    private Context context;
    private TableLayout tl;

    private ArrayList<CurrentCoursework> courseworks = new ArrayList<>();
    private ArrayList<String> headings = new ArrayList<>();

    private final DateFormat DATE_FORMAT = CourseworkGlobals.DATE_FORMAT;
    private final DateFormat DATE_TIME_FORMAT = CourseworkGlobals.DATE_TIME_FORMAT;

    /**
     * Initialises current coursework table generator
     * @param context passed context
     * @param tl passed table layout
     * @param courseworks passed courseworks
     * @param headings passed headings
     */
    public CurrentCWTableGenerator(Context context, TableLayout tl, ArrayList<CurrentCoursework> courseworks, ArrayList<String> headings){
        this.context = context;
        this.tl = tl;
        this.courseworks = courseworks;
        this.headings = headings;
    }

    /**
     * Sorts CurrentCourseworks and generates their table
     * @return the generated TableLayout
     * @see CurrentCoursework
     */
    public TableLayout generateCWTable(){

        TableRow row = new TableRow(context);

        // Init layout params (same for all Coursework table gens)
        TableRow.LayoutParams lp = new TableRow.LayoutParams(300, 200);
        lp.setMargins(5,10,5,10);

        // Add all headings to heading row
        for(int j=0; j<headings.size(); j++){
            tl.removeView(row);
            TextView header = new TextView(context);
            header.setText(headings.get(j));
            header.setGravity(Gravity.CENTER);;
            header.setGravity(CENTER);

            row.setPadding(0,0,10,0);
            row.setBackgroundResource(R.drawable.horizontal_divider);
            row.addView(header);

        }
        // Add headings to table
        tl.addView(row);

        // Add a row for each coursework
        for(int i=0; i<courseworks.size(); i++){
            row = new TableRow(context);

            // Add module text
            TextView moduleCode = new TextView(context);
            moduleCode.setText(courseworks.get(i).getModuleCode());
            // Set text view layout params
            moduleCode.setLayoutParams(lp);
            moduleCode.setGravity(CENTER);

            // Add lecturer text
            TextView lecturer = new TextView(context);
            lecturer.setText(courseworks.get(i).getLecturer());
            lecturer.setLayoutParams(lp);
            lecturer.setGravity(CENTER);

            // Add title text
            TextView title = new TextView(context);
            title.setText(courseworks.get(i).getTitle());
            title.setLayoutParams(lp);
            title.setGravity(CENTER);

            // Add deadline date
            TextView dDate = new TextView(context);
            String date = DATE_TIME_FORMAT.format(courseworks.get(i).getDeadlineDate());
            dDate.setText(date);
            dDate.setLayoutParams(lp);
            dDate.setGravity(CENTER);

            // Add feedback date
            TextView fDate = new TextView(context);
            date = DATE_FORMAT.format(courseworks.get(i).getFeedbackDate());
            fDate.setText(date);
            fDate.setLayoutParams(lp);
            fDate.setGravity(CENTER);

            // Add Views to row
            row.addView(moduleCode);
            row.addView(lecturer);
            row.addView(title);
            row.addView(dDate);
            row.addView(fDate);

            // Add styling
            row.setBackgroundResource(R.drawable.table_row_border);

            // Add rows to table
            tl.addView(row);

        }
        // Return the generated table
        return tl;
    }
}
