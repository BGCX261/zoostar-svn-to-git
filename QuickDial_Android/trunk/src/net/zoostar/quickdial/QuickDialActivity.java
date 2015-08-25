package net.zoostar.quickdial;

import android.app.Activity;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Log;
import android.widget.EditText;
import android.widget.LinearLayout;

import com.google.ads.AdRequest;
import com.google.ads.AdSize;
import com.google.ads.AdView;

public class QuickDialActivity extends Activity {
	
	private static final String QUICKDIAL_ADMOB_PUB_ID = "a14fd262905bff1";
	static final String TAG = "net.zoostar.quickdial";
	static final String DATA_FILENAME = "QuickDialData";
	
	static final String KEY_NUMBER = "number";
	static final String KEY_PIN = "pin";
	static final String KEY_PREPEND = "prepend";
	static final String KEY_APPEND = "append";
	static final String DEFAULT_VALUE = "";
	
	private AdView mAdView;

    private EditText mNumberText;
    private EditText mPinText;
    private EditText mPrependText;
    private EditText mAppendText;
    
    private SharedPreferences mData;

	/** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        
        mAdView = new AdView(this, AdSize.BANNER, QUICKDIAL_ADMOB_PUB_ID);
        LinearLayout layout = (LinearLayout)findViewById(R.id.mainLayout);
        layout.addView(mAdView);
        
        AdRequest adRequest = new AdRequest();
        //adRequest.addTestDevice(AdRequest.TEST_EMULATOR);
        mAdView.loadAd(adRequest);
        
        mNumberText = (EditText)findViewById(R.id.editTextNumber);
        mPinText = (EditText)findViewById(R.id.editTextPin);
        mPrependText = (EditText)findViewById(R.id.editTextPrepend);
        mAppendText = (EditText)findViewById(R.id.editTextAppend);
        
        mData = PreferenceManager.getDefaultSharedPreferences(this);
        mNumberText.setText(mData.getString(KEY_NUMBER, DEFAULT_VALUE));
        mPinText.setText(mData.getString(KEY_PIN, DEFAULT_VALUE));
        mPrependText.setText(mData.getString(KEY_PREPEND, DEFAULT_VALUE));
        mAppendText.setText(mData.getString(KEY_APPEND, DEFAULT_VALUE));
    }

	@Override
	public void onBackPressed() {
		if(mData != null) {
			SharedPreferences.Editor editor = mData.edit();
			editor.putString(KEY_NUMBER, mNumberText.getText().toString());
			editor.putString(KEY_PIN, mPinText.getText().toString());
			editor.putString(KEY_PREPEND, mPrependText.getText().toString());
			editor.putString(KEY_APPEND, mAppendText.getText().toString());
			editor.commit();
		} else {
			Log.w(TAG, "QuickDial data file is NULL! Data will not be persisted.");
		}
		super.onBackPressed();
	}

	@Override
	protected void onDestroy() {
		if(mAdView != null)
			mAdView.destroy();
		
		super.onDestroy();
	}
    
}