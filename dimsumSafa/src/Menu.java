public class Menu {
    private String namaMenu;
    private double harga;
    private String kategori;

    public static void main(String[] args) {
        // Contoh penggunaan class menu
        Menu item = new Menu("Dimsum Original", 21000, "Makanan");
        System.out.println(item.getNamaMenu());
    }

    // Pembuatan objek menu
    public Menu(String namaMenu, double harga, String kategori) {
        this.namaMenu = namaMenu;
        this.harga = harga;
        this.kategori = kategori;
    }

    // Akses keluar
    public String getNamaMenu() {
        return namaMenu;
    }

    public double getHarga() {
        return harga;
    }

    public String getKategori() {
        return kategori;
    }
}
