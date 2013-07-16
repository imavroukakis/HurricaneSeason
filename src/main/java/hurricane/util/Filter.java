package hurricane.util;

import com.google.common.base.Predicate;
import com.google.common.collect.Iterables;
import hurricane.data.Hurricane;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class Filter {


    private Filter() {
        throw new IllegalAccessError("Cannot instantiate utility class");
    }


    public static Iterable<Hurricane> onYear(List<Hurricane> hurricanes, final int year, boolean needsSorting) {

        if (needsSorting) {
            Collections.sort(hurricanes, new Comparator<Hurricane>() {
                // Note: this comparator imposes orderings that are inconsistent with equals.
                @Override
                public int compare(Hurricane hurricaneOne, Hurricane hurricaneTwo) {
                    if (hurricaneOne.getYear() > hurricaneTwo.getYear()) {
                        return 1;
                    } else if (hurricaneOne.getYear() == hurricaneTwo.getYear()) {
                        return 0;
                    } else {
                        return -1;
                    }

                }
            });
        }
        return Iterables.filter(hurricanes, new Predicate<Hurricane>() {
            @Override
            public boolean apply(Hurricane hurricane) {
                return hurricane.getYear() == year;
            }
        });
    }
}
