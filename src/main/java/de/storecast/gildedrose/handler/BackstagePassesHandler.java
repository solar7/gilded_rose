package de.storecast.gildedrose.handler;

import de.storecast.gildedrose.Item;
import de.storecast.gildedrose.policy.ItemPolicyFactory;
import de.storecast.gildedrose.policy.Policy;

public class BackstagePassesHandler extends AbstractHandler
{
    private final Policy<Item> enhancePolicy = ItemPolicyFactory.getQualityEnhancePolicy();
    private final Policy<Item> enhance2xFastPolicy = ItemPolicyFactory.getQualityEnhance2xFastPolicy();
    private final Policy<Item> enhance3xFastPolicy = ItemPolicyFactory.getQualityEnhance3xFastPolicy();
    private final Policy<Item> minQualityPolicy = ItemPolicyFactory.getQualityMinPolicy();

    @Override
    protected boolean isApplicable(Item item)
    {
        return item.name.startsWith("Backstage passes");
    }

    @Override
    protected void handleQuality(Item item)
    {
        if (item.sellIn > 10)
        {
            enhancePolicy.apply(item);
        }
        else if(item.sellIn <= 10 && item.sellIn > 5)
        {
            enhance2xFastPolicy.apply(item);
        }
        else if(item.sellIn <= 5 && item.sellIn > 0)
        {
            enhance3xFastPolicy.apply(item);
        }
        else if (item.sellIn <= 0)
        {
            minQualityPolicy.apply(item);
        }
    }
}
