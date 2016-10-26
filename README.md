# Oblig 2 MOD250
All database tables will be generated by the program on runtime. We have included some dummy data in the database for our group (grp16), but this data is not necessary to run the program.

To properly display the jsf-files, use /faces in the url. The start page for our project will therefore be http://.../faces/index.xhtml

## Features:
* Ability to create user and log in
* Users can create new products from their control panel
* Users can see a list of all available items, and enter their product page
* Users can bid on any product

## API and Web Service
We have made one REST API and one SOAP Web Service. These can be used as follows:

REST for returning active auctions (only works with XML due to error with glassfish):
```
GET http://localhost:8080/mod250Ex2/webresources/no.hib.mod250.entities.product/active
```

SOAP for returning active auctions:
```java
List<Product> getActiveAuctions();
```

## Other things we've done:
* Made a JMS topic with 2 subscribers. This topic notifies subscribers when someone wins an auction
* Made a REST test. 
* Enabled HTTPS

## What we didn't have time to do:
* Encrypt passwords
* Make a GUI so that the client application can bid on products
* 


