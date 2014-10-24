package com.example.ps5_krone;


import java.util.ArrayList;

import android.app.Activity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnFocusChangeListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.TextView.OnEditorActionListener;


public class MainActivity extends Activity {
	private int years;
	private double investmentAmount;
	private double interestRate;
	private double futureValue;
	private boolean investmentEntered;
	private boolean yearSelected;
	private boolean interestEntered;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		//Create an array for the spinner to use
		ArrayList<String> yearArray = new ArrayList<String>();
		for (int i = 0; i<100; i++){
			yearArray.add(((Integer)i).toString());
		}
		Spinner spinner = (Spinner) findViewById(R.id.spinner1);
		ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, yearArray);
		spinner.setAdapter(adapter);
		spinner.setOnItemSelectedListener(new OnItemSelectedListener() {
			public void onItemSelected(AdapterView<?> parent, View view,
			int pos, long id) {
				parent.getContext();
				years = Integer.parseInt(parent.getItemAtPosition(pos).toString());
				if (years != 0){
					yearSelected = true;
					buttonStatus();
				}
				else{
					yearSelected = false;
				}
			}
			@Override
			public void onNothingSelected(AdapterView<?> parent) {
			}
			});
		
		final Button calcButt = (Button) findViewById(R.id.button1);
		final EditText investmentAmountText = (EditText) findViewById(R.id.editText1);
		final EditText interestRateText = (EditText) findViewById(R.id.editText3);
		final TextView futureValueView = (TextView) findViewById(R.id.textView5);
		
		calcButt.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				investmentAmount = Double.parseDouble(investmentAmountText.getText().toString());
				interestRate = Double.parseDouble(interestRateText.getText().toString());
				futureValue = CalcEngine.calc(investmentAmount, years, interestRate);
				futureValueView.setText(String.valueOf(futureValue));
				}
				
			
		});
		
		//button disabler and enablers based on losing focus or pressing enter in a field
		investmentAmountText.setOnEditorActionListener(new OnEditorActionListener() {

			@Override
			public boolean onEditorAction(TextView v, int actionId,
					KeyEvent event) {
				String entered = investmentAmountText.getText().toString();
				if (!entered.equals("")){
					investmentEntered = true;
					buttonStatus();
				}
				else{
					calcButt.setEnabled(false);
				}
				return false;
			}
			});
		
		 investmentAmountText.setOnFocusChangeListener(new OnFocusChangeListener() {  
			 
		        public void onFocusChange(View v, boolean hasFocus) {
		        	String entered = investmentAmountText.getText().toString();
		            if(!hasFocus){
		            	if(!entered.equals("")){
		            		investmentEntered = true;
		            		buttonStatus();
		            	}
		            	else{
		            		calcButt.setEnabled(false);
		            	}
		            }
		        }
		    });
		
		 
		interestRateText.setOnEditorActionListener(new OnEditorActionListener() {

			@Override
			public boolean onEditorAction(TextView v, int actionId,
					KeyEvent event) {
				String entered = interestRateText.getText().toString();
				if (!entered.equals("")){
					interestEntered = true;
					buttonStatus();
				}
				else{
					calcButt.setEnabled(false);
				}
				return false;
			}
			});
		
		interestRateText.setOnFocusChangeListener(new OnFocusChangeListener() {  
			 
		        public void onFocusChange(View v, boolean hasFocus) {
		        	String entered = interestRateText.getText().toString();
		            if(!hasFocus){
		            	if(!entered.equals("")){
		            		interestEntered = true;
		            		buttonStatus();
		            	}
		            	else{
		            		calcButt.setEnabled(false);
		            	}
		            }
		        }
		    });
		
		
	
	}
	//Enable the button
	private void buttonStatus(){
		if (investmentEntered && yearSelected &&  interestEntered){
			final Button calcButt = (Button) findViewById(R.id.button1);
			calcButt.setEnabled(true);
		}
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
}
