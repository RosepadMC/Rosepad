# Rosepad Server&Client

Rosepad is a custom Lilypad QA server/client based on [Smaed's unofficial jars](https://github.com/AlphaVerUnofficialJars)
focused on adding new features to the game.

## Installation

1. Install [JDK8](https://adoptium.net/temurin/releases/?version=8)
2. Clone this repository `$ git clone https://github.com/RosepadMC/Rosepad --recursive`
3. Run `./gradlew prepare injectClasses processPatches shadowJar pack` and wait for build to finish

> /!\\ Caution! /!\\
>
> If you have multiple JDKs installed, use `-Dorg.gradle.java.home`
> JVM argument to force use of JDK8. A warning will be displayed if
> building with JDK9 and above

## Running

Download the jar from the [Releases Page](https://github.com/RosepadMC/Rosepad/releases/tag/beta) or
the latest [Dev Build](https://nightly.link/RosepadMC/Rosepad/workflows/main/master) if you want to
help testing Rosepad

Installation help
- [MultiMC/PrismLauncher](docs/installing/multimc.md)

Rosepad can generate keys, therefore no online registation required

## Contributing

Since Minecraft is not licensed under a free software license, we can't share its source code, any modifications
must be stored in `.patch` files. Do `./gradlew createPatches ejectClasses` to create new patches. Make sure to
test your patches before making a pull request

Please follow the terms of GPLv3 license (please do, I'm serious)
