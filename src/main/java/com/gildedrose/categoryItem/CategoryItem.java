package com.gildedrose.categoryItem;

import com.gildedrose.Item;

public class CategoryItem {

    protected void decrementSellIn(Item item) {
        item.sellIn = item.sellIn - 1;
    }

    protected void decrementQuality(Item item) {
        if (item.quality > 0) {
            item.quality = item.quality - 1;
        }
    }

    protected void incrementQuality(Item item) {
        if (item.quality < 50) {
            item.quality = item.quality + 1;
        }
    }

    protected void updateBeforePreemptionExpired(Item item) {
        decrementQuality(item);
    }

    protected void updateAfterPreemptionExpired(Item item) {
        decrementQuality(item);
    }

    public void update(Item item) {

        updateBeforePreemptionExpired(item);

        decrementSellIn(item);

        if (item.sellIn < 0) {
            updateAfterPreemptionExpired(item);
        }
    }
}
