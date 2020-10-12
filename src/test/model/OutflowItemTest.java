package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

// Unit tests for OutflowItem class
class OutflowItemTest extends ListItemTest {

    @BeforeEach
    public void runBefore() {
        listItem = new OutflowItem(LABEL, AMOUNT, DATE);
    }

    @Test
    public void testSetCategories() {
        super.testSetCategories(OutflowItem.categories);
    }

    @Test
    public void testCompareTo() {
        super.testCompareTo(new OutflowItem(LABEL, AMOUNT, DATE));
    }

}
