# Bheem Nagar Times — News Publisher

A reusable Spring Boot + Thymeleaf publishing studio for turning raw Hindi news into a consistent भीमनगर टाइम्स newspaper front page.

## Features
- Raw Hindi news input
- Automatic local newspaper-style headline, section and subheadline formatting
- Main article image upload
- Optional reference newspaper upload
- Reusable A4 HTML/CSS newspaper template
- Print / browser Save as PDF
- Server-side PDF endpoint using OpenHTMLToPDF
- Responsive editor UI
- Docker and Docker Compose support
- GitHub Actions build
- Configurable AI properties reserved for future AI copy-desk integration

## Technology
- Java 21
- Spring Boot 3.5.5
- Thymeleaf
- OpenHTMLToPDF
- HTML/CSS
- Docker

## Run locally
Install Java 21 and Maven, then run:

mvn spring-boot:run

Open http://localhost:8080

## Build

mvn -DskipTests package
java -jar target/news-0.0.1-SNAPSHOT.jar

## Docker

mvn -DskipTests package
docker compose up --build -d

The application is available on port 8080.

## Publishing workflow
1. Paste the raw news.
2. Enter date, location and section.
3. Upload the main news image.
4. Optionally upload a reference newspaper page.
5. Generate the article.
6. Review the newspaper preview.
7. Print or download PDF.

The HTML/CSS template is intentionally used instead of generating the entire newspaper as an image. This keeps Hindi text selectable, editable and much more reliable for print publishing.

## Production notes
Before publication, verify names, dates, numbers, quotations and official claims against primary sources. Configure HTTPS/reverse proxy and persistent storage for uploaded images in production.
