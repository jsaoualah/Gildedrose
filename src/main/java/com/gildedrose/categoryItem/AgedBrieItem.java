package com.gildedrose.categoryItem;

import com.gildedrose.Item;

public class AgedBrieItem extends CategoryItem {

    protected void updateAfterPreemptionExpired(Item item) {
        incrementQuality(item);
    }

    protected void updateBeforePreemptionExpired(Item item) {
        incrementQuality(item);
    }
}
