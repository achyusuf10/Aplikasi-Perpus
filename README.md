# Aplikasi-Perpus
<!-- PROJECT LOGO -->

<br />
<p align="center">
  <a href="https://github.com/abdullah1006/Aplikasi-Perpus">
    <img src="https://user-images.githubusercontent.com/65402864/100080500-7e02d480-2e78-11eb-9997-d774cec7854a.png" alt="Logo" width="80" height="80">
  </a>

<h3 align="center">Aplikasi Perpus</h3>

<p align="center">
    Aplikasi Perpus use Code Igniter 4
    <br />
    <a href="https://github.com/abdullah1006/Aplikasi-Perpus"><strong>Explore the docs »</strong></a>
    <br />
    <br />
    <a href="https://github.com/abdullah1006/Aplikasi-Perpus">View Demo</a>
    ·
    <a href="https://github.com/abdullah1006/Aplikasi-Perpus/issues">Report Bug</a>
    ·
    <a href="https://github.com/abdullah1006/Aplikasi-Perpus/issues">Request Feature</a>
  </p>
</p>

<!-- TABLE OF CONTENTS -->

<details open="open">
  <summary>Table of Contents</summary>
  <ol>
    <li>
      <a href="#about-the-project">About The Project</a>
      <ul>
        <li><a href="#our-team">Our Team</a></li>
        <li><a href="#built-with">Built With</a></li>
      </ul>
    </li>
    <li>
      <a href="#getting-started">Getting Started</a>
      <ul>
        <li><a href="#prerequisites">Prerequisites</a></li>
        <li><a href="#installation">Installation</a></li>
      </ul>
    </li>
    <li><a href="#more-screenshot">More Screenshot</a></li>
    <li><a href="#license">License</a></li>
  </ol>
</details>

<!-- ABOUT THE PROJECT -->

## About The Project

![image](https://user-images.githubusercontent.com/65402864/104396183-c5eecf80-557c-11eb-8757-aa1cf2cb64d1.png)

Aplikasi Perpus is a Android App, which is made in Android Studio with CI4 and use MySql Database. We created this Android project to fulfill our course assignments at a university.

### Our Team
- Achmad Yusuf Bagus Setiawan (18051204010) [Go to Profile](https://github.com/abdullah1006)
- Fitrah Amaliah              (18051204007) [Go to Profile](https://github.com/Fitrahamaliah)
- Jeptika Herni Niasmara      (18051204004) [Go to Profile](https://github.com/jeptika)


### Built With

- [Android Studio](https://developer.android.com/studio?hl=zh-cn)
- [XAMPP](https://www.apachefriends.org/download.html)
- [Code Igniter](https://codeigniter.com/download)

<!-- GETTING STARTED -->

## Getting Started

Let's start buddy.

### Prerequisites


- Android Studio Version => 4.1
- Php Version => 7.2
  
  ```sh
  php -v
  ```
  

### Installation

1. Clone the repo
2. Copy crud_ci4 folder to your htdocs folder (optional)
3. Start your apache and MySQL from XAMPP (optional)
4. Or you can start the server with

```php
php spark serve
```

5. Create database 'db_anggota' in PhpMyAdmin

6. Create Data Dummy, you can import 'db_anggota.sql' to PhpMyAdmin

7. Test your Rest API on your browser
http://localhost/crud_ci4/public/users or http://localhost:8080/users

8. Or test with [Postman](https://www.postman.com/downloads/). Make sure request is working properly.
- GET     -> http://localhost/crud_ci4/public/users or http://localhost:8080/users
- POST    -> http://localhost/crud_ci4/public/users/create or http://localhost:8080/users/create
- PUT     -> http://localhost/crud_ci4/public/users/{id} or http://localhost:8080/users/{id}
- DELETE  -> http://localhost/crud_ci4/public/users/{id} or http://localhost:8080/users/{id}

9. Open Android Studio

10. Change your ip in RetroServer.java and network_security_config.xml

## More Screenshot

![image](https://user-images.githubusercontent.com/65402864/104396189-c8512980-557c-11eb-9afb-52d29727cda6.png)
![image](https://user-images.githubusercontent.com/65402864/104396193-ca1aed00-557c-11eb-8b7b-96c2d704d47b.png)
![image](https://user-images.githubusercontent.com/65402864/104396199-cb4c1a00-557c-11eb-8287-4248ae427be5.png)
![image](https://user-images.githubusercontent.com/65402864/104396206-cd15dd80-557c-11eb-84fe-c20c905236ec.png)
![image](https://user-images.githubusercontent.com/65402864/104396208-cf783780-557c-11eb-846e-cae01746727d.png)
![image](https://user-images.githubusercontent.com/65402864/104396212-d0a96480-557c-11eb-8ade-a98613b5eb51.png)


<!-- LICENSE -->

## License

Distributed under the MIT License. See `LICENSE` for more information.

