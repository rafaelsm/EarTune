package br.com.rads.eartune;

import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import br.com.rads.eartune.fragments.NotesFragment;
import br.com.rads.eartune.fragments.PageAdapter;
import br.com.rads.eartune.fragments.TrainingFragment;

import com.actionbarsherlock.app.ActionBar;
import com.actionbarsherlock.app.ActionBar.Tab;
import com.actionbarsherlock.app.ActionBar.TabListener;
import com.actionbarsherlock.app.SherlockFragmentActivity;
import com.actionbarsherlock.view.Menu;

public class MainActivity extends SherlockFragmentActivity {

	private ActionBar actionBar;
	private ViewPager viewPager;
	
	
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		actionBar = getSupportActionBar();
		actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);
		
		NotesFragment notesFragment = new NotesFragment();
		TrainingFragment trainingFragment = new TrainingFragment();
		
		PageAdapter adapter = new PageAdapter(getSupportFragmentManager());
		adapter.addFragment(notesFragment);
		adapter.addFragment(trainingFragment);
		
		viewPager = (ViewPager) findViewById(R.id.pager);
		viewPager.setAdapter(adapter);
		viewPager.setOffscreenPageLimit(adapter.getCount());
		viewPager.setCurrentItem(0);
		viewPager.setOnPageChangeListener( new OnPageChangeListener() {
			public void onPageSelected(int position) {
				actionBar.setSelectedNavigationItem(position);
			}
			
			public void onPageScrolled(int arg0, float arg1, int arg2) {}
			
			public void onPageScrollStateChanged(int arg0) {}
		});
		
		createTab("Notas Musicais");
		createTab("Treino");
		
		
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getSherlock().getMenuInflater().inflate(R.menu.activity_main, menu);
		return true;
	}

	
	
	private void createTab(String title){
		Tab tab = getSupportActionBar().newTab();
		tab.setText(title);
		tab.setTabListener( new TabListener() {
			
			public void onTabUnselected(Tab tab, FragmentTransaction ft) {
			}
			
			public void onTabSelected(Tab tab, FragmentTransaction ft) {
				viewPager.setCurrentItem(tab.getPosition(), true);
			}
			
			public void onTabReselected(Tab tab, FragmentTransaction ft) {
			}
		});
		
		getSupportActionBar().addTab(tab);
	}
}
