package de.storecast.gildedrose.processor;

public interface Processor<T>
{
    void process(T item);
}
