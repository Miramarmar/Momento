package com.example.kyle.reminder;

import android.support.v4.content.ContextCompat;
import android.support.v7.widget.CardView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bignerdranch.android.multiselector.MultiSelector;
import com.bignerdranch.android.multiselector.SwappingHolder;

/**
 * Created by nygellopez on 2017-12-28.
 */

public class ReminderViewHolder extends SwappingHolder implements
                                                       View.OnClickListener,
                                                       View.OnLongClickListener {
  private TextView mTitle;
  private TextView mContent;
  private TextView mTime;
  private ImageView mIcon;
  private CardView mCardView;
  private int backColor;

  interface OnClickListener {
    void onClick(ReminderViewHolder holder);
  }

  interface OnLongClickListener {
    boolean onLongClick(ReminderViewHolder holder);
  }

  private OnClickListener mOnClickListener;
  private OnLongClickListener mOnLongClickListener;

  public ReminderViewHolder(View view, MultiSelector multiSelector) {
    super(view, multiSelector);
    view.setOnClickListener(this);
    view.setOnLongClickListener(this);

    mTitle = view.findViewById(R.id.title);
    mContent = view.findViewById(R.id.reminder);
    mTime = view.findViewById(R.id.timeLabel);
    mIcon = view.findViewById(R.id.icon);
    mCardView = view.findViewById(R.id.card_view);

    setSelectionModeBackgroundDrawable(null);
    setSelectionModeStateListAnimator(null);
  }

  public void setTitle(String title) {
    mTitle.setText(title);
  }

  public void setContent(String content) {
    mContent.setText(content);
  }

  public void setBackground(String color){
    if(color.equals("Events")){
      backColor = R.color.red;
      mCardView.setCardBackgroundColor(ContextCompat.getColor(itemView.getContext(),
              backColor ));
    }else if (color.equals("DS")){
      backColor = R.color.vio;
      mCardView.setCardBackgroundColor(ContextCompat.getColor(itemView.getContext(),
              backColor));
    }else {
      backColor = R.color.green;
      mCardView.setCardBackgroundColor(ContextCompat.getColor(itemView.getContext(),
              backColor));
    }
  }

  public void setTimeLabel(String timeLabel) {
    if (timeLabel != null) {
      mTime.setText(timeLabel);
      mTime.setVisibility(View.VISIBLE);
    } else {
      mTime.setVisibility(View.GONE);
    }
  }

  public void setIcon(int resId) {
    if (resId != 0) {
      mIcon.setImageResource(resId);
      mIcon.setVisibility(View.VISIBLE);
    } else {
      mIcon.setVisibility(View.GONE);
    }
  }

  public void setOnClickListener(OnClickListener l) {
    mOnClickListener = l;
  }

  public void setOnLongClickListener(OnLongClickListener l) {
    mOnLongClickListener = l;
  }
  @Override
  public boolean onLongClick(View view) {
    if (mOnLongClickListener != null) {
      return mOnLongClickListener.onLongClick(this);
    }
    return false;
  }

  @Override
  public void onClick(View view) {
    if (mOnClickListener != null) {
      mOnClickListener.onClick(this);
    }
  }

  public void setSelected(boolean selected) {
    if (selected) {
      mCardView.setCardBackgroundColor(ContextCompat.getColor(itemView.getContext(),
          R.color.white));
    } else {
      mCardView.setCardBackgroundColor(ContextCompat.getColor(itemView.getContext(),
          backColor));
    }
  }
}