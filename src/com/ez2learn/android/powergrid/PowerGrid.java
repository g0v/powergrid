package com.ez2learn.android.powergrid;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnKeyListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.ez2learn.android.powergrid.geo.PowerGridFormula;
import com.ez2learn.android.powergrid.geo.TMToLatLon;
import com.ez2learn.android.powergrid.geo.TWD97;
import com.ez2learn.android.powergrid.geo.Util;

public class PowerGrid extends Activity {
	final String TAG = "power_grid";
	private double lat = 0;
	private double lon = 0;

    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        
        EditText textBox = (EditText) findViewById(R.id.CodeEditText);
        textBox.addTextChangedListener(new TextWatcher() {
			@Override
            public void onTextChanged(CharSequence seq, int start, int before, int count) {
				EditText textBox = (EditText) PowerGrid.this.findViewById(R.id.CodeEditText);
				String code = (String)textBox.getText().toString().toLowerCase();

				if(code.length() >= 1) { 
					char g = code.charAt(0);
					if(! (g >= 'a' && g <= 'w') ) {
						// first one should be a-w char
						showError(getString(R.string.g_error));
						return;
					}
				}
				
				if(code.length() >= 2) { 
					try{
						int pp = Integer.parseInt(code.substring(1, Math.min(3, code.length())));
					} catch (NumberFormatException e) {
						// 2~3 char should be number
						showError(getString(R.string.pp_error));
						return;
					}
				}
				
				if(code.length() >= 4) { 
					try{
						int qq = Integer.parseInt(code.substring(3, Math.min(5, code.length())));
					} catch (NumberFormatException e) {
						// 4~5 char should be number
						showError(getString(R.string.qq_error));
						return;
					}
				}
				
				if(code.length() >= 6) { 
					char r =  code.charAt(5);
					if(! (r >= 'a' && r <= 'h') ) {
						// 6th char should be a~h
						showError(getString(R.string.r_error));
						return;
					}
				}
				
				if(code.length() >= 7) { 
					char s = code.charAt(6);
					if(! (s >= 'a' && s <= 'e') ) {
						// 7th char should a~e
						showError(getString(R.string.s_error));
						return;
					}
				}
				
				if(code.length() >= 8) { 
					try{
						int t = Integer.parseInt(code.substring(7, 8));
					} catch (NumberFormatException e) {
						// 8th char should be number
						showError(getString(R.string.t_error));
						return;
					}
				}
				
				if(code.length() >= 9) { 
					try{
						int u = Integer.parseInt(code.substring(8, 9));
					} catch (NumberFormatException e) {
						// 9th char should be number
						showError(getString(R.string.u_error));
						return;
					}
				}
				
				if(code.length() >= 10) { 
					try{
						int v = Integer.parseInt(code.substring(9, 10));
					} catch (NumberFormatException e) {
						// 10th char should be number
						showError(getString(R.string.v_error));
						return;
					}
				}
				
				if(code.length() >= 11) { 
					try{
						int w = Integer.parseInt(code.substring(10, 11));
					} catch (NumberFormatException e) {
						// 11th char should be number
						showError(getString(R.string.w_error));
						return;
					}
				}
				
				EditText twd67TextBox = (EditText) PowerGrid.this.findViewById(R.id.TWD67EditText);
				EditText twd97TextBox = (EditText) PowerGrid.this.findViewById(R.id.TWD97EditText);
				EditText latlonTextBox = (EditText) PowerGrid.this.findViewById(R.id.LatLonEditText);
				if(code.length() >= 9) { 
					double twd67[] = PowerGridFormula.convert(code);
					Log.d(TAG, String.format("TWD67: %f, %f", twd67[0], twd67[1]));
					twd67TextBox.setText(String.format("%f, %f", twd67[0], twd67[1]));
					
					double twd97[] = Util.twd67ToTwd97(twd67[0], twd67[1]);
					Log.d(TAG, String.format("TWD97: %f, %f", twd97[0], twd97[1]));
					
					twd97TextBox.setText(String.format("%f, %f", twd97[0], twd97[1]));
					
					TWD97 parameter = new TWD97();
					double latlon[] = TMToLatLon.convert(parameter, twd97[0], twd97[1]);
			    	Log.d(TAG, String.format("Lat/Lon: %f, %f", latlon[0], latlon[1]));
			    	latlonTextBox.setText(String.format("%f, %f", latlon[0], latlon[1]));
			    	
			    	lat = latlon[0];
			    	lon = latlon[1];
				} else {
					twd67TextBox.setText(getString(R.string.na));
					twd97TextBox.setText(getString(R.string.na));
					latlonTextBox.setText(getString(R.string.na));
			    	lat = 0;
			    	lon = 0;
				}
			}

			@Override
			public void afterTextChanged(Editable s) {}

			@Override
			public void beforeTextChanged(CharSequence s, int start, int count, int after) {} 
        });
        
        Button button = (Button) findViewById(R.id.InputButton);
        button.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				PowerGrid.this.viewPositionOnMap();
			}
        });
        
        textBox.setOnKeyListener(new OnKeyListener () {
			@Override
			public boolean onKey(View view, int keyCode, KeyEvent event) {
				Log.d(TAG, String.format("Keydown: %d", keyCode));
				if(keyCode == KeyEvent.KEYCODE_ENTER) {
					PowerGrid.this.viewPositionOnMap();
				}
				return false;
			}
        });
        
        Button visitAuthorButton = (Button) findViewById(R.id.VisitAuthorButton);
        visitAuthorButton.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				Uri uri = Uri.parse(getString(R.string.author_uri));
				Intent intent = new Intent(Intent.ACTION_VIEW, uri);
				startActivity(intent);
			}
        });
    }
    
    public void showError(String message) {
		Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }
    
    public void viewPositionOnMap() {
		if(lat != 0 && lon != 0) {
			Uri uri = Uri.parse(String.format("geo:%f, %f?z=19", lat, lon));
			Intent intent = new Intent(Intent.ACTION_VIEW, uri);
			startActivity(intent);
		}
    }
}