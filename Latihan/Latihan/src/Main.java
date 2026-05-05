class PerangkatPintar {
    void aktifkan() {
        System.out.println("Perangkat pintar diaktifkan.");
    }
}

class LampuPintar extends PerangkatPintar {
    @Override
    void aktifkan() {
        System.out.println("Lampu menyala dengan tingkat kecerahan standar.");
    }

    // Overloading
    void aturKecerahan(int level) {
        System.out.println("Kecerahan lampu diatur ke level " + level + "%.");
    }

    void aturKecerahan(int level, String warna) {
        System.out.println("Kecerahan lampu diatur ke level " + level + "% dengan warna cahaya " + warna + ".");
    }
}

class AcPintar extends PerangkatPintar {
    @Override
    void aktifkan() {
        System.out.println("AC menyala dan mulai mendinginkan ruangan.");
    }
}

public class Main {
    public static void main(String[] args) {

        // Polymorphism (Soal 3)
        PerangkatPintar[] devices = new PerangkatPintar[2];

        devices[0] = new LampuPintar();
        devices[1] = new AcPintar();

        for (PerangkatPintar device : devices) {
            device.aktifkan(); // overriding jalan
        }

        // Overloading (Soal 2)
        LampuPintar lampu = new LampuPintar();
        lampu.aturKecerahan(70);
        lampu.aturKecerahan(80, "Putih");
    }
}