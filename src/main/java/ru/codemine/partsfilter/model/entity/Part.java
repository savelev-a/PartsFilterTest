package ru.codemine.partsfilter.model.entity;

import org.joda.time.LocalDate;

import java.io.Serializable;
import java.util.Locale;
import java.util.Objects;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

public class Part implements Serializable
{
    private String partName;
    private String partNumber;
    private String vendor;
    private int qty;
    private LocalDate shipped;
    private LocalDate recieve;

    public String getPartName()
    {
        return partName;
    }

    public void setPartName(String partName)
    {
        this.partName = partName;
    }

    public String getPartNumber()
    {
        return partNumber;
    }

    public void setPartNumber(String partNumber)
    {
        this.partNumber = partNumber;
    }

    public String getVendor()
    {
        return vendor;
    }

    public void setVendor(String vendor)
    {
        this.vendor = vendor;
    }

    public int getQty()
    {
        return qty;
    }

    public void setQty(int qty)
    {
        this.qty = qty;
    }

    public LocalDate getShipped()
    {
        return shipped;
    }

    public void setShipped(LocalDate shipped)
    {
        this.shipped = shipped;
    }

    public LocalDate getRecieve()
    {
        return recieve;
    }

    public void setRecieve(LocalDate recieve)
    {
        this.recieve = recieve;
    }
    
    public String getShippedStr()
    {
        DateTimeFormatter formatter = DateTimeFormat.forPattern("MMM dd, yyyy").withLocale(Locale.US);
        return formatter.print(shipped);
    }
    
    public String getRecieveStr()
    {
        DateTimeFormatter formatter = DateTimeFormat.forPattern("MMM dd, yyyy").withLocale(Locale.US);
        return formatter.print(recieve);
    }

    @Override
    public int hashCode()
    {
        int hash = 3;
        hash = 53 * hash + Objects.hashCode(this.partNumber);
        return hash;
    }

    @Override
    public boolean equals(Object obj)
    {
        if (this == obj)
        {
            return true;
        }
        if (obj == null)
        {
            return false;
        }
        if (getClass() != obj.getClass())
        {
            return false;
        }
        final Part other = (Part) obj;
        
        return Objects.equals(this.partNumber, other.partNumber);
    }



}
