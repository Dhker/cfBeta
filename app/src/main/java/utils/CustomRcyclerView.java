package utils;

import android.app.Activity;
import android.os.Handler;
import android.support.v7.widget.RecyclerView;

import com.paginate.Paginate;

import co.dift.ui.SwipeToAction;

/**
 * Created by wassim on 26/12/15.
 */
public class CustomRcyclerView {


    private static RecyclerView recyclerView;
    SwipeToAction swipeToAction;

    private static Paginate.Callbacks callbacks ;
    private Paginate paginate ;


    protected static int threshold = 4;
    protected static boolean addLoadingRow = true;

    public CustomRcyclerView(RecyclerView recyclerView, SwipeToAction.SwipeListener swipeListener, Paginate.Callbacks callbacks, Paginate paginate) {
        this.callbacks =callbacks ;
        this.recyclerView =recyclerView;
        swipeToAction = new SwipeToAction(recyclerView, swipeListener) ;
        //this.paginate  = paginate ;

    }

    public  void setupPagination() {
        // If RecyclerView was recently bound, unbind

        if (paginate != null) {
            paginate.unbind();
        }

        paginate = Paginate.with(recyclerView, callbacks)
                .addLoadingListItem(false)
               .build() ;


    }

}
