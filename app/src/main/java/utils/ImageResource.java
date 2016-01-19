package utils;

import android.app.Activity;

import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v4.app.Fragment;
import android.support.v4.content.CursorLoader;
import android.util.Log;
import android.widget.ImageView;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * Created by Dhker on 1/17/2016.
 */
public class ImageResource {

 private static Fragment context ;
    private  static final int SELECT_FILE = 2016;
    private static final int REQUEST_CAMERA = 1888;
    private static ImageView IMAGE ;
    public static String ImagePATH=null;


    public static void init(Fragment _context,ImageView image)

    {
     context=_context ;

        IMAGE=image ;
    }

    public static void getImageFromGallery(boolean source) {
        if (source) {
            Intent intent = new Intent(
                    Intent.ACTION_PICK,
                    android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
            intent.setType("image/*");
            context.getActivity().startActivityForResult(
                    Intent.createChooser(intent, "Select File"),
                    SELECT_FILE);

        }
        else
        {
            Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
            context.startActivityForResult(intent, REQUEST_CAMERA);
        }
    }
    public static void handleResult(int requestCode, int resultCode, Intent data)
    {
        Log.d("TestOnreult", "Begin");
        if (resultCode == Activity.RESULT_OK) {
            Log.d("TestOnreult", "YESSSSSSSSSSSSSSSSSSSSSSSSSSSSSS");
            if (requestCode == REQUEST_CAMERA) {
                Bitmap thumbnail = (Bitmap) data.getExtras().get("data");
                ByteArrayOutputStream bytes = new ByteArrayOutputStream();
                thumbnail.compress(Bitmap.CompressFormat.JPEG, 90, bytes);
                File destination = new File(Environment.getExternalStorageDirectory(),
                        System.currentTimeMillis() + ".jpg");
                FileOutputStream fo;
                try {
                    destination.createNewFile();
                    fo = new FileOutputStream(destination);
                    fo.write(bytes.toByteArray());
                    fo.close();
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                IMAGE.setImageBitmap(thumbnail);
            } else if (requestCode == SELECT_FILE) {
                Log.d("TestOnreult", "selected file");
                Uri selectedImageUri = data.getData();
                String[] projection = { MediaStore.MediaColumns.DATA };
                CursorLoader cursorLoader = new CursorLoader(context.getContext(),selectedImageUri, projection, null, null,
                        null);
                Cursor cursor =cursorLoader.loadInBackground();
                int column_index = cursor.getColumnIndexOrThrow(MediaStore.MediaColumns.DATA);
                cursor.moveToFirst();
                String selectedImagePath = cursor.getString(column_index);
                Log.d("TestOnreult", selectedImagePath);
                Bitmap bm;
                BitmapFactory.Options options = new BitmapFactory.Options();
                options.inJustDecodeBounds = true;
                BitmapFactory.decodeFile(selectedImagePath, options);
                final int REQUIRED_SIZE = 200;
                int scale = 1;
                while (options.outWidth / scale / 2 >= REQUIRED_SIZE
                        && options.outHeight / scale / 2 >= REQUIRED_SIZE)
                    scale *= 2;
                options.inSampleSize = scale;
                options.inJustDecodeBounds = false;
                bm = BitmapFactory.decodeFile(selectedImagePath, options);
                IMAGE.setImageBitmap(bm);
                ImagePATH = selectedImagePath ;
            }
        }

}}
