package de.storecast.gildedrose.handler;

public interface Handler<T>
{
    boolean handle(T item);
}
