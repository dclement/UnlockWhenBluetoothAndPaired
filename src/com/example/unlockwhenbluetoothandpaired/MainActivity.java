package com.example.unlockwhenbluetoothandpaired;

import java.util.Set;

import android.support.v7.app.ActionBarActivity;
import android.app.Activity;
import android.app.KeyguardManager;
import android.app.KeyguardManager.KeyguardLock;
import android.app.Service;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.os.IBinder;
import android.view.Menu;
import android.view.MenuItem;
import android.view.WindowManager;
import android.widget.TextView;

public class MainActivity extends Activity {

	  TextView out;
	  private static final int REQUEST_ENABLE_BT = 1;

	  /** Called when the activity is first created. */
	  @Override
	  public void onCreate(Bundle savedInstanceState) {
	    super.onCreate(savedInstanceState);
	    setContentView(R.layout.activity_main);
	    Intent i = new Intent(this, BluetoothStatus.class);
        startService(i);     // we start B here
	    
        
//	     
//	    // Getting the Bluetooth adapter
//	    btAdapter = BluetoothAdapter.getDefaultAdapter();
//	    out.append("\nAdapter: " + btAdapter);
//	     
//	    CheckBTState();
	  }
	     
	
	  
	  
//	  /* This routine is called when an activity completes.*/
//	  @Override
//	  protected void onActivityResult(int requestCode, int resultCode, Intent data) {
//	    super.onActivityResult(requestCode, resultCode, data);
////	    if (requestCode == REQUEST_ENABLE_BT) {
////	      CheckBTState();
////	    }
//	  }
//	 
//	  @Override
//	  protected void onDestroy() {
//	    super.onDestroy();
//	  }

//	  private void CheckBTState() {
//	    // Check for Bluetooth support and then check to make sure it is turned on
//	    // If it isn't request to turn it on
//	    // List paired devices
//	    // Emulator doesn't support Bluetooth and will return null
//	    if(btAdapter==null) { 
//	      out.append("\nBluetooth NOT supported. Aborting.");
//	      return;
//	    } else {
//	      if (btAdapter.isEnabled()) {
//	        out.append("\nBluetooth is enabled...");
//	         
//	        // Listing paired devices
//	        out.append("\nPaired Devices:");
//	        Set<BluetoothDevice> devices = btAdapter.getBondedDevices();
//	        for (BluetoothDevice device : devices) {
//	          out.append("\n  Device: " + device.getName() + ", " + device);
//	        }
//	      } else {
//	        //Prompt user to turn on Bluetooth
//	        Intent enableBtIntent = new Intent(btAdapter.ACTION_REQUEST_ENABLE);
//	        startActivityForResult(enableBtIntent, REQUEST_ENABLE_BT);
//	      }
//	    }
//	  }
	  
	     
}


