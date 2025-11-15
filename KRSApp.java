import java.util.Scanner;

// Class MataKuliah
class MataKuliah {
    private String kode;
    private String nama;
    private int sks;

    public MataKuliah(String kode, String nama, int sks) {
        this.kode = kode;
        this.nama = nama;
        this.sks = sks;
    }

    public int getSks() {
        return sks;
    }

    public String toString() {
        return kode + " - " + nama + " (" + sks + " SKS)";
    }
}

// Class Mahasiswa
class Mahasiswa {
    private String nim;
    private String nama;

    private MataKuliah[] krs;
    private int jumlahMK = 0;
    private int totalSks = 0;

    public Mahasiswa(String nim, String nama) {
        this.nim = nim;
        this.nama = nama;
        this.krs = new MataKuliah[10]; // kapasitas maksimal 10 MK
    }

    public void tambahMataKuliah(MataKuliah mk) {
        if (jumlahMK >= krs.length) {
            System.out.println("✖ Tidak bisa menambah MK. Kapasitas penuh!");
            return;
        }

        if (totalSks + mk.getSks() > 24) {
            System.out.println("✖ Tidak bisa menambah MK. Total SKS melebihi batas 24!");
            return;
        }

        krs[jumlahMK] = mk;
        jumlahMK++;
        totalSks += mk.getSks();
        System.out.println("✓ Mata kuliah berhasil ditambahkan!");
    }

    public void cetakKRS() {
        System.out.println("\n================== KARTU RENCANA STUDI ===================");
        System.out.println("NIM   : " + nim);
        System.out.println("Nama  : " + nama);
        System.out.println("----------------------------------------------------------");
        System.out.println("Daftar Mata Kuliah:");

        for (int i = 0; i < jumlahMK; i++) {
            System.out.println("- " + krs[i].toString());
        }

        System.out.println("----------------------------------------------------------");
        System.out.println("Total SKS : " + totalSks);
        System.out.println("\nTanda Tangan KPS");
        System.out.println(" ");
        System.out.println("_____________________");
        System.out.println("KPS: " + nama + " (" + nim + ")");
        System.out.println("==========================================================\n");
    }
}

// Class Main
public class KRSApp {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);

        System.out.println("=== Pengisian KRS Mahasiswa Sistem Informasi ===");

        // Input NIM & Nama
        System.out.print("Masukkan NIM   : ");
        String nim = input.nextLine();

        System.out.print("Masukkan Nama  : ");
        String nama = input.nextLine();

        Mahasiswa mhs = new Mahasiswa(nim, nama);

        while (true) {
            System.out.print("\nMasukkan kode MK   : ");
            String kode = input.nextLine();

            System.out.print("Masukkan nama MK   : ");
            String namaMk = input.nextLine();

            System.out.print("Masukkan jumlah SKS: ");
            int sks = input.nextInt();
            input.nextLine();

            MataKuliah mk = new MataKuliah(kode, namaMk, sks);
            mhs.tambahMataKuliah(mk);

            System.out.print("Tambah MK lagi? (y/n): ");
            String lagi = input.nextLine();

            if (lagi.equalsIgnoreCase("n")) {
                break;
            }
        }

        mhs.cetakKRS();
    }
}
