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

public class Atbash extends ActionBarActivity {

	public final static String TEXT = "deshant.ende.TEXT";
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_atbash);
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
	
	/** Called when the user clicks the process button */
    public void process(View view) {
    	
    	Intent intent = new Intent(this, Atbash_act.class);
        EditText value1 = (EditText) findViewById(R.id.message);
    	String text = value1.getText().toString();
    	    	
    	if (text == null || text.isEmpty() || text.equals(""))
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
			View rootView = inflater.inflate(R.layout.fragment_atbash,
					container, false);
			return rootView;
		}
	}
}
