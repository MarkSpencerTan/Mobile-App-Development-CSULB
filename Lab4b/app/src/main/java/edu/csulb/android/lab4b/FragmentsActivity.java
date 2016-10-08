package edu.csulb.android.lab4b;

import android.app.Activity;
import android.os.Bundle;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.view.Display;
import android.view.WindowManager;
//NOTE toggle between Horizontal and Vertical mode in emulator
// by hitting Cntrl-F11
public class FragmentsActivity extends Activity {
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //add so that dynamically add fragments1 and 2
        FragmentManager fragmentManager = getFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        //get the current display info
        WindowManager wm = getWindowManager();
        Display d = wm.getDefaultDisplay();
        if(d.getWidth() > d.getHeight())
        { //landscape mode display fragment 1
            Fragment1 fragment1 = new Fragment1();
            //adroid.R.id.content refers to the content
            //view of the activity
            fragmentTransaction.replace(android.R.id.content, fragment1);
        }
        else
        { //portrait --display fragment 2
            Fragment2 fragment2 = new Fragment2();
            fragmentTransaction.replace(android.R.id.content, fragment2);
        }
        fragmentTransaction.commit();
        setContentView(R.layout.main);
    }
}