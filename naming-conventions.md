# Naming Conventions for Java Project

## 1. General
- **Rules**:
    - Gunakan bahasa Inggris baik untuk nama kelas, method, variabel, _display message_, maupun _commit message_.

## 2. Package Names

- **Format**: `com.enigma.student_report.[module]`
- **Rules**:
    - Gunakan huruf kecil.
    - Nama package menggunakan satu kata.
    - Contoh: `com.enigma.student_report.controller`, `com.enigma.student_report.repository`

## 3. Class Names

- **Format**: `PascalCase`
- **Rules**:
    - Setiap kata dimulai dengan huruf kapital.
    - Nama kelas sesuai diakhiri dengan fungsi kelas, kecuali kelas entity.
    - Contoh: `StudentService`, `StudentController`, `NewStudentRequest`

## 4. Method Names

- **Format**: `camelCase`
- **Rules**:
    - Huruf pertama huruf kecil, huruf pertama dari setiap kata berikutnya huruf kapital.
    - Gunakan kata kerja yang menggambarkan aksi dari method.
    - Contoh: `createNewStudent()`, `updateSubject()`

## 5. Variable Names

- **Format**: `camelCase`
- **Rules**:
    - Huruf pertama huruf kecil, huruf pertama dari setiap kata berikutnya huruf kapital.
    - Gunakan nama yang deskriptif dan singkat.
    - Contoh: `students`, `subjects`

## 6. Repository Names

- **Format**: `ch_sb_1_[application-name]`
- **Rules**:
    - Gunakan format snake_case untuk nama repository.
    - Gunakan format kebab-case untuk nama aplikasi.
    - Contoh: `ch_sb_1_student_report`

## 7. Git Branch Names

- **Format**: `feature-[FeatureName]`
- **Rules**:
    - Gunakan format kebab-case untuk nama branch.
    - Gunakan format PascalCase untuk nama fitur.
    - Contoh: `feature-SubjectEnrollment`, `feature-StudentEnrollment`

## 8. Commit Message

- **Format**:
    - Tag : `UPPERCASE`
    - Body : `lower case`
- **Description**:
    - `[FEAT]` menambahkan fitur
    - `[UPDATE]` memperbaiki fitur yang sudah ada
    - `[DOCS]` mengubah dokumentasi
    - `[STYLE]` melakukan perubahan yang tidak berdampak pada program
    - `[CHORE]` melakukan commit awal
- **Contoh**
    - "`[FEAT]` Add feature : student input"
    - "`[DOCS]` Fix typo in naming-conventions.md"
    - "`[STYLE]` Add whitespace on Student.java"