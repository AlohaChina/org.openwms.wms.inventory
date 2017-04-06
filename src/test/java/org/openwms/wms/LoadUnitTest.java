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
package org.openwms.wms;

import org.hibernate.TransientPropertyValueException;
import org.junit.Assert;
import org.junit.Test;
import org.openwms.core.test.AbstractJpaSpringContextTests;
import org.openwms.wms.inventory.Product;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.persistence.PersistenceException;

/**
 * A LoadUnitTest.
 * 
 * @author <a href="mailto:scherrer@openwms.org">Heiko Scherrer</a>
 */
public class LoadUnitTest extends AbstractJpaSpringContextTests {

    private static final Logger LOGGER = LoggerFactory.getLogger(LoadUnitTest.class);
    private static final String BARCODE1 = "0004711";
    private Product product;

    /**
     * Positive test to create a LoadUnit with an existing TU, existing Product
     * and a quantity > 0 on a physical Position.
     */
    @Test
    public final void testLoadUnitOk() {
        LoadUnit loadUnit = new LoadUnit(BARCODE1, "LEFT", product);
        entityManager.persist(loadUnit);
        entityManager.flush();
        entityManager.clear();
        LoadUnit lu = entityManager.find(LoadUnit.class, loadUnit.getPk());
        Assert.assertEquals(BARCODE1, lu.getTransportUnitId());
        Assert.assertEquals("LEFT", lu.getPhysicalPosition());
        Assert.assertEquals(product, lu.getProduct());
        Assert.assertFalse(lu.isNew());
        Assert.assertNotNull(lu.getLastModifiedDt());
        Assert.assertNotNull(lu.getCreateDt());
    }

    /**
     * Test that it is possible to create a LoadUnit with no quantity set.
     */
    @Test
    public final void testLoadUnitQtyZero() {
        LoadUnit loadUnit = new LoadUnit(BARCODE1, "LEFT", product);
        entityManager.persist(loadUnit);
    }

    /**
     * Test that it is not possible to create a LoadUnit with a transient
     * TransportUnit.
     */
    @Test
    public final void testLoadUnitTransientTU() {
        try {
            LoadUnit loadUnit = new LoadUnit(BARCODE1, "LEFT", product);
            entityManager.persist(loadUnit);
        } catch (Exception e) {
            if (e instanceof PersistenceException) {
                return;
            } else if (e instanceof IllegalStateException
                    && (e.getCause() != null && (e.getCause() instanceof TransientPropertyValueException))) {
                return;
            }
            Assert.fail("Expected to run into an IllegalStateException or a PersistenceException when trying to persist a LoadUnit with transient TransportUnit. The type of exception depends on the JPA provider");
        }
    }

    /**
     * Test that it is not possible to create a LoadUnit with a transient
     * Product.
     */
    @Test
    public final void testLoadUnitTransientProduct() {
        try {
            LoadUnit loadUnit = new LoadUnit(BARCODE1, "LEFT", new Product("TRANSIENT"));
            entityManager.persist(loadUnit);
        } catch (Exception e) {
            if (e instanceof PersistenceException) {
                return;
            } else if (e instanceof IllegalStateException
                    && (e.getCause() != null && (e.getCause() instanceof TransientPropertyValueException))) {
                return;
            }
            Assert.fail("Expected to run into an IllegalStateException or a PersistenceException when trying to persist a LoadUnit with transient Product. The type of exception depends on the JPA provider");
        }
    }

    @Test(expected = Exception.class)
    public final void testDuplicatedLoadUnit() {
        LoadUnit loadUnit = new LoadUnit(BARCODE1, "LEFT", product);
        entityManager.persist(loadUnit);
        entityManager.flush();
        LoadUnit loadUnit2 = new LoadUnit(BARCODE1, "LEFT", product);
        entityManager.persist(loadUnit2);
    }
}