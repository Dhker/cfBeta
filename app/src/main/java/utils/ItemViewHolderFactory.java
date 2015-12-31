package utils;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.fide.ae.chessfamilybeta.R;

import model.Event;
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




    public abstract class  ItemViewHolder<E> extends RecyclerView.ViewHolder{

         public ItemViewHolder(View itemView) {
             super(itemView);
         }

         public abstract  void  introduce(E e) ;
     }

    public class EventViewHolder  extends ItemViewHolder<Event> {
          @Override
          public void introduce(Event event) {
            multipleContent.setText("this a new text via introcution");
          }

          public TextView multipleContent;
          public EventViewHolder(View itemView) {

              super(itemView) ;

              multipleContent = (TextView)itemView.findViewById(R.id.gameText);
          }
      }

    public class PublicationViewHolder  extends ItemViewHolder<MemberPublication> {
        public TextView multipleContent;
        public PublicationViewHolder(View itemView) {
            super(itemView);
            multipleContent = (TextView)itemView.findViewById(R.id.row_first_name_tv);

        }

        @Override
        public void introduce(MemberPublication memberPublication) {
            multipleContent.setText("this a new text via introcution");
        }
    }

    public class MessageViewHolder  extends ItemViewHolder<Message> {
        public TextView multipleContent;

        public MessageViewHolder(View itemView) {
            super(itemView);
            multipleContent = (TextView) itemView.findViewById(R.id.row_first_name_tv);

        }

        @Override
        public void introduce(Message message) {


        }
    }

    public class NotificationViewHolder  extends ItemViewHolder<Notification> {
        public TextView multipleContent;

        public NotificationViewHolder(View itemView) {
            super(itemView);
            multipleContent = (TextView) itemView.findViewById(R.id.row_first_name_tv);

        }

        @Override
        public void introduce(Notification notification) {

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

        return null ;

    }








}
