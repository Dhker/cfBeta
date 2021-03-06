package utils;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.fide.ae.chessfamilybeta.R;

import java.util.List;

import model.Event;
import model.MeetingPlace;
import model.Member;
import model.MemberPublication;
import model.Message;
import model.Notification;
import utils.ItemViewHolderFactory.* ;
import static utils.ItemViewHolderFactory.*;

/**
 * Created by wassim on 31/12/15.
 */
public class ItemAdapter extends RecyclerView.Adapter<ItemViewHolder> {



    private ItemViewHolderFactory itemViewHolderFactory ;

    private LayoutInflater inflater = null;
    private List listItems ;


     public void add(Object item)
     {
                listItems.add(item) ;
     }
    public void add(List items)
    {
        listItems.addAll(items) ;
    }


    public ItemAdapter(Context context, List<Object> listItems){
        this.listItems =  listItems ;
        inflater = LayoutInflater.from(context);
        itemViewHolderFactory = new ItemViewHolderFactory() ;
    }


    @Override
    public ItemViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = null;

       if (viewType ==event_viewHolder)
            view = inflater.inflate(R.layout.event_item, parent, false);
        else if (viewType ==publication_viewHolder)
            view = inflater.inflate(R.layout.publication_item, parent, false);
       else if (viewType ==message_viewHolder)
           view = inflater.inflate(R.layout.message_item, parent, false);
       else if (viewType ==notification_viewHolder)
           view = inflater.inflate(R.layout.notfication_item, parent, false);
       else if(viewType==member_viewHodler)
           view=inflater.inflate(R.layout.member_item, parent, false) ;
       else if(viewType==meeting_place_viewHolder)
           view=inflater.inflate(R.layout.item_location, parent, false) ;

        return itemViewHolderFactory.getViewHolderByType(viewType, view) ;
    }



    @Override
    public void onBindViewHolder(ItemViewHolder holder, int position) {

        Object item = listItems.get(position) ;


        if(holder instanceof EventViewHolder)
        {
            EventViewHolder eventViewHolder = (EventViewHolder) holder ;
            Event event =(Event) item ;
            eventViewHolder.introduce(event);

        }

        if(holder instanceof PublicationViewHolder)
        {
            PublicationViewHolder publicationViewHolder = (PublicationViewHolder) holder ;
            MemberPublication publication = (MemberPublication) item ;
            publicationViewHolder.introduce(publication);

        }
        if(holder instanceof MessageViewHolder)
        {
            MessageViewHolder messageViewHolder = (MessageViewHolder) holder ;
            Message message = (Message) item ;
            messageViewHolder.introduce(message);
        }

        if(holder instanceof NotificationViewHolder)
        {
            NotificationViewHolder notificationViewHolder = (NotificationViewHolder) holder ;
            Notification notification = (Notification) item ;
            notificationViewHolder.introduce(notification);
        }
        if(holder instanceof  MemberViewHolder)
        {
            MemberViewHolder memberViewHodler = (MemberViewHolder) holder ;
            Member member = (Member) item ;
            memberViewHodler.introduce(member);
        }
        if(holder instanceof  MeetingPlaceViewHolder)
        {
            MeetingPlaceViewHolder meetingPlaceViewHolder = (MeetingPlaceViewHolder) holder ;
            MeetingPlace meetingPlace = (MeetingPlace) item ;
            meetingPlaceViewHolder.introduce(meetingPlace);
        }


    }

    @Override
    public int getItemCount() {
        return (listItems != null && listItems.size() > 0 ) ? listItems.size() : 0;
    }

    @Override
    public int getItemViewType(int position) {

        Object  item = listItems.get(position);

        if (item != null)
        {
            if(item instanceof Event)
            {return event_viewHolder ;}
            if(item instanceof MemberPublication)
            {return publication_viewHolder;}
            if(item instanceof Message)
            {return message_viewHolder;}
            if(item instanceof Notification)
            {return notification_viewHolder;}
            if(item instanceof Member)
            {return member_viewHodler;}
            if(item instanceof MeetingPlace)
            {return meeting_place_viewHolder;}


        }

        return super.getItemViewType(position);
    }




}
