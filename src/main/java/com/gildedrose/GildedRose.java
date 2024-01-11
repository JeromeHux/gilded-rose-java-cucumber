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
                //Do nothing
            } else if (isBackStagePasses(item)) {
                updateBackStagePassesQuality(item);
            } else if (isAgedBrie(item)) {
                updateAgeBrieQuality(item);
            } else {
                updateRegularItemQuality(item);
            }
        }
    }

    private void updateRegularItemQuality(final Item item) {
        decreaseSellIn(item);
        decreaseQualityIfNotUnderLimit(item);
        if (isUnderSellInLimit(item)) {
            decreaseQualityIfNotUnderLimit(item);
        }
    }

    private void updateBackStagePassesQuality(final Item item) {
        decreaseSellIn(item);
        increaseQualityIfNotAboveNonLegendaryLimit(item);
        if (item.sellIn < 10) {
            increaseQualityIfNotAboveNonLegendaryLimit(item);
        }
        if (item.sellIn < 5) {
            increaseQualityIfNotAboveNonLegendaryLimit(item);
        }
        if (isUnderSellInLimit(item)) {
            item.quality = 0;
        }
    }

    private void updateAgeBrieQuality(final Item item) {
        decreaseSellIn(item);
        increaseQualityIfNotAboveNonLegendaryLimit(item);
        if (isUnderSellInLimit(item)) {
            increaseQualityIfNotAboveNonLegendaryLimit(item);
        }
    }

    private static boolean isUnderLegendaryLimitQuality(final Item item) {
        return item.quality < NON_LEGENDARY_LIMIT_QUALITY;
    }

    private boolean isUnderSellInLimit(final Item item) {
        return item.sellIn < 0;
    }

    private void increaseQualityIfNotAboveNonLegendaryLimit(final Item item) {
        if (isUnderLegendaryLimitQuality(item)) {
            increaseQuality(item);
        }
    }

    private void decreaseQualityIfNotUnderLimit(final Item item) {
        if (item.quality > 0) {
            decreaseQuality(item);
        }
    }

    private void decreaseQuality(final Item item) {
        item.quality = item.quality - 1;
    }

    private void decreaseSellIn(final Item item) {
        item.sellIn = item.sellIn - 1;
    }

    private void increaseQuality(final Item item) {
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
