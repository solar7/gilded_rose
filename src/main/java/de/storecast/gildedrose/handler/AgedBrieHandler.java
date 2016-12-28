package de.storecast.gildedrose.handler;

import de.storecast.gildedrose.Item;
import de.storecast.gildedrose.policy.ItemPolicyFactory;
import de.storecast.gildedrose.policy.Policy;

public class AgedBrieHandler extends AbstractHandler
{
    private final Policy<Item> enhancePolicy = ItemPolicyFactory.getQualityEnhancePolicy();

    @Override
    protected boolean isApplicable(Item item)
    {
        return item.name.startsWith("Aged Brie");
    }

    @Override
    protected void handleQuality(Item item)
    {
        enhancePolicy.apply(item);
    }
}
