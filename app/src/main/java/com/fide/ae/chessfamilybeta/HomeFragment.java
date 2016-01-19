package com.fide.ae.chessfamilybeta;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.Image;
import android.os.AsyncTask;
import android.os.Bundle;

import android.os.Handler;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import android.text.Html;
import android.text.method.LinkMovementMethod;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.GridLayout;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TableLayout;
import android.widget.Toast;


import com.cjj.MaterialRefreshLayout;
import com.cjj.MaterialRefreshListener;
import com.dd.processbutton.iml.ActionProcessButton;
import com.paginate.Paginate;
import com.squareup.picasso.Picasso;

import java.net.MalformedURLException;
import java.util.ArrayList;
import java.util.List;


import model.Member;
import model.MemberPublication;
import model.Photo;
import repository.MemberPublicationRepository;
import repository.MemberPublicationRepositoryImpl;
import utils.AsyncTaskResult;
import utils.ChessFamilyUtils;



import utils.ImageResource;
import utils.ItemAdapter;

import utils.ServiceCalls;
import utils.SessionSotrage;


public class HomeFragment extends Fragment implements Paginate.Callbacks,View.OnClickListener {

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
    private MaterialRefreshLayout materialRefreshLayout;
    private ImageButton addPhotoButton;
    private ImageButton addLink;
    private EditText link;
    private Button addbtn;
    private ArrayList<String> publicationPhotos = new ArrayList<String>() ;;
    private ImageButton addNewPhoto;
    private ImageButton finishAddPhoto;
    private GridLayout addedPhototable;
    private ImageView photopicked;
    private ImageButton clearPhotoButton;
    private View addPhotoDialog;


    public HomeFragment() {
        // Required empty public constructor

    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);





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



this.statusHeader = (View)RootView.findViewById(R.id.status_header);
        this.setupHeader();
return RootView ;

    }

    private View statusHeader ;
    private ImageView memberphoto ;
    private EditText saysomthing ;
    private MemberPublication publication ;
    private ActionProcessButton publish ;

    public void setupHeader()
    {

        this.memberphoto = (ImageView)statusHeader.findViewById(R.id.profile_image);

        if(SessionSotrage.CurrentSessionMember!=null)


        Picasso.with(this.getActivity()).load(SessionSotrage.CurrentSessionMember.getPhoto()).into(memberphoto);

        this.saysomthing =(EditText)statusHeader.findViewById(R.id.status_text) ;
        this.addPhotoButton = (ImageButton)statusHeader.findViewById(R.id.add_photo);
        this.addLink = (ImageButton)statusHeader.findViewById(R.id.add_link);

         publish = (ActionProcessButton) statusHeader.findViewById(R.id.status_publish);
        this.publish.setOnClickListener(this);
        publish.setMode(ActionProcessButton.Mode.ENDLESS);
this.publication=new MemberPublication();

        this.addLink.setOnClickListener(this);
        this.addPhotoButton.setOnClickListener(this);
        this.saysomthing.setOnClickListener(this);



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


    @Override
    public void onClick(View v) {


        if(v.equals(this.saysomthing))
        {
            this.saysomthing.setError(null);

            this.publish.setProgress(0);
        }
         if (v.equals(this.publish))
        {

            this.publication.setMember(SessionSotrage.CurrentSessionMember);
            if(!this.saysomthing.getText().toString().isEmpty()){
            this.publication.setText(this.saysomthing.getText().toString());
            this.savePublication();}
            else
            saysomthing.setError("Say Somthing");




        }




        if(v.equals(this.addLink))
        {
          View  view = this.getActivity().getLayoutInflater().inflate(R.layout.dialog_add_link, null);
             link =(EditText)view.findViewById(R.id.link_text);
             addbtn=(Button)view.findViewById(R.id.button_add_link);
            this.addbtn.setOnClickListener(this);
            ChessFamilyUtils.EasyDialog(view, this.getContext(), this.addLink);


        }
        if(v.equals(this.addbtn))
        {
            try {
                saysomthing.setError(null);
                java.net.URL link = new java.net.URL(this.link.getText().toString());

                saysomthing.setText(Html.fromHtml(

                                "<a href=\""+link.toString()+"\">"+link.toString() + "</a> "));
                saysomthing.setMovementMethod(LinkMovementMethod.getInstance());
                ChessFamilyUtils.hideEasyDialog();
                this.publication.setLink(link.toString());

            } catch (MalformedURLException e) {
                this.link.setError("Enter a valid link");
            }

        }

        if(v.equals(this.addPhotoButton))
        {
              addPhotoDialog = this.getActivity().getLayoutInflater().inflate(R.layout.dialog_add_photos, null);
             addedPhototable = (GridLayout)addPhotoDialog.findViewById(R.id.cotainer);
             addNewPhoto = (ImageButton)addPhotoDialog.findViewById(R.id.add_new_photo);
            clearPhotoButton=(ImageButton)addPhotoDialog.findViewById(R.id.clear_photo);
            clearPhotoButton.setVisibility(View.INVISIBLE);
            clearPhotoButton.setOnClickListener(this);
            this.addNewPhoto.setOnClickListener(this);
             finishAddPhoto =(ImageButton)addPhotoDialog.findViewById(R.id.finish_add_photo);

            this.finishAddPhoto.setOnClickListener(this);
            if(this.publicationPhotos.size()!=0) {
                clearPhotoButton.setVisibility(View.VISIBLE);
                for (int i = 0; i < this.publicationPhotos.size(); i++) {
                    ImageView image = new ImageView(this.getContext());
                    image.setLayoutParams(new ViewGroup.LayoutParams(
                            80,
                            80));
                    Bitmap bm;
                    BitmapFactory.Options options = new BitmapFactory.Options();
                    options.inJustDecodeBounds = true;
                    BitmapFactory.decodeFile(this.publicationPhotos.get(i), options);
                    final int REQUIRED_SIZE = 200;
                    int scale = 1;
                    while (options.outWidth / scale / 2 >= REQUIRED_SIZE
                            && options.outHeight / scale / 2 >= REQUIRED_SIZE)
                        scale *= 2;
                    options.inSampleSize = scale;
                    options.inJustDecodeBounds = false;
                    bm = BitmapFactory.decodeFile(this.publicationPhotos.get(i), options);
                    image.setImageBitmap(bm);
                  //  Picasso.with(this.getContext()).load(this.publicationPhotos.get(i)).into(image);
                    Log.d("imagesArray", this.publicationPhotos.get(i));
                    this.addedPhototable.addView(image);

                }


            }
            ChessFamilyUtils.EasyDialog(addPhotoDialog,this.getActivity(),this.addPhotoButton);

        }
        if(v.equals(this.finishAddPhoto))
        {

            ChessFamilyUtils.hideEasyDialog();
        }
        if(v.equals(this.addNewPhoto))
        {
            this.addPhoto(this.addedPhototable);

        }
        if(v.equals(this.clearPhotoButton))
        {
            this.publicationPhotos.clear();
            ((GridLayout) addPhotoDialog.findViewById(R.id.cotainer)).removeAllViews();

            this.clearPhotoButton.setVisibility(View.INVISIBLE);

        }




    }

    private void addPhoto(GridLayout layout)
    {

            ImageView photo = new ImageView(this.getContext());
            photo.setLayoutParams(new ViewGroup.LayoutParams(
                    100,
                    100));
            layout.addView(photo);


            ImageResource.init(this, photo);
            ImageResource.getImageFromGallery(true);
if(ImageResource.ImagePATH!=null)
{this.publicationPhotos.add(ImageResource.ImagePATH);

            Log.d("path", ImageResource.ImagePATH);}
        }
    
     

    private void savePublication() {

this.publication= ServiceCalls.addPublication(this.publication,this.publish);

        if(this.publication!=null) {
            this.savePublicationPhotos();
            Toast.makeText(this.getContext(), getResources().getString(R.string.published), Toast.LENGTH_LONG);
        saysomthing.setText("");
        }
    }

    private void savePublicationPhotos()
    {
        if(this.publicationPhotos.size()!=0)
        {
            for(int i=0;i<this.publicationPhotos.size();i++)
            {
                Photo photo = new Photo() ;
                photo.setPhoto(this.publicationPhotos.get(i));
                photo.setpublication(this.publication);
                ServiceCalls.addPhotoToPublication(photo,this.publish);

            }
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        Log.d("Activity","Fragment");
        ImageResource.handleResult(requestCode,resultCode,data);
    }
}



