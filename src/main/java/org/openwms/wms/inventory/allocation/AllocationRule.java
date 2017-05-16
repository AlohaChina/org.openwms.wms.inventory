/*
 * openwms.org, the Open Warehouse Management System.
 * Copyright (C) 2014 Heiko Scherrer
 *
 * This file is part of openwms.org.
 *
 * openwms.org is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as
 * published by the Free Software Foundation; either version 2 of the
 * License, or (at your option) any later version.
 *
 * openwms.org is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this software. If not, write to the Free Software
 * Foundation, Inc., 51 Franklin St, Fifth Floor, Boston, MA
 * 02110-1301 USA, or see the FSF site: http://www.fsf.org.
 */
package org.openwms.wms.inventory.allocation;

import org.openwms.wms.inventory.Product;

import java.io.Serializable;

/**
 * An AllocationRule encapsulates the demanded amount of {@link Product} items.
 *
 * @author <a href="mailto:scherrer@openwms.org">Heiko Scherrer</a>
 */
public class AllocationRule implements Serializable {

    private long quantity;
    private Product product;

    /**
     * Create a new AllocationRule.
     */
    protected AllocationRule() {
    }

    /**
     * Create a new AllocationRule.
     *
     * @param quantity The amount of Product items
     * @param product  The demanded Product
     */
    public AllocationRule(long quantity, Product product) {
        super();
        this.quantity = quantity;
        this.product = product;
    }

    /**
     * Get the quantity.
     *
     * @return the quantity.
     */
    public long getQuantity() {
        return quantity;
    }

    /**
     * Get the product.
     *
     * @return the product.
     */
    public Product getProduct() {
        return product;
    }
}