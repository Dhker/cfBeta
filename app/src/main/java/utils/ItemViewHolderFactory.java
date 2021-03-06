package utils;

import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.fide.ae.chessfamilybeta.EventActivity;
import com.fide.ae.chessfamilybeta.MeetingPlaceActivity;
import com.fide.ae.chessfamilybeta.MemberProfileActivity;
import com.fide.ae.chessfamilybeta.MessageActivity;
import com.fide.ae.chessfamilybeta.R;
import com.squareup.picasso.Picasso;


import de.hdodenhof.circleimageview.CircleImageView;
import model.Event;
import model.MeetingPlace;
import model.Member;
import model.MemberPublication;
import model.Message;
import model.Notification;


/**
 * Created by wassim on 31/12/15.
 */
public class  ItemViewHolderFactory
{
    public static final  int event_viewHolder =0 ;
    public static final  int publication_viewHolder =1 ;
    public static final  int message_viewHolder =2 ;
    public static final  int notification_viewHolder =3 ;
    public static final  int member_viewHodler = 4 ;
    public static final  int meeting_place_viewHolder=5 ;




    public abstract class  ItemViewHolder<E> extends RecyclerView.ViewHolder {


        protected  View itemView ;
         public ItemViewHolder(View itemView) {
             super(itemView);
             this.itemView = itemView ;
         }

         public abstract  void  introduce(E e) ;
     }

    public class EventViewHolder  extends ItemViewHolder<Event> implements View.OnClickListener{

        private FrameLayout  base;
        public TextView multipleContent;
          @Override
          public void introduce(Event event) {
              multipleContent.setText("this a new text via introcution");
              base =(FrameLayout) itemView.findViewById(R.id.base_element);
              base.setOnClickListener(this) ;
          }


          public EventViewHolder(View itemView) {

              super(itemView) ;

              multipleContent = (TextView)itemView.findViewById(R.id.gameText);
          }

        @Override
        public void onClick(View v) {
            if(v.equals(base)) {
                if(true) {
                    Intent intent = new Intent(itemView.getContext(), EventActivity.class);
                    itemView.getContext().startActivity(intent);
                }
            }
        }
      }

    public class PublicationViewHolder  extends ItemViewHolder<MemberPublication> implements View.OnClickListener {
        public TextView publicationContent;
        public TextView memberName ;
        public TextView publicationDate;
        public ImageView  userImage ;
        public PublicationViewHolder(final View itemView) {
            super(itemView);
            publicationContent = (TextView)itemView.findViewById(R.id.pub_content);
            memberName =(TextView)itemView.findViewById(R.id.sender_name);
            publicationDate =(TextView)itemView.findViewById(R.id.pub_date);
            userImage=(ImageView)itemView.findViewById(R.id.sender_image) ;

        }

        @Override
        public void introduce(MemberPublication memberPublication) {
           if(memberPublication!=null)
           {
             Member member =   memberPublication.getMember();
                if(member!=null )
                {
                    Picasso.with(itemView.getContext())
                            .load(member.getPhoto())
                            .into(userImage);

                    String name = member.getName()+" "+member.getLast_Name() ;
                    memberName.setText(name);
                }
               publicationContent.setText(""+memberPublication.getId());



           }
        }

        @Override
        public void onClick(View v) {

        }
    }

    public class MessageViewHolder  extends ItemViewHolder<Message> implements View.OnClickListener {
        public TextView messageContent ;
        public CircleImageView senderImage ;
        public TextView senderName;
        public LinearLayout base ;

        private Member sender ;
        private Member reciver ;


        public MessageViewHolder(final View itemView) {
            super(itemView);

            messageContent = (TextView) itemView.findViewById(R.id.message_object);
            senderName = (TextView) itemView.findViewById(R.id.sender_name);
            senderImage = (CircleImageView) itemView.findViewById(R.id.sender_image);
            base =(LinearLayout) itemView.findViewById(R.id.base_element);
            base.setOnClickListener(this) ;
        }


        @Override
        public void introduce(Message message) {

            if(message!= null) {


                sender = message.getSender();
                reciver = message.getReceiver();

                if(message.getMessage()!= null)
                {
                    messageContent.setText(message.getMessage());
                }
                if(sender != null)
                {

                    Picasso.with(itemView.getContext())
                            .load(sender.getPhoto())
                            .into(senderImage);
                    senderName.setText(sender.getName()+" "+sender.getLast_Name());

                }


            }





        }

        @Override
        public void onClick(View v) {
           if(v.equals(base)) {
               if((sender!=null)&&(reciver!=null)) {
                   Intent intent = new Intent(itemView.getContext(), MessageActivity.class);
                    intent.putExtra("sender",sender) ;
                    intent.putExtra("reciver",reciver) ;
                   itemView.getContext().startActivity(intent);
               }
           }
        }
    }

    public class NotificationViewHolder  extends ItemViewHolder<Notification> implements View.OnClickListener {
        public TextView multipleContent;

        public NotificationViewHolder(View itemView) {
            super(itemView);
            //multipleContent = (TextView) itemView.findViewById(R.id.row_first_name_tv);

        }

        @Override
        public void introduce(Notification notification) {

        }


        @Override
        public void onClick(View v) {

        }
    }

    public class MemberViewHolder extends ItemViewHolder<Member> implements View.OnClickListener {

        public CircleImageView memberImage ;
        public TextView memberName;
        private LinearLayout  base;
        private  Member member ;
        private View itemView ;

        public MemberViewHolder(View itemView) {
            super(itemView);
           this. itemView = itemView ;
            memberImage =(CircleImageView) itemView.findViewById(R.id.profile_image);
            memberName =(TextView) itemView.findViewById(R.id.member_name) ;
            base =(LinearLayout) itemView.findViewById(R.id.base_element) ;
            base.setOnClickListener(this);
        }

        @Override
        public void introduce(Member member) {

            this.member = member ;
            if(member!=null)
            {
                Picasso.with(itemView.getContext())
                        .load(member.getPhoto())
                        .into(memberImage);
                String name = member.getName()+" "+member.getLast_Name();
                memberName.setText(name);
            }
        }

        @Override
        public void onClick(View v) {
            if(v.equals(base)) {
                if(member!=null){
                    Intent intent = new Intent(itemView.getContext(), MemberProfileActivity.class);
                    intent.putExtra("member",member) ;
                    itemView.getContext().startActivity(intent);
                }
            }
        }
    }

    public class MeetingPlaceViewHolder extends ItemViewHolder<MeetingPlace> implements View.OnClickListener{

        LinearLayout base ;
        public MeetingPlaceViewHolder(View itemView) {
            super(itemView);
            //base =(LinearLayout) itemView.findViewById(R.id.meeting_base) ;
            base.setOnClickListener(this);
        }

        @Override
        public void introduce(MeetingPlace MeetingPlace) {

        }

        @Override
        public void onClick(View v) {
            if(v.equals(base))
            {
                Intent intent = new Intent(itemView.getContext(), MeetingPlaceActivity.class);
                itemView.getContext().startActivity(intent);
            }

        }
    }





    public  ItemViewHolder  getViewHolderByType(int type , View itemView)
    {
            if(type==event_viewHolder)
                return  new EventViewHolder(itemView) ;
            if(type==publication_viewHolder)
                return  new PublicationViewHolder(itemView) ;
            if(type==message_viewHolder)
                return  new MessageViewHolder(itemView) ;
              if(type==notification_viewHolder)
                return  new NotificationViewHolder(itemView) ;
            if(type==member_viewHodler)
                return new MemberViewHolder(itemView) ;
            if(type==meeting_place_viewHolder)
            return new MeetingPlaceViewHolder(itemView) ;
        return null ;

    }








}
