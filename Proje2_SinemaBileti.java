/**
* Ad Soyad: [Cemile Akay]
* Numara: [250541099]
* Proje: [Sinema bileti]
* Tarih: [26.11.2025]
*/

import java.util.Scanner;

public class Proje2_SinemaBileti {

    // hafta sonu mu kontrol etme
    public static boolean isWeekend(int gun){
        return gun >= 6;
    }

    //matine mi kontrol etme
    public static boolean isMatinee(double saat){
        return saat < 12.00;
    }

    // temel fiyat hesaplama
    public static double calculateBasePrice(int gun, double saat){
        boolean weekend = isWeekend(gun);
        boolean matinee = isMatinee(saat);

        if (!weekend) { // Hafta içi
            if (matinee) return 45;  //hafta içi matine
            else return 65;          //hafta içi normal
        } else { // Hafta sonu
            if (matinee) return 55;  //hafta sonu matine
            else return 85;          //hafta sonu normal
        }
    }

    //indirim oranı hesaplama
    public static double calculateDiscount(int yas, int meslek, int gun){
        double indirimOrani = 0;

        if (yas >= 65) {
            indirimOrani = 0.30;
        }
        else if (yas < 12) {
            indirimOrani = 0.25;
        }
        else if (meslek == 1) {
            if (gun >= 1 && gun <= 4) indirimOrani = 0.20;
            else indirimOrani = 0.15;
        }
        else if (meslek == 2 && gun == 3) {
            indirimOrani = 0.35;
        }
        return indirimOrani;
    }

    // film formatı ek ücret
    public static int getFormatExtra(int filmTuru) {
        switch (filmTuru) {
            case 1: return 0;   // 2D
            case 2: return 25;  // 3D
            case 3: return 35;  // IMAX
            case 4: return 50;  // 4DX
            default: return 0;
        }
    }

    // final fiyat hesaplama
    public static double calculateFinalPrice(int gun, double saat, int yas, int meslek, int filmTuru) {

        double base = calculateBasePrice(gun, saat);
        double discountRate = calculateDiscount(yas, meslek, gun);
        double extra = getFormatExtra(filmTuru);

        double indirimli = base - (base * discountRate);
        return indirimli + extra;
    }


    public static void generateTicketInfo(int gun, double saat, int yas, int meslek, int filmTuru) {

        double fiyat = calculateFinalPrice(gun, saat, yas, meslek, filmTuru);

        String gunAdi;
        switch (gun) {
            case 1:
                gunAdi = "Pazartesi";
                break;
            case 2:
                gunAdi = "Salı";
                break;
            case 3:
                gunAdi = "Çarşamba";
                break;
            case 4:
                gunAdi = "Perşembe";
                break;
            case 5:
                gunAdi = "Cuma";
                break;
            case 6:
                gunAdi = "Cumartesi";
                break;
            case 7:
                gunAdi = "Pazar";
                break;
            default:
                gunAdi = "Geçersiz Gün";
                break;
        };

        String meslekAdi;
        switch (meslek) {
            case 1:
                meslekAdi = "Öğrenci";
                break;
            case 2:
                meslekAdi = "Öğretmen";
                break;
            default:
                meslekAdi = "Diğer";
                break;
        };

        String format;
        switch (filmTuru) {
            case 1:
                format = "2D";
                break;
            case 2:
                format = "3D";
                break;
            case 3:
                format = "IMAX";
                break;
            case 4:
                format = "4DX";
                break;
            default:
                format = "Belirsiz";
                break;
        };

        System.out.println("\n===== BİLET BİLGİSİ =====");
        System.out.println("Gün: " + gunAdi);
        System.out.println("Saat: " + saat);
        System.out.println("Yaş: " + yas);
        System.out.println("Meslek: " + meslekAdi);
        System.out.println("Film Türü: " + format);
        System.out.println("Toplam Fiyat: " + fiyat + " TL");
        System.out.println("=========================\n");
    }


    public static void main(String[] args){

        Scanner input = new Scanner(System.in);

        // gün bilgisi
        System.out.print("1-7 arasında bir gün numarası giriniz: ");
        int gun =  input.nextInt();

        // saat bilgisi
        System.out.print("Saat girin: ");
        double saat = input.nextDouble();

        //yaş bilgisi
        System.out.print("Yaş girin: ");
        int yas = input.nextInt();

        // meslek seçimi
        System.out.println("Meslek (1=Öğrenci, 2=Öğretmen, 3=Diğer)");
        System.out.print("Meslek seçiniz: ");
        int meslek = input.nextInt();

        //film türü seçimi
        System.out.println("Film Türü (1=2D, 2=3D, 3=IMAX, 4=4DX)");
        System.out.print("Tür seçiniz: ");
        int filmTuru = input.nextInt();

        // BÜTÜN BİLGİYİ TEK SEFERDE YAZDIR
        generateTicketInfo(gun, saat, yas, meslek, filmTuru);
    }

}
