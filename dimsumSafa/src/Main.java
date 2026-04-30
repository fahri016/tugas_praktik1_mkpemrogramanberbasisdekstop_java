import java.util.Scanner;

public class Main {
   public static void main(String[] args) {
        // 1. Persiapan Data
        // Buat Array dari class Menu untuk menyimpan minimal 4 makanan dan 4 minuman
        Menu[] daftarMenu = {
            new Menu("Dimsum Original", 21000, "Makanan"),
            new Menu("Dimsum Nori", 25000, "Makanan"),
            new Menu("Sate Taichan", 20000, "Makanan"),
            new Menu("Dimsum Mentai", 27000, "Makanan"),
            new Menu("Es Teh Manis", 5000, "Minuman"),
            new Menu("Matcha", 8000, "Minuman"),
            new Menu("Cooko Oreo", 9000, "Minuman"),
            new Menu("Air Mineral", 3000, "Minuman")
        };

       // 2. MENAMPILKAN MENU KE LAYAR
        System.out.println("=====================================");
        System.out.println("      SELAMAT DATANG DI RESTORAN     ");
        System.out.println("=====================================");
        System.out.println("\n--- DAFTAR MAKANAN ---");
        System.out.println("1. " + daftarMenu[0].getNamaMenu() + " - Rp" + daftarMenu[0].getHarga());
        System.out.println("2. " + daftarMenu[1].getNamaMenu() + " - Rp" + daftarMenu[1].getHarga());
        System.out.println("3. " + daftarMenu[2].getNamaMenu() + " - Rp" + daftarMenu[2].getHarga());
        System.out.println("4. " + daftarMenu[3].getNamaMenu() + " - Rp" + daftarMenu[3].getHarga());

        System.out.println("\n--- DAFTAR MINUMAN ---");
        System.out.println("5. " + daftarMenu[4].getNamaMenu() + " - Rp" + daftarMenu[4].getHarga());
        System.out.println("6. " + daftarMenu[5].getNamaMenu() + " - Rp" + daftarMenu[5].getHarga());
        System.out.println("7. " + daftarMenu[6].getNamaMenu() + " - Rp" + daftarMenu[6].getHarga());
        System.out.println("8. " + daftarMenu[7].getNamaMenu() + " - Rp" + daftarMenu[7].getHarga());
        System.out.println("=====================================");

        // 3. PROSES INPUT PEMESANAN
        String[] namaPesanan = new String[4];
        int[] qtyPesanan = new int[4];

        System.out.println("\nKetik pesanan format: Nama Menu = Jumlah (Contoh: Dimsum Original = 3)");
        System.out.println("Ketik 'selesai' jika pesanan sudah cukup.");

        // Input Pesanan 1
        Scanner scanner = new Scanner(System.in);
        System.out.print("Pesanan 1: ");
        String input1 = scanner.nextLine();
        if (!input1.equalsIgnoreCase("selesai")) {
            String[] bagian1 = input1.split(" = ");
            namaPesanan[0] = bagian1[0];
            qtyPesanan[0] = Integer.parseInt(bagian1[1]);

            // Input Pesanan 2
            System.out.print("Pesanan 2: ");
            String input2 = scanner.nextLine();
            if (!input2.equalsIgnoreCase("selesai")) {
                String[] bagian2 = input2.split(" = ");
                namaPesanan[1] = bagian2[0];
                qtyPesanan[1] = Integer.parseInt(bagian2[1]);

                // Input Pesanan 3
                System.out.print("Pesanan 3: ");
                String input3 = scanner.nextLine();
                if (!input3.equalsIgnoreCase("selesai")) {
                    String[] bagian3 = input3.split(" = ");
                    namaPesanan[2] = bagian3[0];
                    qtyPesanan[2] = Integer.parseInt(bagian3[1]);

                    // Input Pesanan 4
                    System.out.print("Pesanan 4: ");
                    String input4 = scanner.nextLine();
                    if (!input4.equalsIgnoreCase("selesai")) {
                        String[] bagian4 = input4.split(" = ");
                        namaPesanan[3] = bagian4[0];
                        qtyPesanan[3] = Integer.parseInt(bagian4[1]);
                    }
                }
            }
        }

        // 4. MENGHITUNG TOTAL & MENCETAK STRUK
        System.out.println("\n=====================================");
        System.out.println("            STRUK PESANAN            ");
        System.out.println("=====================================");

        double subtotal = 0;
        String namaMinumanPromo = "-"; // Untuk menyimpan nama minuman jika dapat promo

        // Proses Pesanan 1
        if (namaPesanan[0] != null) {
            Menu menuDitemukan = cariMenuBerdasarkanNama(namaPesanan[0], daftarMenu);
            if (menuDitemukan != null) {
                double totalPerItem = menuDitemukan.getHarga() * qtyPesanan[0];
                subtotal += totalPerItem;
                System.out.println(menuDitemukan.getNamaMenu() + " \tx" + qtyPesanan[0] + " \t(Rp" + menuDitemukan.getHarga() + ") = Rp" + totalPerItem);
                if (menuDitemukan.getKategori().equalsIgnoreCase("Minuman")) {
                    namaMinumanPromo = menuDitemukan.getNamaMenu();
                }
            }
        }

        // Proses Pesanan 2
        if (namaPesanan[1] != null) {
            Menu menuDitemukan = cariMenuBerdasarkanNama(namaPesanan[1], daftarMenu);
            if (menuDitemukan != null) {
                double totalPerItem = menuDitemukan.getHarga() * qtyPesanan[1];
                subtotal += totalPerItem;
                System.out.println(menuDitemukan.getNamaMenu() + " \tx" + qtyPesanan[1] + " \t(Rp" + menuDitemukan.getHarga() + ") = Rp" + totalPerItem);
                if (menuDitemukan.getKategori().equalsIgnoreCase("Minuman")) {
                    namaMinumanPromo = menuDitemukan.getNamaMenu();
                }
            }
        }

        // Proses Pesanan 3
        if (namaPesanan[2] != null) {
            Menu menuDitemukan = cariMenuBerdasarkanNama(namaPesanan[2], daftarMenu);
            if (menuDitemukan != null) {
                double totalPerItem = menuDitemukan.getHarga() * qtyPesanan[2];
                subtotal += totalPerItem;
                System.out.println(menuDitemukan.getNamaMenu() + " \tx" + qtyPesanan[2] + " \t(Rp" + menuDitemukan.getHarga() + ") = Rp" + totalPerItem);
                if (menuDitemukan.getKategori().equalsIgnoreCase("Minuman")) {
                    namaMinumanPromo = menuDitemukan.getNamaMenu();
                }
            }
        }

        // Proses Pesanan 4
        if (namaPesanan[3] != null) {
            Menu menuDitemukan = cariMenuBerdasarkanNama(namaPesanan[3], daftarMenu);
            if (menuDitemukan != null) {
                double totalPerItem = menuDitemukan.getHarga() * qtyPesanan[3];
                subtotal += totalPerItem;
                System.out.println(menuDitemukan.getNamaMenu() + " \tx" + qtyPesanan[3] + " \t(Rp" + menuDitemukan.getHarga() + ") = Rp" + totalPerItem);
                if (menuDitemukan.getKategori().equalsIgnoreCase("Minuman")) {
                    namaMinumanPromo = menuDitemukan.getNamaMenu();
                }
            }
        }

        System.out.println("-------------------------------------");
        System.out.println("Total Biaya Pesanan : Rp" + subtotal);

        // 5. PENERAPAN DISKON & BIAYA TAMBAHAN
        double diskon = 0;
        // Aturan: Diskon 10% jika total pesanan > Rp 100.000
        if (subtotal > 100000) {
            diskon = subtotal * 0.10;
            System.out.println("Diskon 10%          : -Rp" + diskon);
        }

        double hargaSetelahDiskon = subtotal - diskon;
        double pajak = hargaSetelahDiskon * 0.10; // Pajak 10%
        double biayaLayanan = 20000;              // Biaya pelayanan fix 20.000

        System.out.println("Pajak 10%           : Rp" + pajak);
        System.out.println("Biaya Pelayanan     : Rp" + biayaLayanan);

        // Aturan: Beli 1 Gratis 1 Minuman jika total pesanan > Rp 50.000
        if (subtotal > 50000 && !namaMinumanPromo.equals("-")) {
            System.out.println("Promo Spesial       : Selamat! Anda mendapat GRATIS 1 " + namaMinumanPromo);
        }

        // 6. TOTAL AKHIR
        double grandTotal = hargaSetelahDiskon + pajak + biayaLayanan;
        System.out.println("-------------------------------------");
        System.out.println("TOTAL BAYAR         : Rp" + grandTotal);
        System.out.println("=====================================");
        
        scanner.close();
    }

    // METHOD PENDUKUNG: Mencari data menu tanpa menggunakan Looping
    static Menu cariMenuBerdasarkanNama(String namaPesanan, Menu[] daftarMenu) {
        if (namaPesanan == null) return null;
        
        if (namaPesanan.equalsIgnoreCase(daftarMenu[0].getNamaMenu())) return daftarMenu[0];
        if (namaPesanan.equalsIgnoreCase(daftarMenu[1].getNamaMenu())) return daftarMenu[1];
        if (namaPesanan.equalsIgnoreCase(daftarMenu[2].getNamaMenu())) return daftarMenu[2];
        if (namaPesanan.equalsIgnoreCase(daftarMenu[3].getNamaMenu())) return daftarMenu[3];
        if (namaPesanan.equalsIgnoreCase(daftarMenu[4].getNamaMenu())) return daftarMenu[4];
        if (namaPesanan.equalsIgnoreCase(daftarMenu[5].getNamaMenu())) return daftarMenu[5];
        if (namaPesanan.equalsIgnoreCase(daftarMenu[6].getNamaMenu())) return daftarMenu[6];
        if (namaPesanan.equalsIgnoreCase(daftarMenu[7].getNamaMenu())) return daftarMenu[7];
        
        return null; // Jika nama menu salah atau tidak ditemukan
    }
}