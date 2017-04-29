package com.example.unlockwhenbluetoothandpaired;

import android.app.Activity;
import android.app.KeyguardManager;
import android.app.Service;
import android.app.KeyguardManager.KeyguardLock;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.os.IBinder;
import android.util.Log;
import android.view.WindowManager;
import android.widget.TextView;
import android.widget.Toast;

public class BluetoothStatus extends Service{

	 private boolean isDestroyed = false;
	 private BluetoothAdapter btAdapter; 
	 private KeyguardLock lock;
	 private boolean screenOff;
	 
	    public void onCreate(Bundle b){
	        super.onCreate();
	    }

	    public void onDestroy(){
	        super.onDestroy();
	        isDestroyed = true;
	    }

	    public void onStart(Intent i, int id){
	        super.onCreate();
            IntentFilter filter = new IntentFilter(Intent.ACTION_SCREEN_ON);
            filter.addAction(Intent.ACTION_SCREEN_OFF);
            filter.addAction(BluetoothAdapter.ACTION_STATE_CHANGED);
            filter.addAction(BluetoothDevice.ACTION_ACL_CONNECTED);
            filter.addAction(BluetoothDevice.ACTION_ACL_DISCONNECTED);
            this.registerReceiver(mReceiver, filter);
		    KeyguardManager keyguardManager = (KeyguardManager)getSystemService(KEYGUARD_SERVICE);
	  	    lock = keyguardManager.newKeyguardLock(KEYGUARD_SERVICE);
	  	    Log.i("Service ", "Starting");

	    }
	    
	  //The BroadcastReceiver that listens for bluetooth broadcasts
		  private final BroadcastReceiver mReceiver = new BroadcastReceiver() {
		      @SuppressWarnings("deprecation")
			@Override
		      public void onReceive(Context context, Intent intent) {
		          String action = intent.getAction();
		          BluetoothDevice device = intent.getParcelableExtra(BluetoothDevice.EXTRA_DEVICE);
		          Log.i("Bluetooth", "Receive action " + action);
		          if (intent.getAction().equals(Intent.ACTION_SCREEN_OFF)) {
		                 
		                screenOff = true;
		                
		            } else if (intent.getAction().equals(Intent.ACTION_SCREEN_ON)) {
		                 
		                screenOff = false;
		                 
		            }
		          if (action.equals(BluetoothAdapter.ACTION_STATE_CHANGED)) {
		              final int state = intent.getIntExtra(BluetoothAdapter.EXTRA_STATE, BluetoothAdapter.ERROR);
		              switch(state) {
		                  case BluetoothAdapter.STATE_OFF:
		                	  Log.i("Bluetooth ", "off");
		                      break;
		                  case BluetoothAdapter.STATE_TURNING_OFF:
		                	  Log.i("Bluetooth ", "turning off");
		                      break;
		                  case BluetoothAdapter.STATE_ON:
		                	  Log.i("Bluetooth ", "on");
		                      break;
		                  case BluetoothAdapter.STATE_TURNING_ON:
		                	  Log.i("Bluetooth ", "turning on");
		                      break;
		              }

		          }
		          switch (action){
		            case BluetoothDevice.ACTION_ACL_CONNECTED:
		            	 Log.i("Bluetooth ", "device connected");
		                break;
		            case BluetoothDevice.ACTION_ACL_DISCONNECTED:
		            	 Log.i("Bluetooth ", "device disconnected");
		                break;
		          }
		          
		      }
		  };

		@Override
		public IBinder onBind(Intent intent) {
			// TODO Auto-generated method stub
			return null;
		} 

}
