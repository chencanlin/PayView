package com.ccl.perfectisshit.payview.adapter;

import android.graphics.drawable.Drawable;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.ccl.perfectisshit.payview.R;
import com.ccl.perfectisshit.payview.bean.KeyboardItemBean;
import com.ccl.perfectisshit.payview.widget.KeyboardView;

import java.util.List;

/**
 * Created by ccl on 2017/9/18.
 */

public class KeyboardAdapter extends RecyclerView.Adapter<KeyboardAdapter.ViewHolder> implements View.OnClickListener {

    private final List<KeyboardItemBean> mData;
    private LayoutInflater inflater;
    private KeyboardView.OnInputListener mListener;

    public KeyboardAdapter(List<KeyboardItemBean> data) {
        mData = data;
    }

    @Override
    public int getItemViewType(int position) {
        return mData.get(position).getTag();
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (inflater == null) {
            inflater = LayoutInflater.from(parent.getContext());
        }
        int layoutId;
        if (viewType == KeyboardItemBean.KeyboardTag.TAG_DELETE) {
            layoutId = R.layout.layout_keyboard_delete;
        } else {
            layoutId = R.layout.layout_keyboard_normal;
        }
        View inflate = inflater.inflate(layoutId, parent, false);
        inflate.setTag(viewType);
        return new ViewHolder(inflate);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        KeyboardItemBean keyboardItemBean = mData.get(position);
        if (keyboardItemBean != null) {
            int tag = keyboardItemBean.getTag();
            if (tag == KeyboardItemBean.KeyboardTag.TAG_DELETE) {
                holder.mRlDelete.setOnClickListener(this);
                holder.mRlDelete.setTag(tag);
            } else {
                holder.mTvKeypad.setOnClickListener(this);
                holder.mTvKeypad.setTag(tag);
                holder.mTvKeypad.setText(keyboardItemBean.getTitle());
                if (tag == KeyboardItemBean.KeyboardTag.TAG_BLANK) {
                    holder.mTvKeypad.setBackground(null);
                } else {
                    Drawable drawable = holder.mTvKeypad.getContext().getResources().getDrawable(R.drawable.selector_keyboard_bg);
                    holder.mTvKeypad.setBackground(drawable);
                }
            }
        }
    }

    @Override
    public int getItemCount() {
        return mData == null ? 0 : mData.size();
    }

    @Override
    public void onClick(View v) {
        if (mListener != null) {
            mListener.onInput(v);
        }
    }

    static class ViewHolder extends RecyclerView.ViewHolder {

        private RelativeLayout mRlDelete;
        private TextView mTvKeypad;

        ViewHolder(View itemView) {
            super(itemView);
            Object tagObj = itemView.getTag();
            if (tagObj instanceof Integer) {
                Integer intTag = (Integer) tagObj;
                if (intTag == KeyboardItemBean.KeyboardTag.TAG_DELETE) {
                    mRlDelete = ((RelativeLayout) itemView.findViewById(R.id.rl_delete));
                } else {
                    mTvKeypad = ((TextView) itemView.findViewById(R.id.tv_keypad));
                }
            }
        }
    }

    public void setOnInputListener(KeyboardView.OnInputListener listener) {
        mListener = listener;
    }
}
