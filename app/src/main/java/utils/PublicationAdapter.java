package utils;

import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.fide.ae.chessfamilybeta.R;
import com.squareup.picasso.Picasso;

import java.util.List;

import co.dift.ui.SwipeToAction;
import model.Member;
import model.MemberPublication;
import repository.MemberRepository;
import repository.MemberRepositoryImpl;

/**
 * Created by wassim on 23/12/15.
 */
public class PublicationAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private List<MemberPublication> publications;
    private View view ;
    MemberRepository memberRepository = new MemberRepositoryImpl() ;
    Member member ;

    /** References to the views for each data item **/
    public class PublicationViewHolder extends SwipeToAction.ViewHolder<MemberPublication> {
        public TextView titleView;
        public TextView authorView;
        public ImageView imageView;

        public PublicationViewHolder(View v) {
            super(v);
            view =v ;
            titleView = (TextView) v.findViewById(R.id.title);
            authorView = (TextView) v.findViewById(R.id.author1);
            imageView = (ImageView) v.findViewById(R.id.image);
        }
    }

    /** Constructor **/
    public PublicationAdapter(List<MemberPublication> items) {
        this.publications = items;
    }

    @Override
    public int getItemViewType(int position) {
        return 0;
    }

    @Override
    public int getItemCount() {
        return publications.size();
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.publication_item, parent, false);

        return new PublicationViewHolder(view);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        MemberPublication item = publications.get(position);
        PublicationViewHolder vh = (PublicationViewHolder) holder;
        vh.titleView.setText(item.getText());
        vh.authorView.setText("" + item.getId()) ;
      member = item.getMember() ;
     if(member!= null)
        {

            Log.d("loading" ,"4") ;
            if(member.getPhoto()!= null) {
                Picasso.with(view.getContext())
                        .load(member.getPhoto())
                        .into(vh.imageView);
            }
        }
        vh.data= item ;
    }

    public void add(List<MemberPublication> items) {
        int previousDataSize = this .publications.size();
        this.publications.addAll(items);
        notifyItemRangeInserted(previousDataSize, items.size());
    }

    }
