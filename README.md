FORMAT: 1A
HOST: localhost:8080/

# supermarket

Supermarket is a REST-API allowing front-end developers to create super-market management applications.

## Product [/product/{id}]

### Get a Product [GET]

+ Response 200 (application/json)

        {
            "id": 1,
            "barcode": "2340002112286",
            "name": "Bananas",
            "category": "FRUITS",
            "vatTarrif": "LOW",
            "unit": "KG",
            "price": 0.55,
            "brand": {
                "name": "Chiquita"
            },
            "suppliers": [
                {
                    "id": 9,
                    "name": "Fruits Supplier"
                },
                {
                    "id": 8,
                    "name": "Generic Supplier"
                }
            ]
        }
        
## Product Creation [/product/]

### Create a New Product [POST]

You may create your own product using this action. It takes a JSON
object containing the following properties, another JSON object containing a brand id, and a collection of JSON objects with the supplier(s) id(s).

+ barcode (string) - The product barcode.
+ name (string) - The product name.
+ category (number) - The product category, (UNCATEGORIZED:0, STATIONARY:1, FRUITS:2).
+ vatTarrif (number) - The product VAT category, (NONE:0, ZERO:1, LOW:2, HIGH:3).
+ unit (number) - The product unit of measurement category, (UNSPECIFIED:0, PC:1, KG:2).
+ price (number) - The product price.
+ brand (JSON object) - JSON object representing the product brand properties, insert brand's id for using an already existing brand or name to create a new one.
+ suppliers (Array<json>) - The suppliers associate with the product. Insert an id for using an existing supplier or name to create a new one. 


+ Request (application/json)

        {
            "barcode": "2340002112286",
            "name": "banana",
            "category": "STATIONARY",
            "vatTarrif": "HIGH",
            "unit": "PC",
            "price": null,
            "brand": {
                "name": "Dole"
            },
            "suppliers": [
                {
                    "id": 9  
                },
                {
                    "name": "Fruits Supplier"
                }
            ]
        }
        
+ Response (body)

        {
            "id": 1,
            "barcode": "2340002112286",
            "name": "banana",
            "category": "FRUITS",
            "vatTarrif": "LOW",
            "unit": "KG",
            "price": null,
            "brand": {
                "name": "Dole"
            },
            "suppliers": [
                {
                    "id": 9,
                    "name": "Fruit Supplier"
                },
                {
                    "id": 8,
                    "name": "Generic Supplier"
                }
            ]
        }




## Product Update [/product/{id}]

### Update an Existing Product [PUT]

You may update an existing product using this action. It takes a JSON
object containing any of the following properties that need to be updated, another JSON object containing a brand id, and a collection of JSON objects with the supplier(s) id(s).

+ barcode (string) - The product barcode.
+ name (string) - The product name.
+ category (number) - The product category, (UNCATEGORIZED:0, STATIONARY:1, FRUITS:2).
+ vatTarrif (number) - The product VAT category, (NONE:0, ZERO:1, LOW:2, HIGH:3).
+ unit (number) - The product unit of measurement category, (UNSPECIFIED:0, PC:1, KG:2).
+ price (number) - The product price.
+ brand (JSON object) - JSON object representing the product brand properties, insert brand's id for using an already existing brand or name to create a new one.
+ suppliers (Array<json>) - The suppliers associate with the product. Insert an id for using an existing supplier or name to create a new one. 


+ Request (application/json)

        {
            "barcode": "123456789123",
            "name": "Even more bananas againa",
            "category": 2,
            "vatTarrif": 2,
            "unit": 2,
            "price": null,
            "brand": {
                "id": 2
            },
            "suppliers": [
                {
                    "id": 3
                },
                {
                    "id": 2
                }
            ]
        }

+ Response 201 (application/json)


    + Body

            {
                "barcode": "1234567890123",
                "id": 1,
                "name": "Bananas",
                "category": "FRUITS",
                "vatTarrif": "LOW",
                "unit": "KG",
                "price": null,
                "brand": {
                "id": 2
                },
                "suppliers": [
                    {
                        "id": 3
                    },
                    {
                        "id": 2
                    }
                ]
            }
