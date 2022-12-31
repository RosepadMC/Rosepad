# Rosepad Server&Client

Rosepad is a custom Lilypad QA server/client based on [LPUJ](https://github.com/AlphaVerUnofficialJars)
focused on adding new features to the game.

## Building

1. Install [JDK8](https://adoptium.net/temurin/releases/?version=8) and [Git](https://git-scm.com/) (Git Bash on Windows)
2. Clone this repository `$ git clone https://github.com/RosepadMC/Rosepad --recursive`
3. Run `$ ./gradlew prepare injectClasses processPatches pack` and wait for build to finish

## Running

Download the jar from the [Releases Page](https://github.com/RosepadMC/Rosepad/releases/tag/beta) or
the latest [Dev Build](https://nightly.link/RosepadMC/Rosepad/workflows/main/master) if you want to
help testing Rosepad

Installation help
- [MultiMC/PrismLauncher](docs/installing/multimc.md)
- [Minecraft Launcher](docs/installing/vanilla.md)

Rosepad can generate keys, therefore no online registration required

## Contributing

Since Minecraft is not licensed under a free software license, we can't share its source code, any modifications
must be stored in `.patch` files. Do `./gradlew createPatches ejectClasses` to create new patches. Make sure to
test your patches before making a pull request

> Rosepad is licensed under [GPLv3](https://www.gnu.org/licenses/gpl-3.0.en.html). If you are publishing your changes,
> make sure you are following the terms of the license
