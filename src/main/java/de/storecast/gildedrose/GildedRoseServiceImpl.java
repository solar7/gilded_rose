package de.storecast.gildedrose;

import de.storecast.gildedrose.processor.Processor;

public class GildedRoseServiceImpl implements GildedRoseService
{
    private final Processor<Item> processor;

    public GildedRoseServiceImpl(Processor<Item> processor)
    {
        this.processor = processor;
    }

    @Override
    public void updateQuality(Item[] items)
    {
        for (Item item : items)
        {
            processor.process(item);
        }
    }

}
