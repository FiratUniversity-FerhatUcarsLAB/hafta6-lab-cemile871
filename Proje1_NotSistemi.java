import java.util.Scanner;
public class NotSistemi {

    public static double calculateAverage(int vize, int finalNotu, int odev) {
      return (vize * 0.3) + (finalNotu * 0.4) + (odev * 0.3);
    }
    public static boolean isPassingGrade (double ortalama){
        return ortalama >= 50;
    }
    public static char getLetterGrade (double ortalama){
       if (ortalama >= 90){
           return 'A';
       } else if (ortalama >= 80){
           return 'B';
       } else if (ortalama >= 70){
           return 'C';
       } else if (ortalama >= 60){
           return 'D';
       } else if (ortalama >= 50){
           return 'E';
       } else {
           return 'F';
       }
    }

    public static boolean isHonorList (double ortalama, double vize, double finalNotu, double odev){
        return ortalama >=85 && vize >=70 && finalNotu >=70 && odev >=70;
    }

    public static boolean hasRetakeRight (double ortalama){
        return (40 <= ortalama && ortalama < 50);
    }

    public static void main(String[] args) {

        Scanner input = new Scanner(System.in);

        System.out.print("Vize Notunu Girin: ");
        int vize = input.nextInt();

        System.out.print("Final Notunu Girin: ");
        int finalNotu = input.nextInt();

        System.out.print("Ödev Notunu Girin: ");
        int odev = input.nextInt();

        input.close();

        System.out.println("=== OGRENCI NOT RAPORU ===");
        System.out.println("Vize Notu     : " + vize);
        System.out.println("Final Notu    : " + finalNotu);
        System.out.println("Ödev Notu     : " + odev);
        System.out.println("---------------------------");

        double ortalama = calculateAverage(vize, finalNotu, odev);
        System.out.println("Ortalama      : " + ortalama);
        System.out.println("Harf Notu     : " + getLetterGrade(ortalama));
        System.out.println("Durum         : " + (isPassingGrade(ortalama)? "GEÇTİ" : "KALDI"));
        System.out.println("Onur Listesi  : " + (isHonorList(ortalama,vize,finalNotu,odev)? "EVET" : "HAYIR"));
        System.out.println("Bütünleme     : " + (hasRetakeRight(ortalama)? "VAR" : "YOK"));

   }
}
