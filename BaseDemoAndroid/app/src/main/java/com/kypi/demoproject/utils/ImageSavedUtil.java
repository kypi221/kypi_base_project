package com.kypi.demoproject.utils;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.media.MediaScannerConnection;
import android.os.Environment;
import androidx.core.app.ActivityCompat;
import android.view.ViewGroup;
import android.widget.Toast;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

public class ImageSavedUtil {



    /**
     * Step 4
     *
     * The enterGalleryFlow method is responsible for managing the sequence of events
     * that need to occur before a user can navigate to the Galley. This method:
     *
     * (1) Checks that the user has granted the app `READ_EXTERNAL_STORAGE` permissions
     * (2) If app HASN'T been granted permission, request `READ_EXTERNAL_STORAGE` permission
     * (3) If app HAS been granted permission, open gallery : )
     */
    private void enterGalleryFlow(Activity context, String[] GALLERY_PERMISSION, int REQUEST_CODE_GALLERY, int REQUEST_CODE_CHOOSE_PHOTO ) {
        boolean storageReadPermissionGranted = ActivityCompat.checkSelfPermission(context, Manifest.permission.READ_EXTERNAL_STORAGE) ==
                PackageManager.PERMISSION_GRANTED;
        if (storageReadPermissionGranted == false) {
            ActivityCompat.requestPermissions(context, GALLERY_PERMISSION, REQUEST_CODE_GALLERY);
        } else {
            openGallery(context, REQUEST_CODE_CHOOSE_PHOTO);
        }
    }


    /**
     * Step 4
     *
     * The openGallery() method will:
     * (1) Creates an Intent named `galleryIntent` to access the camera
     * (3) Uses `galleryIntent` to start the camera.
     */
    private void openGallery(Activity context, int REQUEST_CODE_CHOOSE_PHOTO ) {
        Intent galleryIntent = new Intent(Intent.ACTION_GET_CONTENT);

        // ***I think this line will
        // Show only images, no videos or anything else
        galleryIntent.setType("image/*");

        // Always show the chooser (if there are multiple options available)
        galleryIntent = Intent.createChooser(galleryIntent, "Select Picture");

        // Start Activity
        context.startActivityForResult(galleryIntent, REQUEST_CODE_CHOOSE_PHOTO);
    }








    /**
     * Step 5
     *
     * The enterSaveMemeFlow method is responsible for managing the sequence of events
     * that need to occur before a user can save a photo. This method:
     *
     * (1) Checking that the user has granted the app `WRITE_EXTERNAL_STORAGE` permissions
     * (2) If app HASN'T been granted permission, request `WRITE_EXTERNAL_STORAGE` permission
     * (3) If app HAS been granted permission, save meme : )
     */
    public static void enterSaveMemeFlow(ViewGroup layoutRoot, Activity context, String[] SAVE_PERMISSION, int REQUEST_CODE_SAVE ) {
        boolean storageWritePermissionGranted = ActivityCompat.checkSelfPermission(context, Manifest.permission.WRITE_EXTERNAL_STORAGE) ==
                PackageManager.PERMISSION_GRANTED;
        if (!storageWritePermissionGranted) {
            ActivityCompat.requestPermissions(context, SAVE_PERMISSION, REQUEST_CODE_SAVE);
        } else {
            saveMeme(layoutRoot, context);
        }
    }



    /**
     * Step 5
     *
     * The saveMeme() will take the image we see on the thumbnail
     * in the form of a Bitmap (a type of file that stores an image)
     * and create a file for the image in the "memes" folder.
     */
    private static void saveMeme(ViewGroup layoutRoot, Context context) {
        layoutRoot.setDrawingCacheEnabled(true);
        layoutRoot.buildDrawingCache();
        Bitmap full = layoutRoot.getDrawingCache();
        if (Environment.getExternalStorageState().equalsIgnoreCase("mounted")) {
            File imageFolder = new File(Environment.getExternalStorageDirectory(), "Kypi");
            imageFolder.mkdirs();
            FileOutputStream out = null;
            File imageFile = new File(imageFolder, String.valueOf(System.currentTimeMillis()) + ".png");
            try {
                out = new FileOutputStream(imageFile);
                full.compress(Bitmap.CompressFormat.PNG, 100, out);
                out.flush();
                out.close();
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                out = null;
                MediaScannerConnection.scanFile(context, new String[]{imageFile.getAbsolutePath()}, null, null);
            }
        }
        layoutRoot.destroyDrawingCache();
        layoutRoot.setDrawingCacheEnabled(false);
        Toast.makeText(context, "Saved to Kypi folder!", Toast.LENGTH_LONG).show();
    }
}
