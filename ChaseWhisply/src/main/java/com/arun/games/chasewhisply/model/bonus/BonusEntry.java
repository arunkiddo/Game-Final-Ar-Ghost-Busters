package com.arun.games.chasewhisply.model.bonus;

import com.arun.games.chasewhisply.model.inventory.InventoryItemInformation;
import com.arun.games.chasewhisply.model.inventory.InventoryItemInformationFactory;

public class BonusEntry {
    private long mQuantity;
    private int mEffectResourceId;
    private InventoryItemInformation mInventoryItemInformation;
    private boolean mIsEquipped = false;
    private Bonus mBonus;

    public BonusEntry(int inventoryItemType, long quantity) {
        mInventoryItemInformation = InventoryItemInformationFactory.create(inventoryItemType);
        mQuantity = quantity;
    }

    public void setIsEquipped(boolean isEquipped) {
        mIsEquipped = isEquipped;
    }

    public boolean isEquipped() {
        return mIsEquipped;
    }

    public long getQuantity() {
        return mQuantity;
    }

    public int getTitleResourceId() {
        return mInventoryItemInformation.getTitleResourceId();
    }

    public void setTitleResourceId(int resourceId) {
        mInventoryItemInformation.setTitleResourceId(resourceId);
    }

    public int getEffectResourceId() {
        return mEffectResourceId;
    }

    public void setEffectResourceId(int resourceId) {
        mEffectResourceId = resourceId;
    }

    public int getInventoryItemType() {
        return mInventoryItemInformation.getType();
    }

    public Bonus getBonus() {
        return mBonus;
    }

    public void setBonus(Bonus bonus) {
        mBonus = bonus;
    }

    public int getImageResourceId() {
        return mInventoryItemInformation.getImageResourceId();
    }
}
