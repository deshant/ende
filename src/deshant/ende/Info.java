package deshant.ende;

import java.io.IOException;
import java.io.InputStream;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.MenuItem;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.TextView;

public class Info extends ActionBarActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_info);
		
		RelativeLayout layout = new RelativeLayout(this);
		ScrollView m_Scroll;
		
	    TextView info = new TextView(this);
	    info.setId(2);
	    info.setTextSize(20);
	    info.setGravity(Gravity.TOP);
	    
	    
		try {
            // get input stream for text
            InputStream is = getAssets().open("about.txt");
            // check size
            int size = is.available();
            // create buffer for IO
            byte[] buffer = new byte[size];
            // get data to buffer
            is.read(buffer);
            // close stream
            is.close();
            // set result to TextView
            info.setText(new String(buffer));
        }
        catch (IOException ex) {
            return;
        }
		
		// Defining the layout parameters of the EditText
        RelativeLayout.LayoutParams for_info = new RelativeLayout.LayoutParams(
                RelativeLayout.LayoutParams.WRAP_CONTENT,
                RelativeLayout.LayoutParams.WRAP_CONTENT);
        for_info.addRule(RelativeLayout.CENTER_HORIZONTAL,info.getId());
        for_info.setMargins(0, 20, 0, 20);
        
        layout.addView(info, for_info);
        m_Scroll = new ScrollView(this);
        m_Scroll.addView(layout);
        setContentView(m_Scroll);
		
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
