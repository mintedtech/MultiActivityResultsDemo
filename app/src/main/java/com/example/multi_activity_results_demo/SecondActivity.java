package com.example.multi_activity_results_demo;

import android.content.Intent;
import android.os.Bundle;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;

public class SecondActivity extends AppCompatActivity
{
    private int mFABClicks;
    private View mSB_Container;

    @Override
    protected void onCreate (Bundle savedInstanceState)
    {
        super.onCreate (savedInstanceState);
        setContentView (R.layout.activity_second);
        mSB_Container = findViewById(R.id.activity_second);
        setupToolbar ();
        setupFAB ();
        getIncomingData ();
    }

    private void setupToolbar ()
    {
        setToolbarAsActionBar();
        setBackButtonInActionBar();
    }

    private void setToolbarAsActionBar() {
        Toolbar toolbar = findViewById (R.id.toolbar);
        setSupportActionBar (toolbar);
    }

    private void setBackButtonInActionBar() {
        ActionBar actionBar = getSupportActionBar ();
        if (actionBar != null)
            actionBar.setDisplayHomeAsUpEnabled (true);
    }

    private void getIncomingData ()
    {
        Intent intent = getIntent ();
        String msg = "Main Activity sent in: " +                        // "SAMPLE" key matches key
                intent.getIntExtra ("SAMPLE", -1);    // name used in MainActivity
        Snackbar.make (mSB_Container, msg, Snackbar.LENGTH_LONG).show ();
    }

    private void setupFAB ()
    {
        // counter
        mFABClicks=0;

        // Modify FAB listener code to show the number of times clicked
        FloatingActionButton fab = findViewById (R.id.fab);
        fab.setOnClickListener (new View.OnClickListener ()
        {
            @Override
            public void onClick (View view)
            {
                // output and increment counter
                Snackbar.make (view, "You clicked " + ++mFABClicks + " times.",
                               Snackbar.LENGTH_LONG)
                        .show ();
            }
        });
    }

    /**
     * Runs when the Activity is finishing, typically when the user hits the hardware back button
     * Needs to be explicitly called in onOptionsItemSelected(...) to handle the Toolbar back button
     */
    @Override public void finish ()
    {
        //  create an Intent, which has a Bundle
        //  To this bundle, we can add whatever data we want to send back to the calling Activity
        Intent intentResults = new Intent ();

        // Add some sample data
        intentResults.putExtra ("MSG_FROM_ACTIVITY",
                                "SecondActivity-->MainActivity: FAB clicked " + mFABClicks + " times.\n");

        //  Set the result to OK and to pass back this Intent;
        // if this is not set then it assumes it was NOT Ok.
        // if the second argument is blank then nothing will be sent back
        setResult (RESULT_OK, intentResults);

        // Do whatever else the parent class would normally do in its finish() method
        super.finish ();
    }

    @Override public boolean onOptionsItemSelected (@NonNull MenuItem item)
    {
        // If the user hit the back button in the Toolbar
        if (item.getItemId () == android.R.id.home) {
            finish ();
            return true;
        }

        // This should anyways never be called because there are no other menu items
        return super.onOptionsItemSelected (item);
    }
}
