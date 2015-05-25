package com.example.resourceaccessapp.fragments;

import android.content.ContentResolver;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;

import com.example.resourceaccessapp.CustomListAdapter;
import com.example.resourceaccessapp.R;
import com.gc.materialdesign.views.ButtonRectangle;

import java.io.FileNotFoundException;
import java.util.ArrayList;

/**
 * Created by Simon on 25/05/2015.
 */
public class PhotosFragment extends Fragment {
    static final int PICK_PHOTO_REQUEST = 1;
    private ImageView targetImage;

    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState){
        View v = inflater.inflate(R.layout.fragment_photos, container, false);

        ButtonRectangle selectImageButton = (ButtonRectangle) v.findViewById(R.id.selectImageButton);
        this.targetImage = (ImageView) v.findViewById(R.id.imageView);
        this.targetImage.setVisibility(View.GONE);

        selectImageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_PICK,
                        android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                startActivityForResult(intent, PICK_PHOTO_REQUEST);
            }
        });

        return v;
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == PICK_PHOTO_REQUEST) {
            if (resultCode == getActivity().RESULT_OK) {
                Uri targetUri = data.getData();
                Toast.makeText(getActivity(), "" + targetUri, Toast.LENGTH_LONG).show();
                Bitmap bitmap;
                try {
                    bitmap = BitmapFactory.decodeStream(getActivity().getContentResolver().openInputStream(targetUri));
                    targetImage.setImageBitmap(bitmap);
                    this.targetImage.setVisibility(View.VISIBLE);
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
