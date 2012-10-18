package br.com.rads.eartune.fragments;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import br.com.rads.eartune.R;
import br.com.rads.eartune.constants.MusicalNotes.Note;
import br.com.rads.eartune.util.SoundManager;

public class NotesFragment extends Fragment implements OnClickListener {

	private Activity activity;
	private SoundManager soundManager;
	
	// views
	private Button doButton;
	private Button reButton;
	private Button miButton;
	private Button faButton;
	private Button solButton;
	private Button laButton;
	private Button siButton;

	@Override
	public void onAttach(Activity activity) {
		super.onAttach(activity);
		
		this.activity = activity;
	}
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		soundManager = new SoundManager(this.activity);
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {

		View view = inflater.inflate(R.layout.fragment_notes, container, false);

		doButton = (Button) view.findViewById(R.id.button_do);
		reButton = (Button) view.findViewById(R.id.button_re);
		miButton = (Button) view.findViewById(R.id.button_mi);
		faButton = (Button) view.findViewById(R.id.button_fa);
		solButton = (Button) view.findViewById(R.id.button_sol);
		laButton = (Button) view.findViewById(R.id.button_la);
		siButton = (Button) view.findViewById(R.id.button_si);
		
		doButton.setTag(Note.DO);
		reButton.setTag(Note.RE);
		miButton.setTag(Note.MI);
		faButton.setTag(Note.FA);
		solButton.setTag(Note.SOL);
		laButton.setTag(Note.LA);
		siButton.setTag(Note.SI);

		doButton.setOnClickListener(this);
		reButton.setOnClickListener(this);
		miButton.setOnClickListener(this);
		faButton.setOnClickListener(this);
		solButton.setOnClickListener(this);
		laButton.setOnClickListener(this);
		siButton.setOnClickListener(this);

		return view;

	}
	
	public void onClick(View view) {
		soundManager.playSound((Note) view.getTag());
	}
}
