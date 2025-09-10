🚀 Bullet File

Bullet File is a secure and robust file management and sharing application built with a modern technology stack. It allows users to securely upload, download, and share files, with authentication handled through popular social providers.

✨ Features

🔒 User Authentication: Securely log in using your Google or GitHub account.

☁️ File Uploads: Upload files easily with support for sizes up to 10GB.

⬇️ File Download: Download your uploaded files with a single click.

🔗 File Sharing: Share files with others via a unique, secure link.

💾 Database Integration: Data is persisted in a MySQL database.

🛠️ Technologies Used
Layer	Technology
Backend	Spring Boot (Java)
Database	MySQL
Authentication	Spring Security, OAuth2
External Providers	Google OAuth, GitHub OAuth
🚀 Getting Started

Follow these instructions to set up and run the project locally.

Prerequisites

Java Development Kit (JDK) 17 or higher

Apache Maven

MySQL Database

GitHub and Google accounts for OAuth credentials

Setup

Clone the Repository

git clone https://github.com/Sunilrathore435/Bullet-file.git
cd Bullet-file


Create .env File

Create a file named .env in the root directory and add your credentials:

# MySQL Password
MySql_Password=your_mysql_password_here

# Google OAuth
Google_client_ID=your_google_client_id_here
Google_secret=your_google_secret_here

# GitHub OAuth
GITHUB_ID=your_github_client_id_here
GITHUB_SECRET=your_github_client_secret_here


⚠️ Note: Using a .env file is the most secure way to manage sensitive information.

Database Configuration

Create a new MySQL database named: cw_bulletfile

The application will automatically create tables on first run using spring.jpa.hibernate.ddl-auto=update.

Run the Application

Run the project using Maven:

./mvnw spring-boot:run


The application will start at: http://localhost:8080

📫 Contact

For any questions or support, reach out to the project author:

Author: Sunil Rathore

GitHub: [<img src="https://cdn.jsdelivr.net/npm/simple-icons@v9/icons/github.svg" width="30"/>](https://github.com/Sunilrathore435)  

LinkedIn: [<img src="https://cdn.jsdelivr.net/npm/simple-icons@v9/icons/linkedin.svg" width="30"/>](https://www.linkedin.com/in/sunil-rathore/)

🎨 Screenshots (Optional)

You can include some attractive screenshots of your app here to make the README visually appealing. Example:

Login Page

Dashboard

File Upload/Download

Shared File View
