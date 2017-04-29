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
            this.registerReceiver(mReceiver, filter);
	        IntentFilter filter1 = new IntentFilter(BluetoothDevice.ACTION_ACL_CONNECTED);
	        IntentFilter filter2 = new IntentFilter(BluetoothDevice.ACTION_ACL_DISCONNECT_REQUESTED);
	        IntentFilter filter3 = new IntentFilter(BluetoothDevice.ACTION_ACL_DISCONNECTED);
	        this.registerReceiver(mReceiver, filter1);
	        this.registerReceiver(mReceiver, filter2);
	        this.registerReceiver(mReceiver, filter3);
//		    out = (TextView) findViewById(R.id.out);
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
		          if (BluetoothDevice.ACTION_FOUND.equals(action)) {
		        	  
		          }
		          else if (BluetoothDevice.ACTION_ACL_CONNECTED.equals(action)) {
//		        	  out.append("\nAdapter: " + device.getName());
//		        	  getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
		        	  
		        	  lock.disableKeyguard();
		        	  intent.addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
		        	  

		        	  Log.i("Bluetooth", "Disable");
		          }
		          else if (BluetoothAdapter.ACTION_DISCOVERY_FINISHED.equals(action)) {
		             
		          }
		          else if (BluetoothDevice.ACTION_ACL_DISCONNECT_REQUESTED.equals(action)) {
		        	 lock.reenableKeyguard();
		        	 Log.i("Bluetooth", "Enable");
//		        	 intent.addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
		          }
		          else if (BluetoothDevice.ACTION_ACL_DISCONNECTED.equals(action)) {
		             lock.reenableKeyguard();
		             Log.i("Bluetooth", "Enable");
//		             intent.(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
		          }           
		      }
		  };

		@Override
		public IBinder onBind(Intent intent) {
			// TODO Auto-generated method stub
			return null;
		} 

}
