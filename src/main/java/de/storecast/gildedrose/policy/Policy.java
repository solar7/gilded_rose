package de.storecast.gildedrose.policy;

public interface Policy<T>
{
    void apply(T item);
}
