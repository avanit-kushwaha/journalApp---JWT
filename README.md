# 📝 Journal App

A secure and personalized **online journal** application built with **Spring Boot**.  
Users can sign up, log in, and write private journal entries with role-based access control.  
An email is sent on registration and every time a journal entry is created.

---

## 🚀 Features

- 🔐 **JWT Authentication** for secure login
- 📧 **Email Notifications** using JavaMailSender
- 🧑‍🤝‍🧑 **User Registration & Roles** (`USER`, `ADMIN`)
- 🗃️ **Create & View Journal Entries**
- 🛡️ **Spring Security** with route protection
- 🌐 **RESTful APIs** with JSON payloads
- 📅 Automatically timestamps journal entries

---

## 🛠️ Tech Stack

| Layer         | Tech Used                      |
|---------------|-------------------------------|
| Backend       | Spring Boot, Spring MVC       |
| Security      | Spring Security, JWT          |
| Persistence   | Spring Data JPA, MySQL |
| Mail          | JavaMailSender (SMTP)         |
| Build Tool    | Maven                         |
