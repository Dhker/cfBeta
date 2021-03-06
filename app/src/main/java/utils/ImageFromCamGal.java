package utils;

import android.app.AlertDialog;
import android.content.CursorLoader;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import de.hdodenhof.circleimageview.CircleImageView;
import model.Member;
import repository.MemberRepository;
import repository.MemberRepositoryImpl;

/**
 * Created by Dhker on 12/26/2015.
 */
public class ImageFromCamGal {

    private AppCompatActivity activity;
    public static final int REQUEST_CAMERA = 1888;
    public static final int SELECT_FILE = 2016;
    private CircleImageView ivImage;
    public String selectedImagePath ;
    private MemberRepository memberRepository = new MemberRepositoryImpl();
    Member member ;

    public String getSelectedImagePath() {
        return selectedImagePath;
    }

    public ImageFromCamGal(AppCompatActivity activity, CircleImageView ivImage , Member member) {
        this.member=member ;
        this.activity = activity;
        this.ivImage = ivImage;
    }

    public void selectImage() {
        final CharSequence[] items = {"Take Photo", "Choose from Library", "Cancel"};
        AlertDialog.Builder builder = new AlertDialog.Builder(this.activity);
        builder.setTitle("Add Photo!");
        builder.setItems(items, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int item) {
                if (items[item].equals("Take Photo")) {
                    Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                    activity.startActivityForResult(intent, REQUEST_CAMERA);
                } else if (items[item].equals("Choose from Library")) {
                    Intent intent = new Intent(
                            Intent.ACTION_PICK,
                            android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                    intent.setType("image/*");
                    activity.startActivityForResult(
                            Intent.createChooser(intent, "Select File"),
                            SELECT_FILE);
                } else if (items[item].equals("Cancel")) {
                    dialog.dismiss();
                }
            }
        });
        builder.show();
    }

    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        Log.d("EditActivity", "NotPassed") ;
        if (resultCode == AppCompatActivity.RESULT_OK) {
            Log.d("EditActivity", "NotPassed") ;
            if (requestCode == REQUEST_CAMERA) {
                Log.d("EditActivity", "Passed") ;
                Bitmap thumbnail = (Bitmap) data.getExtras().get("data");
                ByteArrayOutputStream bytes = new ByteArrayOutputStream();
                thumbnail.compress(Bitmap.CompressFormat.JPEG, 90, bytes);
                final File destination = new File(Environment.getExternalStorageDirectory(),
                        System.currentTimeMillis() + ".jpg");
                FileOutputStream fo;
                new AsyncTask<Integer, Integer, Boolean>()
                {


                    @Override
                    protected Boolean doInBackground(Integer... params) {

                        try {

                            if(member!=null) {
                                memberRepository.addPhotoToMember("" + member.getID(), destination);
                                return true;
                            }else
                            {
                                return  false ;
                            }
                        } catch (Exception e)
                        {
                            e.printStackTrace();
                            return false;
                        }
                    }
                }.execute() ;


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
                ivImage.setImageBitmap(thumbnail);
            } else if (requestCode == SELECT_FILE) {
                Uri selectedImageUri = data.getData();
                String[] projection = {MediaStore.MediaColumns.DATA};

                CursorLoader cursorLoader = new CursorLoader(activity, selectedImageUri, projection, null, null,
                        null);
                Cursor cursor = cursorLoader.loadInBackground();
                int column_index = cursor.getColumnIndexOrThrow(MediaStore.MediaColumns.DATA);
                cursor.moveToFirst();
                 selectedImagePath = cursor.getString(column_index);
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
                ivImage.setImageBitmap(bm);
                new AsyncTask<Integer, Integer, Boolean>()
                {


                    @Override
                    protected Boolean doInBackground(Integer... params) {

                        try {

                            if(member!=null) {
                                memberRepository.addPhotoToMember(String.valueOf(member.getID()), new File(selectedImagePath));
                                return true;
                            }else
                            {
                                return  false ;
                            }
                        } catch (Exception e) {
                            e.printStackTrace();
                            return false;
                        }
                    }
                }.execute() ;


            }
        }


    }
}