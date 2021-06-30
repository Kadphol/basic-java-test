GET /books
```json
[
  {
    "id": 1,
    "title": "Harry Potter and Philosopher's Stone",
    "price": 100,
    "stock": 100
  },
  {
    "id": 2,
    "title": "Harry Potter and Chamber of Secrets",
    "price": 100,
    "stock": 100
  },
  ...
]
```
GET /books/{:id}
```json
{
 "id": 1,
 "title": "Harry Potter and Philosopher's Stone",
 "price": 100,
 "stock": 100
}
```
POST /purchase

Request
```json
[
  {
    "id": 1,
    "amount": 2
  },
  {
    "id": 2,
    "amount": 2
  }
]
```
Response
```json
{
  "price": 380
}
```
