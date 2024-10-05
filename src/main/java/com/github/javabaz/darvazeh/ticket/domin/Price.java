package com.github.javabaz.darvazeh.ticket.domin;

public record Price(Long price) {
    public Price {
        if (price < 0)
            throw new IllegalArgumentException("price can't be negative ");
    }

    void isEqualOrGreater(Long price) {
        if (this.price.compareTo(price) < 0)
            throw new IllegalArgumentException("price can't be less than " + price);

    }
}
