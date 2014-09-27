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

public class V_act extends ActionBarActivity {
	
	String crypt = null; //will store modified text

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_v_act);
		
		RelativeLayout layout = new RelativeLayout(this);
		ScrollView m_Scroll;

		// Get the message from the intent
	    Intent intent = getIntent();
	    String message = intent.getStringExtra(Vigenere.TEXT);
	    byte[] plain_text = null;
	    	    
	    String kvalue = intent.getStringExtra(Vigenere.KEY);
	    kvalue = kvalue.replaceAll("[^\\w\\s]", "");// remove all special characters from string, or kvalue = kvalue.replaceAll("[^a-zA-Z]+","");
	    byte[] key_text = null;
	    
	    // converting for easy manipulation of characters
	    try {
	 		plain_text = message.getBytes("US-ASCII");
	 	} catch (UnsupportedEncodingException e) {
	 	}
	    
	    try {
			key_text = kvalue.getBytes("US-ASCII");
		} catch (UnsupportedEncodingException e1) {
			// Auto-generated catch block
			// exception is rare/improbable here.
			e1.printStackTrace();
		}
	    
	    String act = intent.getStringExtra(Vigenere.ACT);
	    
	    if(act.equals("encrypt"))
	    {
	    	// Vigenere encryption begins !
		    for (int i = 0, j = 0; i < plain_text.length; i++)
		    {
		    	if(j >= key_text.length)
		    		j=0;
		    	
		      int value = (int) plain_text[i];
		      int mod = (int) key_text[j];
		      
		      if (mod >= 65 && mod <= 90)
		      {
		    	  mod = mod - 64;
		      }
		      else if(mod >= 97 && mod <= 122) // a precaution
		      {
		    	  mod = mod - 96;
		      }
		      else // just a precaution
		      {
		    	  i--;
		    	  j++;
		    	  continue;
		      }
		    	  
		      if (value >= 65 && value <= 90)
		      {
		    		  
		    	  if(value + mod < 91)
		    	  {
		    		  value = value + mod;
		    		  plain_text[i] = (byte) value;
		    	  }
		    	  else
		    	  {
		    		  mod = (mod + value) - 90;
		    		  plain_text[i] = (byte) (mod + 64);
		    	  }
		    	  j++;
		      }
		      else if(value >= 97 && value <= 122)
		      {
		    	  if(value + mod < 123)
		    	  {
		    		  value = value + mod;
		    		  plain_text[i] = (byte) value; 
		    	  }
		    	  else
		    	  {
		    		  mod = (mod + value) - 122;
		    		  plain_text[i] = (byte) (mod + 96);
		    	  }
		    	  j++;
		      }
		      else
		    	  continue;
		    }
	    }
	    else
	    {
	    	// Vigenere decryption begins !
		    for (int i = 0, j = 0; i < plain_text.length; i++)
		    {
		    	if(j >= key_text.length)
		    		j=0;
		    	
		      int value = (int) plain_text[i];
		      int mod = (int) key_text[j];
		      
		      if (mod >= 65 && mod <= 90)
		      {
		    	  mod = mod - 64;
		      }
		      else if(mod >= 97 && mod <= 122) // a precaution
		      {
		    	  mod = mod - 96;
		      }
		      else // just a precaution
		      {
		    	  i--;
		    	  j++;
		    	  continue;
		      }
		    	  
		      if (value >= 65 && value <= 90)
		      {
		    		  
		    	  if(value - mod > 64)
		    	  {
		    		  value = value - mod;
		    		  plain_text[i] = (byte) value; 
		    	  }
		    	  else
		    	  {
		    		  mod = 65 - (value - mod);
		    		  plain_text[i] = (byte) (91- mod);
		    	  }
		    	  j++;
		      }
		      else if(value >= 97 && value <= 122)
		      {
		    	  if(value - mod > 96)
		    	  {
		    		  value = value - mod;
		    		  plain_text[i] = (byte) value; 
		    	  }
		    	  else
		    	  {
		    		  mod = 97 - (value - mod);
		    		  plain_text[i] = (byte) (123 - mod);
		    	  }	    
		    	  j++;
		      }
		      else
		    	  continue;
		    }
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
