# Link-Sender

A lightweight **PaperMC plugin** that allows server administrators to create, manage, and send clickable links to players directly in chat.

Players can easily open websites, Discord invites, store pages, or any other URL with a single click.

## Features

- Create clickable links directly from in-game commands
- Send links to yourself or other players
- Store links in a persistent `links.yml` file
- Tab completion support
- Permission-based system
- Lightweight and simple configuration
- Built for **Paper 1.21+**

## Installation

1. Download the latest release `.jar` file.
2. Place the file in your server's `plugins` folder.
3. Start or restart your server.
4. The plugin will automatically generate a `links.yml` file.

## Commands

| Command | Description |
|-------|-------------|
| `/link` | Shows basic command usage |
| `/link create <name> <url>` | Create a new link |
| `/link delete <name>` | Delete an existing link |
| `/link list` | View available links |
| `/link reload` | Reload the `links.yml` file |
| `/link <name>` | Send a link to yourself |
| `/link <name> <player>` | Send a link to another player |

### Examples

```
/link create discord https://discord.gg/example
/link discord
/link discord Steve
/link delete discord
```

## Permissions

| Permission | Description | Default |
|-----------|-------------|--------|
| `link.create` | Create links | OP |
| `link.delete` | Delete links | OP |
| `link.list` | View link list | Everyone |
| `link.reload` | Reload links.yml | OP |
| `link.send` | Send links to other players | OP |
| `link.me` | Send links to yourself | Everyone |
| `link.*` | All permissions | OP |
| `link.op` | Operator permissions | OP |
| `link.default` | Basic permissions | Everyone |

## Configuration

Links are stored in:

```
plugins/Link-Sender/links.yml
```

Example configuration:

```yaml
links:
  discord: https://discord.gg/example
  store: https://store.example.com
  website: https://example.com
```

Players can access them with:

```
/link discord
```

## Requirements

- **Java 21**
- **PaperMC 1.21+**

## Building from Source

This project uses **Maven**.

```
git clone https://github.com/yourusername/Link-Sender.git
cd Link-Sender
mvn clean package
```

The compiled plugin will appear in:

```
target/Link-Sender-1.3.0.jar
```

## Author

Developed by **Wilkcraft**

## License

This project is open-source. You may modify and distribute it following the repository license.
