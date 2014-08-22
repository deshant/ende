package deshant.ende;

import android.support.v7.app.ActionBarActivity;
import android.support.v4.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
//import android.os.Build;

public class Caesar extends ActionBarActivity {

	public final static String TEXT = "deshant.ende.TEXT";
	public final static String KEY = "deshant.ende.KEY";
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_caesar);
		if (savedInstanceState == null) {
			getSupportFragmentManager().beginTransaction()
					.add(R.id.container, new PlaceholderFragment()).commit();
		}
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.caesar, menu);
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

	/** Called when the user clicks the encrypt button */
    public void encrypt(View view) {
        Intent intent = new Intent(this, C_crypted.class);
        
        EditText value1 = (EditText) findViewById(R.id.message);
    	String text = value1.getText().toString();
    	
    	EditText value2 = (EditText) findViewById(R.id.key);
    	String key = value2.getText().toString();
    	
    	if ((text == null || text.isEmpty() || text.equals("")) || (key == null || key.isEmpty() || key.equals("")))
    	{
    		//do nothing
    	}
    	else
    	{
	    	intent.putExtra(TEXT, text);
	    	intent.putExtra(KEY, key);
	    	
	        startActivity(intent);
    	}
    }
    
	/** Called when the user clicks the encrypt button */
    public void decrypt(View view) {
        Intent intent = new Intent(this, C_decrypted.class);
        
        EditText value1 = (EditText) findViewById(R.id.message);
    	String text = value1.getText().toString();
    	
    	EditText value2 = (EditText) findViewById(R.id.key);
    	String key = value2.getText().toString();
    	
    	if ((text == null || text.isEmpty() || text.equals("")) || (key == null || key.isEmpty() || key.equals("")))
    	{
    		//do nothing
    	}
    	else
    	{
	    	intent.putExtra(TEXT, text);
	    	intent.putExtra(KEY, key);
	    	
	        startActivity(intent);
    	}
    }
    
	
	/**
	 * A placeholder fragment containing a simple view.
	 */
	public static class PlaceholderFragment extends Fragment {

		public PlaceholderFragment() {
		}

		@Override
		public View onCreateView(LayoutInflater inflater, ViewGroup container,
				Bundle savedInstanceState) {
			View rootView = inflater.inflate(R.layout.fragment_caesar,
					container, false);
			return rootView;
		}
	}
}
