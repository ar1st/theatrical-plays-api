# Documentation for Theatrical Plays API

This is the documentation for my thesis. It is about the creation of an API concerning data related to theatrical plays. The main goal is to be used for the implementation of a web-based front end, an Android app and an iOS app.  The API was built using the Spring Framework and the Kotlin programming language. 

------

## Api Response

All the responses from the server are being encapsuled with the ApiResponse container object. 
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



## API Contents

1. [Person](#person)
2. [Production](#production)
3. Venue

## Api Requests

---

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
| PersonDTO      | {id: Int, fullName: String, image: String} |

| [:book: Contents](#api-contents) | [:earth_africa: Person](#person) |

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
| Page<PersonDTO> | {id: Int, fullName: String, image: String} |

| [:book: Contents](#api-contents) | [:earth_africa: Person](#person) |

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
| Page<PersonDTO> | {id: Int, fullName: String, image: String} |

| [:book: Contents](#api-contents) | [:earth_africa: Person](#person) |

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
| Page<ProductionRoleDTO> | {productionId: Int, title: String, url: String, producer: String<br />mediaURL: String, duration: String, description: String, role: String} |

| [:book: Contents](#api-contents) | [:earth_africa: Person](#person) |

---

**Search People By Field**

| GET             | /api/people/search                         |
| --------------- | ------------------------------------------ |
| **Parameters**  |                                            |
| *q*             | <u>Request parameter</u>                   |
|                 | The query to search                        |
|                 | Example: q=fullName~μαρια,id:1928          |
|                 | Operatos: equals is :, like is ~, not !    |
| *page*          | <u>Request parameter</u>                   |
|                 | The index of the page to return. Optional  |
| *size*          | <u>Request parameter</u>                   |
|                 | The size of the page. Optional             |
| **Responses**   |                                            |
| Page<PersonDTO> | {id: Int, fullName: String, image: String} |

| [:book: Contents](#api-contents) | [:earth_africa: Person](#person) |

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

| [:book: Contents](#api-contents) | [:earth_africa: Production](#production) |

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
| Page<ProductionDTO> | {productionId: Int, title: String, url: String, producer: String<br />mediaURL: String, duration: String, description: String, role: String} |

| [:book: Contents](#api-contents) | [:earth_africa: Person](#person) |

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
| Page<ProductionDTO> | {productionId: Int, title: String, url: String, producer: String<br />mediaURL: String, duration: String, description: String, role: String} |

| [:book: Contents](#api-contents) | [:earth_africa: Person](#person) |

---

**Get People By Production Id**

This request is used to retrieve all people contributing to a production and their role.

| GET                 | /api/productions/ID/people                               |
| ------------------- | -------------------------------------------------------- |
| **Parameters**      |                                                          |
| *ID*                | <u>Path variable</u>                                     |
|                     | The identifier of the production to retrieve.            |
| **Responses**       |                                                          |
| List<PersonRoleDTO> | {id: Int, fullName: String, image: String, role: String} |

| [:book: Contents](#api-contents) | [:earth_africa: Person](#person) |

---









/api/productions/ID/events

/api/productions/search

---

/api/venues

/api/venues/ID
