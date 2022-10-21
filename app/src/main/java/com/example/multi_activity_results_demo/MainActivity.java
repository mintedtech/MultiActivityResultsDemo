package com.example.multi_activity_results_demo;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;
import android.view.View;
import android.widget.Toast;

import com.example.multi_activity_results_demo.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity
{
    private ActivityMainBinding binding;

    @Override
    protected void onCreate (Bundle savedInstanceState)
    {
        super.onCreate (savedInstanceState);
        binding = ActivityMainBinding.inflate (getLayoutInflater ());
        setContentView (binding.getRoot ());
    }

    ActivityResultLauncher<Intent> secondaryActivityLauncher =
            registerForActivityResult(new ActivityResultContracts.StartActivityForResult(),
                    this::onReturnFromSecondaryActivityHandler);

    private void onReturnFromSecondaryActivityHandler(ActivityResult result) {
        String resultsText;
        Intent incomingData = result.getData();

        String activityName = incomingData == null ?
                "Unknown Activity" : incomingData.getStringExtra("ACTIVITY_NAME");

        String textFromCalledActivity = incomingData == null ?
                "No Data" : incomingData.getStringExtra ("MSG_FROM_ACTIVITY");

        resultsText = result.getResultCode()==Activity.RESULT_OK ?
                "Successfully returned to Main:\n"+ textFromCalledActivity +
                        " from " +  activityName :
                "No valid result from " + activityName;

        binding.tvResults.setText (resultsText);
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
        secondaryActivityLauncher.launch(intent);
    }

    public void handlerActivity3 (View view)
    {
        // same as handlerActivity2 other than the name of the Activity called
        // so, we just need a ThirdActivity Activity that, like Second Activity
        // accepts the sample data and, on finish, sends back the activity name and results text

/*
        Intent intent = new Intent (getApplicationContext (), ThirdActivity.class);
        intent.putExtra ("SAMPLE", 720);
        secondaryActivityLauncher.launch(intent);*/

        Toast.makeText (this,"Not implemented", Toast.LENGTH_LONG).show ();
    }
}
