package project.util;

import java.time.LocalDateTime;

public class Date implements Comparable<Date>
{
    private Integer year;
    private Integer month;
    private Integer day;

    public Date(int y , int m , int d)
    {
        year = y;
        month = m;
        day = d;
    }

    public Date(String d)
    {
        year = Integer.parseInt(d.substring(0 , 4));
        month = Integer.parseInt(d.substring(5 , 7));
        day = Integer.parseInt(d.substring(8 , 10));
    }

    public Date(Date d)
    {
        this.year = d.getYear();
        this.month = d.getMonth();
        this.day = d.getDay();
    }

    public int getYear()
    {
        return this.year;
    }

    public int getMonth()
    {
        return this.month;
    }

    public int getDay()
    {
        return this.day;
    }

    public static boolean checkDate(String date)
    {
        String datetime[] = date.split(" ");
        if(datetime.length != 2) return false;
        String dateArgs[] = datetime[0].split("-");
        if(dateArgs.length != 3) return false;
        String timeArgs[] = datetime[1].split(":");
        if(timeArgs.length != 2) return false;
        int year = Integer.parseInt(dateArgs[0]);
        int month = Integer.parseInt(dateArgs[1]);
        int day = Integer.parseInt(dateArgs[2]);
        int hour = Integer.parseInt(timeArgs[0]);
        int minute = Integer.parseInt(timeArgs[1]);
        if(minute < 0 || minute > 59 || hour < 0 || hour > 23) return false;
        if(month < 0 || month > 12) return false;
        if((month == 2 || month == 4 || month == 6 || month == 9 || month == 11) && day > 30) return false;
        if(year % 4 == 0 && month == 2 && day > 29) return false;
        if(year % 4 != 0 && month == 2 && day > 28) return false;
        if(year < 0) return false;
        return true;
    }

    public String toString()
    {
        return (this.year < 10 ? "000" + this.year : this.year < 100 ? "00" + this.year : this.year < 1000 ? "0" + this.year : this.year) + "-" +
                (this.month < 10 ? "0" + this.month : this.month) + "-" +
                (this.day < 10 ? "0" + this.day : this.day);
    }

    @Override
    public int compareTo(Date o)
    {
        if(this.year != o.getYear()) return this.year - o.getYear();
        if(this.month != o.getMonth()) return this.month - o.getMonth();
        return this.day - o.getDay();
    }

    public boolean after(Date o)
    {
        return this.compareTo(o) > 0;
    }

    public boolean before(Date o)
    {
        return this.compareTo(o) < 0;
    }

    public boolean equals(Date o)
    {
        return this.compareTo(o) == 0;
    }

    public static Date getToday()
    {
        return new Date(LocalDateTime.now().getYear() ,
                LocalDateTime.now().getMonth().getValue() ,
                LocalDateTime.now().getDayOfMonth());
    }

    public static Date getInAWeek()
    {
        return new Date(LocalDateTime.now().plusDays(7).getYear() ,
                LocalDateTime.now().plusDays(7).getMonth().getValue() ,
                LocalDateTime.now().plusDays(7).getDayOfMonth());
    }
}
