package com.example.multiactivityresultsdemo;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity
{
    private TextView mTvResults;

    // These could really be local but are kept up here to help ensure the codes remain unique
    private final int REQUEST_CODE_ACTIVITY_2 = 18, REQUEST_CODE_ACTIVITY_3 = 90;

    @Override
    protected void onCreate (Bundle savedInstanceState)
    {
        super.onCreate (savedInstanceState);
        setContentView (R.layout.activity_main);

        // do this here so we don't need to do this every time we need this TextView
        mTvResults = (TextView)findViewById (R.id.tv_results);

    }

    /**
     * This is called automatically after the called (i.e. secondary) Activity has finished
     * and control has passed back to MainActivity
     * @param requestCode This is returned from when originally set in startActivityForResult here
     * @param resultCode OK or otherwise
     * @param data contains the Bundle that has the data we need from that auxiliary Activity
     */
    @Override protected void onActivityResult (int requestCode, int resultCode, Intent data)
    {
        String resultsText;
        String textFromCalledActivity = data.getStringExtra ("MSG_FROM_ACTIVITY");

        // Could also first do if (resultCode == REQUEST_CODE_ACTIVITY_2)
        // That would tell you before doing anything else which Activity you've come back from

        // Regardless of which activity just ended...
        if (resultCode == Activity.RESULT_OK) {
            resultsText = "Successfully returned to Main the text:\n"+ textFromCalledActivity
            + "from (Activity request code number is: " + requestCode + "): ";

            }
        else {
            resultsText = "No valid result from Activity request code: " + requestCode;
            super.onActivityResult (requestCode, resultCode, data);
        }

        mTvResults.setText (resultsText);

    }

    /**
     * Launches the SecondActivity and sends in some data to it as well
     * @param view Button that called this; always the Activity2 Button, in this case
     */
    public void handlerActivity2 (View view)
    {
        // Create an Intent that explicitly launches our Activity named SecondActivity
        Intent intent = new Intent (getApplicationContext (), SecondActivity.class);

        // Could add more things to this intent object, if desired. Or not send anything in.
        // Here, we add in a sample number.
        // NOTE: "SAMPLE" must be the same name when getting the Intent in SecondActivity
        intent.putExtra ("SAMPLE", 360);

        // Launch the Activity and wait for a result
        startActivityForResult (intent, REQUEST_CODE_ACTIVITY_2);
    }

    public void handlerActivity3 (View view)
    {
        // similar to above
        // In the startActivityForResult, pass the request code for Activity 3 (see fields above)
        Toast.makeText (this,"Not implemented", Toast.LENGTH_LONG).show ();
    }
}
