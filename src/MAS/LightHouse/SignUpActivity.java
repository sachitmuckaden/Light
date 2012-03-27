package MAS.LightHouse;

/* Java class which has the activity to register an user */

import Transferdetails.Constants;
import Transferdetails.SignUpVO;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.text.InputType;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.resting.Resting;
import com.google.resting.component.impl.ServiceResponse;

public class SignUpActivity extends Activity implements View.OnTouchListener{
	private EditText signupname;
	private EditText signupusername;
	private EditText signuppassword;
	private EditText signupconfirmpassword;
	private Button   signuppagebutton;

	private static final int SIGNUP_FAILURE = 0;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.signuplayout);

		signupname = (EditText) findViewById(R.id.signupname);
		signupusername = (EditText) findViewById(R.id.signupusername);
		signuppassword = (EditText) findViewById(R.id.signuppassword);
		signupconfirmpassword = (EditText) findViewById(R.id.signupconfirmpassword);
		signuppagebutton = (Button) findViewById(R.id.signuppagebutton);

		signupname.setOnTouchListener(this);
		signupusername.setOnTouchListener(this);
		signuppassword.setOnTouchListener(this);
		signupconfirmpassword.setOnTouchListener(this);
}

	@Override
	protected void onResume() {
		super.onRestart();
		setContentView(R.layout.signuplayout);

		signupname = (EditText) findViewById(R.id.signupname);
		signupusername = (EditText) findViewById(R.id.signupusername);
		signuppassword = (EditText) findViewById(R.id.signuppassword);
		signupconfirmpassword = (EditText) findViewById(R.id.signupconfirmpassword);
		signuppagebutton = (Button) findViewById(R.id.signuppagebutton);

		signupname.setOnTouchListener(this);
		signupusername.setOnTouchListener(this);
		signuppassword.setOnTouchListener(this);
		signupconfirmpassword.setOnTouchListener(this);
	}

	public void createaccount(View v) {

		

		SignUpVO signUpVO = new SignUpVO();

		signUpVO.setName(signupname.getText().toString());
		signUpVO.setUsername(signupusername.getText().toString());
		signUpVO.setPassword(signuppassword.getText().toString());
		signUpVO.setConfirmpassword(signupconfirmpassword.getText().toString());
		String status = "UNTESTED";

		String parameters = signUpVO.getName() + "/" + signUpVO.getUsername()
				+ "/" + signUpVO.getPassword() + "/"
				+ signUpVO.getConfirmpassword();
		String request = "http://" + Constants.ipaddress
				+ "/com.gatech.rts/rest/register/" + parameters;
		Log.d("parameter", request);
		if (request.contains("///") || request.contains(" ")) {
			Log.d("INVALIDCHAR", "invalidchar");
			status = "REJECT";
		}

		if (!status.equals("REJECT")) {
			Log.d("Sendreq", "Sendreq");
			ServiceResponse response = Resting.get(request, Constants.port);
			status = response.getResponseString();
			Log.d("status", status);
			if (!status.contains("ERROR")) {
				Log.d("Entry Added", "Entry Added");
				Context context = getApplicationContext();
				CharSequence text = "An account has been created";
				int duration = Toast.LENGTH_LONG;

				Toast toast = Toast.makeText(context, text, duration);
				toast.show();
				finish();
			}

			response = Resting.get("http://" + Constants.ipaddress
					+ "/com.gatech.rts/rest/register/" + status + "/"
					+ signUpVO.getUsername(), Constants.port);
			Log.d("Response2", response.getResponseString());
		}

		else {
		
			showDialog(SIGNUP_FAILURE);
		}
	}

	public boolean onTouch(View v, MotionEvent event) {
		EditText focusEditText = (EditText) v;
		String contents = focusEditText.getText().toString();
		if (contents.equals("Nick Name")) {
			focusEditText.setText("");
			return false;
		}
		
		if (contents.equals("Password")) {
			focusEditText.setText("");
			focusEditText.setInputType(InputType.TYPE_CLASS_TEXT
					| InputType.TYPE_TEXT_VARIATION_PASSWORD);
			return false;
		}
		if (contents.equals("Username")) {
			focusEditText.setText("");
			return false;
		}
		if (contents.equals("Confirm Password")) {
			focusEditText.setText("");
			focusEditText.setInputType(InputType.TYPE_CLASS_TEXT
					| InputType.TYPE_TEXT_VARIATION_PASSWORD);
			return false;
		}
		return false;
	}

	@Override
	protected Dialog onCreateDialog(int id) {
		if (id == SIGNUP_FAILURE) {
			AlertDialog.Builder builder = new AlertDialog.Builder(this);
			builder.setTitle("Registration Error");
			builder.setMessage("One of the details you have entered is incorrect");
			builder.setIcon(R.drawable.ic_launcher);
			builder.setPositiveButton(R.string.retry,
					new DialogInterface.OnClickListener() {

						public void onClick(DialogInterface dialog, int which) {
							return;

						}
					});
			return builder.create();

		}
		return null;
	}

}