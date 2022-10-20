# JHexcast

### Description

This project is the Java version of the original [Hexcast](https://github.com/jmgray/hexcast) and was undertaken as a way to re-familiarize myself with 
the Java ecosystem after some years away, and as a bit of an ad-hoc way of comparing the `Python` and `Java` working environments. Or, at least _my own_ working environments 
 
The functional purpose of the software itself is detailed in the [Hexcast Readme](https://github.com/jmgray/hexcast/blob/master/README.md).
Since the purpose in building this was really about language, process and tooling I did not re-architect or redesign the original solution, 
save in minor ways. I tried to write in paradigmatic `Java`, if not wholly modern `Java`, but I suspect my `Python` habits may have seeped through.
 

### Usage
* Download the zip or `git clone` the project
* navigate to the cloned or unzipped folder
* Run `build.bat` to create the jar
* Execute `JHexcast` commands:
  * next
  * hexinit
  * sequence


### Observations
`Java` is certainly as wordy as I remember it but that bothered me less than I thought it might. The expressive nature of `Python` with its lack of type-safety,
its extraordinarily handy REPL, and its striking brevity made me a quick convert when I moved away from Java a few years ago. 
I missed all of those things in some degree while working on this project but `Intellij` has made so much of the old drudgery disappear, as I am sure 
Its integration with `gradle` for instance, made setting up that part of the project a snap. Of course, where possible, I prefer to use CLI tools 
when learning new technology. It took me a few tries to get building on the command-line working. 
I am a long time [Jetbrains](https://www.jetbrains.com/) fan, but I am certain other modern IDEs offer such assistance as well.  

I ran into static typing issues more than once. For something of this size that matters only slightly 
but for a larger project it might matter a great deal. Again, IDE to the rescue.