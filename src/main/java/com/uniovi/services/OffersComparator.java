package com.uniovi.services;

import java.util.Comparator;
import com.uniovi.entities.Offer;

public class OffersComparator implements Comparator<Offer> {

    private final boolean trueLow;

    public OffersComparator(boolean trueLow) {
	this.trueLow = trueLow;
    }

    @Override
    public int compare(Offer o1, Offer o2) {
	return (o1.isHighlighted() ^ o2.isHighlighted()) ? ((o1.isHighlighted() ^ this.trueLow) ? 1 : -1) : 0;
    }

}
