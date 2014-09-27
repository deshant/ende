package deshant.ende;

import android.support.v7.app.ActionBarActivity;
import android.support.v4.app.Fragment;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.Toast;

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
	public boolean onOptionsItemSelected(MenuItem item) {
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
    		Context context = getApplicationContext();
    		CharSequence pop = "input not valid !";
    		int duration = Toast.LENGTH_SHORT;

    		Toast toast = Toast.makeText(context, pop, duration);
    		toast.show();
    	}
    	else
    	{
	    	intent.putExtra(TEXT, text);
	    	intent.putExtra(KEY, key);
	    	
	        startActivity(intent);
    	}
    }
    
	/** Called when the user clicks the decrypt button */
    public void decrypt(View view) {
        Intent intent = new Intent(this, C_decrypted.class);
        
        EditText value1 = (EditText) findViewById(R.id.message);
    	String text = value1.getText().toString();
    	
    	EditText value2 = (EditText) findViewById(R.id.key);
    	String key = value2.getText().toString();
    	
    	if ((text == null || text.isEmpty() || text.equals("")) || (key == null || key.isEmpty() || key.equals("")))
    	{
    		Context context = getApplicationContext();
    		CharSequence pop = "input not valid !";
    		int duration = Toast.LENGTH_SHORT;

    		Toast toast = Toast.makeText(context, pop, duration);
    		toast.show();
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
