package com.deekea.articlenewscardui.ui.adapter;

import android.animation.Animator;
import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.deekea.articlenewscardui.LocalDisplay;
import com.deekea.articlenewscardui.R;
import com.deekea.articlenewscardui.model.NewsModel;

import java.util.List;

/**
 * Created by JHong on 2015/5/12.
 */
public class NewsAdapter extends RecyclerView.Adapter {
    private List<NewsModel> mNewsList;
    private Context mContext;

    public NewsAdapter(Context mContext, List mNewsList) {
        this.mContext = mContext;
        this.mNewsList = mNewsList;

    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_article_news, viewGroup, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder viewHolder, int i) {
        NewsModel newsModel = mNewsList.get(i);
//        viewHolder.mImage.;
//        viewHolder.mLable.setText(p.name);

    }

    @Override
    public int getItemCount() {
        return mNewsList == null ? 0 : mNewsList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public ImageView mImage;
        public TextView mLabel;
        public TextView mMonth;
        public TextView mDay;
        public TextView mTitle;
        public TextView mSummary;
        public TextView mShortContent;
        public TextView mApproveNum;
        public TextView mCommentNum;
        public View mResMark;

        private RelativeLayout mContent;

        private int mShortContentHeight;

        public ViewHolder(View v) {
            super(v);
            mImage = (ImageView) v.findViewById(R.id.image_news);
            mLabel = (TextView) v.findViewById(R.id.res_news_label);
            mMonth = (TextView) v.findViewById(R.id.date_month);
            mDay = (TextView) v.findViewById(R.id.date_day);
            mTitle = (TextView) v.findViewById(R.id.title_news);
            mSummary = (TextView) v.findViewById(R.id.summary_news);
            mShortContent = (TextView) v.findViewById(R.id.short_content_news);
            mApproveNum = (TextView) v.findViewById(R.id.approve_num_news);
            mCommentNum = (TextView) v.findViewById(R.id.comments_news);
            mResMark = v.findViewById(R.id.res_mark);

            mContent = (RelativeLayout) v.findViewById(R.id.content_news_layout);

            mShortContentHeight = mShortContent.getLineHeight() * 3 + LocalDisplay.dp2px(15);

            mContent.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(mShortContent.getVisibility() == View.VISIBLE) {
                        int move = mShortContentHeight;
                        mImage.setScaleX(1.3f);
                        mImage.setScaleY(1.3f);
                        mLabel.setTranslationY(-move);
                        mLabel.animate().setDuration(0).translationY(0);
                        mImage.animate().setDuration(100).scaleX(1f).scaleY(1f).start();
                        mShortContent.setVisibility(View.GONE);
                        mResMark.setAlpha(0.0f);

                        FrameLayout.LayoutParams params = new FrameLayout.LayoutParams(FrameLayout.LayoutParams.MATCH_PARENT, -move + mContent.getHeight());
                        params.setMargins(0, mImage.getHeight(), 0, 0);
                        mContent.setLayoutParams(params);
                    } else {
                        mShortContent.setVisibility(View.VISIBLE);
                        int move = mShortContentHeight;
                        mImage.setScaleX(1f);
                        mImage.setScaleY(1f);
                        mLabel.setTranslationY(0);
                        mLabel.animate().setDuration(0).translationY(-move);
                        mImage.animate().setDuration(100).scaleX(1.3f).scaleY(1.3f);
                        mResMark.setAlpha(0.7f);

                        FrameLayout.LayoutParams params = new FrameLayout.LayoutParams(FrameLayout.LayoutParams.MATCH_PARENT, move + mContent.getHeight());
                        params.setMargins(0, mImage.getHeight() - move, 0, 0);
                        mContent.setLayoutParams(params);

                        mShortContent.setScaleY(0.5f);
                        mShortContent.animate().setDuration(100).scaleY(1f).start();
                    }
                }
            });
        }
    }
}
