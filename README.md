OpenWMS.org WMS: Inventory
=====================

The main responsibilities of the Inventory service are as follows:
  
  - **Allocation**. The Allocation strategy chooses the best available **PackagingUnit** of a 
   **Product** according to the demanded amount.

# Resources

[![License][license-image]][license-url]

[license-image]: http://img.shields.io/:license-GPLv2-blue.svg?style=flat-square
[license-url]: LICENSE

# Current state of development

Under development

# Models

![DomainModel][1]

## Anti Corruption Layer (ACL)

A **Location** in the context of inventory management is identified by a 5-tupel of area,
aisle, x, y, z. Where area defines the logical area of the Location, like "Pallet" or 
"Hanging Goods", aisle the particular stock aisle (0001, 0002, etc.) and the coordinate in 
the aisle as x-y-z tupel.

## Domain Model

Basically this service deals with Products and LoadUnits. A **Product** is the representation
of a real world product, or an article and has at least a [SKU](https://en.wikipedia.org/wiki/Stock_keeping_unit) (identifier) and a
descriptive text. Products may not be placed everywhere in a stock but only in certain
areas. Toxic liquids for example might only be stored down at the bottom of the stock aisle
but never in the upper rows. This mapping is defined with **StockZones** where a Product may
tied to.

A **PackagingUnit** contains an amount of a particular Product item. For example a box of
100 screws or a tot of 1 fl oz liquid cleaner is a PackagingUnit of screws or liquid
cleaner.

A **LoadUnit** is used to divide a **TransportUnit** into physical areas. Each of these areas can
be assigned to a particular Product only and may refer to a number of PackagingUnits that
are placed in the area. LoadUnits are used in the picking process to tell the operator
where to take PackagingUnits or an amount of Product items from.

[1]: src/site/resources/images/domain-model.png
