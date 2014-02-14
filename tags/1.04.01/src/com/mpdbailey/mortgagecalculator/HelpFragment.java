package com.mpdbailey.mortgagecalculator;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.Html;
import android.text.method.ScrollingMovementMethod;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class HelpFragment extends Fragment{
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_help, container, false);
        return rootView;
    }
    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
    	super.onActivityCreated(savedInstanceState);
		TextView textView = (TextView) getActivity().findViewById(R.id.HelpTextBox);
		textView.setText(Html.fromHtml(getString(R.string.help_html)));
		textView.setMovementMethod(new ScrollingMovementMethod());
    }

}
