package com.mycompany.main;


import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.*;
public class Main {

    public static void main(String[] args) {
        SJF();
    }
    
    
    static void dosyaOkuma(){
     try {
      File file = new File("C:\\Users\\taner\\Desktop\\prosesler.txt");
      Scanner myReader = new Scanner(file);
      while (myReader.hasNextLine()) {
        String data = myReader.nextLine();
        System.out.println(data);
      }
      myReader.close();
    } catch (FileNotFoundException e) {
      System.out.println("Bir hata tespit edildi.");
      e.printStackTrace();
    }
      System.out.println("dosya okundu");
    }
    
    static void FCFS(){
        Scanner sc = new Scanner(System.in);
System.out.println("proses sayisini giriniz : ");
int n = sc.nextInt();
int proses_id[] = new int[n];   // process ids
int varis_suresi[] = new int[n];     // arrival times
int islem_suresi[] = new int[n];     // burst or execution times
int tamamlanma_zamani[] = new int[n];     // completion times
int donus_zamani[] = new int[n];     // turn around times
int bekleme_zamani[] = new int[n];     // waiting times
int temp;
float bekle=0,don=0;
 
for(int i = 0; i < n; i++)
{
System.out.println("prosesin " + (i+1) + " bekleme zamanı: ");
varis_suresi[i] = sc.nextInt();
System.out.println(" prosesin " + (i+1) + " islem süresi : ");
islem_suresi[i] = sc.nextInt();
proses_id[i] = i+1;
}
 
//sorting according to arrival times
for(int i = 0 ; i <n; i++)
{
for(int  j=0;  j < n-(i+1) ; j++)
{
if( varis_suresi[j] > varis_suresi[j+1] )
{
temp = varis_suresi[j];
varis_suresi[j] = varis_suresi[j+1];
varis_suresi[j+1] = temp;
temp = islem_suresi[j];
islem_suresi[j] = islem_suresi[j+1];
islem_suresi[j+1] = temp;
temp = proses_id[j];
proses_id[j] = proses_id[j+1];
proses_id[j+1] = temp;
}
}
}
// finding completion times
for(int  i = 0 ; i < n; i++)
{
if( i == 0)
{
tamamlanma_zamani[i] = varis_suresi[i] + islem_suresi[i];
}
else
{
if( varis_suresi[i] > tamamlanma_zamani[i-1])
{
tamamlanma_zamani[i] = varis_suresi[i] + islem_suresi[i];
}
else
tamamlanma_zamani[i] = tamamlanma_zamani[i-1] + islem_suresi[i];
}
donus_zamani[i] = tamamlanma_zamani[i] - varis_suresi[i] ;          // turnaround time= completion time- arrival time
bekleme_zamani[i] = donus_zamani[i] - islem_suresi[i] ;          // waiting time= turnaround time- burst time
bekle += bekleme_zamani[i] ;               // total waiting time
don += donus_zamani[i] ;               // total turnaround time
}
System.out.println("\nproses id bekleme ve islemeyi bitirdi bekleniyor...    ");
for(int  i = 0 ; i< n;  i++)
{
System.out.println(proses_id[i] + "  \t " + varis_suresi[i] + "\t" + islem_suresi[i] + "\t" + tamamlanma_zamani[i] + "\t" + donus_zamani[i] + "\t"  + bekleme_zamani[i] ) ;
}
sc.close();
System.out.println("\nOrtalama Bekleme Suresi : "+ (bekle/n));     // printing average waiting time.
System.out.println("Ortalama donus suresi:"+(don/n));    // printing average turnaround time.
}
    
    
    
    
    static void SJF(){
      Scanner sc = new Scanner(System.in);
    System.out.println("Kullanilacak proses sayisini girin : \n");
    int n = sc.nextInt();
    int ID;
    int ArrivalTime[] = new int[n];
    int ServiceTime[] = new int[n];
    int FinishTime[] = new int[n];
    int StartTime[] = new int[n];
    int WaitingTime[] = new int[n];
    int TurnAroundTime [] = new int[n];
    int TotalWaitingTime = 0;
    int TotalTurnAroundTime = 0;
    double AVGWaitingTime;
    double AVGTurnAroundTime;


    System.out.println("_____________________________________");

    for (int i = 0; i < n; i++) {
        System.out.println("Proses id girin " + (i + 1));
        ID = sc.nextInt();
    }

    System.out.println("______________________________________");

    System.out.println("*islemler icin varis suresi girin*");
    for (int i = 0; i < n; i++) {
        System.out.println("islemler icin varis suresi girin  " + (i + 1));
        ArrivalTime[i] = sc.nextInt();
    }

    System.out.println("_______________________________________");

    System.out.println("*islem suresini girin :*");
    for (int i = 0; i < n; i++) {
        System.out.println("islem suresini girin  " + (i + 1));
        ServiceTime[i] = sc.nextInt();
    }

    //______________________________________________________________________
    //process
    //______________________________________________________________________
    int time = 0; // "time" for the current time.
    int min = 0;  // "min" for the least service time.
    int j = 0;    // "j" to save the task with the least time.
    int count;    // "count" to identify if it is the first operation in this turn. 

    for (int c = 0; c < n; c++) {
        count = 0;
        for (int i = 0; i < n; i++) //looping through all the tasks
        {
            if (FinishTime[i] == 0) //checking if the task wasn't done before
            {
                if (ArrivalTime[i] <= time) //checking if the task arrived or not
                {
                    count++;
                    if (count == 1) //checking if it's the first task in this turn
                    {
                        min = ServiceTime[i];
                        j = i;
                    } else {
                        if (ServiceTime[i] < min) //checking if the task's service time is less than the current min
                        {
                            min = ServiceTime[i];
                            j = i; //takes procees number and puts it in "j" then compares again
                        }
                    }
                }
            }
        }
        StartTime[j] = time;
        time += min;
        FinishTime[j] = time;
    }

    //calculating waiting time and turn around time
    for (int y = 0; y < n; y++) {
        WaitingTime[y] = StartTime[y] - ArrivalTime[y];
        TurnAroundTime[y] = WaitingTime[y] + ServiceTime[y];
        TotalWaitingTime += WaitingTime[y];
        TotalTurnAroundTime += TurnAroundTime[y];
    }

    AVGWaitingTime = (double) TotalWaitingTime / n;
    AVGTurnAroundTime = (double) TotalTurnAroundTime / n;

    //______________________________________________________________________
    //output
    //______________________________________________________________________
    System.out.println("*Finish time for the processes*");

    System.out.println("_______________________________________");

    for (int i = 0; i < n; i++) {
        System.out.println("Finish time for process  " + (i + 1) + "  " + FinishTime[i]);
    }

    System.out.println("____________________________________________________");

    System.out.println("*Waiting time time for the processes*");

    System.out.println("_______________________________________");

    for (int i = 0; i < n; i++) {
        System.out.println("Waiting time for process  " + (i + 1) + "  " + WaitingTime[i]);
    }

    System.out.println("____________________________________________________");

    for (int i = 0; i < n; i++) {
        System.out.println("Turnaround time time for process  " + (i + 1) + "  " + TurnAroundTime[i]);
    }

    System.out.println("____________________________________________________");

    System.out.println("_______________________________________");

    System.out.println("AVGWaiting time for processes  " + "  " + AVGWaitingTime);

    System.out.println("____________________________________________________");

    System.out.println("AVGTurnAroundTime time for processes  " + "  " + AVGTurnAroundTime);

    System.out.println("____________________________________________________");

 }
    
    
    }
    





    

    
