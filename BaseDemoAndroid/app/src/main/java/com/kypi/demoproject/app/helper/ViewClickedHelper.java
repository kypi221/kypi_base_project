package com.kypi.demoproject.app.helper;

import androidx.annotation.NonNull;
import android.view.View;

import com.kypi.demoproject.R;
import com.kypi.demoproject.base.BaseActivity;

/**
 * Created by Khoa on 8/21/2017.
 */

public class ViewClickedHelper {

    public interface FUNCTION{
        interface COMMON{
            String SHOW_BOOK_DETAIL_BY_ID = "SHOW_BOOK_DETAIL_BY_ID";
        }

        public interface PLAYER {
            public String PLAY_PAUSE = "PLAYER_PLAY_PAUSE";
        }

        public interface SEARCH {
            public String DO_SEARCH_FROM_HISTORY = "DO_SEARCH_FROM_HISTORY";
        }
    }


    /**
     * handle action
     * @param activity
     * @param viewClicked
     */
    public static final void handleViewClicked(@NonNull BaseActivity activity, @NonNull View viewClicked){
        String function = (String) viewClicked.getTag(R.id.tag_function);

        // Show category
//        if (FUNCTION.COMMON.SHOW_CATEGORY_BY_ID.equalsIgnoreCase(function)){
//            int cateId = (int) viewClicked.getTag(R.id.tag_object);
//            CategoryDetailActivity.showMe(activity, cateId);
//        }


    }

    public static void setClickForView(View viewClicked, Object object,
                                       View.OnClickListener listener, String function) {
        // Set sự kiện clicked
        setClickForView(viewClicked, object, listener, function, -1, null);
    }

    public static void setClickForView(View viewClicked, Object object,
                                       View.OnClickListener listener, String function,
                                       int position, View viewTarget) {
        // Set sự kiện clicked
        viewClicked.setTag(R.id.tag_object, object);
        viewClicked.setTag(R.id.tag_function, function);
        viewClicked.setTag(R.id.tag_position, position);
        viewClicked.setTag(R.id.tag_view, viewTarget);
        viewClicked.setOnClickListener(listener);
    }
}
