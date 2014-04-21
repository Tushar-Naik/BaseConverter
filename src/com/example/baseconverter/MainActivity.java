package com.example.baseconverter;

import android.os.Bundle;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.text.Editable;
import android.text.InputType;
import android.text.TextWatcher;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView.AdapterContextMenuInfo;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends Activity {

	private static String base_conv(String n1, long from, long to) {
		// TODO Auto-generated method stub
		String s = "";
		long n = 0;
		long r;
		int i = 0;
		//Convert n1 to base 10 'n'
		for (int j = n1.length() - 1; j >= 0; j--) {
			char c = n1.charAt(j);
			if (c < 'A')
				r = c - '0';
			else
				r = c - 55;
			n = n + (long) (r * Math.pow(from, i));
			i++;
		}
		//Convert n to base 'to'
		while (n > 0) {
			r = n % to;
			if (r > 9) {
				char x = (char) (r + 55);
				s = x + s;
			} else
				s = Integer.toString((int) r) + s;
			n = n / to;
		}
		return s;
	}

	private static boolean check(String n, int base) {
		boolean yea = true;
		if (base < 10) {
			int n1 = Integer.parseInt(n);
			while (n1 > 0) {
				int r = n1 % 10;
				if (r >= base || r<0) {
					yea = false;
					break;
				}
				n1 = n1 / 10;
			}
		} else {
			for (int i = 0; i < n.length(); i++) {
				char c = n.charAt(i);
				if (c > 55 + base-1 || c<48) {
					yea = false;
					break;
				}
			}
		}
		return yea;

	}


	EditText e1, e2, e3, e4;

	int focus,fromBB,toBB;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		e1 = (EditText) findViewById(R.id.editText1);
		e2 = (EditText) findViewById(R.id.editText2);
		e3 = (EditText) findViewById(R.id.editText3);
		e4 = (EditText) findViewById(R.id.editText4);
		e1.getLayoutParams().height=55;
		e2.getLayoutParams().height=55;
		e3.getLayoutParams().height=55;
		e4.getLayoutParams().height=55;
		e3.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD |InputType.TYPE_TEXT_FLAG_CAP_CHARACTERS);

		e1.addTextChangedListener(new EditTextWatcher(e1));
		e2.addTextChangedListener(new EditTextWatcher(e2));
		e3.addTextChangedListener(new EditTextWatcher(e3));
		e4.addTextChangedListener(new EditTextWatcher(e4));

		Button b=(Button)findViewById(R.id.button1);
		b.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				startActivity(new Intent(MainActivity.this,B1B2.class));
				
				
			}
		});
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		menu.add("Quit");
		menu.addSubMenu("Hi");
		menu.add("this");
		return true;
	}
	@Override
	public boolean onContextItemSelected(MenuItem item) {
	    AdapterContextMenuInfo info = (AdapterContextMenuInfo) item.getMenuInfo();
	    Log.d("menu",item+"  "+item.toString());
	    if(item.getMenuInfo().equals("Quit")||info.toString()=="Quit")
	    {
	    	MainActivity.this.finish();
	    }
	    return true;
	}

	private class EditTextWatcher implements TextWatcher {

		EditText v;
		int from;

		public EditTextWatcher(EditText view) {
			this.v = view;
		}

		@SuppressLint("DefaultLocale")
		public void afterTextChanged(Editable s) {

			Log.d(getLocalClassName(), s.toString() + " " + v.getId() + " "
					+ focus + "  " + R.id.editText1 + " " + R.id.editText2);

			switch (v.getId()) {
			case R.id.editText1:
				Log.d("tag", "IN DECIMAL");
				if (e1.hasFocus()) {
					try {
						from = 10;
						if (s.length() != 0 && s.length() <= 10) {
							System.out.println(s);

							String input = e1.getText().toString();
							if (check(input, from)) {
								String k;

								k = base_conv(input, from, 2);
								e2.setText(k);

								k = base_conv(input, from, 16);
								e3.setText(k);

								k = base_conv(input, from, 8);
								e4.setText(k);
							} else {
								Toast t1 = Toast.makeText(
										getApplicationContext(),
										"Not Allowed ! Exceeds Base !",
										Toast.LENGTH_SHORT);
								t1.show();
								String s1 = "";
								for (int i = 0; i < input.length() - 1; i++) {
									s1 += input.charAt(i);
								}
								e1.setTextKeepState(s1);
							}
						} else if (s.length() > 10) {
							Toast toast = Toast.makeText(
									getApplicationContext(),
									"Maximum 10 digits allowed !",
									Toast.LENGTH_SHORT);
							toast.show();
							String s1 = "";
							for (int i = 0; i < 10; i++) {
								s1 += s.charAt(i);
							}
							e1.setTextKeepState(s1);

						} else {
							e2.setText("");
							e3.setText("");
							e4.setText("");
						}
					} catch (Exception e) {
						Log.e("tag", "Error decimal"+e);
					}
				}
				break;
			case R.id.editText2:

				Log.d("tag", "IN Binary");
				if (e2.hasFocus()) {
					try {

						from = 2;
						if (s.length() != 0 && s.length() <= 10) {
							System.out.println(s);

							String input = e2.getText().toString();
							if (check(input, from)) {
								String k;

								k = base_conv(input, from, 10);
								e1.setText(k);

								k = base_conv(input, from, 16);
								e3.setText(k);

								k = base_conv(input, from, 8);
								e4.setText(k);
							} else {
								Toast t1 = Toast.makeText(
										getApplicationContext(),
										"Not Allowed ! Exceeds Base !",
										Toast.LENGTH_SHORT);
								t1.show();
								String s1 = "";
								for (int i = 0; i < input.length() - 1; i++) {
									s1 += input.charAt(i);
								}
								e2.setTextKeepState(s1);
							}
						} else if (s.length() > 10) {
							Toast toast = Toast.makeText(
									getApplicationContext(),
									"Maximum 10 digits allowed !",
									Toast.LENGTH_SHORT);
							toast.show();
							String s1 = "";
							for (int i = 0; i < 10; i++) {
								s1 += s.charAt(i);
							}
							e2.setTextKeepState(s1);
						} else {
							e1.setText("");
							e3.setText("");
							e4.setText("");
						}
					} catch (Exception e) {
						Log.e("tag", "Error Binary" + e);
					}

				}
				break;
			case R.id.editText3:

				if (e3.hasFocus()) {
					try {
						from = 16;
						if (s.length() != 0 && s.length() <= 10) {
							System.out.println(s);

							String input = e3.getText().toString().toUpperCase();
							
							if (check(input, from)) {
								String k;

								k = base_conv(input, from, 10);
								e1.setText(k);

								k = base_conv(input, from, 2);
								e2.setText(k);

								k = base_conv(input, from, 8);
								e4.setText(k);
							} else {
								Toast t1 = Toast.makeText(
										getApplicationContext(),
										"Not Allowed ! Exceeds Base !",
										Toast.LENGTH_SHORT);
								t1.show();
								String s1 = "";
								for (int i = 0; i < input.length() - 1; i++) {
									s1 += input.charAt(i);
								}
								e3.setTextKeepState(s1);
							}
						} else if (s.length() > 10) {
							Toast toast = Toast.makeText(
									getApplicationContext(),
									"Maximum 10 digits allowed !",
									Toast.LENGTH_SHORT);
							toast.show();
							String s1 = "";
							for (int i = 0; i <10; i++) {
								s1 += s.charAt(i);
							}
							e3.setTextKeepState(s1);

						} else {
							e1.setText("");
							e2.setText("");
							e4.setText("");
						}
					} catch (Exception e) {
						Log.e("tag", "Error Hexa" + e);
					}
				}

				break;
			case R.id.editText4:

				if (e4.hasFocus()) {
					try {

						from = 8;
						if (s.length() != 0 && s.length() <= 10) {
							System.out.println(s);

							String input = e4.getText().toString();
							
							
							if (check(input, from)) {
								String k;

								k = base_conv(input, from, 10);
								e1.setText(k);

								k = base_conv(input, from, 2);
								e2.setText(k);

								k = base_conv(input, from, 16);
								e3.setText(k);
							} else {
								Toast t1 = Toast.makeText(
										getApplicationContext(),
										"Not Allowed ! Exceeds Base !",
										Toast.LENGTH_SHORT);
								t1.show();
								String s1 = "";
								for (int i = 0; i < input.length() - 1; i++) {
									s1 += input.charAt(i);
								}
								e4.setTextKeepState(s1);
							}
						} else if (s.length() > 10) {
							Toast toast = Toast.makeText(
									getApplicationContext(),
									"Maximum 10 digits allowed !",
									Toast.LENGTH_SHORT);
				
							toast.show();
							String s1 = "";
							for (int i = 0; i < 10; i++) {
								s1 += s.charAt(i);
							}
							e4.setTextKeepState(s1);
						} else {
							e1.setText("");
							e2.setText("");
							e3.setText("");
						}
					} catch (Exception e) {
						Log.e("tag", "Error octal" + e);
					}

				}
				
				break;
				
			default:
			}
		}

		public void beforeTextChanged(CharSequence s, int start, int count,
				int after) {
		}
		public void onTextChanged(CharSequence s, int start, int before,
				int count) {
		}

	}
}