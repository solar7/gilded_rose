package de.storecast.gildedrose;

import static de.storecast.gildedrose.policy.ItemPolicyFactory.MAX_LEGENDARY_QUALITY;
import static de.storecast.gildedrose.policy.ItemPolicyFactory.MAX_QUALITY;
import static de.storecast.gildedrose.policy.ItemPolicyFactory.MIN_QUALITY;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import org.junit.Before;
import org.junit.Test;

import de.storecast.gildedrose.handler.AbstractHandler;
import de.storecast.gildedrose.handler.AgedBrieHandler;
import de.storecast.gildedrose.handler.BackstagePassesHandler;
import de.storecast.gildedrose.handler.ConjuredHandler;
import de.storecast.gildedrose.handler.NormalItemHandler;
import de.storecast.gildedrose.handler.SulfurasHandler;
import de.storecast.gildedrose.processor.ItemProcessor;

public class GildedRoseTest
{
    private GildedRoseService gildedRoseService;

    public final static String REGULAR_NAME1 = "Regular Item1...";
    public final static String REGULAR_NAME2 = "Regular Item2...";
    public final static String REGULAR_NAME3 = "Regular Item3...";
    public final static String AGED_BRIE_NAME = "Aged Brie...";
    public final static String SULFURAS_NAME = "Sulfuras...";
    public final static String CONJURED_NAME = "Conjured...";
    public final static String BACKSTAGE_PASSES_NAME = "Backstage passes...";
    
    @Before
    public void init()
    {
        AbstractHandler[] handlers = new AbstractHandler[]
        {
            new AgedBrieHandler(),
            new SulfurasHandler(),
            new ConjuredHandler(),
            new BackstagePassesHandler(),
            new NormalItemHandler(),
        };

        gildedRoseService = new GildedRoseServiceImpl(new ItemProcessor(handlers));
    }

    @Test
    public void testNormalQualityIsNeverNegative()
    {
        Item[] items = { new Item(REGULAR_NAME1, 5, 5) };
        callServiceTimes(items, 10);
        assertThat(items[0].quality, is(0));
        assertThat(items[0].sellIn, is(-5));
    }

    @Test
    public void testNormalQualityDegradation()
    {
        Item[] items = { new Item(REGULAR_NAME1, 10, 10) };
        callServiceTimes(items, 5);
        assertThat(items[0].quality, is(5));
        assertThat(items[0].sellIn, is(5));
    }

    @Test
    public void testTwiceFastQualityDegradation()
    {
        Item[] items = { new Item(REGULAR_NAME1, 15, 30) };
        callServiceTimes(items, 22);
        assertThat(items[0].quality, is(1));
        assertThat(items[0].sellIn, is(-7));
    }

    @Test
    public void testTwiceFastQualityDegradationMinQuality()
    {
        Item[] items = { new Item(REGULAR_NAME1, 15, 30) };
        callServiceTimes(items, 23);
        assertThat(items[0].quality, is(0));
        assertThat(items[0].sellIn, is(-8));
    }

    @Test
    public void testQualityOfAgedBrieItemMaxQuality()
    {
        Item[] items = { new Item(AGED_BRIE_NAME, 0, 40) };
        callServiceTimes(items, 15);
        assertThat(items[0].quality, is(MAX_QUALITY));
        assertThat(items[0].sellIn, is(-15));
    }
 
    @Test
    public void testSulfurasItemMaxQuality()
    {
        Item[] items = { new Item(SULFURAS_NAME, 20, 40) };
        callServiceTimes(items, 100);
        assertThat(items[0].quality, is(MAX_LEGENDARY_QUALITY));
        assertThat(items[0].sellIn, is(20));
    }

    @Test
    public void testConjuredItem2xDegradation()
    {
        Item[] items = { new Item(CONJURED_NAME, 20, 40) };
        callServiceTimes(items, 10);
        assertThat(items[0].quality, is(20));
        assertThat(items[0].sellIn, is(10));
    }

    @Test
    public void testConjuredItem4xDegradation()
    {
        Item[] items = { new Item(CONJURED_NAME, 20, 140) };
        callServiceTimes(items, 40);
        assertThat(items[0].quality, is(20));
        assertThat(items[0].sellIn, is(-20));
    }

    @Test
    public void testConjuredItemQualityIs0()
    {
        Item[] items = { new Item(CONJURED_NAME, 5, 7) };
        callServiceTimes(items, 10);
        assertThat(items[0].quality, is(0));
        assertThat(items[0].sellIn, is(-5));
    }

    @Test
    public void testBackstagePassesItemMaxQuality()
    {
        Item[] items = { new Item(BACKSTAGE_PASSES_NAME, 100, 10) };
        callServiceTimes(items, 100);
        assertThat(items[0].quality, is(50));
        assertThat(items[0].sellIn, is(0));
    }

    @Test
    public void testBackstagePassesItem1xIncreases()
    {
        Item[] items = { new Item(BACKSTAGE_PASSES_NAME, 20, 10) };
        callServiceTimes(items, 10);
        assertThat(items[0].quality, is(20));
        assertThat(items[0].sellIn, is(10));
    }

    @Test
    public void testBackstagePassesItem1xAnd2xIncreases()
    {
        Item[] items = { new Item(BACKSTAGE_PASSES_NAME, 12, 10) };
        callServiceTimes(items, 5);
        assertThat(items[0].quality, is(18));
        assertThat(items[0].sellIn, is(7));
    }

    @Test
    public void testBackstagePassesItem2xAnd3xIncreases()
    {
        Item[] items = { new Item(BACKSTAGE_PASSES_NAME, 7, 10) };
        callServiceTimes(items, 5);
        assertThat(items[0].quality, is(23));
        assertThat(items[0].sellIn, is(2));
    }

    @Test
    public void testBackstagePassesItemQualityIs0()
    {
        Item[] items = { new Item(BACKSTAGE_PASSES_NAME, 3, 10) };
        callServiceTimes(items, 5);
        assertThat(items[0].quality, is(0));
        assertThat(items[0].sellIn, is(-2));
    }

    @Test
    public void testAllHandlers()
    {
        Item[] items =
        {
            new Item(REGULAR_NAME1, 5, 20),
            new Item(CONJURED_NAME, 5, 50),
            new Item(REGULAR_NAME2, 10, 5),
            new Item(BACKSTAGE_PASSES_NAME, 20, 10),
            new Item(AGED_BRIE_NAME, 0, 30),
            new Item(SULFURAS_NAME, 0, 75),
            new Item(REGULAR_NAME3, 0, 40),
        };
        callServiceTimes(items, 10);
        assertThat(items[0].quality, is(5));
        assertThat(items[1].quality, is(20));
        assertThat(items[2].quality, is(MIN_QUALITY));
        assertThat(items[3].quality, is(20));
        assertThat(items[4].quality, is(40));
        assertThat(items[5].quality, is(MAX_LEGENDARY_QUALITY));
        assertThat(items[6].quality, is(20));
    }

    private void callServiceTimes(Item[] arg, int times)
    {
        for (int i = 0; i < times; i++)
        {
            gildedRoseService.updateQuality(arg);
        }
    }
}
