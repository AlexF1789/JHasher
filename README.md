JHasher
===

Simple JavaFX program which computes the (SHA1, SHA256 and SHA512) hash of a file or a text string and checks if it corresponds with a given one. It's meant to be a way easier alternative to other well known softwares doing the same thing.

## Usage
To run the program you need Java 25 or higher (version built on Java 25). You can either download it from the *Release* section of the repo or by building it as its explained in the relative section of this file.

To run it just double click on the .jar file or use the command
```
java -jar jhasher.jar
```

## Building
To build the software you can download the source code (both from the repo itself or from the *Release* section) and use the command
```
mvn package
```

If you prefer to just run it fastly you can use the command
```
mvn javafx:run
```

## Java 8 version
To work on older systems it's available a version, which is not part of the *Release* section of the repo, on the branch *java8/stable*. You can build it in a similar way on systems using Java 8.

Differently from the *main* branch version it doesn't use the AtlantaFX theme and it's meant not to use the features that are not supported from Java 8.

## Credits
The project uses the following open source projects:

- Apache Commons Codec to calculate the hashes
- Papirus Icon Theme for the icon **GPG-Key4** which is used as the application icon
- AtlantaFX for the user interface theme

The code which belongs to each of these projects is distributed according to the license choosen by the creators and has nothing to do with this project one.