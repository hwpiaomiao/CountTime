package com.example.counttime;

import java.util.Timer;
import java.util.TimerTask;

import android.app.Activity;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends Activity implements OnClickListener {

	private EditText inputTime;
	private Button getTime, startTime, stopTime;
	private TextView time;
	private Timer timer = null;;
	private TimerTask task = null;

	private int i = 0;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		initView();
	}

	private void initView() {
		inputTime = (EditText) findViewById(R.id.inputTime);
		getTime = (Button) findViewById(R.id.getTime);
		startTime = (Button) findViewById(R.id.startTime);
		stopTime = (Button) findViewById(R.id.stopTime);
		time = (TextView) findViewById(R.id.time);
		getTime.setOnClickListener(this);
		startTime.setOnClickListener(this);
		stopTime.setOnClickListener(this);

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch (v.getId()) {
		case R.id.getTime:
			time.setText(inputTime.getText().toString());
			i = Integer.parseInt(inputTime.getText().toString());
			break;
		case R.id.startTime:
			startTime();
			break;
		case R.id.stopTime:
			stopTime();
			break;

		default:
			break;
		}

	}

	private Handler mHandler = new Handler() {
		public void handleMessage(android.os.Message msg) {
			time.setText(msg.arg1+"");
			startTime();
		};
	};

	public void startTime() {
		timer = new Timer();
		task = new TimerTask() {

			@Override
			public void run() {
				i--;
				Message message = mHandler.obtainMessage();
				message.arg1 = i;
				mHandler.sendMessage(message);

			}
		};
		timer.schedule(task, 1000);
	}

	public void stopTime() {
		timer.cancel();
	}
}
