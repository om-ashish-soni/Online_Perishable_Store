package date;

import java.io.Serializable;


public class Date implements Comparable<Date>, Serializable{
    int day,month,year;
    String date;
    public Date(int day,int month , int year){
        this.date=year+"/"+month+"/"+day;
    }
    public String toString(){
        return this.date;
    }
    public int compareTo(Date d){
        if(0<this.date.compareTo(d.date)) return -1;
        return 0;
    }
}