package de.storecast.gildedrose.policy;

import de.storecast.gildedrose.Item;

public class ItemPolicyFactory
{
    public static final int SPEED_1X = 1;
    public static final int SPEED_2X = 2;
    public static final int SPEED_3X = 3;
    public static final int SPEED_4X = 4;
    public static final int MIN_QUALITY = 0;
    public static final int MAX_QUALITY = 50;
    public static final int MAX_LEGENDARY_QUALITY = 80;

    public static Policy<Item> getQualityEnhancePolicy()
    {
        return (item) -> incrementQuality(item, SPEED_1X, MAX_QUALITY);
    }

    public static Policy<Item> getQualityEnhance2xFastPolicy()
    {
        return (item) -> incrementQuality(item, SPEED_2X, MAX_QUALITY);
    }

    public static Policy<Item> getQualityEnhance3xFastPolicy()
    {
        return (item) -> incrementQuality(item, SPEED_3X, MAX_QUALITY);
    }

    public static Policy<Item> getSulfurasQualityEnhancePolicy()
    {
        return (item) -> incrementQuality(item, SPEED_1X, MAX_LEGENDARY_QUALITY);
    }

    public static Policy<Item> getQualityMinPolicy()
    {
        return (item) -> item.quality = MIN_QUALITY;
    }

    public static Policy<Item> getQualityDegradatePolicy()
    {
        return (item) -> decrementQuality(item, SPEED_1X);
    }

    public static Policy<Item> getQualityDegradate2xFastPolicy()
    {
        return (item) -> decrementQuality(item, SPEED_2X);
    }

    public static Policy<Item> getQualityDegradate4xFastPolicy()
    {
        return (item) -> decrementQuality(item, SPEED_4X);
    }

    public static Policy<Item> getSellInDegradatePolicy()
    {
        return (item) -> item.sellIn -= SPEED_1X;
    }

    private static void decrementQuality(Item item, int speed)
    {
        int quality = item.quality - speed;
        item.quality = (quality > MIN_QUALITY) ? quality : MIN_QUALITY;
    }

    private static void incrementQuality(Item item, int speed, int maxLimit)
    {
        int quality = item.quality + speed;
        item.quality = (quality < maxLimit) ? quality : maxLimit;
    }

}
