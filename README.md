# JavaUtil

Small Java utility project for date/time helpers and string display-width helpers.

## Requirements

- JDK 8 or newer
- Maven 3.x

## Install Java And Maven

### Ubuntu/Debian

```bash
sudo apt-get update
sudo apt-get install -y openjdk-17-jdk maven
```

### macOS

```bash
brew install openjdk@17 maven
```

If `java` is not found after installing OpenJDK with Homebrew, follow the caveat printed by `brew install openjdk@17` to add Java to your shell profile.

### Windows

Using winget:

```powershell
winget install EclipseAdoptium.Temurin.17.JDK
winget install Apache.Maven
```

After installation, restart the terminal so `java` and `mvn` are available on `PATH`.

### Verify Installation

```bash
java -version
javac -version
mvn -v
```

## Build And Test

```bash
mvn clean test
mvn package
```

## Run

```bash
mvn package
java -cp target/leedox-1.0-SNAPSHOT.jar kr.leedox.App
```

## Project Notes

- `CalendarUtil` contains parsing, formatting, date movement, and date/time field helpers.
- `Util` contains string repeat, right-padding, and display-width helpers.
- The minimum verification gate is `mvn clean test` plus `mvn package`.
