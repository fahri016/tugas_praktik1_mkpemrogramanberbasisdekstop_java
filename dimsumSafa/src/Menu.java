public class Menu {
    // 1. DEKLARASI ATRIBUT (Variabel Class)
    // Menggunakan tipe data String dan double. 
    // Menggunakan keyword 'private' untuk keamanan data (Encapsulation).
    private String namaMenu;
    private double harga;
    private String kategori;

    // 2. CONSTRUCTOR
    // Method khusus untuk mencetak/membuat objek menu baru
    public Menu(String namaMenu, double harga, String kategori) {
        // Keyword 'this' digunakan untuk membedakan atribut class dengan parameter
        this.namaMenu = namaMenu;
        this.harga = harga;
        this.kategori = kategori;
    }

    // 3. GETTER METHODS
    // Karena atribut di atas 'private', kita butuh method 'public' untuk mengambil nilainya
    public String getNamaMenu() {
        return namaMenu;
    }

    public double getHarga() {
        return harga;
    }

    public String getKategori() {
        return kategori;
    }

    // 4. SETTER METHOD (Fitur Baru untuk Admin)
    // Method ini mengizinkan Admin untuk mengubah/menimpa (overwrite) harga menu yang sudah ada
    public void setHarga(double harga) {
        this.harga = harga;
    }
}