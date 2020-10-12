package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

// Unit tests for IncomeItem class
class IncomeItemTest extends ListItemTest {

    @BeforeEach
    public void runBefore() {
        listItem = new IncomeItem(LABEL, AMOUNT, DATE);
    }

    @Test
    public void testSetCategories() {
        super.testSetCategories(IncomeItem.categories);
    }

    @Test
    public void testCompareTo() {
        super.testCompareTo(new IncomeItem(LABEL, AMOUNT, DATE));
    }

}