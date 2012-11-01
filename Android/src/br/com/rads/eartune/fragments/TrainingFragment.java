package br.com.rads.eartune.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import br.com.rads.eartune.GameActivity;
import br.com.rads.eartune.R;

public class TrainingFragment extends Fragment {

	private Button trainingButton;
	private Spinner spinner;
	
	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);
	}
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {

		View view = inflater.inflate(R.layout.fragment_training, container, false);
		
		spinner = (Spinner) view.findViewById(R.id.difficult_spinner);
		
		ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(getActivity(), R.array.difficult_array, android.R.layout.simple_spinner_dropdown_item);
		spinner.setAdapter(adapter);
		
		trainingButton = (Button) view.findViewById(R.id.traning_button);
		trainingButton.setOnClickListener( new OnClickListener() {
			
			public void onClick(View v) {
				Intent intent =  new Intent(getActivity(), GameActivity.class);
				intent.putExtra("difficult", (String)spinner.getSelectedItem());

				startActivity(intent);
				
			}
		});
		
		return view;
	}

}
