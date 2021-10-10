# Documentation for Theatrical Plays API

This is the documentation for my thesis. It is about the creation of an API concerning data related to theatrical plays. The main goal is to be used for the implementation of a web-based front end, an Android app and an iOS app.  The API was built using the Spring Framework, the Kotlin programming language and ElasticSearch to index through specific fields. 

------

## Api Response

All the responses from the server are being encapsuled within the ApiResponse container object. 
It consists of three fields: `data`, `errors` and `status`.

The `data` field contains the requested data.
The `errors` field contains any possible errors occurred during a request. When `errors` is empty the request was successful.
The `status` field contains the HTTP status code converted to String.

Example: 

```json
{
    "data" : "Some data",
    "errors" : null,
    "status" : "OK"
}
```



## Contents

1. [Configure ElasticSearch](#configure-elasticsearch)
2. [Person](#person)
3. [Production](#production)
4. [Venue](#venue)
5. [Change log](#change-log)

## Configure ElasticSearch
Before deploying the SpringBoot application, the ElasticSearch Service must be started.

First download [ElasticSearch 7.6.2](https://www.elastic.co/downloads/past-releases/elasticsearch-7-6-2) <br/>
Unzip file on desired location and then execute *%ELASTIC_HOME%/bin/elasticsearch.bat*  <br/>
Verify that ElasticSearch has started (http://localhost:9200/) <br/>

You are all set!! You can start the SpringBoot application

---

## Api Requests

## Person

This collection contains all the requests regarding a person.

**Get Person**

This request is used to retrieve a person and their image by the provided identifier.

| GET            | /api/people/{ID}                           |
| -------------- | ------------------------------------------ |
| **Parameters** |                                            |
| *ID*           | <u>Path variable</u>                       |
|                | The identifier of the person to retrieve.  |
| **Responses**  |                                            |
| PersonDTO      | {id: Int, fullName: String, image: List\<String\>} |

| [:book: Contents](#contents) | [:earth_africa: Person](#person) |

---

**Get People**

This request is used to retrieve all people.

| GET             | /api/people                                |
| --------------- | ------------------------------------------ |
| **Parameters**  |                                            |
| *page*          | <u>Request parameter</u>                   |
|                 | The index of the page to return. Optional  |
| *size*          | <u>Request parameter</u>                   |
|                 | The size of the page. Optional             |
| **Responses**   |                                            |
| Page\<PersonDTO\> | {id: Int, fullName: String, image: List\<String\>} |

| [:book: Contents](#contents) | [:earth_africa: Person](#person) |

---

**Get People By Role**

This request returns people filtered by the provided role.

| GET             | /api/people/role                           |
| --------------- | ------------------------------------------ |
| **Parameters**  |                                            |
| *value*         | <u>Request parameter</u>                   |
|                 | The role provided to filter the results    |
| *page*          | <u>Request parameter</u>                   |
|                 | The index of the page to return. Optional  |
| *size*          | <u>Request parameter</u>                   |
|                 | The size of the page. Optional             |
| **Responses**   |                                            |
| Page\<PersonDTO\> | {id: Int, fullName: String, image: List\<String\>} |

| [:book: Contents](#contents) | [:earth_africa: Person](#person) |

---

**Get Production and Role by Person ID**

This request returns all the productions a person participated in and the corresponding role.

| GET                     | /api/people/{ID}/productions                                 |
| ----------------------- | ------------------------------------------------------------ |
| **Parameters**          |                                                              |
| *ID*                    | <u>Path variable</u>                                         |
|                         | The identifier of the person.                                |
| *page*                  | <u>Request parameter</u>                                     |
|                         | The index of the page to return. Optional                    |
| *size*                  | <u>Request parameter</u>                                     |
|                         | The size of the page. Optional                               |
| **Responses**           |                                                              |
| Page\<ProductionRoleDTO\> | {productionId: Int, title: String, url: String, producer: String<br />mediaURL: String, duration: String, description: String, role: String} |

| [:book: Contents](#contents) | [:earth_africa: Person](#person) |

---

**Search People By Field**

| GET             | /api/people/search                         |
| --------------- | ------------------------------------------ |
| **Parameters**  |                                            |
| *q*             | <u>Request parameter</u>                   |
|                 | The query to search                        |
|                 | Example: q=fullName~μαρια,id:1928          |
|                 | Operatos: equals is :, like is ~, not is !   |
| *page*          | <u>Request parameter</u>                   |
|                 | The index of the page to return. Optional  |
| *size*          | <u>Request parameter</u>                   |
|                 | The size of the page. Optional             |
| **Responses**   |                                            |
| Page\<PersonDTO\> | {id: Int, fullName: String, image: List\<String\>} |

| [:book: Contents](#contents) | [:earth_africa: Person](#person) |

---

## Production

This collection contains all the requests regarding a person.

**Get Production**

This request is used to retrieve a production by the provided identifier.

| GET            | /api/productions/{ID}                                        |
| -------------- | ------------------------------------------------------------ |
| **Parameters** |                                                              |
| *ID*           | <u>Path variable</u>                                         |
|                | The identifier of the production to retrieve.                |
| **Responses**  |                                                              |
| ProductionDTO  | {productionId: Int, title: String, url: String, producer: String<br />mediaURL: String, duration: String, description: String, role: String} |

| [:book: Contents](#contents) | [:earth_africa: Production](#production) |

---

**Get Productions**

This request is used to retrieve all productions.

| GET                 | /api/productions                                             |
| ------------------- | ------------------------------------------------------------ |
| **Parameters**      |                                                              |
| *page*              | <u>Request parameter</u>                                     |
|                     | The index of the page to return. Optional                    |
| *size*              | <u>Request parameter</u>                                     |
|                     | The size of the page. Optional                               |
| **Responses**       |                                                              |
| Page\<ProductionDTO\> | {productionId: Int, title: String, url: String, producer: String<br />mediaURL: String, duration: String, description: String, role: String} |

| [:book: Contents](#contents) | [:earth_africa: Production](#production) |

---

**Get Latest Productions**

This request is used to retrieve all productions sorted by their event date.

| GET                 | /api/productions/latest                                      |
| ------------------- | ------------------------------------------------------------ |
| **Parameters**      |                                                              |
| *page*              | <u>Request parameter</u>                                     |
|                     | The index of the page to return. Optional                    |
| *size*              | <u>Request parameter</u>                                     |
|                     | The size of the page. Optional                               |
| **Responses**       |                                                              |
| Page\<ProductionDTO\> | {productionId: Int, title: String, url: String, producer: String<br />mediaURL: String, duration: String, description: String, role: String} |

| [:book: Contents](#contents) | [:earth_africa: Production](#production) |

---

**Get People By Production Id**

This request is used to retrieve all people contributing to a production and their role.

| GET                 | /api/productions/ID/people                               |
| ------------------- | -------------------------------------------------------- |
| **Parameters**      |                                                          |
| *ID*                | <u>Path variable</u>                                     |
|                     | The identifier of the production to retrieve.            |
| **Responses**       |                                                          |
| List\<PersonRoleDTO\> | {id: Int, fullName: String, image: String, role: String} |

| [:book: Contents](#contents) | [:earth_africa: Production](#production) |

---

**Get Events By Production Id**

This request is used to retrieve all the events linked to a production.

| GET                 | /api/productions/ID/events                               |
| ------------------- | -------------------------------------------------------- |
| **Parameters**      |                                                          |
| *ID*                | <u>Path variable</u>                                     |
|                     | The identifier of the production to retrieve.            |
| **Responses**       |                                                          |
| List\<EventVenueDTO\> | {eventId: Int, date: Date, image: String, pricaRange: String </br > venueId: Int, title: String, address: String} |

| [:book: Contents](#contents) | [:earth_africa: Production](#production) |

---

**Search Productions By Field**
> :warning: **Prone to errors. Not fully implemented yet**

| GET             | /api/productions/search                         |
| --------------- | ------------------------------------------ |
| **Parameters**  |                                            |
| *q*             | <u>Request parameter</u>                   |
|                 | The query to search                        |
|                 | Example: q=fullName~μαρια,id:1928          |
|                 | Operatos: equals is :, like is ~, not is !   |
| *page*          | <u>Request parameter</u>                   |
|                 | The index of the page to return. Optional  |
| *size*          | <u>Request parameter</u>                   |
|                 | The size of the page. Optional             |
| **Responses**   |                                            |
| Page\<ProductionDTO\> | {productionId: Int, title: String, url: String, producer: String<br />mediaURL: String, duration: String, description: String, role: String} |

| [:book: Contents](#contents) | [:earth_africa: Production](#production) |

---

**Search Productions By Title Or Description Using ElasticSearch**

| GET             | /api/productions/elasticsearch                         |
| --------------- | ------------------------------------------ |
| **Parameters**  |                                            |
| *value*         | <u>Request parameter</u>                   |
|                 | The value to search for                    |
| *page*          | <u>Request parameter</u>                   |
|                 | The index of the page to return. Optional  |
| *size*          | <u>Request parameter</u>                   |
|                 | The size of the page. Optional             |
| **Responses**   |                                            |
| Page\<ProductionDTO\> | {productionId: Int, title: String, url: String, producer: String<br />mediaURL: String, duration: String, description: String, role: String} |

| [:book: Contents](#contents) | [:earth_africa: Production](#production) |

---

## Venue

This collection contains all the requests regarding a venue.

**Get venue**

This request is used to retrieve a venue by the provided identifier.

| GET            | /api/venue/{ID}                            |
| -------------- | ------------------------------------------ |
| **Parameters** |                                            |
| *ID*           | <u>Path variable</u>                       |
|                | The identifier of the venue to retrieve.   |
| **Responses**  |                                            |
| VenueDTO       | {id: Int, title: String, address: String}  |

| [:book: Contents](#contents) | [:earth_africa: Venue](#venue) |

---

**Get Venues**

This request is used to retrieve all venues.

| GET             | /api/venues                                |
| --------------- | ------------------------------------------ |
| **Parameters**  |                                            |
| *page*          | <u>Request parameter</u>                   |
|                 | The index of the page to return. Optional  |
| *size*          | <u>Request parameter</u>                   |
|                 | The size of the page. Optional             |
| **Responses**   |                                            |
| Page\<VenueDTO\>| {id: Int, title: String, address: String}  |

| [:book: Contents](#contents) | [:earth_africa: Venue](#venue) |

---
**Get Productions By Venue Id**

This request is used to retrieve all productions located in the given venue.

| GET             | /api/venues/ID/productions                 |
| --------------- | ------------------------------------------ |
| **Parameters**  |                                            |
| *ID*            | <u>Path variable</u>                       |
|                 | The identifier of the venue to retrieve.   |
| *page*          | <u>Request parameter</u>                   |
|                 | The index of the page to return. Optional  |
| *size*          | <u>Request parameter</u>                   |
|                 | The size of the page. Optional             |
| **Responses**   |                                            |
| Page\<ProductionDTO\> | {productionId: Int, title: String, url: String, producer: String<br />mediaURL: String, duration: String, description: String, role: String} |

| [:book: Contents](#contents) | [:earth_africa: Venue](#venue) |

---
## Change log

<details>
    <summary>10/10/2021</summary>
        <pre>
1. Support multiple images per person
2. Disable elastic search temporarily
    End point /api/productions/elasticsearch is disabled
        </pre>
</details>
<details>
    <summary>25/07/2021</summary>
        <pre>
1. Add documenetion for /api/productions/elasticsearch
        </pre>
</details>
<details>
    <summary>17/04/2021</summary>
        <pre>
1. Change log created. 
2. Add documentation for person, production and some venue actions.
    Endpoints added:
        Person:
            GET /api/people/
            GET /api/people/{ID}
            GET /api/people/role
            GET /api/people/{ID}/productions
            GET /api/people/search
        Production:
            GET /api/productions/
            GET /api/productions/{ID}
            GET /api/productions/latest
            GET /api/productions/{ID}/people
            GET /api/productions/{ID}/events
            GET /api/productions/search
        Venue:  
            GET /api/venues/
            GET /api/venues/{ID}
            GET /api/venues/{ID}/productions 
        </pre>
</details>

| [:book: Contents](#contents) |

