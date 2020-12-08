@Adidas

  Feature: Pet Store validation

    Scenario Outline: Get available pets and assert result
      Given GET request on <BASEURI>, <ENDPOINT> for <STATUS> pets
      Then Validate GET response
      Examples:
      | BASEURI|ENDPOINT | STATUS |
      |    https://petstore.swagger.io|/v2/pet/findByStatus?status=available|    available    |

    Scenario Outline: POST a pet to store and validate result
      Given POST request on <BASEURI>, <ENDPOINT> for <PAYLOAD> pet to store
      Then Validate pet is added to store


      Examples:
        | BASEURI|ENDPOINT |PAYLOAD|
        |https://petstore.swagger.io|/v2/pet|{  "id": 0,  "category": {    "id": 0,    "name": "string"  },  "name": "doggie",  "photoUrls": [    "string"  ],  "tags": [    {      "id": 0,      "name": "string"    }  ],  "status": "available"}|

#####################Implmentation pending##############################
#    Scenario: UPDATE pet status to sold and validate result
#      Given UPDATE request for pet status to sold
#      Then Validate pet status
#
#    Scenario: Delete pet and validate result
#      Given DELETE pet from store
#      Then Validate delete request result



