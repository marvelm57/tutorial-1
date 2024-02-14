### Link deployment: https://tutorial-1-marvelmartin.koyeb.app/
---
# Module 1

### Reflection 1
Dalam mengimplementasikan fitur edit dan delete product, saya telah berfokus pada prinsip-prinsip clean code dan praktik pengkodean yang aman. Saya memperhatikan penamaan yang jelas dan deskriptif untuk variabel, metode, dan kelas, serta memanfaatkan dependency injection melalui fitur autowiring Spring. Penggunaan anotasi Spring juga membantu dalam mengatur dan membaca kode dengan lebih baik. Meskipun demikian, saya menyadari perlunya penambahan validasi input pada level controller untuk memastikan data yang diterima sesuai dengan yang diharapkan. Saya juga menyadari pentingnya penanganan yang lebih baik terhadap situasi ketika produk tidak ditemukan.
### Reflection 2
- Setelah menulis unit test, saya merasa kode saya menjadi lebih jauh lebih aman . Unit test membantu saya memverifikasi bahwa fungsi-fungsi yang saya implementasikan berjalan sesuai yang diharapkan, serta memberikan jaminan bahwa perubahan yang saya buat tidak akan memengaruhi perilaku yang sudah ada sebelumnya.
- Tidak ada aturan baku tentang berapa banyak unit test yang harus dibuat dalam sebuah kelas. Namun, setiap fungsionalitas atau metode yang memiliki logika yang berbeda atau sebaiknya memiliki setidaknya satu unit test yang memverifikasi perilakunya. Ini membantu dalam memastikan bahwa setiap bagian dari kode telah diuji secara menyeluruh.
- Untuk memastikan bahwa unit test kita sudah cukup untuk memverifikasi program kita, kita dapat menggunakan konsep code coverage. Code coverage dapat membantu kita memahami seberapa banyak dari kode sumber yang telah diuji. Namun, memiliki 100% code coverage tidak menjamin bahwa kode kita tidak memiliki bug atau kesalahan. 

# Module 2
### Reflection
1. Berdasarkan workflow PMD yang saya implementasikan, terdapat 2 isu pada kode saya:
   - **_Unused import_**: Pada kode saya terdeteksi adanya _import_ yang tidak digunakan. Strategi yang saya lakukan adalah menghapus _import_ tersebut dan menggantinya dengan _import_ yang lebih sesuai dan spesifik.
   - _**Unnecessary modifier**_: Saya menggunakan modifier 'public' pada method-method di interface, padahal secara _default_ method-method tersebut adalah `public` dan `abstract`. Strategi yang saya lakukan adalah menghapus modifier tersebut karena tidak diperlukan.
2. _**Continuous Integration(CI)**_ telah saya implementasikan dengan membuat _workflows_ yang akan berjalan setiap kali ada push pada setiap branch. Saya membuat 3 workflows, yaitu `ci.yml`, `scorecard.yml`, dan `pmd.yml` yang secara otomatis menjalankan pengujian, menganalisis kualitas kode, dan memberikan umpan balik tentang masalah yang ditemukan pada kode saya. Selain itu, saya juga telah menerapkan _**Continuous Deployment**_ menggunakan Koyeb Paas sebagai platform _deployment_ yang secara otomatis akan melakukan _re-deploy_ setiap ada perubahan pada branch `main`.
