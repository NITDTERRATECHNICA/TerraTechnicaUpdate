package delhi.android.nit.com.terratechnica.About_Us;


import android.graphics.Typeface;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import delhi.android.nit.com.terratechnica.R;


/**
 * A simple {@link Fragment} subclass.
 */
public class About_Us extends Fragment {

    ImageView image_logo;
    TextView about_text;
    public About_Us() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_about__us, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        Typeface custom_font = Typeface.createFromAsset(getContext().getAssets(),  "fonts/JosefinSans-Regular.ttf");
        about_text = (TextView) view.findViewById(R.id.about_text);
        about_text.setTypeface(custom_font);
        image_logo = (ImageView) view.findViewById(R.id.image_logo);
        Uri uri = Uri.parse("android.resource://delhi.android.nit.com.terratechnica/drawable/logo2");
        Picasso.with(getContext())
                .load(uri)
                .into(image_logo);
    }

}
