package com.example.notepad_version_1;

import android.app.Activity;
import android.support.v4.app.Fragment;

public class StringSplitter {
	public static String getReadableActivityName(Activity activity) {
		if (activity != null && activity instanceof Activity) {

			String activityname = activity.toString();
			String[] Readable_ActivityName_split1 = (activityname.split("\\@")[0])
					.split("\\.");
			return Readable_ActivityName_split1[(Readable_ActivityName_split1.length - 1)];
			
		}
		else{
			return null;
		}
		
	}
	
	public static String getReadablFragmentName(Fragment fragment){
		
		if (fragment != null && fragment instanceof Fragment) {
			String FragmentName = fragment.toString();
			return FragmentName.split("\\{")[0];
		}
		else{
			return null;
		}

	}

}
