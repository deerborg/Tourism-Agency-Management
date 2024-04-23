# Patika Tourism Agency Project

<img src = "assets/banner.png" />

This project is a hotel management system developed to digitize the daily operations of Patika Tourism Agency and optimize customer reservation processes.



## Opening the Project

1. Open the main folder where the `src` directory is located to start the project.

2. You cannot start the project without opening the main folder because the file path will not be found.

3. It is necessary to open the folder where the project is located completely.

4. **Important Note:** If you open the entire folder, you need to correct the path `"src/Log/DBconfigure.properties"` in the `Database.java` file. The correct path should be `Tourism-Agency-Management/Tourism-Agency-Management/src/Log/DBconfigure.properties`.

## Requirements

- Java 22 or higher version.
- [PostgreSQL JDBC driver](https://jdbc.postgresql.org/download.html) (e.g., postgresql-42.7.3.jar)

## Adding the Library

1. Add the downloaded PostgreSQL JDBC driver (e.g., `postgresql-42.7.3.jar`) file to your project's directory structure.

2. After opening your project, add this library to your project in your IDE or the tool you use for compilation. This step ensures that the JDBC driver can be used when compiling the project.


##
---
    Requires Java 22.0.0 to be controlled

## Features

- There are two types of users: admin and agency staff.
- User management: Admin can add, delete, update users and assign roles.
- Hotel management: Agency staff can add and edit hotels.
- Room management: Agency staff can add and price rooms.
- Period management: Agency staff can add periods for hotels.
- Price management: Agency staff can set room prices.
- Room search: Agency staff can search for rooms based on desired criteria and make reservations.
- Reservation management: Agency staff can list, add, update, and delete reservations.

## Technical Details

- The project is designed according to SOLID principles and MVC architecture.
- PostgreSQL is used as the database.
- SwingGUI is used for the user interface.
- The project follows a multi-layered architecture.

## Installation

1. Clone the project:
    ```bash
    git clone https://github.com/deerborg/Tourism-Agency-Management.git
    ```
2. Install PostgreSQL and update the connection details.
3. Open the project in your IDE.
4. Configure the database connection in `src/main/resources/src/Log/DBconfigure.properties`.
5. Compile and run the project.

## Video
[![Watch the video](https://img.youtube.com/vi/64gIdw2fkA0/0.jpg)](https://www.youtube.com/watch?v=64gIdw2fkA0)

## Screenshots

- User Login Screen

<img src = "assets/1.png" />

- Admin Panel

<img src = "assets/3.png" />


- Agency Staff Panel

<img src = "assets/2.png" />

- Hotel Adding Screen

<img src = "assets/2.png" />

- Room Adding Screen

<img src = "assets/8.png" />

- Reservation Screen

<img src = "assets/14.png" />


## Usage

1. Admin logs in and creates the first user.
2. Admin adds agency staff and authorizes them.
3. Agency staff adds hotels and rooms to the system.
4. Agency staff adds periods and sets prices.
5. Agency staff searches for rooms based on customer requests and makes reservations.

## License

This project is licensed under the MIT License. See the [LICENSE](LICENSE) file for details.
