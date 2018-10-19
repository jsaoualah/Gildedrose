package com.gildedrose;

import static org.junit.Assert.*;

import org.junit.Test;

public class GildedRoseTest {

    @Test
    public void foo() {
        Item[] items = new Item[] { new Item("foo", 0, 0) };
        GildedRose app = new GildedRose(items);
        app.updateQuality();
        assertEquals("foo", app.items[0].name);

    }

    @Test
    public void should_quality_stay_positif_for_all_products(){
        Item[] items = new Item[] {
                new Item("+5 Dexterity Vest", 6, 20), //
                new Item("Elixir of the Mongoose", 5, 7),
                new Item("Sulfuras, Hand of Ragnaros", 0, 80),
                new Item("Backstage passes to a TAFKAL80ETC concert", 25, 19),
                new Item("Aged Brie",14,20),
                new Item("Conjured Mana Cake",14,20)};
        GildedRose gildedRose = new GildedRose(items);
        int days = 30;
        for (int i = 0; i < days; i++) {
            gildedRose.updateQuality();
        }
        assertEquals(gildedRose.items[0].quality,0);
        assertEquals(gildedRose.items[1].quality,0);
        assertEquals(gildedRose.items[2].quality,80);
        assertEquals(gildedRose.items[3].quality,0);
        assertEquals(gildedRose.items[4].quality,50);
        assertEquals(gildedRose.items[5].quality,0);
    }

    @Test
    public void should_diminue_value_and_quality_every_day_avant_preemption_for_normal_product(){

        Item[] items = new Item[]{new Item("+5 Dexterity Vest", 10, 20),
                new Item("+5 Dexterity Vest", 7, 13)};
        GildedRose gildedRose = new GildedRose(items);
        int days = 4;
        for (int i = 0; i < days; i++) {
            gildedRose.updateQuality();
        }
        assertEquals(gildedRose.items[0].sellIn,6);
        assertEquals(gildedRose.items[0].quality,16);
        assertEquals(gildedRose.items[1].sellIn,3);
        assertEquals(gildedRose.items[1].quality,9);
    }

    @Test
    public void should_diminue_quality_deux_fois_rapidement_lorsque_preemption_passée_for_normal_product(){

        Item[] items = new Item[]{new Item("+5 Dexterity Vest", 5, 30),
                new Item("+5 Dexterity Vest", 3, 40)};
        GildedRose gildedRose = new GildedRose(items);
        int days = 7;
        for (int i = 0; i < days; i++) {
            gildedRose.updateQuality();
        }
        assertEquals(gildedRose.items[0].quality,21);
        assertEquals(gildedRose.items[1].quality,29);
    }

    @Test
    public void should_augmente_quality_aged_brie_avec_le_le_temps() {

        Item[] items = new Item[]{new Item("Aged Brie", 14, 20)};
        GildedRose gildedRose = new GildedRose(items);
        int days = 22;
        for (int i = 0; i < days; i++) {
            System.out.println("-------- day " + i + " --------");
            System.out.println("name, sellIn, quality");
            System.out.println(gildedRose.items[0].toString());
            gildedRose.updateQuality();
            System.out.println(gildedRose.items[0].toString());
        }
        assertEquals(gildedRose.items[0].quality, 50);
    }

    @Test
    public void should_quality_limit_50_except_sulfuras(){

        Item[] items = new Item[] {
                new Item("+5 Dexterity Vest", 30, 46),
                new Item("Aged Brie", 20, 47), //
                new Item("Elixir of the Mongoose", 20, 48),
                new Item("Backstage passes to a TAFKAL80ETC concert", 25, 48)};
        GildedRose gildedRose = new GildedRose(items);
        int days = 4;
        for (int i = 0; i < days; i++) {
            System.out.println("-------- day " + i + " --------");
            System.out.println("-------- before update --------");
            System.out.println("name, sellIn, quality");
            for (Item item : items) {
                System.out.println(item.toString());
            }
            gildedRose.updateQuality();
            System.out.println("-------- after update --------");
            for (Item item : items) {
                System.out.println(item.toString());
            }
        }
        assertEquals(gildedRose.items[0].quality,42);
        assertEquals(gildedRose.items[1].quality,50);
        assertEquals(gildedRose.items[2].quality,44);
        assertEquals(gildedRose.items[3].quality,50);
    }

    @Test
    public void should_sulfuras_stay_constant(){

        Item[] items = new Item[]{new Item("Sulfuras, Hand of Ragnaros", 0, 80)};
        GildedRose gildedRose = new GildedRose(items);
        int days = 20;
        for (int i = 0; i < days; i++) {
            System.out.println("-------- day " + i + " --------");
            System.out.println("name, sellIn, quality");
            System.out.println(gildedRose.items[0].toString());
            gildedRose.updateQuality();
            System.out.println(gildedRose.items[0].toString());
        }
        assertEquals(gildedRose.items[0].sellIn,0);
        assertEquals(gildedRose.items[0].quality,80);

    }

    @Test
    public void should_augmente_quality_backstage_pass_avec_le_le_temps(){

        Item[] items = new Item[]{new Item("Backstage passes to a TAFKAL80ETC concert", 24, 30)};
        GildedRose gildedRose = new GildedRose(items);
        int days = 8;
        for (int i = 0; i < days; i++) {
            System.out.println("-------- day " + i + " --------");
            System.out.println("name, sellIn, quality");
            System.out.println("-------- before update --------");
            System.out.println(gildedRose.items[0].toString());
            gildedRose.updateQuality();
            System.out.println("-------- after update --------");
            System.out.println(gildedRose.items[0].toString());
        }
        assertEquals(gildedRose.items[0].quality,38);
    }


    @Test
    public void should_backstagePasse_augment_quality_2_fois_quand_reste_10_jours_ou_moins_and_3_fois_quand_reste_5_jour_ou_moins(){

        Item[] items = new Item[]{new Item("Backstage passes to a TAFKAL80ETC concert", 11, 15)};
        GildedRose gildedRose = new GildedRose(items);
        int days = 10;
        for (int i = 0; i < days; i++) {
            System.out.println("-------- day " + i + " --------");
            System.out.println("name, sellIn, quality");
            System.out.println("-------- before update --------");
            System.out.println(gildedRose.items[0].toString());
            gildedRose.updateQuality();
            System.out.println(gildedRose.items[0].toString());
        }
        assertEquals(gildedRose.items[0].quality,38);
    }

    @Test
    public void should_backstagePasse_quality_nombre_a_zero_apres_cancert(){

        Item[] items = new Item[]{new Item("Backstage passes to a TAFKAL80ETC concert", 11, 15)};
        GildedRose gildedRose = new GildedRose(items);
        int days = 15;
        for (int i = 0; i < days; i++) {
            System.out.println("-------- day " + i + " --------");
            System.out.println("name, sellIn, quality");
            System.out.println("-------- before update --------");
            System.out.println(gildedRose.items[0].toString());
            gildedRose.updateQuality();
            System.out.println(gildedRose.items[0].toString());
        }
        assertEquals(gildedRose.items[0].quality,0);
    }

    // Les tests pour le nouveau produit :

    @Test
    public void should_diminue_value_and_deux_fois_quality_every_day_avant_preemption_for_conjured_product(){

        Item[] items = new Item[]{new Item("Conjured Mana Cake", 10, 20)};
        GildedRose gildedRose = new GildedRose(items);
        int days = 4;
        for (int i = 0; i < days; i++) {
            System.out.println(gildedRose.items[0]);
            gildedRose.updateQuality();
            System.out.println(gildedRose.items[0]);
        }
        assertEquals(gildedRose.items[0].sellIn,6);
        assertEquals(gildedRose.items[0].quality,12);

    }

    @Test
    public void should_diminue_value_and_4_fois_quality_every_day_apres_preemption_for_conjured_product(){

        Item[] items = new Item[]{new Item("Conjured Mana Cake", 1, 20)};
        GildedRose gildedRose = new GildedRose(items);
        int days = 3;
        for (int i = 0; i < days; i++) {
            System.out.println(gildedRose.items[0]);
            gildedRose.updateQuality();
            System.out.println(gildedRose.items[0]);
        }
        assertEquals(gildedRose.items[0].sellIn,-2);
        assertEquals(gildedRose.items[0].quality,10);

    }
}
