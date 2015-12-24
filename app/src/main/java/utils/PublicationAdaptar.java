package utils;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.fide.ae.chessfamilybeta.R;

import java.util.ArrayList;

import model.Member;
import model.MemberPublication;

/**
 * Created by wassim on 23/12/15.
 */
public class PublicationAdaptar extends ArrayAdapter<MemberPublication> {


    public PublicationAdaptar(Context context, ArrayList<MemberPublication> publications) {

        super(context,0,  publications);
    }


    private static class ViewHolder {
        TextView   publicationText ;
        TextView   date ;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        MemberPublication publication = getItem(position ) ;



        ViewHolder viewHolder; // view lookup cache stored in tag
        if (convertView == null) {
            viewHolder = new ViewHolder();
            LayoutInflater inflater = LayoutInflater.from(getContext());
            convertView = inflater.inflate(R.layout.publication_item, parent, false);
            viewHolder.publicationText =(TextView) convertView.findViewById(R.id.short_publication_text) ;
            viewHolder.date = (TextView) convertView.findViewById(R.id.pubication_date) ;
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
         viewHolder.publicationText .setText(publication.getText());
         viewHolder.publicationText .setText(publication.getText());
        //date.setText(publication.getTime().getDate());
     //   time.setText(""+publication.getTime().getTime());
        return convertView ;
    }

}
