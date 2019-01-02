package com.example.multiactivityresultsdemo;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

public class SecondActivity extends AppCompatActivity
{
    private int mFABClicks;

    @Override
    protected void onCreate (Bundle savedInstanceState)
    {
        super.onCreate (savedInstanceState);
        setContentView (R.layout.activity_second);

        setupToolbar ();
        getIncomingData ();
        setupFAB ();

    }

    private void setupToolbar ()
    {
        Toolbar toolbar = (Toolbar) findViewById (R.id.toolbar);
        setSupportActionBar (toolbar);
        getSupportActionBar ().setDisplayHomeAsUpEnabled (true);
    }

    private void getIncomingData ()
    {
        Intent intent = getIntent ();
        Toast.makeText (this,
                        "Main Activity sent in: " +
                                // "SAMPLE" key is identical to the intent putExtra in MainActivity
                                intent.getIntExtra ("SAMPLE", -1),
                        Toast.LENGTH_LONG).
                show ();
    }

    private void setupFAB ()
    {
        // counter
        mFABClicks=0;

        // Modify FAB listener code to show the number of times clicked
        FloatingActionButton fab = (FloatingActionButton) findViewById (R.id.fab);
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

    @Override public boolean onOptionsItemSelected (MenuItem item)
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
