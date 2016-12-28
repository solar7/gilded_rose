package de.storecast.gildedrose.handler;

import de.storecast.gildedrose.Item;
import de.storecast.gildedrose.policy.ItemPolicyFactory;
import de.storecast.gildedrose.policy.Policy;

public abstract class AbstractHandler implements Handler<Item>
{
    public static final int SELL_IN_THRESHOLD = 0;

    private final Policy<Item> sellInPolicy = ItemPolicyFactory.getSellInDegradatePolicy();

    protected abstract boolean isApplicable(Item item);

    protected abstract void handleQuality(Item item);

    @Override
    public boolean handle(Item item)
    {
        if (isApplicable(item))
        {
            handleQuality(item);
            handleSellIn(item);
            return true;
        }

        return false;
    }

    protected void handleSellIn(Item item)
    {
        sellInPolicy.apply(item);
    }

}
