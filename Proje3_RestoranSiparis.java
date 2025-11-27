/**
* Ad Soyad: [Cemile Akay]
* Numara: [250541099]
* Proje: [Restoran sipariş]
* Tarih: [27.11.2025]
*/

import java.util.Scanner;
import java.math.BigDecimal;
import java.math.RoundingMode;

public class RestoranSiparis {

    // 1. Ana yemek fiyatı
    public static double getMainDishPrice(int secim) {
        switch (secim) {
            case 1: return 85.0;  // Izgara Tavuk
            case 2: return 120.0; // Adana Kebap
            case 3: return 110.0; // Levrek
            case 4: return 65.0;  // Mantı
            default: return 0.0;
        }
    }

    // 2. Başlangıç fiyatı
    public static double getAppetizerPrice(int secim) {
        switch (secim) {
            case 1: return 25.0;  // Çorba
            case 2: return 45.0;  // Humus
            case 3: return 55.0;  // Sigara Böreği
            default: return 0.0;
        }
    }

    // 3. İçecek fiyatı
    public static double getDrinkPrice(int secim) {
        switch (secim) {
            case 1: return 15.0; // Kola
            case 2: return 12.0; // Ayran
            case 3: return 35.0; // Taze Meyve Suyu
            case 4: return 25.0; // Limonata
            default: return 0.0;
        }
    }

    // 4. Tatlı fiyatı
    public static double getDessertPrice(int secim) {
        switch (secim) {
            case 1: return 65.0; // Künefe
            case 2: return 55.0; // Baklava
            case 3: return 35.0; // Sütlaç
            default: return 0.0;
        }
    }

    // 5. Combo mu? (Ana yemek + İçecek + Tatlı varsa)
    public static boolean isComboOrder(boolean anaVar, boolean icecekVar, boolean tatliVar) {
        return anaVar && icecekVar && tatliVar;
    }

    // 6. Happy hour mu? (14:00-17:00 arası)
    public static boolean isHappyHour(int saat) {
        return saat >= 14 && saat < 17;
    }

    // 7. İndirimleri hesapla.
    // Dönen değer bir dizi: [toplamIndirim, comboIndirim, happyHourIndirim, ogrenciIndirim]
    public static double[] calculateDiscount(double araToplam, double drinkPrice, boolean combo,
                                             boolean ogrenci, int saat, int gun) {
        double comboIndirim = 0.0;
        double happyHourIndirim = 0.0;
        double ogrenciIndirim = 0.0;

        // Combo: toplam üzerinden %15 (örnekte böyle uygulandı)
        if (combo) {
            comboIndirim = araToplam * 0.15;
        }

        // Happy hour: içeceklerde %20 indirim (saat kontrolü)
        if (isHappyHour(saat) && drinkPrice > 0) {
            happyHourIndirim = drinkPrice * 0.20;
        }

        // Öğrenci: hafta içi (1..5) ise kalan tutar üzerinden %10
        double kalan = araToplam - comboIndirim - happyHourIndirim;
        if (ogrenci && (gun >= 1 && gun <= 5)) {
            ogrenciIndirim = kalan * 0.10;
        }

        double toplamIndirim = comboIndirim + happyHourIndirim + ogrenciIndirim;
        return new double[]{round(toplamIndirim, 2), round(comboIndirim, 2), round(happyHourIndirim, 2), round(ogrenciIndirim, 2)};
    }

    // 8. Bahşiş önerisi: %10 (istedik)
    public static double calculateServiceTip(double tutar) {
        return round(tutar * 0.10, 2);
    }

    // Yardımcı: yuvarlama
    public static double round(double value, int places) {
        if (places < 0) throw new IllegalArgumentException();
        BigDecimal bd = BigDecimal.valueOf(value);
        bd = bd.setScale(places, RoundingMode.HALF_UP);
        return bd.doubleValue();
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.println("----- RESTORAN SİPARİŞ SİSTEMİ -----");
        System.out.println("Ana Yemekler (1-4): 1:Izgara Tavuk(85) 2:Adana Kebap(120) 3:Levrek(110) 4:Mantı(65)");
        System.out.print("Ana Yemek (1-4): ");
        int ana = sc.nextInt();

        System.out.println("Başlangıçlar (0-3): 1:Çorba(25) 2:Humus(45) 3:Sigara Böreği(55)");
        System.out.print("Başlangıç (1-3): ");
        int bas = sc.nextInt();

        System.out.println("İçecekler (0-4): 1:Kola(15) 2:Ayran(12) 3:Taze Meyve Suyu(35) 4:Limonata(25)");
        System.out.print("İçecek (1-4): ");
        int ice = sc.nextInt();

        System.out.println("Tatlılar (0-3): 1:Künefe(65) 2:Baklava(55) 3:Sütlaç(35)");
        System.out.print("Tatlı (1-3): ");
        int tat = sc.nextInt();

        System.out.print("Saat (8-23): ");
        int saat = sc.nextInt();

        System.out.print("Öğrenci misiniz? (E/H): ");
        char ogrChar = sc.next().toUpperCase().charAt(0);
        boolean ogrenci = (ogrChar == 'E');

        System.out.print("Hangi gün? (1=Pazartesi ... 7=Pazar): ");
        int gun = sc.nextInt();

        // Fiyatlar
        double anaFiyat = getMainDishPrice(ana);
        double basFiyat = getAppetizerPrice(bas);
        double iceFiyat = getDrinkPrice(ice);
        double tatFiyat = getDessertPrice(tat);

        boolean anaVar = anaFiyat > 0;
        boolean iceVar = iceFiyat > 0;
        boolean tatVar = tatFiyat > 0;

        double araToplam = anaFiyat + basFiyat + iceFiyat + tatFiyat;
        araToplam = round(araToplam, 2);

        boolean combo = isComboOrder(anaVar, iceVar, tatVar);
        boolean happy = isHappyHour(saat);

        double[] indirimler = calculateDiscount(araToplam, iceFiyat, combo, ogrenci, saat, gun);
        double toplamIndirim = indirimler[0];
        double comboInd = indirimler[1];
        double happyInd = indirimler[2];
        double ogrInd = indirimler[3];

        double toplam = araToplam - toplamIndirim;

        // 200 TL üzeri %10 indirim (örnek şartı: toplam (indirimlerden önce mi sonra mı?)
        // Kullanıcı örneğinde 200 TL üstü indirimi yoktu; ama gereksinimde var.
        // Burada 200 TL ÜZERİ indirimi "araToplam" üzerinden sonradan uygulasak adil olur.
        // Ancak not: örnekte bu adım kullanılmamış. Aşağıda 200 TL üzeri indirimi araToplam sonrası uyguluyoruz.
        double indirim200 = 0.0;
        if (araToplam > 200.0) {
            // Uygulanacak senaryoya göre bu indirimi toplam üzerinden uyguluyoruz (örnek: %10)
            indirim200 = round((araToplam - toplamIndirim) * 0.10, 2);
            toplam -= indirim200;
        }

        toplam = round(toplam, 2);

        double servisOnerisi = calculateServiceTip(toplam);

        // Çıktı
        System.out.println("\n----- HESAP ÖZETİ -----");
        System.out.printf("Ara Toplam: %.2f TL\n", araToplam);
        if (combo) System.out.printf("Combo indirimi (%%15): -%.2f TL\n", comboInd);
        if (happy) System.out.printf("Happy Hour (içecek %%20): -%.2f TL\n", happyInd);
        if (ogrenci && (gun >=1 && gun <=5)) System.out.printf("Öğrenci indirimi (%%10, hafta içi): -%.2f TL\n", ogrInd);
        if (indirim200 > 0) System.out.printf("200 TL üzeri indirim (%%10): -%.2f TL\n", indirim200);
        System.out.printf("Toplam: %.2f TL\n", toplam);
        System.out.printf("Bahşiş önerisi (%%10): %.2f TL\n", servisOnerisi);

        sc.close();
    }
}
