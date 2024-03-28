SocialHub - Fullstack Social Media Application

SocialHub is a fullstack social media application developed using React JS, MaterialUI, Formik, Spring Boot 3, Spring Security 6, JWT, MySQL, and RazorPay. This project aims to provide users with a platform for social interactions akin to Twitter, with features such as tweet posting, editing, liking, sharing, real-time chatting, and seamless payments integration via RazorPay.
Table of Contents

    Technologies Used
    Project Overview
    Features
    Security
    Backend Integration
    How to Run

Technologies Used

    React JS
    MaterialUI
    Formik
    Spring Boot
    Spring Security 3
    JWT
    MySQL
    RazorPay

Project Overview

SocialHub is a Twitter clone developed to provide users with a modern and interactive social media platform. Leveraging the power of React JS and MaterialUI for the frontend, and Spring Boot with Spring Security 3 for the backend, SocialHub offers a seamless user experience with robust security measures and efficient data management.
Features

    Tweet Posting: Users can create and post tweets.
    Tweet Editing: Users can edit their posted tweets.
    Tweet Liking: Users can like tweets.
    Tweet Sharing: Users can share tweets with others.
    Real-time Chatting: SocialHub provides real-time chat functionality for users.
    RazorPay Integration: Seamless payments integration for enhanced user experience.

Security

SocialHub prioritizes security with the following measures:

    Encrypted Login/Signup: User authentication processes are encrypted for security.
    Secure Data Transmission: All data transmission is secured to prevent unauthorized access.
    JWT Token-based Authentication: Robust JWT token-based authentication ensures secure user accounts.

Backend Integration

The backend of SocialHub is integrated with MySQL for efficient data management. This integration enables scalable and secure storage of user information and social interactions within the application.
How to Run

To run the SocialHub application locally, follow these steps:

    Clone the repository from GitHub.
    Navigate to the project directory.
    Ensure you have JDK (Java Development Kit), Node.js, npm, and MySQL Server installed on your system.
    Configure MySQL database settings in the application.properties file.
    Build the frontend:

    bash

cd frontend
npm install
npm run build

Run the Spring Boot application:

bash

    mvn spring-boot:run

    Access the application in your browser at http://localhost:8080.

That's it! You have successfully set up and run the SocialHub application locally.
