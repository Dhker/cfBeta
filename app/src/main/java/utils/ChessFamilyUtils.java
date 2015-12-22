package utils;

import android.content.Context;
import android.graphics.Color;
import android.graphics.LightingColorFilter;
import android.graphics.PorterDuff;
import android.media.Image;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

import com.fide.ae.chessfamilybeta.R;
import com.gitonway.lee.niftymodaldialogeffects.lib.Effectstype;
import com.gitonway.lee.niftymodaldialogeffects.lib.NiftyDialogBuilder;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by wassim on 21/12/15.
 */
public class ChessFamilyUtils {


    // verify email
   public static boolean isEmailValid(String email) {
        return android.util.Patterns.EMAIL_ADDRESS.matcher(email)
                .matches();
    }

    // create a nofity Dialog
   public  static void createNiftyDialog(String title , String message, Context context)
    {
        NiftyDialogBuilder.getInstance(context)
                .withMessage(message)
                .withTitle(title)
                .withDialogColor("#965a36")
                .withDuration(300)
                .withEffect(Effectstype.Shake)
                .show();


    }
    public static Date convertStringToDate (String date)throws Exception{
        SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
        return  formatter.parse(date) ;

    }


    //EFFECT WHEN BUTTON IS CLICKED

    public static void ClickEffect(ImageButton view)

    {
        view.setOnTouchListener(new View.OnTouchListener() {

            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switch (event.getAction()) {
                    case MotionEvent.ACTION_DOWN: {


                        v.setBackgroundColor(Color.GRAY);

                        break;
                    }
                    case MotionEvent.ACTION_UP:
                        // Your action here on button click
                    case MotionEvent.ACTION_CANCEL: {

                        v.setBackgroundColor(Color.TRANSPARENT);

                        break;
                    }
                }
                return true;
            }
        });
    }
}
