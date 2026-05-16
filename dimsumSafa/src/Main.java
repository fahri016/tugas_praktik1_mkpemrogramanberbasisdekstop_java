import java.util.Scanner;

public class Main {
    // ==========================================
    // VARIABEL GLOBAL (Bisa diakses oleh semua method)
    // ==========================================
    // Implementasi Array untuk menampung menu restoran. Kapasitas diset besar (100) 
    // karena array di Java tidak bisa membesar otomatis saat ada penambahan menu baru.
    static Menu[] daftarMenu = new Menu[100]; 
    static int jumlahMenu = 0; // Berfungsi sebagai pelacak ada berapa menu yang terisi saat ini
    static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        // Panggil method untuk mengisi 8 data menu awal ke dalam Array
        inisialisasiMenuAwal(); 

        boolean aplikasiBerjalan = true;

        // Implementasi struktur pengulangan WHILE
        // Aplikasi akan terus mengulang menu utama ini sampai user memilih opsi 3 (Keluar)
        while (aplikasiBerjalan) {
            System.out.println("\n=====================================");
            System.out.println("      SISTEM MANAJEMEN RESTORAN      ");
            System.out.println("=====================================");
            System.out.println("1. Pesan Makanan (Menu Pelanggan)");
            System.out.println("2. Kelola Menu (Menu Admin)");
            System.out.println("3. Keluar dari Aplikasi");
            System.out.print("Pilih akses navigasi (1/2/3): ");
            String pilihan = scanner.nextLine();

            // Implementasi struktur keputusan SWITCH-CASE
            // Untuk menavigasi menu berdasarkan pilihan user
            switch (pilihan) {
                case "1":
                    menuPelanggan(); // Lompat ke method sistem pelanggan
                    break;
                case "2":
                    menuAdmin();     // Lompat ke method sistem admin
                    break;
                case "3":
                    aplikasiBerjalan = false; // Menghentikan perulangan WHILE di atas
                    System.out.println("Terima kasih telah menggunakan aplikasi ini.");
                    break;
                default:
                    // Jika user mengetik selain 1, 2, atau 3
                    System.out.println("[Error] Pilihan tidak valid. Silakan input kembali.");
            }
        }
    }

    // ==========================================
    // BAGIAN 1: SISTEM PELANGGAN
    // ==========================================
    static void menuPelanggan() {
        tampilkanMenu(); // Menampilkan katalog menu ke layar

        // Implementasi Array untuk menyimpan keranjang belanjaan pelanggan
        String[] pesananNama = new String[100];
        int[] pesananQty = new int[100];
        int jumlahPesanan = 0; // Pelacak jumlah item yang sudah dipesan

        System.out.println("\nKetik pesanan format: Nama Menu = Jumlah (Contoh: Dimsum Nori = 2)");
        System.out.println("Ketik 'selesai' jika pesanan sudah cukup.");

        // Implementasi perulangan WHILE (TRUE)
        // Akan terus menagih input pesanan tidak terbatas hingga user mengetik 'selesai'
        while (true) {
            System.out.print("Pesanan " + (jumlahPesanan + 1) + ": ");
            String input = scanner.nextLine();

            // Struktur Keputusan IF-ELSE
            if (input.equalsIgnoreCase("selesai")) {
                break; // Perintah untuk mendobrak/keluar dari perulangan While
            }

            // Validasi Input: Cek apakah user mengetik tanda " = "
            if (!input.contains(" = ")) {
                System.out.println("[Error] Format salah! Gunakan spasi sebelum dan sesudah tanda =");
                continue; // Memaksa program kembali ke atas (skip proses di bawahnya)
            }

            // Membelah teks inputan (String)
            String[] bagian = input.split(" = ");
            String namaInput = bagian[0]; // Bagian kiri (Nama)
            int qtyInput;

            // Memastikan jumlah (bagian kanan) berupa angka, bukan huruf
            try {
                qtyInput = Integer.parseInt(bagian[1]); 
            } catch (Exception e) {
                System.out.println("[Error] Jumlah harus berupa angka!");
                continue; // Kembali ke atas menagih input
            }

            // Cek apakah menu yang diketik pelanggan benar-benar ada di daftar kita
            int indexDitemukan = cariIndexMenu(namaInput);
            
            // Nested IF (IF Bersarang)
            if (indexDitemukan != -1) {
                // Jika ketemu, masukkan ke keranjang Array
                pesananNama[jumlahPesanan] = namaInput;
                pesananQty[jumlahPesanan] = qtyInput;
                jumlahPesanan++; // Tambah pelacak pesanan
            } else {
                System.out.println("[Error] Menu tidak ditemukan. Silakan input kembali menu yang ada di daftar.");
            }
        }

        // Cek jika keranjang tidak kosong, maka cetak struk
        if (jumlahPesanan > 0) {
            cetakStruk(pesananNama, pesananQty, jumlahPesanan);
        } else {
            System.out.println("Tidak ada pesanan yang dibuat. Kembali ke Menu Utama.");
        }
    }

    static void cetakStruk(String[] pesananNama, int[] pesananQty, int jumlahPesanan) {
        System.out.println("\n=====================================");
        System.out.println("            STRUK PESANAN            ");
        System.out.println("=====================================");

        double subtotal = 0;
        String namaMinumanPromo = "-";

        // Implementasi pengulangan FOR
        // Mengulang sebanyak jumlah pesanan yang ada di keranjang untuk menghitung total
        for (int i = 0; i < jumlahPesanan; i++) {
            int index = cariIndexMenu(pesananNama[i]);
            Menu menuDitemukan = daftarMenu[index];
            
            double totalPerItem = menuDitemukan.getHarga() * pesananQty[i];
            subtotal += totalPerItem; // Menambahkan ke subtotal keseluruhan

            System.out.println(menuDitemukan.getNamaMenu() + " \tx" + pesananQty[i] + " \t(@Rp" + menuDitemukan.getHarga() + ") = Rp" + totalPerItem);

            // Mengecek apakah menu tersebut adalah minuman untuk syarat promo
            if (menuDitemukan.getKategori().equalsIgnoreCase("Minuman")) {
                namaMinumanPromo = menuDitemukan.getNamaMenu();
            }
        }

        System.out.println("-------------------------------------");
        System.out.println("Total Biaya Pesanan : Rp" + subtotal);

        // Diskon 10% jika belanja di atas 100rb
        double diskon = 0;
        if (subtotal > 100000) {
            diskon = subtotal * 0.10;
            System.out.println("Diskon 10%          : -Rp" + diskon);
        }

        // Perhitungan Pajak dan Layanan
        double hargaSetelahDiskon = subtotal - diskon;
        double pajak = hargaSetelahDiskon * 0.10;
        double biayaLayanan = 20000;

        System.out.println("Pajak 10%           : Rp" + pajak);
        System.out.println("Biaya Pelayanan     : Rp" + biayaLayanan);

        // Promo Beli 1 Gratis 1 jika belanja di atas 50rb DAN ada pesanan minuman
        if (subtotal > 50000 && !namaMinumanPromo.equals("-")) {
            System.out.println("Promo Spesial       : Selamat! Anda mendapat GRATIS 1 " + namaMinumanPromo);
        }

        double grandTotal = hargaSetelahDiskon + pajak + biayaLayanan;
        System.out.println("-------------------------------------");
        System.out.println("TOTAL BAYAR         : Rp" + grandTotal);
        System.out.println("=====================================\n");
    }

    // ==========================================
    // BAGIAN 2: SISTEM ADMIN (KELOLA MENU)
    // ==========================================
    static void menuAdmin() {
        boolean diMenuAdmin = true;
        
        // Implementasi pengulangan DO-WHILE
        // Akan menjalankan blok menu admin terlebih dahulu, baru mengecek kondisi di akhir
        do {
            System.out.println("\n--- PENGELOLAAN MENU (ADMIN) ---");
            System.out.println("1. Tambah Menu Baru");
            System.out.println("2. Ubah Harga Menu");
            System.out.println("3. Hapus Menu");
            System.out.println("4. Kembali ke Menu Utama");
            System.out.print("Pilih aksi (1-4): ");
            String pilihanAdmin = scanner.nextLine();

            // Struktur keputusan IF-ELSE IF bersarang
            if (pilihanAdmin.equals("1")) {
                tambahMenuBaru();
            } else if (pilihanAdmin.equals("2")) {
                ubahHargaMenu();
            } else if (pilihanAdmin.equals("3")) {
                hapusMenu();
            } else if (pilihanAdmin.equals("4")) {
                diMenuAdmin = false; // Mengakhiri DO-WHILE, kembali ke menu utama Parent
            } else {
                System.out.println("[Error] Pilihan tidak valid!");
            }
        } while (diMenuAdmin);
    }

    static void tambahMenuBaru() {
        int jumlahTambah = 0;
        boolean inputValid = false;

        // Validasi input menggunakan perulangan agar aplikasi tidak crash jika admin salah ketik
        while (!inputValid) {
            try {
                System.out.print("Berapa menu baru yang ingin ditambahkan sekaligus? ");
                jumlahTambah = Integer.parseInt(scanner.nextLine());
                inputValid = true; // Jika sukses angka, hentikan perulangan
            } catch (Exception e) {
                System.out.println("[Error] Harap masukkan angka!");
            }
        }

        // Pengulangan FOR untuk menginput beberapa menu sekaligus sesuai jumlah yang diminta
        for (int i = 0; i < jumlahTambah; i++) {
            System.out.println("\nInput Menu Ke-" + (i + 1));
            System.out.print("Nama Menu: ");
            String namaBaru = scanner.nextLine();
            
            System.out.print("Harga: ");
            double hargaBaru = Double.parseDouble(scanner.nextLine());
            
            System.out.print("Kategori (Makanan/Minuman): ");
            String kategoriBaru = scanner.nextLine();

            // Memasukkan objek menu baru ke dalam urutan Array yang kosong
            daftarMenu[jumlahMenu] = new Menu(namaBaru, hargaBaru, kategoriBaru);
            jumlahMenu++; // Tambah total pelacak menu
            System.out.println("[Sukses] Menu ditambahkan.");
        }
    }

    static void ubahHargaMenu() {
        tampilkanMenuSemuaKategori(); // Tampilkan daftar beserta nomornya
        System.out.print("\nMasukkan Nomor Menu yang ingin diubah: ");
        
        // Dikurang 1 karena input manusia mulai dari angka 1, sedangkan index Array mulai dari 0
        int noUrut = Integer.parseInt(scanner.nextLine()) - 1; 

        // Validasi apakah nomor yang diinput tidak melebihi jumlah menu yang ada
        if (noUrut >= 0 && noUrut < jumlahMenu) {
            System.out.print("Konfirmasi, ubah harga " + daftarMenu[noUrut].getNamaMenu() + "? (Ya/Tidak): ");
            String konfirmasi = scanner.nextLine();
            
            if (konfirmasi.equalsIgnoreCase("Ya")) {
                System.out.print("Masukkan harga baru: ");
                double hargaBaru = Double.parseDouble(scanner.nextLine());
                
                // Memanggil Setter dari class Menu untuk mengubah harga
                daftarMenu[noUrut].setHarga(hargaBaru); 
                System.out.println("[Sukses] Harga berhasil diubah.");
            } else {
                System.out.println("Aksi dibatalkan.");
            }
        } else {
            System.out.println("[Error] Nomor menu tidak valid.");
        }
    }

    static void hapusMenu() {
        tampilkanMenuSemuaKategori();
        System.out.print("\nMasukkan Nomor Menu yang ingin dihapus: ");
        int noUrut = Integer.parseInt(scanner.nextLine()) - 1;

        if (noUrut >= 0 && noUrut < jumlahMenu) {
            System.out.print("Konfirmasi, YAKIN hapus " + daftarMenu[noUrut].getNamaMenu() + "? (Ya/Tidak): ");
            String konfirmasi = scanner.nextLine();
            
            if (konfirmasi.equalsIgnoreCase("Ya")) {
                // Konsep Hapus Array (Array Shifting): 
                // Menggeser data di sebelah kanan menu yang dihapus untuk mundur satu langkah ke kiri
                for (int i = noUrut; i < jumlahMenu - 1; i++) {
                    daftarMenu[i] = daftarMenu[i + 1]; 
                }
                daftarMenu[jumlahMenu - 1] = null; // Menghapus ruang ganda di posisi paling akhir
                jumlahMenu--; // Mengurangi total pelacak menu
                System.out.println("[Sukses] Menu berhasil dihapus.");
            } else {
                System.out.println("Aksi dibatalkan.");
            }
        } else {
            System.out.println("[Error] Nomor menu tidak valid.");
        }
    }

    // ==========================================
    // BAGIAN 3: METHOD PENDUKUNG LOGIKA
    // ==========================================
    
    // Method untuk menampilkan menu ke pelanggan yang dikelompokkan
    static void tampilkanMenu() {
        System.out.println("\n--- DAFTAR MAKANAN ---");
        for (int i = 0; i < jumlahMenu; i++) {
            if (daftarMenu[i].getKategori().equalsIgnoreCase("Makanan")) {
                System.out.println("- " + daftarMenu[i].getNamaMenu() + " (Rp" + daftarMenu[i].getHarga() + ")");
            }
        }
        System.out.println("\n--- DAFTAR MINUMAN ---");
        for (int i = 0; i < jumlahMenu; i++) {
            if (daftarMenu[i].getKategori().equalsIgnoreCase("Minuman")) {
                System.out.println("- " + daftarMenu[i].getNamaMenu() + " (Rp" + daftarMenu[i].getHarga() + ")");
            }
        }
    }

    // Method untuk menampilkan semua menu berjejer untuk keperluan Admin
    static void tampilkanMenuSemuaKategori() {
        System.out.println("\n--- DAFTAR SEMUA MENU ---");
        for (int i = 0; i < jumlahMenu; i++) {
            System.out.println((i + 1) + ". " + daftarMenu[i].getNamaMenu() + " - Rp" + daftarMenu[i].getHarga());
        }
    }

    // Method mesin pencari. Mengubah teks nama menu menjadi angka posisi indeks array
    static int cariIndexMenu(String namaPesanan) {
        for (int i = 0; i < jumlahMenu; i++) {
            if (daftarMenu[i].getNamaMenu().equalsIgnoreCase(namaPesanan)) {
                return i; // Mengembalikan posisi indeks jika namanya cocok
            }
        }
        return -1; // -1 adalah penanda khusus bahwa data tidak ditemukan
    }

    // Mengisi array dengan data pabrikan
    static void inisialisasiMenuAwal() {
        daftarMenu[0] = new Menu("Dimsum Original", 21000, "Makanan");
        daftarMenu[1] = new Menu("Dimsum Nori", 25000, "Makanan");
        daftarMenu[2] = new Menu("Sate Taichan", 20000, "Makanan");
        daftarMenu[3] = new Menu("Dimsum Mentai", 27000, "Makanan");
        daftarMenu[4] = new Menu("Es Teh Manis", 5000, "Minuman");
        daftarMenu[5] = new Menu("Matcha", 8000, "Minuman");
        daftarMenu[6] = new Menu("Cooko Oreo", 9000, "Minuman");
        daftarMenu[7] = new Menu("Air Mineral", 3000, "Minuman");
        jumlahMenu = 8; // Memberitahu sistem bahwa saat ini ada 8 slot array yang terisi
    }
}