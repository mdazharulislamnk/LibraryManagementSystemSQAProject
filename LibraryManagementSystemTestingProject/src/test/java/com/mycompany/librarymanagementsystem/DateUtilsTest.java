package com.mycompany.librarymanagementsystem;

import org.junit.jupiter.api.Test;
import java.time.LocalDate;
import static org.junit.jupiter.api.Assertions.*;

public class DateUtilsTest {

    @Test
    public void testGetToday() {
        LocalDate today = DateUtils.getToday();
        assertEquals(LocalDate.now(), today);
    }

    @Test
    public void testCalculateDueDate() {
        int borrowPeriodDays = 14;
        LocalDate expectedDueDate = LocalDate.now().plusDays(borrowPeriodDays);
        LocalDate result = DateUtils.calculateDueDate(borrowPeriodDays);

        assertEquals(expectedDueDate, result);
    }

    @Test
    public void testCalculateDaysBetween() {
        LocalDate startDate = LocalDate.of(2024, 5, 1);
        LocalDate endDate = LocalDate.of(2024, 5, 11);

        long expectedDays = 10;
        long result = DateUtils.calculateDaysBetween(startDate, endDate);

        assertEquals(expectedDays, result);
    }

    @Test
    public void testIsOverdueTrue() {
        LocalDate dueDate = LocalDate.now().minusDays(5);

        assertTrue(DateUtils.isOverdue(dueDate));
    }

    @Test
    public void testIsOverdueFalse() {
        LocalDate dueDate = LocalDate.now().plusDays(5);

        assertFalse(DateUtils.isOverdue(dueDate));
    }
    
    @Test
public void testCalculateDueDateWithZeroDays() {
    LocalDate expected = LocalDate.now(); // due today if 0 days
    LocalDate result = DateUtils.calculateDueDate(0);

    assertEquals(expected, result);
}

@Test
public void testCalculateDaysBetweenSameDay() {
    LocalDate today = LocalDate.of(2024, 5, 22);
    long result = DateUtils.calculateDaysBetween(today, today);

    assertEquals(0, result); // Same day should return 0
}

@Test
public void testCalculateDaysBetweenReverseOrder() {
    LocalDate future = LocalDate.of(2024, 12, 31);
    LocalDate past = LocalDate.of(2024, 1, 1);

    long result = DateUtils.calculateDaysBetween(future, past);

    assertEquals(-365, result); // Negative if order is reversed
}

@Test
public void testDueDateOnLeapYear() {
    LocalDate feb29 = LocalDate.of(2024, 2, 29);
    LocalDate result = DateUtils.calculateDueDate((int) DateUtils.calculateDaysBetween(LocalDate.now(), feb29));

    assertEquals(feb29, result);
}

@Test
public void testIsOverdueExactlyToday() {
    LocalDate dueToday = LocalDate.now();

    assertFalse(DateUtils.isOverdue(dueToday)); // Due today is not overdue
}

}
