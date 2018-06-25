package com.alex.readabc.letters.myapplication.presentation.ui.fragments;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.alex.readabc.letters.myapplication.R;
import com.alex.readabc.letters.myapplication.domain.executor.impl.ThreadExecutor;
import com.alex.readabc.letters.myapplication.domain.model.Contact;
import com.alex.readabc.letters.myapplication.presentation.presenters.AllContactsPresenter;
import com.alex.readabc.letters.myapplication.presentation.presenters.impl.AllContactsPresenterImpl;
import com.alex.readabc.letters.myapplication.storage.ContactsRepositoryImpl;
import com.alex.readabc.letters.myapplication.threading.MainThreadImpl;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link AllContactsFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link AllContactsFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class AllContactsFragment extends Fragment implements AllContactsPresenter.View {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;

    public AllContactsFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment AllContactsFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static AllContactsFragment newInstance(String param1, String param2) {
        AllContactsFragment fragment = new AllContactsFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Log.v("vvv", "onCreate Fragment");
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }

        if (savedInstanceState != null) {
            if (savedInstanceState.containsKey("s")) {
                s = savedInstanceState.getString("s");
            }
        }

        // create a presenter for this view
        allContactsPresenter = new AllContactsPresenterImpl(
                ThreadExecutor.getInstance(),
                MainThreadImpl.getInstance(),
                this,
                ContactsRepositoryImpl.getInstance()
        );


    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        Log.v("vvv", "onCreate Fragment view");
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_all_contacts, container, false);


        ((Button) view.findViewById(R.id.submitBtn)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //do stuff

                allContactsPresenter.displayContacts();
            }
        });

        ((TextView) view.findViewById(R.id.txt1)).setText(s);

        return view;
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }

    @Override
    public void showProgress() {

    }

    @Override
    public void hideProgress() {

    }

    @Override
    public void showError(String message) {

    }

    String s = "";

    @Override
    public void showContactsList(ArrayList<Contact> contacts) {
        s = "";
        for (int i = 0; i < contacts.size(); i++) {
            s = s + contacts.get(i).getName();
        }
        ((TextView) getActivity().findViewById(R.id.txt1)).setText(s);
    }

    AllContactsPresenter allContactsPresenter;

    @Override
    public void onResume() {
        super.onResume();
        Log.v("vvv", "onResume Fragment");
        //allContactsPresenter.resume();
        allContactsPresenter.setView(this);
    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        Log.v("vvv", "onSaveInstanceState fragment");
        outState.putString("s", s);

    }
}
