package MAS.LightHouse;


import com.google.gson.Gson;
import com.google.resting.Resting;
import com.google.resting.component.impl.ServiceResponse;

import Transferdetails.Constants;
import Transferdetails.*;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.text.InputType;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class LoginActivity extends Activity implements View.OnTouchListener
{
	private	Button loginpageloginbutton;
	private Button loginpagesignupbutton;
	private EditText username;
	private EditText password;
	
	private static final int LOGIN_FAILURE=0;

	
	/** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) 
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.loginlayout);
        
        
        loginpageloginbutton=(Button)findViewById(R.id.loginpageloginbutton);
        loginpagesignupbutton=(Button)findViewById(R.id.loginpagesignupbutton);
        
        
        username=(EditText)findViewById(R.id.loginpageusername);
        password=(EditText)findViewById(R.id.loginpagepassword);
        

        username.setOnTouchListener(this);
        password.setOnTouchListener(this);
       
    }
    
    @Override
    protected void onResume() 
    {
    	super.onResume();
    	Log.d("RESUME","RESUME");
    	//loginpageloginbutton.setBackgroundResource(R.drawable.loginbutton1);
        //loginpagesignupbutton.setBackgroundResource(R.drawable.signupbutton);
        username.setText(R.string.username);
        password.setText(R.string.password);
        
    	
    }
   
    /* Method to perform the login operation */ 
    
    public void login(View view)
    {
    	//loginpageloginbutton.setBackgroundResource(R.drawable.loginbuttonclicked);
    	String userNameText=username.getText().toString().replace(' ','_');
    	if(userNameText.equals(""))
    	{
    		userNameText="null";
    	}
    	String passwordText=password.getText().toString().replace(' ','_');
    	if(passwordText.equals(""))
    	{
    		passwordText="null";
    	}
     	
    	/*ServiceResponse response=Resting.get("http://"+Constants.ipaddress+"/com.gatech.rts/rest/login/"+userNameText+"/"+passwordText,Constants.port);
    	
    	Gson gson=new Gson();
    	LoginVO loginVO=gson.fromJson(response.getResponseString(), LoginVO.class);
    	
    	String result=loginVO.getStatus();*/
    	if(result.equals("ACCEPT"))
    	{
    		//loginpageloginbutton.setBackgroundResource(R.drawable.loginbuttonclicked);
    		Intent i=new Intent(this, MainActivity.class);
    		i.putExtra("name", loginVO.getName());
    		i.putExtra("userid",loginVO.getUserId()+"");
    		startActivity(i);
    	}
    	if(result.equals("REJECT"))
    	{
    		//loginpageloginbutton.setBackgroundResource(R.drawable.loginbutton1);
    		showDialog(LOGIN_FAILURE);
    	}
    }
    
    public void signup(View view)
    {
    	//loginpagesignupbutton.setBackgroundResource(R.drawable.loginbuttonclicked);
    	Intent i=new Intent(this,SignUpActivity.class);
    	startActivity(i);
    }

	public boolean onTouch(View v, MotionEvent event) 
	{
		EditText focusEditText=(EditText)v;
		String contents=focusEditText.getText().toString();
		if(contents.equals("App Username"))
		{
			
			focusEditText.setText("");
			return false;
		}
		if(contents.equals("Password"))
		{
			focusEditText.setText("");
			focusEditText.setInputType(InputType.TYPE_CLASS_TEXT|InputType.TYPE_TEXT_VARIATION_PASSWORD);
			return false;
		}
		return false;
	}
	
	@Override
	protected Dialog onCreateDialog(int id) 
	{
		if(id==LOGIN_FAILURE)
		{
			AlertDialog.Builder builder = new AlertDialog.Builder(this);
			builder.setTitle("Login Error");
			builder.setMessage("The username or the password you entered is incorrect");
			builder.setIcon(R.drawable.ic_launcher);
			builder.setPositiveButton(R.string.retry, new DialogInterface.OnClickListener() {
				
				public void onClick(DialogInterface dialog, int which) 
				{
				return;
					
				}
			});
			return builder.create();
			
			
		}
		return null;
	}
	



}