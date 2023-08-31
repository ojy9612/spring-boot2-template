package com.zeki.springboot2template.domain._common.querydsl;

import com.querydsl.core.types.NullExpression;
import com.querydsl.core.types.Order;
import com.querydsl.core.types.OrderSpecifier;
import com.querydsl.core.types.dsl.PathBuilder;
import org.springframework.data.domain.Pageable;

public class QueryDslUtil {

    public static OrderSpecifier<?>[] pageableToOrderSpecifiers(Pageable pageable, PathBuilder<?> pathBuilder) {
        return pageable.getSort().stream()
                .map(order -> {
                    if (order.isAscending()) {
                        return pathBuilder.getString(order.getProperty()).asc();
                    } else {
                        return pathBuilder.getString(order.getProperty()).desc();
                    }
                })
                .toArray(OrderSpecifier[]::new);
    }

    @SuppressWarnings({"rawtypes", "unchecked"})
    public static class OrderByNull extends OrderSpecifier {

        private static final OrderByNull DEFAULT = new OrderByNull();

        private OrderByNull() {
            super(Order.ASC, NullExpression.DEFAULT, NullHandling.Default);
        }

        public static OrderByNull getDefault() {
            return OrderByNull.DEFAULT;
        }
    }
}
