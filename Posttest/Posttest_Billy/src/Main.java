class MetodePembayaran {
    void bayar(double nominal) {
        System.out.println("Memproses pembayaran umum sebesar Rp" + nominal);
    }
}

class EWallet extends MetodePembayaran {

    @Override
    void bayar(double nominal) {
        System.out.println("Memotong saldo E-Wallet sebesar Rp" + nominal);
    }

    // Overloading
    void bayar(double nominal, String nomorHP) {
        System.out.println("Memotong saldo E-Wallet sebesar Rp" + nominal + " dari nomor " + nomorHP);
    }
}

class KartuKredit extends MetodePembayaran {

    @Override
    void bayar(double nominal) {
        System.out.println("Mencetak tagihan Kartu Kredit sebesar Rp" + nominal);
    }

    void verifikasiPIN() {
        System.out.println("Memverifikasi PIN Kartu Kredit... BERHASIL");
    }
}

public class Main {
    public static void main(String[] args) {

        MetodePembayaran[] metodePembayaran = new MetodePembayaran[2];
        metodePembayaran[0] = new EWallet();
        metodePembayaran[1] = new KartuKredit();

        for (MetodePembayaran metode : metodePembayaran) {

            // Runtime polymorphism
            metode.bayar(100000);

            // instanceof 
            if (metode instanceof EWallet) {
                EWallet ew = (EWallet) metode;
                ew.bayar(50000, "08123456789");
            }

            if (metode instanceof KartuKredit) {
                KartuKredit kk = (KartuKredit) metode;
                kk.verifikasiPIN();
            }

            System.out.println();
        }
    }
}