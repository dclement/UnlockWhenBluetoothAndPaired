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
	  }	     
}


