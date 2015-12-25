package com.fide.ae.chessfamilybeta;

import android.app.Activity;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.app.Fragment;
import android.os.Handler;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import com.paginate.Paginate;
import com.paginate.abslistview.LoadingListItemCreator;

import java.util.ArrayList;
import java.util.Date;

import model.MemberPublication;
import utils.AsyncTaskResult;
import utils.PublicationAdaptar;


public class HomeFragment extends Fragment implements
        Paginate.Callbacks,
        AbsListView.OnScrollListener,
        AdapterView.OnItemClickListener,
        AdapterView.OnItemLongClickListener {



    private View RootView;
    private ListView  publicationList  ;
    private PublicationAdaptar adapter;
    private boolean loading = false;

    private Handler handler;
    private Paginate paginate;

   //  pagination parameters
    private int page = 2;
    private int itemsPerPage = 10;

    protected int threshold = 4;
    protected boolean  finish = false ;

    protected long networkDelay = 5000;


    public HomeFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        handler = new Handler();

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        this.RootView = inflater.inflate(R.layout.home_layout, container, false);
        publicationList =(ListView) RootView.findViewById(R.id.list) ;

        setupPagination() ;
// Create the adapter to convert the array to views
       // PublicationAdaptar adapter = new PublicationAdaptar (getActivity(), arrayOfUsers);
// Attach the adapter to a ListView

        return RootView;

    }




    protected void setupPagination() {
        if (paginate != null) {
            paginate.unbind();
        }

        ArrayList<MemberPublication> publications = new ArrayList<MemberPublication>();
        handler.removeCallbacks(fakeCallback);
        adapter = new PublicationAdaptar(getActivity(), publications);
        loading = false;
        page = 0;


        AbsListView absListView = (AbsListView) RootView.findViewById(R.id.list);
        if ((absListView instanceof ListView)) {
            ListView listView = (ListView) absListView;
            listView.addHeaderView(LayoutInflater.from(getActivity()).inflate(R.layout.list_view_header, absListView, false));
         //   listView.addFooterView(LayoutInflater.from(getActivity()).inflate(R.layout.list_view_footer, absListView, false));
        }

        absListView.setAdapter(adapter);
        absListView.setOnItemClickListener(this);
        absListView.setOnItemLongClickListener(this);

        paginate = Paginate.with(absListView, this)
                .setOnScrollListener(this)
                .setLoadingTriggerThreshold(threshold)
                .addLoadingListItem(true)
               // .setLoadingListItemCreator( new CustomLoadingListItemCreator() )
                .build();
    }




    public  void loadPublications(int page )
    {


        new AsyncTask<Integer  ,Integer , AsyncTaskResult<ArrayList<MemberPublication>>>(){

            @Override
            protected void onPostExecute(AsyncTaskResult<ArrayList<MemberPublication>> result) {

                if (result.getError() == null)
                {
                    ArrayList<MemberPublication> publications =result.getResult()  ;

                    if(publications!= null )
                        adapter.addAll(publications);
                    else
                    {

                        loading = false  ;
                        finish =true ;
                    }
                }else
                {
                    loading = false  ;
                    finish =true ;

                }

            }

            @Override
            protected AsyncTaskResult<ArrayList<MemberPublication>> doInBackground(Integer... params) {

              int page  = params[0] ;

                AsyncTaskResult<ArrayList<MemberPublication>> result =null ;
                if(page!=0)
                {

                    try {
                        //  make the call to the web service
                        //  throw new Exception() ;

                      //  result= new AsyncTaskResult<ArrayList<MemberPublication>>(null) ;

                        return result;
                    } catch (Exception e) {
                        Log.d("error", e.toString())    ;
                        e.printStackTrace();
                        result = new  AsyncTaskResult<ArrayList<MemberPublication>>(e) ;
                        return result ;
                    }
                }

                return null;
            }
        }.execute(page);
    }


    @Override
    public void onLoadMore() {
        Log.d("Paginate", "onLoadMore");
        //handler.postDelayed(fakeCallback, networkDelay);
        loadPublications(this.page) ;

    }

    @Override
    public boolean isLoading() {
        return loading;
    }

    @Override
    public boolean hasLoadedAllItems() {
        return finish;
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

    }

    @Override
    public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
        return false;
    }

    @Override
    public void onScrollStateChanged(AbsListView view, int scrollState) {

    }

    @Override
    public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {

    }

    private Runnable fakeCallback = new Runnable() {
        @Override
        public void run() {
            page++;
            for( int i=0  ;i<5 ; i++)
            {
                MemberPublication pub  = new MemberPublication() ;
                pub.setText("pub"+i);
                pub.setTime(new Date());
                adapter.add(pub);
            }

            loading = false;
        }
    };

}
