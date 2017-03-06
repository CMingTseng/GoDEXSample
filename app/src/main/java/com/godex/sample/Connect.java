package com.godex.sample;

import com.godex.Godex;


import android.app.Activity;
import android.bluetooth.BluetoothAdapter;
import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import android.net.wifi.WifiManager;


public class Connect extends Activity
{
	
	private Button connectWiFiButton,connectBTButton,sampleButton;	
	private EditText mOutEditText;
	
	
	public static String BTaddress = "";// 5C:6B:32:AE:B8:6F
	public static String WFaddress = "";// 192.168.0.1
	public String message;	
	boolean N,F=true;	
	@Override
	public void onCreate(Bundle savedInstanceState)
	{
		
		super.onCreate(savedInstanceState);		
		setContentView(R.layout.connect);
		
		Godex.debug(3);
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN);
		mOutEditText = (EditText) findViewById(R.id.edit00);
		
		
		WifiManager wifi = (WifiManager) this.getSystemService(Context.WIFI_SERVICE);
		if (!wifi.isWifiEnabled()) 
			wifi.setWifiEnabled(true);
		BluetoothAdapter btAdapter = BluetoothAdapter.getDefaultAdapter();
		if (!btAdapter.isEnabled()){
            Intent enable = new Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE);
            startActivity(enable);
        }
		connectWiFiButton = (Button) findViewById(R.id.WFcnt);
		connectWiFiButton.setOnClickListener
	        ( new OnClickListener() 
	          {
	            public void onClick(View v) 
	            {
	            	WFaddress = mOutEditText.getText().toString();
		            N=Godex.open(WFaddress,1);		            		
				    if(N==false)
				    	Toast.makeText(getApplicationContext(), "WiFi Connect fail", Toast.LENGTH_SHORT).show();
				    else
					{
				    	Toast.makeText(getApplicationContext(), "WiFi Connected", Toast.LENGTH_SHORT).show();
					    sampletool();
					}
	            }
	          }
	        );
		connectBTButton = (Button) findViewById(R.id.BTcnt);
		connectBTButton.setOnClickListener
	        ( new OnClickListener() 
	          {
	            public void onClick(View v) 
	            {
	            	BTaddress = mOutEditText.getText().toString();
			        N=Godex.open(BTaddress,2);
				    if(N==false)
					    Toast.makeText(getApplicationContext(), "Bluetooth Connect fail", Toast.LENGTH_SHORT).show();
				    else
					{
				    	Toast.makeText(getApplicationContext(), "Bluetooth Connected", Toast.LENGTH_SHORT).show();
					    sampletool();
					}
	            }
	          }
	        );
		sampleButton = (Button) findViewById(R.id.sample);
		sampleButton.setOnClickListener
	        ( new OnClickListener() 
	          {
	            public void onClick(View v) 
	            {
	            	 Toast.makeText(getApplicationContext(), "Without connection!! It will don't work. ", Toast.LENGTH_SHORT).show();
					 sampletool();					            
	            }
	          }
	        );			
	}
	@Override
	public void onRestoreInstanceState(Bundle savedInstanceState)
	{
		super.onRestoreInstanceState(savedInstanceState);
	}
	@Override
	public void onRestart()
	{
		super.onRestart();
	}
	@Override
	public void onStart()
	{
		super.onStart();
	}
	@Override
	public void onResume()
	{
		super.onResume();
	}	
        
    
	public void sampletool()
    {
    	Intent intent = new Intent(this, Tool.class);    	
        startActivity(intent);
    }
	@Override
	public void onSaveInstanceState(Bundle savedInstanceState)
	{
		super.onSaveInstanceState(savedInstanceState);
	}
	@Override
	public void onPause()
	{
		super.onPause();
	}
	@Override
	public void onStop()
	{
		super.onStop();
	}
	@Override
	public void onDestroy()
	{
		super.onDestroy();		
	}
	
	@Override
	public void onConfigurationChanged(Configuration newConfig) {
	    super.onConfigurationChanged(newConfig);
	 
	    // Checks the orientation of the screen
	    if (newConfig.orientation == Configuration.ORIENTATION_LANDSCAPE) {
	        Toast.makeText(this, "landscape", Toast.LENGTH_SHORT).show();
	    } else if (newConfig.orientation == Configuration.ORIENTATION_PORTRAIT){
	        Toast.makeText(this, "portrait", Toast.LENGTH_SHORT).show();
	    }
	}
	
}