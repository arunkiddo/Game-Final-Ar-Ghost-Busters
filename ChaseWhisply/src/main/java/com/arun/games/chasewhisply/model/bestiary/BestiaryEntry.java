package com.arun.games.chasewhisply.model.bestiary;

import com.arun.games.chasewhisply.model.TargetableItem;

public class BestiaryEntry {
    private int mTitleResourceId;
    private TargetableItem mTargetableItem;
    private int mImageResourceId;


    public void setTargetableItem(TargetableItem targetableItem) {
        mTargetableItem = targetableItem;
    }

    public String getHealth() {
        return String.valueOf(mTargetableItem.getHealth());
    }

    public String getExpValue() {
        return String.valueOf(mTargetableItem.getExpPoint());
    }

    public String getPointValue() {
        return String.valueOf(mTargetableItem.getBasePoint());
    }

    public int getImageResourceId() {
        return mImageResourceId;
    }

    public void setImageResourceId(int resourceId) {
        mImageResourceId = resourceId;
    }

    public int getTitleResourceId() {
        return mTitleResourceId;
    }

    public void setTitleResourceId(int resourceId) {
        mTitleResourceId = resourceId;
    }
}
