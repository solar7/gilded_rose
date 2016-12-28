package de.storecast.gildedrose.processor;

import de.storecast.gildedrose.Item;
import de.storecast.gildedrose.handler.Handler;

public class ItemProcessor implements Processor<Item>
{
    private final Handler<Item>[] handlers;

    public ItemProcessor(Handler<Item>[] handlers)
    {
        this.handlers = handlers;
    }

    @Override
    public void process(Item item)
    {
        for (Handler<Item> handler : handlers)
        {
            boolean wasHandled = handler.handle(item);

            if (wasHandled)
            {
                break;
            }
        }
    }

}
