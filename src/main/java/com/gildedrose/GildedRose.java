package com.gildedrose;
import com.gildedrose.categoryItem.*;

class GildedRose {
    Item[] items;

    public GildedRose(Item[] items) {
        this.items = items;
    }

    public void updateQuality() {

        CategoryItem categoryItem;

        for (Item item : items) {

            if (item.name.equals("Aged Brie")){
                categoryItem = new AgedBrieItem();
            }
            else if (item.name.equals("Sulfuras, Hand of Ragnaros")){
                categoryItem = new SulfurasItem();
            }
            else if (item.name.equals("Backstage passes to a TAFKAL80ETC concert")){
                categoryItem = new BackstagePassesItem();
            }
            else if (item.name.equals("Conjured Mana Cake")){
                categoryItem = new ConjuredItem();
            }
            else  categoryItem = new CategoryItem();

            categoryItem.update(item);
        }
    }
}
