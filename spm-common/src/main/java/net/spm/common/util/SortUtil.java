package net.spm.common.util;

import org.springframework.data.domain.Sort;

import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

public class SortUtil {

    /**
     * Convert Map<String, Sort.Direction> to Sort
     *
     * @param orders    map of properties (sorting fields) and directions (ASC or DESC)
     * @return          Sort object
     */
    public static Sort toSort(Map<String, Sort.Direction> orders) {
        return new Sort(orders
                .entrySet()
                .stream()
                .map(entry -> new Sort.Order(entry.getValue(), entry.getKey()))
                .collect(Collectors.toList()));
    }

    /**
     * Convert  Sort to Map<String, Sort.Direction> for sending via Spring Integration
     *
     * @param sort  Sort object
     * @return      map of properties (sorting fields) and directions (ASC or DESC)
     */
    public static Map<String, Sort.Direction> toMap(Sort sort) {
        Map<String, Sort.Direction> orders = new HashMap<>();
        for (Sort.Order order : sort) {
            orders.put(order.getProperty(), order.getDirection());
        }
        return orders;
    }
}
