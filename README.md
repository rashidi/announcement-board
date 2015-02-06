# Announcement Board
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