# Aplikasi Enigma Student System Information

## Deskripsi

Enigma SIS atau Enigma Student System Information adalah API pengelola sistem akademik Akademi Enigma.
API ini menyediakan berbagai fitur yang dapat diakses oleh pengguna yang terdaftar sebagai siswa maupun pengajar di Akademi Enigma.

### Fitur
Adapun fitur-fitur yang Enigma SIS ini adalah sebagai berikut:

1. Register dan Login Akun
2. Pengelola data Siswa dan Guru
3. Mesin pencari data Mata Pelajaran
4. Pendaftaran kelas

### Cara Menjalankan Test:

1. Pastikan Anda telah menginstal Java Development Kit (JDK) `Minimal Versi 17` dan Maven di komputer Anda.
2. Pastikan Anda memiliki API Testing Tools, seperti `Postman`.
3. Jalankan `EnigmaSISApplication`.


## API Specification

### Auth API

#### Register

Request :

- Method : POST
- Endpoint : `/api/v1/sis-auth/register`
- Header :
    - Content-Type: application/json
    - Accept: application/json
- Body :

```json 
{
  "username": "string",
  "password": "string",
  "email": "string"
}
```

- Catatan : email yang dapat didaftarkan adalah email `@student.enigma.ac.id` dan `@teacher.enigma.ac.id`

Response :

```json 
{
  "statusCode": "integer",
  "message": "string",
  "data": {
    "username": "string",
    "roles": [
      "string"
    ]
  },
  "paging": null
}
```

#### Login

Request :

- Method : POST
- Endpoint : `/api/v1/sis-auth/login`
- Header :
    - Content-Type: application/json
    - Accept: application/json
- Body :

```json 
{
  "username": "string",
  "password": "string"
}
```

Response :

```json 
{
  "statusCode": "integer",
  "message": "string",
  "data": {
    "username": "string",
    "token": "string",
    "roles": [
      "string"
    ]
  },
  "paging": null
}
```

### Student API

#### Get Student By Id

Request :

- Method : GET
- Endpoint : `/api/v1/students/{id}`
- Header :
    - Authorization: Bearer Token 
    - Content-Type: application/json
    - Accept: application/json

Response :

```json 
{
  "statusCode": "integer",
  "message": "string",
  "data": {
    "id": "string",
    "name": "string",
    "studyGroup": "string",
    "studentEmail": "string",
    "userAccountId": "string"
  },
  "paging": null
}
```

#### Get All Students

Request :

- Method : GET
- Endpoint : `/api/v1/students`
- Header :
    - Authorization: Bearer Token
    - Content-Type: application/json
    - Accept: application/json
- Query Param :
    - name : string `optional`
    - page : string

Response :

```json 
{
  "statusCode": "integer",
  "message": "string",
  "data": [{
    "id": "string",
    "name": "string",
    "studyGroup": "string",
    "studentEmail": "string",
    "userAccountId": "string"
  }],
  "paging": {
    "totalPage": "integer",
    "totalElement": "long",
    "page": "integer",
    "size": "integer",
    "hasNext": "boolean",
    "hasPrevious": "boolean"
  }
}
```

#### Update Student

Request :

- Method : PUT
- Endpoint : `/api/v1/students`
- Header :
    - Authorization: Bearer Token for corresponding student
    - Content-Type: application/json
    - Accept: application/json
- Body :

```json 
{
  "id": "string",
  "name": "string",
  "birthDate": "date",
  "studyGroup": "string",
  "mobilePhone": "string"
}
```

Response :

```json 
{
  "statusCode": "integer",
  "message": "string",
  "data": {
    "id": "string",
    "name": "string",
    "studyGroup": "string",
    "studentEmail": "string",
    "userAccountId": "string"
  },
  "paging": null
}
```

#### Delete Student

Request :

- Method : DELETE
- Endpoint : `/api/v1/students/{id}`
- Header :
  - Authorization: Bearer Token for role `SUPER_ADMIN`
  - Accept: application/json

Response :

```json 
{
  "statusCode": "integer",
  "message": "string",
  "data": "OK",
  "paging": null
}
```

### Teacher API

#### Get Teacher By Id

Request :

- Method : GET
- Endpoint : `/api/v1/teachers/{id}`
- Header :
    - Authorization: Bearer Token
    - Content-Type: application/json
    - Accept: application/json

Response :

```json 
{
  "statusCode": "integer",
  "message": "string",
  "data": {
    "id": "string",
    "name": "string",
    "status": "boolean",
    "teacherEmail": "string",
    "userAccountId": "string"
  },
  "paging": null
}
```

#### Get All Teachers

Request :

- Method : GET
- Endpoint : `/api/v1/teachers`
- Header :
    - Authorization: Bearer Token
    - Content-Type: application/json
    - Accept: application/json
- Query Param :
    - name : string `optional`
    - page : string

Response :

```json 
{
  "statusCode": "integer",
  "message": "string",
  "data": [{
    "id": "string",
    "name": "string",
    "status": "boolean",
    "teacherEmail": "string",
    "userAccountId": "string"
  }],
  "paging": {
    "totalPage": "integer",
    "totalElement": "long",
    "page": "integer",
    "size": "integer",
    "hasNext": "boolean",
    "hasPrevious": "boolean"
  }
}
```

#### Update Teacher

Request :

- Method : PUT
- Endpoint : `/api/v1/teachers`
- Header :
    - Authorization: Bearer Token for corresponding teacher
    - Content-Type: application/json
    - Accept: application/json
- Body :

```json 
{
  "id": "string",
  "name": "string",
  "mobilePhone": "string"
}
```

Response :

```json 
{
  "statusCode": "integer",
  "message": "string",
  "data": {
    "id": "string",
    "name": "string",
    "status": "boolean",
    "teacherEmail": "string",
    "userAccountId": "string"
  },
  "paging": null
}
```

#### Update Teacher Status

Request :

- Method : PUT
- Endpoint : `/api/v1/teachers/{id}`
- Header :
    - Authorization: Bearer Token for role `SUPER_ADMIN`
    - Content-Type: application/json
    - Accept: application/json
- Query Param
    - status : string

Response :

```json 
{
  "statusCode": "integer",
  "message": "string",
  "data": "OK",
  "paging": null
}
```

#### Delete Teacher

Request :

- Method : DELETE
- Endpoint : `/api/v1/teachers/{id}`
- Header :
    - Authorization: Bearer Token for role `SUPER_ADMIN`
    - Accept: application/json

Response :

```json 
{
  "statusCode": "integer",
  "message": "string",
  "data": "OK",
  "paging": null
}
```

### Subject API

#### Create Subject

Request :

- Method : POST
- Endpoint : `/api/v1/subject`
- Header :
    - Authorization: Bearer Token for role `SUPER_ADMIN`
    - Content-Type: application/json
    - Accept: application/json
- Body :

```json 
{
  "id": "string",
  "name": "string",
  "lessonsHours": "integer",
  "teacherId": "string"
}
```

Response :

```json 
{
  "statusCode": "integer",
  "message": "string",
  "data": {
    "id": "string",
    "name": "string",
    "lessonsHours": "integer",
    "teacherId": "string"
  },
  "paging": null
}
```

#### Get Subject By Id

Request :

- Method : GET
- Endpoint : `/api/v1/subjects/{id}`
- Header :
    - Authorization: Bearer Token
    - Content-Type: application/json
    - Accept: application/json

Response :

```json 
{
  "statusCode": "integer",
  "message": "string",
  "data": {
    "id": "string",
    "name": "string",
    "lessonsHours": "integer",
    "teacherId": "string"
  },
  "paging": null
}
```

#### Get All Subjects

Request :

- Method : GET
- Endpoint : `/api/v1/teachers`
- Header :
    - Authorization: Bearer Token
    - Content-Type: application/json
    - Accept: application/json
- Query Param :
    - name : string `optional`
    - page : string

Response :

```json 
{
  "statusCode": "integer",
  "message": "string",
  "data": [{
    "id": "string",
    "name": "string",
    "lessonsHours": "integer",
    "teacherId": "string"
  }],
  "paging": {
    "totalPage": "integer",
    "totalElement": "long",
    "page": "integer",
    "size": "integer",
    "hasNext": "boolean",
    "hasPrevious": "boolean"
  }
}
```

#### Update Teacher

Request :

- Method : PUT
- Endpoint : `/api/v1/teachers`
- Header :
    - Authorization: Bearer Token for role `SUPER_ADMIN`
    - Content-Type: application/json
    - Accept: application/json
- Body :

```json 
{
  "id": "string",
  "name": "string",
  "lessonsHours": "integer",
  "teacherId": "string"
}
```

Response :

```json 
{
  "statusCode": "integer",
  "message": "string",
  "data": {
    "id": "string",
    "name": "string",
    "lessonsHours": "integer",
    "teacherId": "string"
  },
  "paging": null
}
```

#### Delete Subject

Request :

- Method : DELETE
- Endpoint : `/api/v1/teachers/{id}`
- Header :
    - Authorization: Bearer Token for role `SUPER_ADMIN`
    - Accept: application/json

Response :

```json 
{
  "statusCode": "integer",
  "message": "string",
  "data": "OK",
  "paging": null
}
```

### Enrollment API

#### Create Enrollment

Request :

- Method : POST
- Endpoint : `/api/v1/enrollments`
- Header :
    - Authorization: Bearer Token for corresponding student
    - Content-Type: application/json
    - Accept: application/json
- Body :

```json
{
  "studentId": "string",
  "enrollmentTerm": "string",
  "enrollmentDetails": [
    {
      "subjectId": "string"
    },
    {
      "subjectId": "string"
    }
  ]
}
```

Response :

```json
{
  "statusCode": "integer",
  "message": "string",
  "data": {
    "id": "string",
    "studentId": "string",
    "enrollmentTerm": "string",
    "enrollmentDetails": [{
      "id": "string",
      "subjectId": "string",
      "lessonsHours": "integer",
      "teacherId": "string"
    }]
  },
  "paging": null
}
```

#### Get Enrollments

Request :

- Method : GET
- Endpoint : `/api/v1/enrollments/{id_student}`
- Header :
    - Authorization: Bearer Token for corresponding student
    - Accept: application/json
- Body :

Response :

```json
{
  "statusCode": "integer",
  "message": "string",
  "data": {
    "id": "string",
    "studentId": "string",
    "enrollmentTerm": "string",
    "enrollmentDetails": [{
      "id": "string",
      "subjectId": "string",
      "lessonsHours": "integer",
      "teacherId": "string"
    }]
  },
  "paging": null
}
```
