# JHexcast

### Description

This project is the Java version of my original [Hexcast](https://github.com/jmgray/hexcast) and was undertaken as a way to re-familiarize myself with 
the Java ecosystem after some years away.
 
The functional purpose of the software itself is detailed in the [Hexcast Readme](https://github.com/jmgray/hexcast/blob/master/README.md).
The gist of the problem is:
```
Write a program that generates 8-digit hexadecimal codes
The rules are as follows:
1. Every time you run the program, it should emit one 8-digit hexadecimal code;
2. It should emit every possible code before repeating;
3. It should not print "odd-looking" codes such as 0xAAAAAAAA or 0x01234567
   or any commonly used words, phrases, or hexspeak such as 0xDEADBEEF;
4. Codes should be emitted in apparently random order.
```
Since the purpose in building this was really about language, process and tooling I did not re-architect or redesign the original solution, 
save in minor ways. I tried to write in paradigmatic `Java`, if not wholly modern `Java`, but I suspect my `Python` habits may have seeped through.
 

### Usage
* Download the zip or `git clone` the project
* navigate to the cloned or unzipped folder
* Run `build.bat` to create the jar
* Execute `JHexcast` commands<sup>1</sup> (to be run from command line):
  * `next.bat` displays the next code in the sequence as determined by the `hfa.properties` file.
  * `sequence.bat` displays the sequence of nodes that are to be visited (codes to be emitted)
  * `hexinit.bat` initializes the `hfa.properties` file to support Hexadecimal sequence
  
For a full explanation of the algorithm see [explanation](https://github.com/jmgray/hexcast/blob/master/explanation.md)<br><br>
<sup>1. This was built on a windows platform and is not completely cross-platform. I.e, *.bat files only</sup>
