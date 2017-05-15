OpenWMS.org WMS: Inventory
=====================

The main responsibilities of the Inventory service are as follows:
  
  - **Allocation**. The Allocation strategy chooses the best available PackagingUnit of a 
   Product in the demanded amount.

# Resources

[![License][license-image]][license-url]

[license-image]: http://img.shields.io/:license-GPLv3-blue.svg?style=flat-square
[license-url]: LICENSE

# Current state of development

Under development

# Domain Model

Basically this service deals with Products and LoadUnits. The Product is the representation
of a real world product, or an article and has at least a sku (identifier) and a
descriptive text. Products may not be placed everywhere in a stock but only in certain
areas. Toxic liquids for example might only be stored down at the bottom in a stock aisle
but never in the upper rows. This mapping is defined with StockZones where a Product may
refer to.

![DomainModel][1]

A PackagingUnit contains an amount of particular Product items. For example a box of
100 screws or a tot of 1 fl oz liquid cleaner is the PackagingUnit of screws or liquid
cleaner.

A LoadUnit is used to divide a TransportUnit into physical areas. Each of these areas can
be assigned to a particular Product only and may refer to a number of PackagingUnits that
are placed in the area. LoadUnits are used in the picking process to tell the operator
where to take PackagingUnits or an amount of Product items from.

[1]: src/site/resources/images/domain-model.png
