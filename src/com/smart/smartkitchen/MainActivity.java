package com.smart.smartkitchen;

import jexxus.server.Server;

import android.os.AsyncTask;
import android.os.Bundle;
import android.app.Activity;
import android.util.Log;
import android.view.Menu;
import android.widget.Toast;

public class MainActivity extends Activity {
	String TAG = "Kitchen Server";
	ReceiveOrderFromTable roft = new ReceiveOrderFromTable();
	Server server = new Server(new MyServer(this), 15652, false);

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		Log.d(TAG, "Kitchen Started");
		setContentView(R.layout.activity_main);
		Toast.makeText(this, "Server Started", Toast.LENGTH_SHORT).show();
		roft.execute("");
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	public void makeToast(String message) {
		Toast.makeText(getBaseContext(), message, Toast.LENGTH_LONG).show();
	}

	private class ReceiveOrderFromTable extends AsyncTask<String, Integer, String> {

		@Override
		protected String doInBackground(String... messages) {
			server.startServer();
			Log.d(TAG, "Server Started");
			return "Server Started";
		}
		
		@Override
		protected void onPostExecute (String result){
			Toast.makeText(getApplicationContext(), result, Toast.LENGTH_LONG).show();
		}

	}

}
