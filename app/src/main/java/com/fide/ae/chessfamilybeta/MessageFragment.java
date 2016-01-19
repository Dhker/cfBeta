package com.fide.ae.chessfamilybeta;

import android.app.Activity;
import android.net.Uri;
import android.os.Bundle ;


import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.cjj.MaterialRefreshLayout;
import com.cjj.MaterialRefreshListener;

import java.util.ArrayList;
import java.util.List;

import model.Event;
import model.Member;
import model.MemberPublication;
import model.Message;
import utils.ItemAdapter;


public class MessageFragment extends Fragment {

    RecyclerView recyclerView;
    ItemAdapter adapter ;
    List messages = new ArrayList<>();
    MaterialRefreshLayout materialRefreshLayout ;

    private OnFragmentInteractionListener mListener;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);




    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
      View  rootView =inflater.inflate(R.layout.fragment_message, container, false);

        populate(); 

        recyclerView = (RecyclerView)rootView.findViewById(R.id.message_list);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setHasFixedSize(true);

        materialRefreshLayout = (MaterialRefreshLayout) rootView.findViewById(R.id.refresh);
        materialRefreshLayout.setMaterialRefreshListener(new MaterialRefreshListener() {
            @Override
       public void onRefresh(final MaterialRefreshLayout materialRefreshLayout) {
       //refreshing...
       }

        @Override
       public void onRefreshLoadMore(MaterialRefreshLayout materialRefreshLayout)
        {
       //load more refreshing...
         }

         } );

// refresh complete
                materialRefreshLayout.finishRefresh();

// load more refresh complete
        materialRefreshLayout.finishRefreshLoadMore();





        adapter = new ItemAdapter(getContext() ,messages) ;
        recyclerView.setAdapter(adapter);


        return  rootView ;


    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
       /* try {
            mListener = (OnFragmentInteractionListener) activity;
        } catch (ClassCastException e) {
            throw new ClassCastException(activity.toString()
                    + " must implement OnFragmentInteractionListener");
        }*/
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
        public void onFragmentInteraction(Uri uri);
    }

    public void populate()
    {
        for(int i= 0 ; i<10 ; i++)
        {
           Message message = new Message() ;
            Member sender = new Member() ;
            Member reciver = new Member() ;

            sender.setName("senderName" + i);
            sender.setLast_Name("senderlast_Name" + i);
            reciver.setName("reciverName"+i);
            reciver.setLast_Name("reciverlast_Name"+i);
            message.setSender(sender);
            message.setReceiver(reciver);
            message.setMessage("this is my  message nb :"+i);
            messages.add(message) ;

           //  messages.add(new Event());

        }
    }



}
