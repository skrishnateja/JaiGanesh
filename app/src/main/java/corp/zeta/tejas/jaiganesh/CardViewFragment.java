package corp.zeta.tejas.jaiganesh;


import android.graphics.Rect;
import android.media.Image;
import android.os.Bundle;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;

import java.util.ArrayList;
import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 */
public class CardViewFragment extends Fragment {


    private View parent_view;

    private RecyclerView recyclerView;
    private AdapterGridTwoLine mAdapter;

    public CardViewFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        parent_view = inflater.inflate(R.layout.fragment_card_view, container, false);

        initComponent();
        return parent_view;
    }

    private void initComponent() {
        recyclerView = (RecyclerView) parent_view.findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new GridLayoutManager(getActivity(), 2));
        recyclerView.addItemDecoration(new SpacingItemDecoration(2, Tools.dpToPx(getActivity(), 3), true));
        recyclerView.setHasFixedSize(true);

        List<Item> items = new ArrayList<Item>();
        Item i = new Item();
        i.brief = "Size: 6ft";
        i.name = "Eco Ganesh";
        i.counter = 1;
        items.add(i);
        items.add(i);
        items.add(i);
        items.add(i);
        items.add(i);
        items.add(i);
        items.add(i);
        items.add(i);
        items.add(i);
        items.add(i);
        items.add(i);

        //set data and list adapter
        mAdapter = new AdapterGridTwoLine(getActivity(), items);
        recyclerView.setAdapter(mAdapter);

        // on item list clicked
        mAdapter.setOnItemClickListener(new AdapterGridTwoLine.OnItemClickListener() {
            @Override
            public void onItemClick(View view, Item obj, int position) {
                Snackbar s = Snackbar.make(parent_view, obj.name + " clicked", Snackbar.LENGTH_SHORT);
                ((CoordinatorLayout.LayoutParams)s.getView().getLayoutParams()).gravity = Gravity.TOP;
                s.show();
            }
        });
    }


    /**
     * RecyclerView item decoration - give equal margin around grid item
     */
    public class SpacingItemDecoration extends RecyclerView.ItemDecoration {

        private int spanCount;
        private int spacingPx;
        private boolean includeEdge;

        public SpacingItemDecoration(int spanCount, int spacingPx, boolean includeEdge) {
            this.spanCount = spanCount;
            this.spacingPx = spacingPx;
            this.includeEdge = includeEdge;
        }

        @Override
        public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
            int position = parent.getChildAdapterPosition(view); // item position
            int column = position % spanCount; // item column

            if (includeEdge) {
                outRect.left = spacingPx - column * spacingPx / spanCount;
                outRect.right = (column + 1) * spacingPx / spanCount;

                if (position < spanCount) { // top edge
                    outRect.top = spacingPx;
                }
                outRect.bottom = spacingPx; // item bottom
            } else {
                outRect.left = column * spacingPx / spanCount;
                outRect.right = spacingPx - (column + 1) * spacingPx / spanCount;
                if (position >= spanCount) {
                    outRect.top = spacingPx; // item top
                }
            }
        }
    }
}
