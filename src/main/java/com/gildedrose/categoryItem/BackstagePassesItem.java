package com.gildedrose.categoryItem;

import com.gildedrose.Item;

public class BackstagePassesItem extends CategoryItem {

    protected void updateAfterPreemptionExpired(Item item) {
        item.quality = 0;
    }

    protected void updateBeforePreemptionExpired(Item item) {
        incrementQuality(item);

        if (item.sellIn < 11) {
            incrementQuality(item);
        }

        if (item.sellIn < 6) {
            incrementQuality(item);
        }

    }

}
