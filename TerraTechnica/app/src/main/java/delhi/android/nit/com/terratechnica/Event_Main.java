package delhi.android.nit.com.terratechnica;


import android.content.Intent;
import android.graphics.Typeface;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.transition.Explode;
import android.transition.Fade;
import android.transition.Slide;
import android.transition.Transition;
import android.transition.TransitionInflater;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.squareup.picasso.Picasso;


/**
 * A simple {@link Fragment} subclass.
 */
public class Event_Main extends Fragment {


    private static final String ARG_PARAM1 = "param1";
    RecyclerView rvEvent;
    private int mParam1;


    public Event_Main() {
    }

    public static Event_Main newInstance(int param1) {

        Event_Main fragment = new Event_Main();
        Bundle args = new Bundle();
        args.putInt(ARG_PARAM1, param1);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            Explode explode = new Explode();
            explode.setDuration(500);
            explode.excludeTarget(android.R.id.navigationBarBackground, true);
            getActivity().getWindow().setEnterTransition(new Slide(Gravity.TOP));
            getActivity().getWindow().setExitTransition(new Fade());
            Transition transition1 = TransitionInflater.from(getActivity()).inflateTransition(R.transition.transition);
            transition1.excludeTarget(android.R.id.navigationBarBackground, true);
            getActivity().getWindow().setSharedElementExitTransition(transition1);
            getActivity().getWindow().setReenterTransition(new Fade());
        }

        if (getArguments() != null) {
            mParam1 = getArguments().getInt(ARG_PARAM1);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_event__main, container, false);

    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        rvEvent = (RecyclerView) view.findViewById(R.id.rvEvent);
        rvEvent.setLayoutManager(new GridLayoutManager(getContext(), 2));
        rvEvent.setAdapter(new Adater());

    }

    private class Adater extends RecyclerView.Adapter<Holder> {

        public Adater() {
        }

        @Override
        public Holder onCreateViewHolder(ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.custom_view1, parent, false);
            Holder holder = new Holder(view);
            return holder;
        }

        @Override
        public void onBindViewHolder(final Holder holder, int position) {
            if (mParam1 == 1) {
                holder.textView2.setText(Data.codingEvents[position]);
                Picasso.with(getContext())
                        .load(Data.codingPics[position])
                        .resize(500, 500)
                        .centerCrop()
                        .into(holder.imageView2);

            } else {
                Picasso.with(getContext())
                        .load(R.drawable.asd)
                        .resize(700, 700)
                        .into(holder.imageView2);
            }
            // ViewCompat.setTransitionName(holder.album_image,String.valueOf(position) + "_albumart");
        }

        @Override
        public int getItemCount() {
            if (mParam1 == 1)
                return 5;
            else
                return 5;
        }
    }


    private class Holder extends RecyclerView.ViewHolder {
        ImageView imageView2;
        TextView textView2;
        LinearLayout back;

        public Holder(final View itemView) {
            super(itemView);
            imageView2 = (ImageView) itemView.findViewById(R.id.imageView2);
            textView2 = (TextView) itemView.findViewById(R.id.textView2);
            Typeface custom_font = Typeface.createFromAsset(getContext().getAssets(), "fonts/JosefinSans-Regular.ttf");
            textView2.setTypeface(custom_font);
            back = (LinearLayout) itemView.findViewById(R.id.back);

            itemView.setOnClickListener(
                    new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                                Intent intent = new Intent(getActivity(), Event_Description.class);
                                intent.putExtra("position", rvEvent.getChildAdapterPosition(itemView));
                                intent.putExtra("type", "coding");
                                ActivityOptionsCompat activityOptionsCompat = ActivityOptionsCompat.makeSceneTransitionAnimation(getActivity(), imageView2, imageView2.getTransitionName());
                                startActivity(intent, activityOptionsCompat.toBundle());
                            } else {
                                Intent intent = new Intent(getActivity(), Event_Description.class);
                                intent.putExtra("position", rvEvent.getChildAdapterPosition(itemView));
                                intent.putExtra("type", "coding");
                                startActivity(intent);
                            }
                        }
                    }
            );
        }
    }

}
