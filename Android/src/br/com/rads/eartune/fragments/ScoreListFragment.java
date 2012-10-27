package br.com.rads.eartune.fragments;

import br.com.rads.eartune.R;
import android.os.Bundle;
import android.support.v4.app.ListFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class ScoreListFragment extends ListFragment{

	private String[] errors = {"12","33","2","41","7","89"};
	private String[] corrects = {"122","133","12","141","17","189"};
	private String[] playTime = {"12:12:01","33:12:01","2:12:01","41:12:01","7:12:01","89:12:01"};
	
	
	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);
		
		ArrayAdapter<String> adapter = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_list_item_1, playTime);
		setListAdapter(adapter);
	}
	
}
