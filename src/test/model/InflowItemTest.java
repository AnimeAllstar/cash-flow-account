package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

// Unit tests for InflowItem class
class InflowItemTest extends ListItemTest {

    @BeforeEach
    public void runBefore() {
        listItem = new InflowItem(LABEL, AMOUNT, DATE);
    }

    @Test
    public void testSetCategories() {
        super.testSetCategories(InflowItem.categories);
    }

    @Test
    public void testCompareTo() {
        super.testCompareTo(new InflowItem(LABEL, AMOUNT, DATE));
    }

}