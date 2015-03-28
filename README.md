# Announcement Board 

[![Build Status](https://travis-ci.org/StarTrackDevKL/announcement-board.svg?branch=develop)](https://travis-ci.org/StarTrackDevKL/announcement-board) 
[![Coverage Status](https://coveralls.io/repos/StarTrackDevKL/announcement-board/badge.svg?branch=develop)](https://coveralls.io/r/StarTrackDevKL/announcement-board?branch=develop)
[![devDependency Status](https://david-dm.org/StarTrackDevKL/announcement-board/dev-status.svg?style=flat)](https://david-dm.org/StarTrackDevKL/announcement-board#info=devDependencies)
___

Send and receive announcements in real time.

## Background
An initiative taken by the Star Track Developers in Kuala Lumpur to gain experience with [AngularJS](https://angularjs.org/) and [MongoDB](http://www.mongodb.org/).

## Badges
Badges in this README are pointing to [develop](https://github.com/StarTrackDevKL/announcement-board/tree/develop).

## Components
We are using several open source components:

- [Spring Boot](http://projects.spring.io/spring-boot/)
- [AngularJS](https://angularjs.org/)
- [Angular Material](https://material.angularjs.org/)
- [SockJS](https://github.com/sockjs/sockjs-client)
- [Stomp-websocket](https://github.com/jmesnil/stomp-websocket)

Exact Spring components that being used can be found in [build.gradle](./build.gradle). While the versions of each Javascript components can be found in [bower.json](./bower.json).

## Building
The project can be build by executing the following command:

```
gradlew clean build -Pdb.uri=<database uri> -Pdb.username=<database username> -Pdb.password=<database password>
```

For example:

```
gradlew clean build -Pdb.uri=mongodb://localhost/gfk -Pdb.username=gfk -Pdb.password=password
```