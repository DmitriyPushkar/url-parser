# Amazon URL Screenshot to PDF Converter

This Java application reads a list of Amazon product URLs and infringing brand names from an Excel file,
takes screenshots of each page, and saves them as organized PDFs for legal evidence collection.

---

## Features
- Reads URLs and brand names from an Excel (.xlsx) file.
- Takes a full-page screenshot of each URL using headless Chrome.
- Converts screenshots into high-quality PDFs.
- Organizes PDFs into brand-specific folders.
- Automatic timestamping in filenames.

---

## Setup

1. **Requirements**:
    - Java 21+
    - Maven
    - Chrome installed
    - Chromedriver auto-managed by WebDriverManager (no manual setup needed)

2. **Configuration**:
    - Open the file `src/main/java/com/example/urlparser/Config.java`
    - Set your paths:
      ```java
      public static final String EXCEL_PATH = "C:\\path\\to\\your\\excel_file.xlsx";
      public static final String OUTPUT_DIR = "C:\\path\\to\\save\\pdfs";
      ```

3. **How to Run**:
    - Build the project using Maven:
      ```
      mvn clean install
      ```
    - Run the `MainRunner` class from your IDE or terminal.

---

## Excel File Format

- Column **C**: Page URL
- Column **E**: Infringing Brand

Other columns are ignored.

---

## Example Folder Structure

