# Smart Clinic

JavaFX desktop app for clinic management (PBO coursework).

## Prerequisites

- **JDK 17+** (workspace uses Temurin JDK 25)
- **JavaFX SDK 21.0.11**
- **MySQL** on `localhost:3306`
- **Extension Pack for Java** in Cursor/VS Code

## Setup

1. Install the [Extension Pack for Java](https://marketplace.visualstudio.com/items?itemName=vscjava.vscode-java-pack) when prompted.
2. Import `smartClinic.sql` into MySQL to create the `smart_clinic` database.
3. Ensure `lib/mysql-connector-j-*.jar` is present (see `lib/README.txt` if missing).
4. Open this folder in Cursor and wait for the Java project to finish importing.

## Run

1. Open **Run and Debug** (F5).
2. Choose **Run JavaFX**.
3. The dashboard should open with the title **Smart Clinic**.

Database connection settings are in `src/database/DBConnection.java` (default: user `root`, empty password).

## Troubleshooting

- **JavaFX not found**: Check that the path in `.vscode/launch.json` `vmArgs` matches your JavaFX SDK `lib` folder.
- **Wrong Java version**: Use **Java: Configure Java Runtime** and confirm **JavaSE-25** is the workspace default.
- **Database errors**: Start MySQL, import `smartClinic.sql`, and verify credentials in `DBConnection.java`.

