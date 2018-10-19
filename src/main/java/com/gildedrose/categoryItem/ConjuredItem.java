package com.gildedrose.categoryItem;

import com.gildedrose.Item;

public class ConjuredItem extends CategoryItem {

    protected void updateBeforePreemptionExpired(Item item) {
        decrementQuality(item);
        decrementQuality(item);
    }

    protected void updateAfterPreemptionExpired(Item item) {
        decrementQuality(item);
        decrementQuality(item);
    }

}
