import java.util.ArrayList;

// Class induk untuk semua layanan ekspedisi
class LayananEkspedisi {

    // Protected dipakai supaya subclass bisa akses atribut ini
    protected String nomorResi;
    protected double beratAktualKg;
    protected double panjang;
    protected double lebar;
    protected double tinggi;

    // Constructor untuk isi data awal pengiriman
    public LayananEkspedisi(String nomorResi, double beratAktualKg,
                            double panjang, double lebar, double tinggi) {

        this.nomorResi = nomorResi;
        this.beratAktualKg = beratAktualKg;
        this.panjang = panjang;
        this.lebar = lebar;
        this.tinggi = tinggi;
    }

    // Menghitung berat efektif
    // Berat efektif = nilai terbesar antara berat asli dan berat volumetrik
    public double hitungBeratEfektif() {

        // Rumus volumetrik dari soal
        double beratVolumetrik = (panjang * lebar * tinggi) / 6000;

        // Math.max dipakai buat ambil nilai paling besar
        return Math.max(beratAktualKg, beratVolumetrik);
    }

    // Method untuk menampilkan data resi
    public void cetakResi() {

        System.out.println("Nomor Resi : " + nomorResi);
        System.out.println("Berat Efektif : " + hitungBeratEfektif() + " Kg");
    }

    // Method polymorphism
    // Dibuat default dulu lalu nanti dioverride subclass
    public double hitungOngkir() {
        return 0.0;
    }
}

// Subclass layanan reguler
class LayananReguler extends LayananEkspedisi {

    // Constructor subclass
    public LayananReguler(String nomorResi, double beratAktualKg,
                          double panjang, double lebar, double tinggi) {

        // super dipakai buat manggil constructor parent
        super(nomorResi, beratAktualKg, panjang, lebar, tinggi);
    }

    // Override method hitungOngkir
    // Tarif reguler = 15 ribu per Kg
    @Override
    public double hitungOngkir() {

        return hitungBeratEfektif() * 15000;
    }

    // Overloading method hitungOngkir
    // Method ini punya parameter tambahan
    public double hitungOngkir(boolean isMember, int jarakKm) {

        // Ambil ongkir dasar dulu
        double total = hitungOngkir();

        // Kalau member dapat diskon 10%
        if (isMember) {
            total = total - (total * 0.10);
        }

        // Tambahkan surcharge jarak
        total = total + (500 * jarakKm);

        return total;
    }
}

// Subclass layanan express
class LayananExpress extends LayananEkspedisi {

    public LayananExpress(String nomorResi, double beratAktualKg,
                          double panjang, double lebar, double tinggi) {

        super(nomorResi, beratAktualKg, panjang, lebar, tinggi);
    }

    // Tarif express = 30 ribu per Kg
    @Override
    public double hitungOngkir() {

        return hitungBeratEfektif() * 30000;
    }

    // Method khusus express untuk klaim asuransi
    public void klaimAsuransi(double nilaiBarang) {

        // Kalau nilai barang lebih dari 1 juta
        // maka dianggap VIP
        if (nilaiBarang > 1000000) {

            System.out.println(
                "Klaim Asuransi VIP Rp" + nilaiBarang +
                " untuk resi " + nomorResi +
                " sedang diproses prioritas."
            );

        } else {

            System.out.println(
                "Klaim Asuransi Standar diproses dalam 7 hari kerja."
            );
        }
    }
}

// Subclass layanan internasional
class LayananInternasional extends LayananEkspedisi {

    // Tambahan atribut khusus internasional
    private String negaraTujuan;
    private double nilaiBarangUSD;

    public LayananInternasional(String nomorResi, double beratAktualKg,
                                double panjang, double lebar, double tinggi,
                                String negaraTujuan, double nilaiBarangUSD) {

        super(nomorResi, beratAktualKg, panjang, lebar, tinggi);

        this.negaraTujuan = negaraTujuan;
        this.nilaiBarangUSD = nilaiBarangUSD;
    }

    // Override ongkir internasional
    @Override
    public double hitungOngkir() {

        // Ongkir dasar internasional
        double ongkirDasar = hitungBeratEfektif() * 200000;

        // Pajak default = 0
        double pajak = 0;

        // Kalau nilai barang > 50 USD kena pajak 20%
        if (nilaiBarangUSD > 50) {
            pajak = ongkirDasar * 0.20;
        }

        // Total ongkir = ongkir dasar + pajak
        return ongkirDasar + pajak;
    }

    // Method khusus internasional
    public void cetakManifest() {

        System.out.println(
            "Manifest Internasional ke " + negaraTujuan +
            " - Deklarasi Nilai: $" + nilaiBarangUSD
        );
    }
}

// Main class
public class Main {

    public static void main(String[] args) {

        // ArrayList dipakai buat menyimpan berbagai object layanan
        // Di sini terjadi upcasting karena semua object disimpan
        // dalam tipe parent yaitu LayananEkspedisi
        ArrayList<LayananEkspedisi> daftarPengiriman = new ArrayList<>();

        // Data layanan reguler
        daftarPengiriman.add(
            new LayananReguler(
                "REG-11",
                2,
                50,
                50,
                50
            )
        );

        // Data layanan express
        daftarPengiriman.add(
            new LayananExpress(
                "EXP-22",
                5,
                10,
                10,
                10
            )
        );

        // Data layanan internasional
        daftarPengiriman.add(
            new LayananInternasional(
                "INT-33",
                3,
                20,
                20,
                20,
                "Korea",
                100
            )
        );

        // Variabel untuk menyimpan total pendapatan perusahaan
        double totalPendapatanPerusahaan = 0.0;

        // Looping untuk membaca isi ArrayList
        for (LayananEkspedisi layanan : daftarPengiriman) {

            // Cetak data resi
            layanan.cetakResi();

            // Tambahkan ongkir dasar ke total perusahaan
            totalPendapatanPerusahaan += layanan.hitungOngkir();

            // Cek apakah object termasuk layanan reguler
            if (layanan instanceof LayananReguler) {

                // Downcasting ke LayananReguler
                LayananReguler reguler = (LayananReguler) layanan;

                // Hitung ongkir dengan promo member dan surcharge
                double hargaPromo = reguler.hitungOngkir(true, 25);

                System.out.println(
                    "Ongkir setelah promo + surcharge : Rp" +
                    hargaPromo
                );
            }

            // Cek apakah object termasuk layanan express
            else if (layanan instanceof LayananExpress) {

                // Downcasting ke LayananExpress
                LayananExpress express = (LayananExpress) layanan;

                // Panggil method klaim asuransi
                express.klaimAsuransi(1500000);
            }

            // Cek apakah object termasuk layanan internasional
            else if (layanan instanceof LayananInternasional) {

                // Downcasting ke LayananInternasional
                LayananInternasional internasional =
                    (LayananInternasional) layanan;

                // Cetak manifest internasional
                internasional.cetakManifest();
            }

            // Spasi biar output lebih rapi
            System.out.println();
        }

        // Menampilkan total pendapatan perusahaan
        System.out.println(
            "Total Pendapatan Perusahaan : Rp" +
            totalPendapatanPerusahaan
        );
    }
}