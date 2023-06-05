# Building Rosepad from source

Rosepad's build system might be quite frustrating to use for newcomers, so please be sure to get used to it before
contributing to the project

## Setting up
Make sure you have [Java Development Kit (Version 8)](https://adoptium.net/temurin/releases/?version=8) installed. If
you're on Windows, it's preferable to use [Git Bash](https://git-scm.com/downloads), [MSYS2](https://www.msys2.org/) or
[Windows Subsystem for Linux](https://learn.microsoft.com/en-us/windows/wsl/install). For further it will be assumed
that you're using `bash` shell.

You might also want to install [Java Development Kit (Version 17)](https://adoptium.net/temurin/releases/?version=17)
natively. To make Rosepad use system JDK17, define `TOOLKIT_JAVA17` environment variable with the path to JDK17's
[Java Home](https://pds.nasa.gov/datastandards/training/documents/Finding%20and%20Setting%20JAVA%20HOME.pdf).
> Due to JDK autodownload bug on Windows, users of that OS have to complete this step anyway

After cloning the repo (`git clone https://github.com/RosepadMC/Rosepad.git --recursive --depth 3`), make sure you are
using Java 8 (check with `java -version`). If not, set `JAVA_HOME` environment variable with
`export JAVA_HOME=/path/to/java_home_8` (this will only set it for your current terminal session). Build Rosepad using
`./gradlew`. Build should take several minutes to complete. Jars are located at `loader/build/libs`

## Modifying code
Rosepad's source code is located at `client/src/main/` and `server/src/main/`. There you can also find `orig` directory,
which is a helper used for creating and applying patches. Modifying files in `orig` may lead to breaking your
development environment, in which case you need to delete the repository and re-do the setup process.

To build modified sources, re-run `./gradlew`. This will automatically create the patches and injects. Injects
generation is only done for code, so any resource injections must be added manually to `client/inject/resources`

To start server or client run `./gradlew runServer` or `./gradlew runClient` respectively. Both can be running at the
same time. Files for server and client are located at `run/<side>`.
