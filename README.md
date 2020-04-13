<h1 align="center">
  <br>
  <a href="https://github.com/Dewy2B/Devent"><img src="https://i.imgur.com/bs04VqR.png" alt="Devent - Dewy's Event System"></a>
  <br>
  Devent
  <br>
</h1>

<h4 align="center">My rubbish Java event system.</h4>

<p align="center">
  <a href="#overview">Overview</a>
  •
  <a href="#usage">Usage</a>
  •
  <a href="#javadocs">Javadocs</a>
  •
  <a href="#license">License</a>
  •
  <a href="#credits">Credits</a>
</p>

# Overview

**Devent** is an event system I whipped up over the course of a day for use in my personal projects.
Nothing much to see here, really. Just another event system. If you want to use it for whatever reason, head **[here](#usage)**.

# Usage

To begin, declare the `maven.dewy.dev` repository and the dependency for Devent:

**Gradle**

```groovy
repositories {
    // Other gay repositories
    maven {
        url "https://maven.dewy.dev/release"
    }
}

dependencies {
    implementation "dev.dewy:devent:LATEST_RELEASE"
}
```

**Maven**

```xml
  <repositories>
    <!-- Other gay repositories -->
    <repository>
      <id>dewy</id>
      <name>dewy</name>
      <url>http://maven.dewy.dev/release</url>
    </repository>
  </repositories>
  <dependencies>
    <dependency>
      <groupId>dev.dewy</groupId>
      <artifactId>devent</artifactId>
      <version>LATEST_RELEASE</version>
    </dependency>
  </dependencies>  
```

Once you've done that, you can begin using Devent for all of your event-y needs.
See `src/test/java/dev/dewy/devent` for an example program / test using Devent.

# Javadocs

Javadocs for all of my public projects, including Devent, can be found [here](https://javadocs.dewy.dev/).

# License

Devent is released under the [MIT](https://github.com/Dewy2b/Devent/blob/master/LICENSE.md) license.

Here's a basic run-down of the license:

**You Can**

- Use Devent or any derivative for commercial purposes.
- Modify Devent and create derivatives.
- Distribute Devent and any derivative.
- Use and modify Devent in private.

**Provided That**

- You include a copy of the [license](https://github.com/Dewy2b/Devent/blob/master/LICENSE.md) and a copyright notice in the software.

**Limitations**

- Liability
- Warranty

# Credits

- Dewy
- Any contributors who may come by <3

### Dedication

To Johanne.