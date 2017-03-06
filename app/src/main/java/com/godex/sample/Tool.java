package com.godex.sample;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;

import android.content.res.Configuration;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.view.View.OnClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.godex.Godex;

public class Tool extends Connect
{
	//private TextView myTextView;
	private Button sendButton,disconnectButton,printButton,settingButton,uploadButton;
	private ListView mConversationView;
	private EditText mOutEditText;
	private EditText mOutpathText;
	public static ArrayAdapter<String> mConversationArrayAdapter;	
	public static String message;	
	boolean N;
	// Intent request codes   
	@Override
	public void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);		
		setContentView(R.layout.tool);	
		Godex.debug(3);
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN);
		mOutEditText = (EditText) findViewById(R.id.edit00);		
		mOutpathText = (EditText) findViewById(R.id.pathText);
		mConversationArrayAdapter = new ArrayAdapter<String>(this, R.layout.message);
        mConversationView = (ListView) findViewById(R.id.in);
        mConversationView.setAdapter(mConversationArrayAdapter);		
        sendButton = (Button) findViewById(R.id.send);
		sendButton.setOnClickListener
	        ( new OnClickListener() 
	          {
	            public void onClick(View v) 
	            {
	            	String[] zText=new String[1];	            	
	            	String Message = mOutEditText.getText().toString();
	            	if("".equals(mOutEditText.getText().toString().trim()))
		                	Toast.makeText(getApplicationContext(), "Please enter a command", Toast.LENGTH_SHORT).show();
		            else
			                {		
		                		N=Godex.sendCommand(Message);
			                }
	            	if(N==false)
		            	Toast.makeText(getApplicationContext(), "Send fail", Toast.LENGTH_SHORT).show();
	            	else
	            	{
	            		Toast.makeText(getApplicationContext(), "Sent", Toast.LENGTH_SHORT).show();	            		
	            	}
	            	try {						            									            			
            			
						if(Godex.read(zText)!=0)
						{
							message=zText[0];
							Toast.makeText(getApplicationContext(), "you did receive  ", Toast.LENGTH_SHORT).show();
							//receiveTextView.setText(message);
							mConversationArrayAdapter.add(message);
						}												
					} catch (IOException e) 
					{
						// TODO Auto-generated catch block
						e.printStackTrace();
					} catch (InterruptedException e)
					{
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
	            }
	          }
	        );	
		disconnectButton = (Button) findViewById(R.id.dcnt);
		disconnectButton.setOnClickListener
	        ( new OnClickListener() 
	          {
	            public void onClick(View v) 
	            {
	            	try {
						N=Godex.close();
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
	            	if(N==false)
		            	Toast.makeText(getApplicationContext(), "Disconnect fail", Toast.LENGTH_SHORT).show();
	            	else
	            	{
	            		WFaddress="";
	            		BTaddress="";
	            		Toast.makeText(getApplicationContext(), "Disconnected", Toast.LENGTH_SHORT).show();	            		
	            		finish();
	            	}
	            }
	          }
	        );
		printButton = (Button) findViewById(R.id.print);
		printButton.setOnClickListener
	        ( new OnClickListener() 
	          {
	            public void onClick(View v) 
	            {	  
            	
	            	InputStream bitmap = null;
					try {
						bitmap = getAssets().open("test.jpg");
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
	            	Bitmap myBitmap = BitmapFactory.decodeStream(bitmap);
	            	Godex.sendCommand("^L");	                	
	            	Godex.sendCommand("AD,30,130,1,1,0,0,Width:"+ myBitmap.getWidth());
	            	Godex.sendCommand("AD,30,200,1,1,0,0,Height:"+ myBitmap.getHeight());
	            	Godex.putimage(10, 10, myBitmap);
	                Godex.sendCommand("E");

	            }
	          }
	        );
		uploadButton = (Button) findViewById(R.id.upload);
		uploadButton.setOnClickListener
	        ( new OnClickListener() 
	          {
	            public void onClick(View v) 
	            {	            		            	
	            	try {
						Godex.loadImage(mOutpathText.getText().toString(), "testpic");
					} catch (FileNotFoundException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
	            }
	          }
	        );
		settingButton = (Button) findViewById(R.id.set);
		settingButton.setOnClickListener
	        ( new OnClickListener() 
	          {
	            public void onClick(View v) 
	            {	            		            	
	            	Godex.setup("20", "10", "2", "1", "2", "0");
	            }
	          }
	        );
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
}