# 🔗 Link-Sender

**Link-Sender** is a lightweight Paper plugin that allows players to create, manage, and share clickable links directly in Minecraft chat. 🌐 Perfect for servers that want to provide easy access to websites, social media, Discord servers, or any external links.

## ✨ Features

- 🛠️ Create and manage links easily via commands  
- 📤 Send clickable links to yourself or other players  
- 🔒 Customizable permissions for full control  
- ⚙️ Works with Minecraft Paper 1.21+  

## 📜 Commands

| Command | Description | Permission |
|---------|-------------|------------|
| `/link` | Shows basic command usage | - |
| `/link create <name> <url>` | ➕ Create a new link | `link.create` |
| `/link delete <name>` | ❌ Delete an existing link | `link.delete` |
| `/link list` | 📋 List all available links | `link.list` |
| `/link reload` | 🔄 Reload links from `links.yml` | `link.reload` |
| `/link <name>` | 👤 Show a clickable link to yourself | `link.me` |
| `/link <name> <player>` | 📨 Send a clickable link to another player | `link.send` |

### Examples

```
/link create discord https://discord.gg/example
/link discord
/link discord Steve
/link delete discord
```

## 🔐 Permissions

| Permission | Description | Default |
|------------|-------------|---------|
| `link.create` | ➕ Allows creating links | OP |
| `link.delete` | ❌ Allows deleting links | OP |
| `link.list` | 📋 Allows viewing the link list | Everyone |
| `link.reload` | 🔄 Allows reloading the links file | OP |
| `link.send` | 📨 Allows sending links to other players | OP |
| `link.me` | 👤 Allows sending links to yourself | Everyone |
| `link.*` | 🌟 All permissions | OP |
| `link.op` | 🛡️ Operator-level permissions | OP |
| `link.default` | ⚙️ Default basic permissions | Everyone |

## 📦 Installation

1. 📥 Download the latest `.jar` from Modrinth.  
2. 📂 Place it in your server's `plugins` folder.  
3. ▶️ Start or reload your server.  
4. 📝 Configure your links in `plugins/Link-Sender/links.yml`.  

## ⚙️ Configuration

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

## 📝 Notes

- 👥 Only players can execute link commands.  
- 💬 Clickable links use Minecraft's Adventure text API for a clean in-chat experience.  
- ⚙️ Requires Paper server 1.21+ and Java 21.

## 🛠️ Building from Source

This project uses Maven.

```
git clone https://github.com/yourusername/Link-Sender.git
cd Link-Sender
mvn clean package
```

The compiled plugin will appear in:

```
target/Link-Sender-1.3.0.jar
```

## 🚀 Future ideas

- 🎨 Custom clickable message formats (colors, hover text, prefixes)
- ⏳ Cooldowns to prevent link spam
- 🔗 GUI menu to browse and click links easily
- 🔒 Private links (only visible to specific players or groups)
- 🎯 Permission-based link visibility

## 👤 Author

Developed by **Wilkcraft**

## 📄 License

This project is licensed under the **MIT License** - see the [LICENSE](LICENSE) file for details.

---

🚀 Enhance your server by making important links easily accessible to your players!
