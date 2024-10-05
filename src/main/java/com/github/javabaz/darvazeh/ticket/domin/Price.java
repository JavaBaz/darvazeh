package com.github.javabaz.darvazeh.ticket.domin;

public record Price(Long price) {
    public Price {
        if (this.price() < 0)
            throw new IllegalArgumentException("price can't be negative ");
    }
}
