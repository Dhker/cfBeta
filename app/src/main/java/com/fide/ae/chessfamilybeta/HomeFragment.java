package com.fide.ae.chessfamilybeta;

import android.os.AsyncTask;
import android.os.Bundle;
import android.app.Fragment;
import android.os.Handler;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.RecyclerView.OnScrollListener;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.paginate.Paginate;

import java.util.ArrayList;
import java.util.List;

import co.dift.ui.SwipeToAction;
import model.Member;
import model.MemberPublication;
import repository.MemberPublicationRepository;
import repository.MemberPublicationRepositoryImpl;
import utils.AsyncTaskResult;
import utils.CustomRcyclerView;

import utils.EndlessRecyclerOnScrollListener;
import utils.PublicationAdapter;


public class HomeFragment extends Fragment implements SwipeToAction.SwipeListener<MemberPublication> {

    RecyclerView recyclerView;

    CustomRcyclerView customRcyclerView ;
    List<MemberPublication> publications = new ArrayList<>();

    LinearLayoutManager layoutManager ;
    private Member  member ;
    private View RootView;
    private ListView  publicationList  ;
    private PublicationAdapter adapter;
    private boolean loading = true;
    private SwipeToAction swipeToAction;

    private Paginate paginate;

   //  pagination parameters
    private int page = 1;
    private int itemsPerPage = 2;


    protected boolean  finish = false ;

    protected long networkDelay = 5000;

    private MemberPublicationRepository  publicationRepository =  new MemberPublicationRepositoryImpl()  ;


    public HomeFragment() {
        // Required empty public constructor

    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

      /*  Bundle bundle= this.getActivity().getIntent().getExtras();
        member =(Member) bundle.get("member");*/


    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        this.RootView = inflater.inflate(R.layout.home_layout, container, false);


        recyclerView = (RecyclerView)RootView.findViewById(R.id.list);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setHasFixedSize(true);

        loadPublications(page);
        recyclerView.addOnScrollListener(new EndlessRecyclerOnScrollListener(layoutManager) {
            @Override
            public void onLoadMore(int current_page) {
                // do something..
                // .

                if(!finish) {
                    Log.d("currentPage", "" + current_page);

                    //  populate();
                    loadPublications(current_page);
                    //  adapter.notifyDataSetChanged();
                }
            }
        });
        adapter = new PublicationAdapter(this.publications);

        recyclerView.setAdapter(adapter);

        swipeToAction = new SwipeToAction(recyclerView , this) ;




return RootView ;

    }








    public  void loadPublications(int page )
    {


        new AsyncTask<Integer  ,Integer , AsyncTaskResult<ArrayList<MemberPublication>>>(){

            @Override
            protected void onPostExecute(AsyncTaskResult<ArrayList<MemberPublication>> result) {

               if (result.getError() == null) {
                    ArrayList<MemberPublication> publications = result.getResult();

                    if (publications != null)

                    {
                         ; Log.d("length", "" + publications.size()) ;
                        adapter.add(publications);
                      //  adapter.notifyDataSetChanged();

                        finish=false ;

                    }
                    else
                    {
                        finish =true ;
                        Log.d("finish",""+true) ;
                    }
                }else
                {

                    finish = true  ;
                    Log.d("finish",""+true) ;


                }

            }

            @Override
            protected AsyncTaskResult<ArrayList<MemberPublication>> doInBackground(Integer... params) {

                int page = params[0];

                AsyncTaskResult<ArrayList<MemberPublication>> result = null;


                    try {
                        //  make the call to the web service
                        //  throw new Exception() ;
                        Log.d("page", ""+page) ;


                        ArrayList<MemberPublication> publications = publicationRepository.GetFeeds("4", itemsPerPage, page);

                        result = new AsyncTaskResult<ArrayList<MemberPublication>>(publications);

                        return result;
                    } catch (Exception e) {
                        Log.d("error", e.toString());
                        e.printStackTrace();
                        result = new AsyncTaskResult<ArrayList<MemberPublication>>(e);
                        return result;
                    }


            }
        }.execute(page);
    }



    private int removePublication(MemberPublication publication)
    {
        int pos = publications.indexOf(publication);
        publications.remove(publication);
        adapter.notifyItemRemoved(pos);
        return pos;
    }


    @Override
    public boolean swipeLeft(final MemberPublication itemData) {
        final int pos = removePublication(itemData);

        Log.d("swipeLeft", "" + true) ;
        return true;
    }

    @Override
    public boolean swipeRight(MemberPublication itemData) {
        //  displaySnackbar(itemData.getTitle() + " loved", null, null);
        Log.d("swipeRight",""+true) ;
        return true;
    }

    @Override
    public void onClick(MemberPublication itemData) {
        //  displaySnackbar(itemData.getText() + " clicked", null, null);
    }

    @Override
    public void onLongClick(MemberPublication itemData) {
        //  displaySnackbar(itemData.getText() + " long clicked", null, null);
    }



}

