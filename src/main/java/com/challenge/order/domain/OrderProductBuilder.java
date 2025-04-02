package com.challenge.order.domain;

import java.math.BigDecimal;
import java.util.UUID;

public class OrderProductBuilder {

    protected UUID id;
    protected String name;
    protected BigDecimal value;
    protected Order order;

    public OrderProductBuilder id(UUID id) {
        this.id = id;
        return this;
    }

    public OrderProductBuilder name(String name) {
        this.name = name;
        return this;
    }

    public OrderProductBuilder value(BigDecimal value) {
        this.value = value;
        return this;
    }

    public OrderProductBuilder order(Order order) {
        this.order = order;
        return this;
    }

    public OrderProduct build() {
        return new OrderProduct(this);
    }

}
