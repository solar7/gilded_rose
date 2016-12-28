package de.storecast.gildedrose.handler;

import de.storecast.gildedrose.Item;
import de.storecast.gildedrose.policy.ItemPolicyFactory;
import de.storecast.gildedrose.policy.Policy;

public class SulfurasHandler extends AbstractHandler
{
    private final Policy<Item> enhancePolicy = ItemPolicyFactory.getSulfurasQualityEnhancePolicy();

    @Override
    protected boolean isApplicable(Item item)
    {
        return item.name.startsWith("Sulfuras");
    }

    @Override
    protected void handleQuality(Item item)
    {
        enhancePolicy.apply(item);
    }

    @Override
    protected void handleSellIn(Item item)
    {
        //do nothing, always constant
    }

}
