package nl.obduro.AutoGridView;

import android.content.Context;
import android.content.res.Configuration;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.widget.GridView;
import android.widget.ListAdapter;

/**
 * Automatically calculates the ideal for each row
 * @author JJ
 * https://github.com/JJdeGroot/AutoGridView
 *
 */
public class AutoGridView extends GridView {

    private static final String TAG = "AutoGridView";
    private int previousFirstVisible;
    private int numColumnsResourceId;
    private int numColumns = 1;

    public AutoGridView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        init(attrs);
    }

    public AutoGridView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(attrs);
    }

    public AutoGridView(Context context) {
        super(context);
    }

    /**
     * Sets the numColumns based on the attributeset
     */
    private void init(AttributeSet attrs) {
        // Read numColumns out of the AttributeSet
        if(attrs!=null) {
            numColumnsResourceId = attrs.getAttributeResourceValue("http://schemas.android.com/apk/res/android", "numColumns", R.integer.gridview_num_coloumn);
            updateColumns();
        }
        Log.d(TAG, "numColumns set to: " + numColumns);
    }


    /**
     * Reads the amount of columns from the resource file and
     * updates the "numColumns" variable
     */
    private void updateColumns() {
        this.numColumns = getContext().getResources().getInteger(numColumnsResourceId);
        setNumColumns(numColumns);
    }

    @Override
    public void setNumColumns(int numColumns) {
        this.numColumns = numColumns;
        super.setNumColumns(numColumns);

        Log.d(TAG, "setSelection --> " + previousFirstVisible);
        setSelection(previousFirstVisible);
    }

    @Override
    protected void onLayout(boolean changed, int leftPos, int topPos, int rightPos, int bottomPos) {
        super.onLayout(changed, leftPos, topPos, rightPos, bottomPos);
        setHeights();
        Log.i(TAG,"onLayout");
    }

    @Override
    protected void onConfigurationChanged(Configuration newConfig) {
        updateColumns();
        setNumColumns(this.numColumns);
        Log.i(TAG,"onConfigurationChanged");
    }

    @Override
    protected void onScrollChanged(int newHorizontal, int newVertical, int oldHorizontal, int oldVertical) {
        // Check if the first visible position has changed due to this scroll
        int firstVisible = getFirstVisiblePosition();
        if(previousFirstVisible != firstVisible) {
            // Update position, and update heights
            previousFirstVisible = firstVisible;
            setHeights();
        }

        super.onScrollChanged(newHorizontal, newVertical, oldHorizontal, oldVertical);
        Log.i(TAG,"onScrollChanged");
    }

    /**
     * Sets the height of each view in a row equal to the height of the tallest view in this row.
     * @param //firstVisible The first visible position (adapter order)
     */
    private void setHeights() {
        ListAdapter adapter = getAdapter();

        if(adapter != null) {
            for(int i = 0; i < getChildCount(); i+=numColumns) {
                // Determine the maximum height for this row
                int maxHeight = 0;
                for(int j = i; j < i+numColumns; j++) {
                    View view = getChildAt(j);
                    if(view != null && view.getHeight() > maxHeight) {
                        maxHeight = view.getHeight();
                    }
                }
                //Log.d(TAG, "Max height for row #" + i/numColumns + ": " + maxHeight);

                // Set max height for each element in this row
                if(maxHeight > 0) {
                    for(int j = i; j < i+numColumns; j++) {
                        View view = getChildAt(j);
                        if(view != null && view.getHeight() != maxHeight) {
                            view.setMinimumHeight(maxHeight);
                        }
                    }
                }
            }
        }
    }


}