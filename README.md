<p align="center">
    <img src="https://github.com/LAL4F/Uboa-VMF-Obfuscator/assets/168286052/13f97225-05d7-4e96-aea8-913d960fcb40" style="width:60%">
</p>

---

A GUI obfuscator for VMF (Valve Map Format) files. A robust and configurable program written in Java Swing with support for batch operations.

### Features
<ul>
    <li>UI personalization</li>
    <li>Responsive, adaptable UI made for all screen resolutions</li>
    <li>Support for individual and batch operations</li>
    <li>Entity targetname and origin obfuscation</li>
    <li>Hammer Editor integration (allows you to quickly open newly obfuscated maps in the Editor)</li>
    <li>Entity dictionary. The ability to define which entities have their origin obfuscated, useful for including non-HL2 entities</li>
    <li>Customizable sound notifications</li>
    <li>Inefficient algorithms</li>
</ul>

## User Guide
### Basic Operation



https://github.com/LAL4F/Uboa-VMF-Obfuscator/assets/168286052/73e3e3ba-dfdd-45b0-9d5f-eb99a79337cf



<ol>
    <li>Open Uboa through the .exe or .jar executable</li>
    <li>Set input path to either an individual file or a directory for batch operations</li>
    <li>Set output path if necessary</li>
    <li>Check obfuscation parameters</li>
    <li>Click the big obfuscation button and wait</li>
    <li>???</li>
    <li>Profit</li>
</ol>

### Origin Obfuscations

<ul>
    <li>Overlap: all whitelisted entities will be moved to the first suitable coordinates</li>
    <li>Randomize: all whitelisted entities will exchange origins, ensuring no overlap whatsoever</li>
    <li>Randomize w/ Overlap: all whitelisted entities will exchange origins, allowing overlap</li>
</ul>

### Targetname Obfuscations

<ul>
    <li>Exchange: all entities will exchange names</li>
    <li>Randomize: all entities will have their names set to a string of random characters</li>
</ul>

**WARNING!!** targetname obfuscation might be iffy on more complex features like wildcards, this will break your I/O

### Entity Dictionary (whitelist)

This is what you'll use in order to introduce new entity definitions into the application. It's a plain text file that resides in the same directory as the executable and it will be rebuilt if not found. You could modify it with any text editor of your choice, but Uboa provides you with the tools to add and remove entities from this list (this can be found in the Options tab). Essentially, any entity that is written into this list will have both their **targetname** and **origins** obfuscated. Entities **not** included in this list will exclusively have their **targetname** obfuscated.

Here's an example of the Entity Dictionary in question. Origin obfuscation results before and after adding **editor_text** to the entity dictionary:

<p align="center">
    <img src="https://github.com/LAL4F/Uboa-VMF-Obfuscator/assets/168286052/e952ede7-422a-4271-a6ef-caefdd65010a" style="width:80%">
</p>

### Sound Alerts

In order to notify users when the program is done obfuscating a file, finishes a batch operation or runs into an error, a sound notification will play. All of this can be found by going into the Sound panel in the Options tab. There you'll have total control over which sounds should play, the master volume of the application which is set to 50% by default to prevent bleeding eardrums, and even the ability to modify what sound plays in case of an event. This effectively makes Uboa an MP3 player. Only .wav files are supported at the moment.

<p align="center">
    <img src="https://github.com/LAL4F/Uboa-VMF-Obfuscator/assets/168286052/5ca641ba-996c-41a8-b1bd-ba6aac7802aa" style="width:50%">
    <img src="https://github.com/LAL4F/Uboa-VMF-Obfuscator/assets/168286052/8b0e0169-a643-4afb-91fd-1630ab3d6e88" style="width:20%">
</p>

To add custom sounds to the application, create a folder named **sound** in the same directory as the applicacion executable and place your .wav files there. This folder should be created automatically anyway.

## Open Source: Developer's Guide

You might want to open the project in Apache NetBeans, as it is the IDE used in the creation of this application. Other IDEs should work fine though. Make sure to always build the project with dependencies or the equivalent in your IDE of choice, or else it will predictably not launch. Launch4J was used to output a Windows executable from the resulting .jar file. 

TODO: in-depth documentation

## Screenshots

<p align="center">
    <img src="https://i.imgur.com/OKckIZa.jpeg" style="width:70%">
    <p align="center"><img src="https://i.imgur.com/rvbH1uO.jpeg" style="width:40%">
    <img src="https://i.imgur.com/Zz0q0hG.jpeg" style="width:40%">
</p>

## License

In plain English: you're allowed to replicate this program, modify it and do whatever you want with it.

## Credits

[JNA](https://github.com/java-native-access/jna)

[JNA File Chooser](https://github.com/steos/jnafilechooser)

[FormDev Software FlatLaf](https://www.formdev.com/flatlaf/)

[Crowbar & ZeqMacaw for inspiring the GUI](https://steamcommunity.com/groups/CrowbarTool)
