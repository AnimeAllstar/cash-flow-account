package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

// Unit tests for IncomeItem class
class IncomeItemTest extends ItemTest {

    @BeforeEach
    public void runBefore() {
        item = new IncomeItem(LABEL, AMOUNT, DATE);
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