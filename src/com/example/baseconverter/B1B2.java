package com.example.baseconverter;

import android.app.Activity;
import android.os.Bundle;
import android.text.Editable;
import android.text.InputType;
import android.text.TextWatcher;
import android.util.Log;
import android.widget.EditText;
import android.widget.Toast;

public class B1B2 extends Activity {

	private static String base_conv(String n1, long from, long to) {
		// TODO Auto-generated method stub
		String s = "";
		long n = 0;
		long r;
		int i = 0;
		for (int j = n1.length() - 1; j >= 0; j--) {
			char c = n1.charAt(j);
			if (c < 'A')
				r = c - '0';
			else
				r = c - 55;
			n = n + (long) (r * Math.pow(from, i));
			i++;
		}
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
				if (r >= base || r< 0) {
					yea = false;
					break;
				}
				n1 = n1 / 10;
			}
		} else {
			for (int i = 0; i < n.length(); i++) {
				char c = n.charAt(i);
				if (c > 55 + base - 1 || c<48) {
					yea = false;
					break;
				}
			}
		}
		return yea;

	}

	EditText et1, et2, et3, et4;
	int fromBB = 2, toBB = 2;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.b1b2);

		et1 = (EditText) findViewById(R.id.editText11);
		et2 = (EditText) findViewById(R.id.editText12);
		et3 = (EditText) findViewById(R.id.editText14);
		et4 = (EditText) findViewById(R.id.editText13);
		et1.getLayoutParams().height = 55;
		et2.getLayoutParams().height = 55;
		et3.getLayoutParams().height = 55;
		et4.getLayoutParams().height = 55;
		
		et1.requestFocus();


		et1.addTextChangedListener(new EditTextWatcher(et1));
		et2.addTextChangedListener(new EditTextWatcher(et2));
		et3.addTextChangedListener(new EditTextWatcher(et3));
		
	}

	private class EditTextWatcher implements TextWatcher {

		EditText v;

		public EditTextWatcher(EditText view) {
			this.v = view;
		}

		public void afterTextChanged(Editable s) {
		}

		public void beforeTextChanged(CharSequence s, int start, int count,
				int after) {
		}

		public void onTextChanged(CharSequence s, int start, int before,
				int count) {

			switch (v.getId()) {
			case R.id.editText11:
				if (et1.hasFocus() && s.length() != 0) {


					fromBB = Integer.parseInt(et1.getText().toString());
					if (fromBB <= 10)
						et3.setInputType(InputType.TYPE_CLASS_NUMBER);
					else
						et3.setInputType(InputType.TYPE_TEXT_FLAG_CAP_CHARACTERS);
					et3.setText("");
					et4.setText("");

					String input = et1.getText().toString();
					if (Integer.parseInt(input) > 36) {
						Toast t1 = Toast.makeText(getApplicationContext(),
								"Not Allowed ! Maximum 36 !",
								Toast.LENGTH_SHORT);
						t1.show();
						String s1 = "";
						for (int i = 0; i < input.length() - 1; i++) {
							s1 += input.charAt(i);
						}
						et1.setTextKeepState(s1);
					}

				}

				break;
			case R.id.editText12:
				if (et2.hasFocus() && s.length() != 0) {

					if (et2.hasFocus()) {

						fromBB = Integer.parseInt(et1.getText().toString());
						if (fromBB <= 10)
							et3.setInputType(InputType.TYPE_CLASS_NUMBER);
						else
							et3.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD |InputType.TYPE_TEXT_FLAG_CAP_CHARACTERS);
						et3.setText("");
						et4.setText("");
						String input = et2.getText().toString();
						if (Integer.parseInt(input) > 36) {
							Toast t1 = Toast.makeText(getApplicationContext(),
									"Not Allowed ! Maximum 36 !",
									Toast.LENGTH_SHORT);
							t1.show();
							String s1 = "";
							for (int i = 0; i < input.length() - 1; i++) {
								s1 += input.charAt(i);
							}
							et2.setTextKeepState(s1);
						}

					}
				}

				break;
			case R.id.editText14:

				if (et3.hasFocus()) {
					if (s.length() != 0 && s.length() <= 10) {
						if (et1.getText().length() == 0 ||Integer.parseInt(et1.getText().toString())<2) {
							Log.d("tag", "ZERO LENGTH from");
							Toast t1 = Toast
									.makeText(getApplicationContext(),
											"Enter the (From) Base (>2)!",
											Toast.LENGTH_SHORT);
							t1.show();
							et3.setText("");
							et1.requestFocus();

						} else if (et2.getText().length() == 0||Integer.parseInt(et2.getText().toString())<2) {

							Log.d("tag", "ZERO LENGTH to2");
							Toast t1 = Toast.makeText(getApplicationContext(),
									"Enter the (To) Base (>2)!", Toast.LENGTH_SHORT);
							t1.show();
							et3.setText("");
							et2.requestFocus();

						} else {

							fromBB = Integer.parseInt(et1.getText().toString());
							toBB = Integer.parseInt(et2.getText().toString());

							String input = et3.getText().toString();
							if (check(input, fromBB)) {
								String k;

								k = base_conv(input, fromBB, toBB);
								et4.setText(k);

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
								et3.setTextKeepState(s1);
							}
						}
					} else if (s.length() > 10) {
						Toast toast = Toast.makeText(getApplicationContext(),
								"Maximum 10 digits allowed !",
								Toast.LENGTH_SHORT);
						toast.show();
						String s1 = "";
						for (int i = 0; i < 10; i++) {
							s1 += s.charAt(i);
						}
						et3.setTextKeepState(s1);
					}
					else
						et4.setText("");

				}
				break;

			default:
			}

		}

	}

}
