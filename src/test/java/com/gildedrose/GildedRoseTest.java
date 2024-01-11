package com.gildedrose;


import org.assertj.core.api.Assertions;
import org.junit.Test;

import static org.assertj.core.api.Assertions.*;

public class GildedRoseTest {
    private static final String CONJURED_MANA_CAKE = "Conjured Mana Cake";

    @Test
    public void conjuredItem() {
        Item[] items = new Item[]{
                new Item(CONJURED_MANA_CAKE, 3, 6)
        };

        GildedRose gildedRose = new GildedRose(items);

        gildedRose.updateQuality();
        final Item[] itemsUpdated = gildedRose.items;
        final Item actualItem = itemsUpdated[0];

        final Item expectedItem = new Item(CONJURED_MANA_CAKE, 2, 4);
        assertThat(actualItem.name).isEqualTo(expectedItem.name);
        assertThat(actualItem.quality).isEqualTo(expectedItem.quality);
        assertThat(actualItem.sellIn).isEqualTo(expectedItem.sellIn);
    }

}
