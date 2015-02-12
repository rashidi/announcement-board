# Announcement Board [![Build Status](https://travis-ci.org/StarTrackDevKL/announcement-board.svg?branch=develop)](https://travis-ci.org/StarTrackDevKL/announcement-board) [![Coverage Status](https://coveralls.io/repos/StarTrackDevKL/announcement-board/badge.svg)](https://coveralls.io/r/StarTrackDevKL/announcement-board)
Utilises on web socket

## Building the Project
The project can be build by executing the following gradle command:

```
gradle clean build -Pdb.uri=<database uri> -Pdb.username=<database username> -Pdb.password=<database password>
```

For example:

```
gradle clean build -Pdb.uri=mongodb://localhost/gfk -Pdb.username=gfk -Pdb.password=password
```