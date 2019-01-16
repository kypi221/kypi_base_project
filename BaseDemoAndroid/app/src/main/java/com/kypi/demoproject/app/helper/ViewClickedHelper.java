package com.kypi.demoproject.app.helper;

import android.support.annotation.NonNull;
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
//
//        // Show Book
//        else if(FUNCTION.COMMON.SHOW_BOOK_DETAIL_BY_ID.equals(function)){
//            int bookId = (int) viewClicked.getTag(R.id.tag_object);
//            BookDetailActivity.showMe(activity, bookId);
//        }
//
//        // Show Author
//        else if (FUNCTION.COMMON.SHOW_AUTHOR_BY_ID.equals(function)){
//            int authorId = (int) viewClicked.getTag(R.id.tag_object);
//            AuthorDetailActivity.showMe(activity, authorId);
//        }
//
//        // Show Speaker
//        else if (FUNCTION.COMMON.SHOW_SPEAKER_BY_ID.equals(function)){
//            int speakerId = (int) viewClicked.getTag(R.id.tag_object);
//            SpeakerDetailActivity.showMe(activity, speakerId);
//        }
//
//        // Show Speaker
//        else if (FUNCTION.COMMON.ADD_BOOK_CASE.equals(function)){
//            IReadToast.showNotAvailableFeature(activity);
//        }

//        else if (ViewConstants.FUNCTION.SHOW_AUTHOR_BY_ID.equals(function)){
//            int authorId = (int) viewClicked.getTag(R.id.tag_object);
//            AuthorDetailActivity.showMe(activity, authorId);
//        }
//
//        // Show Tags
//        else if(ViewConstants.FUNCTION.SHOW_TAG.equals(function)){
//            BookTag tag = (BookTag) viewClicked.getTag(R.id.tag_object);
//            TagActivity.showMe(activity, tag.tagId, tag.tagName);
//        }
//
//        // Show User
//        else if(ViewConstants.FUNCTION.SHOW_USER_PROFILE.equals(function)){
//            int userId = (int) viewClicked.getTag(R.id.tag_object);
//            OtherUserWallPageActivity.showMe(activity, userId);
//        }
//
//        // Show Login
//        else if(ViewConstants.FUNCTION.SHOW_LOGIN.equals(function)){
//            LoginActivity.showMe(activity);
//        }
//
//        // Show Trang mua hội viên
//        else if(ViewConstants.FUNCTION.SHOW_BUY_MEMBER.equals(function)){
//            MyMemberPackageActivity.showMe(activity);
//        }
//
//        // Show Login
//        else if(ViewConstants.FUNCTION.SHOW_WEB_VIEW.equals(function)){
//            String link = (String) viewClicked.getTag(R.id.tag_object);
//            WebViewActivity.showMe(activity, link);
//        }
//        // Show Login
//        else if(ViewConstants.FUNCTION.SHOW_FANS_OF_AUTHOR.equals(function)){
//            AuthorInfo authorInfo = (AuthorInfo) viewClicked.getTag(R.id.tag_object);
//            FansOfAuthorActivity.showMe(activity, authorInfo);
//        }
//
//        // Show category cấp 1
//        else if (ViewConstants.FUNCTION.SHOW_CATEGORY_LEVEL_1.equals(function)) {
//            CategoryInfo category = (CategoryInfo) viewClicked.getTag(R.id.tag_object);
//            CategoryLevelOneDetailActivity.showMe(activity, category.catId);
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
