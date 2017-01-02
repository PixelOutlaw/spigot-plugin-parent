# spigot-plugin-parent [ ![Download](https://api.bintray.com/packages/pixeloutlaw/pixeloutlaw-jars/spigot-plugin-parent/images/download.svg) ](https://bintray.com/pixeloutlaw/pixeloutlaw-jars/spigot-plugin-parent/_latestVersion)
Maven parent for all Pixel Outlaw Spigot plugins.

## How to Get
Add the following repository to your POM:
~~~
<repositories>
    <repository>
        <snapshots>
            <enabled>false</enabled>
        </snapshots>
        <id>bintray-pixeloutlaw-pixeloutlaw-jars</id>
        <name>bintray</name>
        <url>http://dl.bintray.com/pixeloutlaw/pixeloutlaw-jars</url>
    </repository>
</repositories>
~~~

Add the following dependency to your POM:
~~~
<parent>
    <groupId>io.pixeloutlaw</groupId>
    <artifactId>spigot-plugin-parent</artifactId>
    <version>1.11.2.1</version>
</parent>
~~~