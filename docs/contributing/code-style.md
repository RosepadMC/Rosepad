# Code style
> Draft document! 

Whenever you contribute to Rosepad you must follow the Code Style Guidelines so all the code in Rosepad's codebase can
be consistent and easy to understand. While Minecraft codebase is not yet following those guidelines, feel free to
submit styling PRs.

## Indentation and empty lines

1. A file should end with an empty line.

2. Code must be indented with 4 spaces.

```java
// bad

class Code {
        public void doSomething() {
                Utils.staticMethod();
        }
}

// good

class Code {
    public void doSomething() {
        Utils.staticMethod();
    }
}
```

3. Different subtasks a function performs should be separated with empty lines.

```java
// bad

class Code {
    public Map fetchMap() {
        HttpRequest mapFetcher = HttpRequest.get("https://example.com/map.json");
        if (!mapFetcher.ok()) {
            throw new RuntimeException("Failed to fetch map");
        }
        String body = mapFetcher.getBody().toString();
        JSONParser parser = new JSONParser(body);
        Map map = new Map();
        for (String str : parser.toArray().<String>toList()) {
            map.register(str);
        }
        return map;
    }
}

// good

class Code {
    public Map fetchMap() {
        HttpRequest mapFetcher = HttpRequest.get("https://example.com/map.json");
        if (!mapFetcher.ok()) {
            throw new RuntimeException("Failed to fetch map");
        }

        String body = mapFetcher.getBody().toString();
        JSONParser parser = new JSONParser(body);

        Map map = new Map();
        for (String str : parser.toArray().<String>toList()) {
            map.register(str);
        }
        return map;
    }
}
```

4. Each class method must be separated by an empty line except for method overloads and between empty methods (i. e.
interfaces or abstract methods).

```java
// bad

class Code {
    public void methodOne() {}
    public void methodTwo() {}
    public void codeOne() {
        // Code here.
    }
    public void codeTwo() {
        // Code here.
    }
    public void codeTwo(String arg) {
        // Code here.
    }
}

// good

class Code {
    public void methodOne() {}
    public void methodTwo() {}

    public void codeOne() {
        // Code here.
    }

    public void codeTwo() {
        // Code here.
    }
    public void codeTwo(String arg) {
        // Code here.
    }
}
```

5. Class fields must be defined at the top of the class starting with `static final`, `static` and `final`. If there are
multiple fields with the same type and modifiers they must be surrounded with newlines

```java
class Code {
    private static final int SOME_CONSTANT = 0;
    private static final int SOME_CONSTANT_1 = 0;
    private static final int SOME_CONSTANT_2 = 0;
    private static final int SOME_CONSTANT_3 = 0;

    private static final List<String> LIST_OF_MEMES = new ArrayList<>();

    private static int SOME_NON_CONSTANT = 12;

    private final List<String> listOfLocalMemes = new ArrayList<>();
}
```

## Naming and casing

- Classes and interfaces must use PascalCase (`ProtocolPacket`, `Block`)
- Static class fields and enum variants must use UPPERCASE_SNAKE_CASE (`STONE_STAIRS`, `GL_TEXTURE_2D`)
- Variables and method names must use camelCase (`jsonObject`, `block`)

1. Names must clearly represent what something is and what it does.
```java
// bad

class Packets {
    private final NetworkChannel channel;
    private final List<Packet> sendQueue = new ArrayList<>();
    private final List<Packet> receiveQueue = new ArrayList<>();

    public void send(Packet packet) {
        queue.add(packet);
    }

    public @Nullable Packet receive() {
        if (receiveQueue.isEmpty()) {
            return null;
        }

        return receiveQueue.get(0);
    }
}

// good

class NetworkQueue {
    private final NetworkChannel channel;
    private final List<Packet> sendQueue = new ArrayList<>();
    private final List<Packet> receiveQueue = new ArrayList<>();

    public void append(Packet packet) {
        queue.add(packet);
    }

    public @Nullable Packet poll() {
        if (receiveQueue.isEmpty()) {
            return null;
        }

        return receiveQueue.get(0);
    }
}
```

2. Method prefixes must be used depending on what the method does.

- `to` must be used for conversion methods (unless stated by the method name)
- `get` must be used for methods that return a set value
- `set` must be used for methods that set a value
