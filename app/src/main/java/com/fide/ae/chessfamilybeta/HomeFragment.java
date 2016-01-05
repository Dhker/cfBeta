package com.fide.ae.chessfamilybeta;

import android.os.AsyncTask;
import android.os.Bundle;

import android.os.Handler;
import android.support.v4.app.Fragment;
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
import model.Event;
import model.MeetingPlace;
import model.Member;
import model.MemberPublication;
import repository.MemberPublicationRepository;
import repository.MemberPublicationRepositoryImpl;
import utils.AsyncTaskResult;
import utils.CustomRcyclerView;

import utils.EndlessRecyclerOnScrollListener;
import utils.ItemAdapter;
import utils.PublicationAdapter;


public class HomeFragment extends Fragment implements Paginate.Callbacks {

    RecyclerView recyclerView;

    List  publications = new ArrayList<>();

    LinearLayoutManager layoutManager ;
    private Member  member ;
    private View RootView;
    private ListView  publicationList  ;
    private ItemAdapter adapter;
    private boolean loading = true;
    private Paginate paginate;

   //  pagination parameters
    private int nbpage = 1;
    private int itemsPerPage = 5;


    protected boolean  finish = false ;


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

      //  populate();
        recyclerView = (RecyclerView)RootView.findViewById(R.id.list);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setHasFixedSize(true);

        //loadPublications(page);

       /* loadPublications(page);
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
                    });*/

        adapter = new ItemAdapter(getContext() ,publications) ;
        recyclerView.setAdapter(adapter);

        loading =false ;

          if (paginate != null) {
                    paginate.unbind();


                }

       paginate = Paginate.with(recyclerView, this)
                .setLoadingTriggerThreshold(2)
                .addLoadingListItem(true )
                .build();





return RootView ;

    }







    public  void loadPublications(final int page )
    {


        new AsyncTask<Integer  ,Integer , AsyncTaskResult<ArrayList<MemberPublication>>>(){

            @Override
            protected void onPostExecute(AsyncTaskResult<ArrayList<MemberPublication>> result) {

               if (result.getError() == null) {
                    ArrayList publications = result.getResult();

                    if (publications != null)

                    {
                         ; Log.d("length", "" + publications.size()) ;
                        adapter.add(publications);
                        adapter.notifyDataSetChanged();

                            nbpage++ ;
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


                        ArrayList publications = publicationRepository.GetPublicationsByIDMember("4", itemsPerPage, page);

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

/*
    public void populate()
    {
        page++;
        Log.d("mypage" ,""+page) ;
         for(int i= 0 ; i<10 ; i++)
         {
             publications.add(new MemberPublication()) ;

         }
        if(page==3)
        {
            finish=true ;
            loading =false;
            paginate.setHasMoreDataToLoad(false);
        }
        adapter.notifyDataSetChanged();

    }
*/
    @Override
    public void onLoadMore() {

       // loading = true;
        loadPublications(nbpage);
    }

    @Override
    public boolean isLoading() {
        return loading;
    }

    @Override
    public boolean hasLoadedAllItems() {
        return finish;
    }
}



