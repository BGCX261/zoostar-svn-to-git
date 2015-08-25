package net.zoostar.quickdial;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.preference.PreferenceManager;

public class QuickDialDialer extends Activity {

	public static final String CALL_PHONE_PREFIX = "tel:";
	public static final String PAUSE = ",";
	
    private SharedPreferences mData;
    
    @Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
        
		Uri number = Uri.parse(buildNumber(getIntent().getData().toString()));
        Intent intent = new Intent(Intent.ACTION_CALL, number);
		startActivity(intent);
		finish();
	}
	
    protected String buildNumber(String data) {
        mData = PreferenceManager.getDefaultSharedPreferences(this);
        StringBuilder number = new StringBuilder(CALL_PHONE_PREFIX);
        if (mData != null) {

            number.append(mData.getString(QuickDialActivity.KEY_NUMBER, QuickDialActivity.DEFAULT_VALUE));
            number.append(PAUSE).append(PAUSE);
            
            String pin = mData.getString(QuickDialActivity.KEY_PIN, "");
            if(pin != null && !pin.trim().equals("")) {
            	number.append(pin).append(PAUSE);
            }
            
            number.append(mData.getString(QuickDialActivity.KEY_PREPEND, QuickDialActivity.DEFAULT_VALUE));
            number.append(data == null ? "" : parse(Uri.decode(data.substring(4, data.length())))); //Trim leading "tel:"
            number.append(mData.getString(QuickDialActivity.KEY_APPEND, QuickDialActivity.DEFAULT_VALUE));
        }
        
        return number.toString();
    }
    
    protected String parse(final String number) {
    	StringBuilder parsedNumber = new StringBuilder(number.length());
    	for(int i=0; i<number.length(); i++) {
    		char ch = number.charAt(i);
    		if(Character.isDigit(ch))
    			parsedNumber.append(ch);
    	}
    	return parsedNumber.toString();
    }
}
