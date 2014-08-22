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
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.TextView;

public class C_decrypted extends ActionBarActivity {
	
	String crypt = null;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		RelativeLayout layout = new RelativeLayout(this);
		ScrollView m_Scroll;
			
		// Get the message from the intent
	    Intent intent = getIntent();
	    String message = intent.getStringExtra(Caesar.TEXT);
	    byte[] plain_text = null;
		try {
			plain_text = message.getBytes("US-ASCII");
		} catch (UnsupportedEncodingException e) {
			
			//error and abort
		}
	    
	    String kvalue = intent.getStringExtra(Caesar.KEY);
	    int key = Integer.parseInt(kvalue) % 26;
	    
	    for (int i = 0; i < plain_text.length; i++)
	    {
	      int value = (int) plain_text[i];
	      
	      if (value >= 65 && value <= 90)
	      {
	    	  if(value - key > 64)
	    	  {
	    		  value = value - key;
	    		  plain_text[i] = (byte) value; 
	    	  }
	    	  else
	    	  {
	    		  key = 65 - (value - key);
	    		  plain_text[i] = (byte) (91- key);
	    	  }
	      }
	      else if(value >= 97 && value <= 122)
	      {
	    	  if(value - key > 96)
	    	  {
	    		  value = value - key;
	    		  plain_text[i] = (byte) value; 
	    	  }
	    	  else
	    	  {
	    		  key = 97 - (value - key);
	    		  plain_text[i] = (byte) (123 - key);
	    	  }	    	  
	      }
	      else
	    	  continue;
	    }
	    
		try {
			crypt = new String(plain_text, "US-ASCII");
		} catch (UnsupportedEncodingException e) {
			//error and error handling
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
        btn.setId(3);
        btn.setGravity(Gravity.BOTTOM);        
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                putText(v);
            }
        });
        
        // Create the text view for message
	    TextView info = new TextView(this);
	    info.setId(4);
	    info.setTextSize(18);
	    info.setGravity(Gravity.BOTTOM);
	    info.setText("click on button to copy the decrypted text");
	    
        
        
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
        for_b.setMargins(0, 10, 0, 10);
        
        // setting up
        layout.addView(text, for_text);
        layout.addView(btn, for_b);
        layout.addView(info, for_info);
	    
        m_Scroll = new ScrollView(this);
        m_Scroll.addView(layout);
        setContentView(m_Scroll);
        //setContentView(layout);

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
