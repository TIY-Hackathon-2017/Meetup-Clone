# GATHER
An event management system.
RESTful API created by David and Vijee
NOTE: This API is in development. The name of endpoints as well as data structures are subject to change. If you are using this API, please check frequently for modifications.

## Data Structures

### User
The table below represents the data returned when a user is requested or part of a payload. An example JSON payload of a single user can be found below the table.

| Parameter | Data Type    | Description                        | Allow Null |
|:---------:|:------------:|:-----------------------------------|:-----------:
| id        | Number (int) | Unique identification of a user    | FALSE      |
| firstname | String       | The first name of a user           | FALSE      |
| lastname  | String       | The last name of a user            | FALSE      |
| username  | String       | The username used for login        | FALSE      |
| email     | String       | The email address of a user        | TRUE       |
| phone     | String       | The phone number of a user         | FALSE      |
| isAdmin   | Boolean      | Flag if the user an administrator  | FALSE      |

##### Example Payload
###### Request
```JavaScript
fetch(/api/users/42)
  .then( (res) => {
    if(res.status !== 200){
      //Errors handled here
    } else {
      res.json()
        .then( (data) => {
          let user = data.payload;
        });
    }
  });
```
###### Response
```JSON
user = {
          "id": "42",
          "firstname": "Douglas",
          "lastname": "Adams",
          "username": "hitchhiker42",
          "email": null,
          "phone": "8675309",
          "isAdmin": "false"
       }
```

### Event
The table below represents the data structure of an event that could be returned as a response or as part of a payload. An example JSON payload of a single event can be found below the table.

| Parameter   | Data Type    | Description                             | Allow Null |
|:-----------:|:------------:|:----------------------------------------|:----------:|
| id          | Number (int) | Unique identification of an event       | FALSE      |
| admin       | User         | ID for user who created the event       | FALSE      |
| name        | String       | Name of the event                       | FALSE      |
| location    | String       | Address for the location                | FALSE      |
| description | String       | Short description for the event         | TRUE       |
| date        | Time-stamp   | Date that the event starts              | FALSE      |
| status      | String       | Current status (new, ongoing, finished) | FALSE      |

##### Example Payload
###### Request
```JavaScript
fetch(/api/events/7)
  .then( (res) => {
    if(res.status !== 200){
      //Errors handled here
    } else {
      res.json()
        .then( (data) => {
          let event = data.payload;
        });
    }
  });
```
###### Response
```JSON
event = {
          "id": "7",
          "admin": {
            "id": "42",
            "firstname": "Douglas",
            "lastname": "Adams",
            "username": "hitchhiker42",
            "email": null,
            "phone": "8675309",
            "isAdmin": "true"
          },
          "name": "Spaceship Meetup",
          "location": "The moon",
          "description": "Let's discuss space travel!",
          "date": "1970-01-01 00:00",
          "status": "IN_PROGRESS"
       }
```

### Note
The table below represents the data structure for a note a user can make about someone at an event. An example JSON payload of a single note can be found below the table.

| Parameter   | Data Type    | Description                        | Allow Null  |
|:-----------:|:------------:|:-----------------------------------|:-----------:|
| id          | Number (int) | Unique identification of a note    | FALSE       |
| author      | User         | User that created the note         | FALSE       |
| about       | User         | User the note is about             | FALSE       |
| body        | String       | Content of the note                | FALSE       |

##### Example Payload
###### Request
```JavaScript
fetch(/api/notes/2)
  .then( (res) => {
    if(res.status !== 200){
      //Errors handled here
    } else {
      res.json()
        .then( (data) => {
          let note = data.payload;
        });
    }
  });
```
###### Response
```JSON
note = {
          "id": "7",
          "author":{
            "id": "42",
            "firstname": "Douglas",
            "lastname": "Adams",
            "username": "hitchhiker42",
            "email": null,
            "phone": "8675309",
            "isAdmin": "true"
          },
          "about":{
            "id": "66",
            "firstname": "Jim",
            "lastname": "Palmer",
            "username": "cakes",
            "email": "oriolemagic@me.com",
            "phone": "1970018",
            "isAdmin": "false"
          },
          "body": "This guy had a weird sweater on."
       }
```

## Response Data Overview
All responses will be sent with a proper HTTP status (refer to the tables below for a list of possible status that each endpoint can return).

#### 200 Status Responses
When an HTTP status of 200 is received all data will be sent via JSON with the following overall structure:

```JSON
{
  "payload":
}
```

In this case, payload will contain the data that was request. More info on what a payload will contain for a specific endpoint can be found in the following tables.

#### Non-200 Status Response
When an HTTP status other than 200 is received this should be caught and not processed as a JSON. In the case of a non-200 response, a more detailed read out of the specific error can be found on the response body.

#### Implementation
Below is a template for an implementation of accessing an api endpoint written in JavaScript.

```JavaScript
fetch(url)
  .then( (res) => {
    if(res.status !== 200){
      //Error message will be on the response body
      let errMsg = res.body;
    } else {
      res.json()
        .then( (data) => {
          //Requested information is in the payload
          let info = data.payload;
        });
    }
  });
```

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
| URL               | Payload  |
|:------------------|:---------|
| /                 | [Event]  |
| /{eventId}        | Event    |

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
| URL        | Payload   |
|:-----------|:----------|
| /{userId}  | User      |

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
| URL                 | Payload   | Description                                                                |
|:--------------------|:----------|:---------------------------------------------------------------------------|
| /of/{userId}        | [User]    | An array of all users that are acquaintances of the specific user          |
| /incoming/{userId}  | [User]    | An array of users who are requesting an acquaintance with a specific user  |
| /outgoing/{userId}  | [User]    | An array of users currently being requested by a specific user             |

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
| URL           | Payload   |
|:--------------|:----------|
| /{noteId}     | Note      |
| /for/{userId} | [Note]    |
