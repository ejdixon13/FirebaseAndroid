package com.example.firebase;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.firebase.client.ValueEventListener;

import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.ActionBar;
import android.support.v4.app.Fragment;
import android.app.ListActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.TextView;
import android.os.Build;

public class MainActivity extends ListActivity {

    //LIST OF ARRAY STRINGS WHICH WILL SERVE AS LIST ITEMS
    ArrayList<String> listItems=new ArrayList<String>();

    //DEFINING A STRING ADAPTER WHICH WILL HANDLE THE DATA OF THE LISTVIEW
    ArrayAdapter<String> adapter;
    
    //RECORDING HOW MANY TIMES THE BUTTON HAS BEEN CLICKED
    int clickCounter=0;
    
    // text field for taking names
    EditText firstName;
    EditText lastName;
    
	@Override
	protected void onCreate(Bundle icicle) {
		super.onCreate(icicle);
		setContentView(R.layout.main);
		lastName = (EditText) findViewById(R.id.editText2);
		firstName = (EditText) findViewById(R.id.editText1);
		adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, listItems);
		setListAdapter(adapter);
		
	
	
		
    	Firebase f = new Firebase("https://incandescent-fire-4835.firebaseio.com/Users");
		f.addValueEventListener(new ValueEventListener() {
		  @Override
		  public void onDataChange(DataSnapshot snapshot) {
				//TextView newsFeed = (TextView)findViewById(R.id.newsFeed);
				for (DataSnapshot obj : snapshot.getChildren()) {
					String firstName = obj.child("first").getValue().toString();
					String lastName = obj.child("last").getValue().toString();
					if (!listItems.contains(lastName + ", " + firstName)) {
						listItems.add(lastName + ", " + firstName);
					}
			        adapter.notifyDataSetChanged();
				}
		  }

		  @Override
		  public void onCancelled(FirebaseError error) {
		    System.err.println("Listener was cancelled");
		  }
		});
	}
    //METHOD WHICH WILL HANDLE DYNAMIC INSERTION
    public void addItems(View v) {
    	Firebase f = new Firebase("https://incandescent-fire-4835.firebaseio.com/Users");
    	
    	// add first and last name inputted
		Map<String, Object> toSet = new HashMap<String, Object>();
		toSet.put("first", firstName.getText().toString());
		toSet.put("last", lastName.getText().toString());
		f.push().setValue(toSet);
		
		
		f.addValueEventListener(new ValueEventListener() {
		  @Override
		  public void onDataChange(DataSnapshot snapshot) {
				//TextView newsFeed = (TextView)findViewById(R.id.newsFeed);
				for (DataSnapshot obj : snapshot.getChildren()) {
					String firstName = obj.child("first").getValue().toString();
					String lastName = obj.child("last").getValue().toString();
					if (!listItems.contains(lastName + ", " + firstName)){
						listItems.add(lastName + ", " + firstName);
					}
			        adapter.notifyDataSetChanged();
				}
		  }

		  @Override
		  public void onCancelled(FirebaseError error) {
		    System.err.println("Listener was cancelled");
		  }
		});

        
    }
//		if (savedInstanceState == null) {
//			getFragmentManager().beginTransaction()
//					.add(R.id.container, new PlaceholderFragment()).commit();
//		}
		
		
//		Firebase f = new Firebase("https://incandescent-fire-4835.firebaseio.com/Users");
//		Map<String, Object> toSet = new HashMap<String, Object>();
//		toSet.put("first", "Blanch");
//		toSet.put("last", "Swansoniteey");
//		f.push().setValue(toSet);
//		f.setValue(0);
//		f.setValue(1);

		
		//toSet.put("Users", "");
		//f.setValue("Users");
		
//		f = new Firebase("https://incandescent-fire-4835.firebaseio.com/Users/Name");
//		f.push();
//		f.setValue("Another Name");

	//	toSet.put
		
//		f.addValueEventListener(new ValueEventListener() {
//		  @Override
//		  public void onDataChange(DataSnapshot snapshot) {
//				TextView newsFeed = (TextView)findViewById(R.id.newsFeed);
////				for (DataSnapshot obj : snapshot.getChildren()) {
////					System.out.println(obj.getValue());
////				}
//		  }
//
//		  @Override
//		  public void onCancelled(FirebaseError error) {
//		    System.err.println("Listener was cancelled");
//		  }
//		});
//		
//		f.addValueEventListener(new ValueEventListener() {
//
//			@Override
//			public void onCancelled(FirebaseError arg0) {
//				// TODO Auto-generated method stub
//				
//			}
//
//			@Override
//			public void onDataChange(DataSnapshot arg0) {
//				//TextView newsFeed = (TextView)findViewById(R.id.newsFeed);
//				//newsFeed.setText(arg0.getValue(String.class));
//			}
//			
//		});
//	}
//	
//	public void sunny(View view) {
//		Firebase f = new Firebase("https://incandescent-fire-4835.firebaseio.com/Users/");
//		//f.push();
//		//f.setValue("Another Name");
//	}
//	public void foggy(View view) {
//		Firebase f = new Firebase("https://incandescent-fire-4835.firebaseio.com/");
//		f.setValue("Foggy..");
//	}
//	@Override
//	public boolean onCreateOptionsMenu(Menu menu) {
//
//		// Inflate the menu; this adds items to the action bar if it is present.
//		getMenuInflater().inflate(R.menu.main, menu);
//		return true;
//	}
//
//	@Override
//	public boolean onOptionsItemSelected(MenuItem item) {
//		// Handle action bar item clicks here. The action bar will
//		// automatically handle clicks on the Home/Up button, so long
//		// as you specify a parent activity in AndroidManifest.xml.
//		int id = item.getItemId();
//		if (id == R.id.action_settings) {
//			return true;
//		}
//		return super.onOptionsItemSelected(item);
//	}



}
