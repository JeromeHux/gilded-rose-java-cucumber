package com.gildedrose;

import static org.junit.Assert.*;

import io.cucumber.java.Before;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;


import java.util.List;

import static java.lang.String.format;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.presentation.StandardRepresentation.registerFormatterForType;
public class StepDefinitions {
    private Item[] items = new Item[1];
    private GildedRose app;

    @Given("The item as {string}")
    public void initial_sellin_is_and_quality_is(String name) {
        items[0] = new Item(name, 0, 0);
        app = new GildedRose(items);
    }

    @When("I update the quality")
    public void i_update_the_quality() {
        app.updateQuality();
    }

    @Then("I should get item as {string}")
    public void i_should_get_sellin_as_and_quality_as(String expected) {
        assertEquals(expected, app.items[0].name);
    }


    private GildedRose gildedRose;

    @Before
    public void setUp() {
        registerFormatterForType(Item.class, this::formatItem);
    }

    @Given("^I register the following items in the inventory$")
    public void i_register_the_following_items_in_the_inventory(List<Item> items) {
        assertThat(items).as("inventory items").isNotEmpty();
        gildedRose = new GildedRose(toArray(items));
    }

    @When("^(\\d+) day(?:s)? pass(?:es)?$")
    public void day_passes(int days) {
        assertThat(gildedRose).isNotNull();
        for (int i = 0; i < days; ++i) {
            gildedRose.updateQuality();
        }
    }

    @Then("^the inventory holds the following items$")
    public void the_inventory_holds_the_following_items(List<Item> items) {
        assertThat(gildedRose.items)
                .usingFieldByFieldElementComparator()
                .containsOnly(toArray(items));
    }

    private String formatItem(Object object) {
        Item item = (Item) object;
        return format("(%s, sellIn=%d, quality=%d)", item.name, item.sellIn, item.quality);
    }

    private Item[] toArray(List<Item> items) {
        return items.toArray(new Item[items.size()]);
    }
}

