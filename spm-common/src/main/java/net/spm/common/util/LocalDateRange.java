package net.spm.common.util;

import java.time.LocalDate;
import java.util.Iterator;
import java.util.NoSuchElementException;

public class LocalDateRange implements Iterable<LocalDate> {

    private final LocalDate start;
    private final LocalDate end;
    private long daysToAdd;

    public LocalDateRange(LocalDate start, LocalDate end, long daysToAdd) {
        this.start = start;
        this.end = end;
        this.daysToAdd = daysToAdd;
    }

    public Iterator<LocalDate> iterator() {
        return new LocalDateRangeIterator(start, end, daysToAdd);
    }

    private static class LocalDateRangeIterator implements Iterator<LocalDate> {

        private LocalDate current;
        private final LocalDate end;
        private long daysToAdd;

        private LocalDateRangeIterator(LocalDate start, LocalDate end, long daysToAdd) {
            this.current = start;
            this.end = end;
            this.daysToAdd = daysToAdd;
        }

        public boolean hasNext() {
            return current != null;
        }

        public LocalDate next() {
            if (current == null) {
                throw new NoSuchElementException();
            }
            LocalDate ret = current;
            current = current.plusDays(daysToAdd);
            if (current.compareTo(end) > 0) {
                current = null;
            }
            return ret;
        }

        public void remove() {
            throw new UnsupportedOperationException();
        }
    }
}
