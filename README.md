# GATHER
An event management system.
RESTful API created by David and Vijee

## Data Structures

### User

### Event

### Note

## Response Data Overview
All responses will be sent back in the form of a JSON string (even those in which an error has occurred). Each response JSON will be in the following structure:

```JSON
"{
  "status":
  "payload":
}"
```
In this JSON, the status key will have the value of the HTTP status of the response while the payload key will have the value of what ever information was sent back (data, error messages, etc.). This standard JSON response is chosen so that the front end may always act the same way in process down to data regardless of the response sent. As an example see the JavaScript code below:

```JavaScript
fetch(url)
  .then( (res) => return res.json())
  .then( (data) => {
    //Check response status and handle errors
    if(data.status !== 200){
      //Handle errors with error messages in payload
    }
    //Required data is in payload
  });
```

For specifics of the payload that will be sent, refer to each individual section.

## Endpoints
The endpoints are organized into domains of functionality.  The domains are Login/Register, Events, Users, Acquaintances, Notes. In each section can be found some useful information.
###### Parameters
This table specifies any parameters that need to be added to an endpoint, such as an ID.
###### URL
This table lists each endpoint in the section. Along with the URL are the methods that are allowed, a brief description of the endpoint as well as possible HTTP statuses that can be sent back with the response.

### Login/Register
These are the endpoints that handle logging in a user and registering new users.  Special note should be paid towards what information is expected in both cases (see notes after the table). All endpoints here should be prefaced with /api/ (e.g. /login -> /api/login)

#### URLs
| URL       | Method  | Description          | Possible Statuses  |
|:----------|:-------:|:---------------------|:-------------------|
| /login    | POST    | Log in a user        | 200, 400, 401, 500 |
| /register | POST    | Register a new user  | 200, 400, 500      |

### Events
These are the endpoints that handle events. Many of these endpoints require admin privileges, so take note from the table. All endpoints here should be prefaced with /api/events (i.e. / -> /api/events/ OR /{eventId} -> /api/events/{eventId} , ETC)

#### Parameters
|Parameter | Description                                    |
|:---------|:-----------------------------------------------|
| {eventId}| The unique identification number for event.    |

#### URLs
| URL               | Method  | Description                 | Admin Required | Possible Statuses       |
|:------------------|:-------:|:----------------------------|:--------------:|:------------------------|
| /                 | GET     | Gets all events             | FALSE          | 200, 204, 500           |
| /                 | POST    | Creates new eventId         | TRUE           | 201, 400, 403, 500      |
| /{eventId}        | GET     | Gets a specific event       | FALSE          | 200, 404, 500           |
| /{eventId}        | PUT     | Updates a specific event    | TRUE           | 200, 304, 400, 403, 500 |
| /{eventId}        | DELETE  | Deletes a specific eventId  | TRUE           | 200, 403, 404, 500      |
| /{eventId}/start  | POST    | Marks an event as started   | TRUE           | 200, 403, 404, 500      |
| /{eventId}/stop   | POST    | Marks an event as finished  | TRUE           | 200, 403, 404, 500      |
| /{eventId}/rsvp   | POST    | Adds the user as going      | FALSE          | 200, 404, 500           |

#### Payload
| URL               | Status  | Payload  |
|:------------------|:--------|:---------|
| /                 | 200     | [Event]  |
| /{eventId}        | 200     | Event    |

NOTE: for all HTTP statuses that are not 200, the payload will be an array of error messages that may be displayed or used.

### Users
These endpoints handle retrieving information pertaining to a user.  As it stands most information is not need directly from a user but users are found consequently through requests for event, etc. All end points in this section should be prefaced with /api/users (i.e / -> /api/users OR /{userId} -> /api/users/{userId}).

#### Parameters
|Parameter | Description                                    |
|:---------|:-----------------------------------------------|
| {userId} | The unique identification number for a user.   |

#### URLs
| URL                | Method  | Description            | Possible Statuses |
|:-------------------|:-------:|:-----------------------|:------------------|
| /{userId}          | GET     | Gets a specific user   | 200, 404, 500     |
| /block/{id}        | POST    | Block a specified user | 200, 404, 500     |

#### Payload
| URL        | Status | Payload   |
|:-----------|:------:|:----------|
| /{userId}  | 200    | User      |

NOTE: for all HTTP statuses that are not 200, the payload will be an array of error messages that may be displayed or used.


### Acquaintance
These endpoints are used to create, find, and remove acquaintance requests between the active user and some other user. All endpoints in this section should be prefaced with /api/acquaintance (e.g /{userId} -> /api/acquaintance/{userId}).

#### Parameters
|Parameter | Description                                      |
|:---------|:-------------------------------------------------|
| {userId} | The unique identification number for a user.     |
| {id}     | The unique identification number to another user |
| {reqId}  | The unique identification number to a request    |

#### URLs
| URL                | Method  | Description                                     | Possible Statuses      |      
|:-------------------|:-------:|:------------------------------------------------|:-----------------------|
| /request/to/{id}   | POST    | Create an acquaintance request                  | 200, 404, 500          |
| /of/{userId}       | GET     | Get all acquaintances of specific user          | 200, 404, 500          |
| /incoming/{userId} | GET     | Get all incoming acquaintance requests          | 200, 404, 500          |
| /outgoing/{userId} | GET     | Get all outgoing acquaintance requests          | 200, 404, 500          |
| /accept/{reqId}    | POST    | Accepts an acquaintance request                 | 200, 404, 500          |
| /{aqId}            | DELETE  | Delete a specific acquaintance                  | 200, 400, 404, 500     |
| /request/{reqId}   | DELETE  | Delete a specific acquaintance request          | 200, 400, 404, 500     |

#### Payload
| URL                 | Status | Payload   | Description                                                                |
|:--------------------|:------:|:----------|:---------------------------------------------------------------------------|
| /of/{userId}        | 200    | [User]    | An array of all users that are acquaintances of the specific user          |
| /incoming/{userId}  | 200    | [User]    | An array of users who are requesting an acquaintance with a specific user  |
| /outgoing/{userId}  | 200    | [User]    | An array of users currently being requested by a specific user             |

NOTE: for all HTTP statuses that are not 200, the payload will be an array of error messages that may be displayed or used.

### Notes
These endpoints are for creating, finding, updating, and deleting notes that a user can make about an acquaintance. All endpoints in this section should be prefaced with /api/notes (e.g /{userId} -> /api/notes/{userId}).

#### Parameters
|Parameter | Description                                      |
|:---------|:-------------------------------------------------|
| {userId} | The unique identification number for a user.     |
| {id}     | The unique identification number to another user |
| {noteId} | The unique identification number to a noteId     |

#### URLs
| URL              | Method  | Description                               | Possible Statuses  |
|:-----------------|:-------:|:------------------------------------------|:-------------------|
| /about/{id}      | POST    | Create a new note about a specific person | 200, 404, 500      |
| /{noteId}        | GET     | Get a specific note                       | 200, 400, 404, 500 |
| /{noteID}        | PUT     | Update a specific note                    | 200, 400, 404, 500 |
| /{noteId}        | DELETE  | Deletes a specific note                   | 200, 400, 404, 500 |
| /for/{userId}    | GET     | Get all notes for a specified user        | 200, 404, 500      |

#### Payload
| URL           | Status | Payload   |
|:--------------|:------:|:----------|
| /{noteId}     | 200    | Note      |
| /for/{userId} | 200    | [Note]    |

NOTE: for all HTTP statuses that are not 200, the payload will be an array of error messages that may be displayed or used.
