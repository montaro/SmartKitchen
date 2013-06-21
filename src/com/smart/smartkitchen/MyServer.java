package com.smart.smartkitchen;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.Toast;
import jexxus.common.Connection;
import jexxus.common.ConnectionListener;
import jexxus.server.ServerConnection;

public class MyServer implements ConnectionListener {
	public Context ctx;
	
	public MyServer(Context ctx){
		this.ctx = ctx;
	}
	
	String TAG = "Table Client";
	@Override
	public void connectionBroken(Connection broken, boolean forced) {
		Log.d(TAG, "Connection lost:");
		String message = "Connection lost: " + broken;
		System.out.println(message);
//		Toast.makeText(ctx, message, Toast.LENGTH_SHORT).show();
	}

	@Override
	public void receive(byte[] data, Connection from) {
		String message = "Received message: " + new String(data);
		Log.d(TAG, "Received message: " + message);
		System.out.println(message);
		new ShowTheOrder().execute(message);
//		Toast.makeText(ctx, message, Toast.LENGTH_SHORT).show();
	}

	@Override
	public void clientConnected(ServerConnection conn) {
		Log.d(TAG, "Client Connected:");
		String message = "Client Connected: " + conn.getIP();
		System.out.println(message);
//		Toast.makeText(ctx, message, Toast.LENGTH_SHORT).show();
	}
	
	private class ShowTheOrder extends AsyncTask<String, Void, String> {

		@Override
		protected String doInBackground(String... messages) {
			Log.d(TAG, "Showing The order - BG");
			return messages[0];
		}
		
		@Override
		protected void onPostExecute (String result){
			Log.d(TAG, "Showing The order - POST");
			Toast.makeText(ctx, result, Toast.LENGTH_LONG).show();
		}

	}

}