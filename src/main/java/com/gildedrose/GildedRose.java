package com.gildedrose;

class GildedRose {
    private static final String BACKSTAGE_PASSES_TO_A_TAFKAL_80_ETC_CONCERT = "Backstage passes to a TAFKAL80ETC concert";
    private static final String AGED_BRIE = "Aged Brie";
    private static final String SULFURAS_HAND_OF_RAGNAROS = "Sulfuras, Hand of Ragnaros";
    private static final int NON_LEGENDARY_LIMIT_QUALITY = 50;

    Item[] items;

    public GildedRose(Item[] items) {
        this.items = items;
    }

    public void updateQuality() {
        for (final Item item : items) {
            if (isSulfurasHandOfRagnaros(item)) {
                continue;
            }
            if (isAgedBrie(item) || isBackStagePasses(item)) {
                if (item.quality < NON_LEGENDARY_LIMIT_QUALITY) {
                    increaseQuality(item);
                    if (isBackStagePasses(item)) {
                        if (item.sellIn < 11 && (item.quality < NON_LEGENDARY_LIMIT_QUALITY)) {
                            increaseQuality(item);
                        }
                        if (item.sellIn < 6 && (item.quality < NON_LEGENDARY_LIMIT_QUALITY)) {
                            increaseQuality(item);
                        }
                    }
                }
            } else {
                if (item.quality > 0) {
                    decreaseQuality(item);
                }
            }
            decreaseSellIn(item);

            if (item.sellIn < 0) {
                if (isAgedBrie(item)) {
                    if (item.quality < NON_LEGENDARY_LIMIT_QUALITY) {
                        increaseQuality(item);
                    }
                } else {
                    if (isBackStagePasses(item)) {
                        item.quality = 0;
                    } else {
                        if (item.quality > 0) {
                            decreaseQuality(item);

                        }
                    }
                }
            }
        }
    }

    private static void decreaseQuality(final Item item) {
        item.quality = item.quality - 1;
    }

    private static void decreaseSellIn(final Item item) {
        item.sellIn = item.sellIn - 1;
    }

    private static void increaseQuality(final Item item) {
        item.quality = item.quality + 1;
    }

    private boolean isSulfurasHandOfRagnaros(final Item item) {
        return item.name.equals(SULFURAS_HAND_OF_RAGNAROS);
    }

    private boolean isBackStagePasses(final Item item) {
        return item.name.equals(BACKSTAGE_PASSES_TO_A_TAFKAL_80_ETC_CONCERT);
    }

    private boolean isAgedBrie(final Item item) {
        return item.name.equals(AGED_BRIE);
    }
}
