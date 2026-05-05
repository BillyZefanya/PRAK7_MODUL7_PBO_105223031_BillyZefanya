// Soal 1: Superclass
class PerangkatPintar {
    void aktifkan() {
        System.out.println("Perangkat pintar diaktifkan.");
    }
}

// Soal 1 & 2: Inheritance + Overriding + Overloading
class LampuPintar extends PerangkatPintar {

    // Soal 1: Override
    @Override
    void aktifkan() {
        System.out.println("Lampu menyala dengan tingkat kecerahan standar.");
    }

    // Soal 2: Overloading
    void aturKecerahan(int level) {
        System.out.println("Kecerahan lampu diatur ke level " + level + "%.");
    }

    void aturKecerahan(int level, String warna) {
        System.out.println("Kecerahan lampu diatur ke level " + level + "% dengan warna cahaya " + warna + ".");
    }
}

// Soal 1 & 4: Inheritance + method khusus
class AcPintar extends PerangkatPintar {

    // Soal 1: Override
    @Override
    void aktifkan() {
        System.out.println("AC menyala dan mulai mendinginkan ruangan.");
    }

    // Soal 4: Method khusus
    void aturSuhu(int suhu) {
        System.out.println("Suhu ruangan diatur menjadi " + suhu + " derajat.");
    }
}

public class Main {
    public static void main(String[] args) {

        // Soal 3: Polymorphism
        PerangkatPintar[] devices = new PerangkatPintar[2];
        devices[0] = new LampuPintar();
        devices[1] = new AcPintar();

        for (PerangkatPintar device : devices) {
            device.aktifkan();
        }

        // Analisis Soal 3:
        // Method yang dijalankan sesuai objek (polymorphism).

        // Soal 2: Overloading
        LampuPintar lampu = new LampuPintar();
        lampu.aturKecerahan(70);
        lampu.aturKecerahan(80, "Putih");

        // Soal 4: Downcasting
        for (PerangkatPintar device : devices) {
            if (device instanceof AcPintar) {
                ((AcPintar) device).aturSuhu(24);
            }
        }

        // Analisis Soal 4:
        // Perlu casting karena method hanya ada di AcPintar.

        // Soal 5: Error
        PerangkatPintar alat1 = new LampuPintar();

        // alat1.aturKecerahan(75, "Putih"); // error

        // Analisis Soal 5:
        // Method tidak bisa dipanggil karena tidak ada di tipe PerangkatPintar.

        // Perbaikan:
        ((LampuPintar) alat1).aturKecerahan(75, "Putih");
    }
}