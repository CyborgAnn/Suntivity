# 🌻 Suntivity

## Application Description

Suntivity is a gamified productivity application designed to help children build positive habits by completing tasks, earning rewards, and growing a virtual plant. The application encourages responsibility and motivation by transforming everyday activities into an interactive experience.

Parents can create child accounts, assign tasks, set point values, and approve completed tasks. Children can complete assigned tasks, earn points, grow their virtual plant, and customize their plant using rewards earned through their progress.

The goal of Suntivity is to provide a fun and engaging way for children to develop productivity skills while allowing parents to monitor and encourage progress.

---

# Contributors

The Suntivity development team consists of:

## Cyann T. Williams
Contributed to JavaFX application development, including parent and child account systems, login functionality, task management, plant growth features, file-based data persistence, debugging, testing, and GitHub project management. Also contributed to documentation and the final project demonstration.

## Saron A. Asfaha
Contributed to MVC controller design, UML diagrams, team documentation, meeting participation, and the final project video by introducing the application and demonstrating parent and child account creation.

## Victoria A. Baladez
Contributed UI screen designs, application artwork, character and cosmetic designs, project documentation, data organization, and JavaFX controllers for the start, login, and sign-up screens. Also contributed to meetings and the final project video.

## Samuel H. Coleman
Contributed the Adobe XD prototype, UML diagrams, model design and implementation, team documentation, meetings, and the final project video.

---

# Running the Application

## Requirements

To run Suntivity, the following are required:

- Java Development Kit (JDK) 21 or later
- JavaFX dependencies
- Maven
- IntelliJ IDEA (recommended)
- Internet access may be required initially to download Maven dependencies

---

## Setup Instructions

1. Clone the repository:

```bash
git clone https://github.com/CyborgAnn/Suntivity.git
```

2. Open the project in IntelliJ IDEA.

3. Allow Maven to load all required dependencies from the `pom.xml` file.

4. Verify that the project is configured with JDK 21 or later.

5. Run the application from:

```
src/main/java/edu/utsa/cs3443/suntivity/application/SuntivityApplication.java
```

6. The Suntivity application will launch and display the start screen.

---

# Application Features

## Parent Features

- Create and manage a parent account
- Create child accounts through the parent dashboard
- Generate unique child linking codes
- Assign tasks to children
- Set task descriptions, due dates, and point values
- Create recurring tasks
- Review completed tasks
- Approve or deny submitted tasks
- Monitor linked child accounts

## Child Features

- Log into a personal child account
- View assigned tasks
- Complete tasks for approval
- Earn points from approved tasks
- Track plant growth progress
- Customize a virtual plant
- Purchase customization items using earned points
- Receive notifications and rewards

---

# Plant Growth System

Children's virtual plants grow based on points earned from completed and approved tasks.

| Points | Plant Stage |
|--------|-------------|
| 0 - 250 | Worst Health |
| 251 - 500 | Medium Health |
| 501 - 750+ | Best Health |

---

# Data Storage

Suntivity uses local file storage to save account information and user progress.

Saved parent account information includes:

- Username
- Password
- Linking code
- Connected child accounts

Saved child account information includes:

- Username
- Password
- Points
- Plant growth stage
- Plant customization selections

---

# Technologies Used

- Java
- JavaFX
- FXML
- Maven
- Object-Oriented Programming (OOP)
- Git and GitHub

---

# Application Structure

Suntivity follows an MVC-inspired structure.

## Model Classes

The model package contains the application's data and logic, including:

- Account
- ParentAccount
- ChildAccount
- Task
- Plant
- Item
- PlantStatus

## Controller Classes

The controller package manages user interaction, including:

- LoginController
- NavigationController
- ParentDashboardController
- ChildDashboardController
- CreateChildController
- ShopController
- SignUpController

## View Files

The application interface is created using JavaFX FXML files, including:

- Start screen
- Login screen
- Parent dashboard
- Child dashboard
- Child task view
- Create child account screen
- Shop screen
- Signup screen

---

# Known Issues

The following issues are known in the current version of Suntivity:

- Account information is stored locally using files instead of a database.
- The application currently runs as a desktop JavaFX application and has not been deployed as a mobile application.
- Some interface elements may require additional resizing adjustments depending on screen size.
- Some task-related information may not persist after closing and reopening the application.
- Additional testing may be needed for edge cases involving multiple accounts and task management.

---

# Repository Information

All source code, resources, FXML files, and project files are located in the official Suntivity GitHub repository.

Repository:

https://github.com/CyborgAnn/Suntivity

The final submitted version of the application is located in the `Cyann-code` branch.
