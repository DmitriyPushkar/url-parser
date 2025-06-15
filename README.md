# 🖼️ Web Page Screenshot Archiver

A Java Spring Boot application that processes a list of web page URLs from an Excel file, captures screenshots using headless Chrome, and converts them to organized PDF files.

Useful for evidence collection, content monitoring, reporting, or archiving large numbers of web pages.

---

## ✨ Features

- Upload `.xlsx` Excel files via a web interface
- Choose which columns contain URLs and folder labels (A–Z)
- Opens each page in headless Chrome and takes a screenshot
- Converts screenshots to high-quality PDF files
- Organizes PDFs into folders by label (e.g. brand, project)
- Automatically timestamps filenames for traceability
- Handles 600+ pages/hour (≈10 pages/minute)

---

## 🧰 Requirements

- Java 21+
- Google Chrome installed
- Internet connection
- OS: Windows, macOS, or Linux

> Maven is **not required** if you're using the prebuilt JAR.  
> Chromedriver is automatically handled via WebDriverManager.

---

## 🚀 Running the Application

### Option 1: Using the Prebuilt JAR (recommended)

1. Clone the repository:

   ```bash
   git clone https://github.com/your-org/web-page-screenshot-archiver.git
   cd web-page-screenshot-archiver
   ```

2. Run the application:

   ```bash
   java -jar target/amazon-automation-screenshots-1.0-SNAPSHOT.jar
   ```

3. Open in your browser:

   ```
   http://localhost:8080
   ```

---

### 📹 Demo / Example

You can view a complete usage walkthrough (upload, processing, and result) here:

👉 [View Example on Google Drive](https://drive.google.com/drive/folders/186bqd6Q5n0mQRdYfOEK01LQATrrNrtn1?usp=sharing)

This includes:

- Uploading the Excel file
- Selecting columns and output folder
- Starting the processing
- Folder structure and resulting PDFs

## 📥 How to Use

1. Open the web interface.
2. Upload an `.xlsx` file.
3. Specify:
   - Output folder (e.g. `C:/Output`)
   - URL column (e.g. `C`)
   - Label column (e.g. `E`)
4. Click **Start Processing**.
5. A success message will appear.

---

## 📄 Excel File Format

- Format: `.xlsx` (first sheet only)
- The first row is treated as a header
- Processing starts from row 2

| A   | B     | C                          | D   | E        |
|-----|-------|----------------------------|-----|----------|
| ID  | Lang  | https://example.com/page1  | ... | Brand X  |
| ... | ...   | https://example.com/page2  | ... | Brand Y  |

---

## 📂 Output Structure

```
C:/Output/
├── Brand X/
│   └── BrandX_20240615_120305_https___example_com_page1.pdf
├── Brand Y/
│   └── BrandY_20240615_120309_https___example_com_page2.pdf
```

---

## ⚙️ How It Works

1. Excel file is uploaded and saved as a temporary file
2. The specified columns are parsed into (URL, Label) pairs
3. For each entry:
   - The URL is opened in headless Chrome
   - A screenshot is taken
   - The screenshot is converted to PDF
   - The PDF is saved into a folder named after the label
4. PNG is deleted after PDF is created

---

## ⚡ Performance

- ~10 pages per minute
- ~600+ pages per hour
- Depends on page loading time and system performance

---

## 🧾 Notes

- Only `.xlsx` supported
- Pages must be accessible without login
- Long URLs are truncated and sanitized in filenames
- Output folder must be an **absolute path**

---

## 💡 Roadmap

- [ ] Multi-sheet Excel support
- [ ] Set custom delays for page load
- [ ] Download ZIP of results
- [ ] Display processing progress