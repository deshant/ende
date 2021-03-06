package deshant.ende;

import java.io.UnsupportedEncodingException;

import android.support.v7.app.ActionBarActivity;
import android.annotation.SuppressLint;
import android.content.ClipData;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import android.view.Gravity;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

public class Atbash_act extends ActionBarActivity {
	
	String crypt = null; //will store modified text
	byte[] small = new byte[26];
	byte[] cap = new byte[26];

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_atbash_act);
		
		for(int i = 0; i < 26;i++)
		{
			small[i] = (byte) (97 + i);
			cap[i] = (byte) (65 + i);
		}
		RelativeLayout layout = new RelativeLayout(this);
		ScrollView m_Scroll;

		// Get the message from the intent
	    Intent intent = getIntent();
	    String message = intent.getStringExtra(Vigenere.TEXT);
	    byte[] plain_text = null;
	    	    
	    // converting for easy manipulation of characters
	    try {
	 		plain_text = message.getBytes("US-ASCII");
	 	} catch (UnsupportedEncodingException e) {
	  			//formality
	 	}
	    
	   int value;
	    	for(int i = 0; i < plain_text.length; i++)
	    	{
	    		value = (int) plain_text[i];
	    		plain_text[i] = (byte) converted(value);
	    	}
	    
	    try {
			crypt = new String(plain_text, "US-ASCII");
		}
		catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}

	    // Create the text view for crypted text
	    TextView text = new TextView(this);
	    text.setId(2);
	    text.setTextSize(20);
	    text.setGravity(Gravity.TOP);
	    text.setText(crypt);
	    
	    // create button for copying text
	    Button btn = (Button)getLayoutInflater().inflate(R.layout.template_btn, null);
        btn.setText("COPY");
        btn.setTextSize(18);
        btn.setId(3);
        btn.setGravity(Gravity.BOTTOM);
        btn.setTextAppearance(this, R.style.btnStyleBeige);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            	Context context = getApplicationContext();
        		CharSequence pop = "Text Copied !";
        		int duration = Toast.LENGTH_SHORT;

        		Toast toast = Toast.makeText(context, pop, duration);
        		toast.show();
        		
                putText(v);
            }
        });
        
        // Create the text view for message
	    TextView info = new TextView(this);
	    info.setId(4);
	    info.setTextSize(18);
	    info.setGravity(Gravity.BOTTOM);
	    info.setText("click on button to copy the result");
	    
        
        
        // Defining the layout parameters of the EditText
        RelativeLayout.LayoutParams for_text = new RelativeLayout.LayoutParams(
                RelativeLayout.LayoutParams.WRAP_CONTENT,
                RelativeLayout.LayoutParams.WRAP_CONTENT);
        for_text.addRule(RelativeLayout.CENTER_HORIZONTAL,text.getId());
        for_text.setMargins(0, 20, 0, 20);
        
     // Defining the layout parameters of the TextView
        RelativeLayout.LayoutParams for_info = new RelativeLayout.LayoutParams(
                RelativeLayout.LayoutParams.WRAP_CONTENT,
                RelativeLayout.LayoutParams.WRAP_CONTENT);
        for_info.addRule(RelativeLayout.CENTER_HORIZONTAL);  
        for_info.addRule(RelativeLayout.BELOW, btn.getId());
        for_info.setMargins(0, 20, 0, 20);
        
        // Defining the layout parameters of the button
        RelativeLayout.LayoutParams for_b = new RelativeLayout.LayoutParams(
                RelativeLayout.LayoutParams.WRAP_CONTENT,
                RelativeLayout.LayoutParams.WRAP_CONTENT);
        for_b.addRule(RelativeLayout.CENTER_HORIZONTAL,btn.getId());
        for_b.addRule(RelativeLayout.BELOW, text.getId());
        
        // setting up
        layout.addView(text, for_text);
        layout.addView(btn, for_b);
        layout.addView(info, for_info);
        
        m_Scroll = new ScrollView(this);
        m_Scroll.addView(layout);
        setContentView(m_Scroll);
        //setContentView(layout);
	}

	public int converted(int x)
	{
		if (x >= 65 && x <= 90)
	    {
			x = x - 65;
			x = cap[25 - x];
	    }
		else if(x >= 97 && x <= 122)
		{
			x = x - 97;
			x = small[25 - x];
		}
		else
			return x;
		
		return x;
	}
	
    
	@SuppressLint("NewApi") 
	@SuppressWarnings("deprecation")
	public void putText(View v){
	    int sdk = android.os.Build.VERSION.SDK_INT;
	    if(sdk < android.os.Build.VERSION_CODES. HONEYCOMB) {
	        android.text.ClipboardManager clipboard = (android.text.ClipboardManager) this.getSystemService(Context.CLIPBOARD_SERVICE);
	        clipboard.setText(crypt);
	    } else {
	        android.content.ClipboardManager clipboard = (android.content.ClipboardManager) this.getSystemService(Context.CLIPBOARD_SERVICE); 
	        android.content.ClipData clip = ClipData.newPlainText("simple text",crypt);
	        clipboard.setPrimaryClip(clip);
	    }
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}
}
