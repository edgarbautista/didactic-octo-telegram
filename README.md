# sample-code
This code is meant as a simple sample on how to iterate through a JsonNode Graph.

## The app exposes an endpoint that takes in JSON and returns string reprensetaion of the fields path mapped to data type.

# TODO: 
- *TESTS*

# NOTES: 
- It only grabs the first item from an array. 
- When the parent object is an ARRAY, it will print as first line of the output as ARRAY.

# USE:
- RUN: ```./mvnw spring-boot:run```
- Sample request: 
```
curl -X POST \
  http://localhost:8080/api/json \
  -H 'Content-Type: application/json' \
  -d '{
    "test": "value",
    "test2": [
        {
            "nested": {
                "nested2": "ok",
                "intField": 20,
                "other": [
                    "more"
                ]
            }
        },
        {
            "nested": {
                "nested2": "ok",
                "intField": 20,
                "other": [
                    "more",
                    "ok"
                ]
            }
        }
    ],
    "test3": {
        "nested": 30,
        "nested2": 10
    },
    "test4": "test"
}'
```
- Sample output:
```
test::STRING
test2::ARRAY
test2:nested:nested2::STRING
test2:nested:intField::NUMBER
test2:nested:other::ARRAY
test2:nested:other::STRING
test3:nested::NUMBER
test3:nested2::NUMBER
test4::STRING
```
