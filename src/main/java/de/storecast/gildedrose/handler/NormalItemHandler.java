package de.storecast.gildedrose.handler;

import de.storecast.gildedrose.Item;
import de.storecast.gildedrose.policy.ItemPolicyFactory;
import de.storecast.gildedrose.policy.Policy;

public class NormalItemHandler extends AbstractHandler
{
    private final Policy<Item> degradatePolicy = ItemPolicyFactory.getQualityDegradatePolicy();
    private final Policy<Item> degradate2xFastPolicy = ItemPolicyFactory.getQualityDegradate2xFastPolicy();

    @Override
    protected boolean isApplicable(Item item)
    {
        return true;
    }

    @Override
    protected void handleQuality(Item item)
    {
        if (item.sellIn > SELL_IN_THRESHOLD)
        {
            degradatePolicy.apply(item);
        }
        else
        {
            degradate2xFastPolicy.apply(item);
        }
    }

}
