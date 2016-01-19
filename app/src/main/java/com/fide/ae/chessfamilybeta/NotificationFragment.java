package com.fide.ae.chessfamilybeta;

import android.os.Bundle;
import android.support.annotation.Nullable;
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

import model.Message;
import model.Notification;
import utils.ItemAdapter;

/**
 * Created by wassim on 30/12/15.
 */
public class NotificationFragment extends Fragment {


    private RecyclerView recyclerView;
    private View RootView ;
    private ItemAdapter adapter ;
    private  List notifications = new ArrayList<>();
    private MaterialRefreshLayout materialRefreshLayout;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        this.RootView = inflater.inflate(R.layout.fragment_notification, container, false);


        populate() ;
        recyclerView = (RecyclerView)RootView.findViewById(R.id.notification_list);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setHasFixedSize(true);
        adapter = new ItemAdapter(getContext() ,notifications) ;
        recyclerView.setAdapter(adapter);

        materialRefreshLayout = (MaterialRefreshLayout) RootView.findViewById(R.id.refresh);
        materialRefreshLayout.setMaterialRefreshListener(new MaterialRefreshListener() {
            @Override
            public void onRefresh(final MaterialRefreshLayout materialRefreshLayout) {
                //refreshing...
            }

            @Override
            public void onRefreshLoadMore(MaterialRefreshLayout materialRefreshLayout) {
                //load more refreshing...
            }

        });

        return RootView ;
    }


    public void populate()
    {
        for(int i= 0 ; i<10 ; i++)
        {
            notifications.add(new Notification()) ;

            //  messages.add(new Event());

        }
    }

}
