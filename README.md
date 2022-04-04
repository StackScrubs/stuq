# 🙋‍♂️ STUQ
A full-stack solution for a student queuing system.

Created as a submission for the voluntary project in the course IDATT2105 Fullstack development at NTNU.

*NOTE:* This is *not by any means* a complete solution, as this project went over the course of a week, there was very limited time for such a big project.

## 📝 Documentation

### 💾 Back-end
Exhaustive JavaDoc may be found in the Java class files.

Swagger has also been implemented, documenting all of the models and endpoints of the back-end.
Swagger documentation may be found at http://yourdomain.com/swagger-ui/index.html once the back-end is up and running.
No authentication is required to read this documentation.

The database structure may be found as an EER diagram [on the wiki](https://github.com/StackScrubs/stuq/wiki/Database-EER).
The database purely uses JPA as well, so it's entirely possible to generate your own schema from the source code as well.

## 🔎 Testing
Testing has been done throughout the entirety of the development process using continous integration.

The CI pipeline may be found [here](https://github.com/StackScrubs/stuq/actions/workflows/ci.yml).

Running the tests yourself is also a fairly straight forward process.

Running tests on back-end: `cd backend && mvn surefire:test`

Running tests on front-end: `cd frontend && npm run test:unit`

## 🔨 Building and running
Running STUQ is as easy as heading on over to [releases](https://github.com/StackScrubs/stuq/releases) and downloading the latest Docker compose config there.

The Docker images for the front- and back-end are automatically built and published on push to master due to our [continous delivery pipeline](https://github.com/StackScrubs/stuq/actions/workflows/cd.yml).

Building the project yourself is equally as easy. Simply clone the project and run `docker-compose -f docker-compose.dev.yml build`.

This will cause the front- and back-end Dockerfiles to build and get tagged.

To run the project, simply run:

For production: `docker-compose -f docker-compose.prod.yml up`

For development: `docker-compose -f docker-compose.dev.yml up`

## ⚙️ Functionality
As noted above, the current state of the project is by no means a complete solution.

We at StackScrubs heavily prioritized code quality and stability, which we believe are reflected in the final submission.

The project is therefore heavily documented and tested, with thoroughly reviewed code. Due to this, the amount of functionality may be found to be lacking in comparison to what may be expected of a full solution.

### 💾 Back-end
Nearly all of the back-end endpoints are set up and ready for use, including - but not limited to:
- Full management of student queues, allowing for creation and deletion, as well as adding and removing student from them.
- Creation and usage of user sessions and tokens with automatic cleanup on expiration
- Roles associated with users/teachers and teaching assistants. These are not currently being used, but it is implemeted.
- Partial management of subjects, allowing for creation, deletion, updating and retrieval of stored subjects, the teachers that teach them, the teaching assistants associated with them, assignments handed out in them, active queues for them etc.

### 🏠 Front-end
Main functionality we managed to add here in time, was login and queue form enlisting. Given that a student already has been registered in the database, the user can log in. A student should not be able to create his/her own account, only log in to one. The teacher/admin should have the ability to register students in the future.
In the queueing-form the user can pick assignments to enque with, to enque for submission or help, and optionally add a message. The user should also be able to pick location, but we did not manage to properly add this in time.

Some front-end endpoints to use for the API: 
- Login request/response, with token storage in vuex state.
- Subject requests, get, update, delete.
- Assignment and submission requests for the queuing form. (Currently not entirely working so we substituted it with local data)
