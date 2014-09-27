package deshant.ende;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.ActionBarActivity;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;


public class MainActivity extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.container, new PlaceholderFragment())
                    .commit();
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
    
    /** Called when the user clicks the Caesar cipher button */
    public void caesar(View view) {
        Intent intent = new Intent(this, Caesar.class);
        startActivity(intent);
    }
    
    /** Called when the user clicks the Vigenere Cipher button */
    public void vigenere(View view) {
        Intent intent = new Intent(this, Vigenere.class);
        startActivity(intent);
    }
    
    /** Called when the user clicks the info button */
    public void info(View view) {
        Intent intent = new Intent(this, Info.class);
        startActivity(intent);
    }
    
    /** Called when the user clicks the Vigenere Cipher button */
    public void vig_auto(View view) {
        Intent intent = new Intent(this, Vig_auto.class);
        startActivity(intent);
    }
    
    /** Called when the user clicks the Atbash Cipher button */
    public void atbash(View view) {
        Intent intent = new Intent(this, Atbash.class);
        startActivity(intent);
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
            View rootView = inflater.inflate(R.layout.fragment_main, container, false);
            return rootView;
        }
    }
}
