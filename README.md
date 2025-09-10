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

[![GitHub](https://img.shields.io/badge/GitHub-000000?style=for-the-badge&logo=github&logoColor=white)](https://github.com/Sunilrathore435)  
[![LinkedIn](https://img.shields.io/badge/LinkedIn-0077B5?style=for-the-badge&logo=linkedin&logoColor=white)](https://www.linkedin.com/in/sunil-rathore-548/)

🎨 Screenshots (Optional)

You can include some attractive screenshots of your app here to make the README visually appealing. Example:

Login Page
<img width="1897" height="904" alt="Screenshot 2025-09-10 191852" src="https://github.com/user-attachments/assets/cd7af825-71ec-4802-8d35-580cc5080f78" />
Dashboard
<img width="1895" height="913" alt="Screenshot 2025-09-10 191949" src="https://github.com/user-attachments/assets/38ab9bdb-7da0-4611-bb47-655e1a8a28df" />
File Upload/Download
<img width="559" height="793" alt="Screenshot 2025-09-10 192011" src="https://github.com/user-attachments/assets/4c6ceb5d-2fc5-435c-9960-813a0afbd399" />
Shared File View
<img width="1911" height="904" alt="Screenshot 2025-09-10 192031" src="https://github.com/user-attachments/assets/cec10fc2-4314-440f-98da-f1053c31b2ed" />

