package de.storecast.gildedrose.handler;

import de.storecast.gildedrose.Item;
import de.storecast.gildedrose.policy.ItemPolicyFactory;
import de.storecast.gildedrose.policy.Policy;

public class ConjuredHandler extends AbstractHandler
{
    private final Policy<Item> degradate2xFastPolicy = ItemPolicyFactory.getQualityDegradate2xFastPolicy();
    private final Policy<Item> degradate4xFastPolicy = ItemPolicyFactory.getQualityDegradate4xFastPolicy();

    @Override
    protected boolean isApplicable(Item item)
    {
        return item.name.startsWith("Conjured");
    }

    @Override
    protected void handleQuality(Item item)
    {
        if (item.sellIn > SELL_IN_THRESHOLD)
        {
            degradate2xFastPolicy.apply(item);
        }
        else
        {
            degradate4xFastPolicy.apply(item);
        }
    }

}
