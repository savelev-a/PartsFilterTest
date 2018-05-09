/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ru.codemine.partsfilter.model.entity;

import java.util.Objects;
import org.joda.time.LocalDate;

/**
 *
 * @author Alchemist
 */

//this is DTO to transfer data from router to service
public class PartDTO
{
    private String partName;
    private String partNumber;
    private String vendor;
    private int qty;
    private LocalDate shippedBefore;
    private LocalDate recieveBefore;
    private LocalDate shippedAfter;
    private LocalDate recieveAfter;
    
    public PartDTO()
    {
        this.partName = "";
        this.partNumber = "";
        this.vendor = "";
        this.qty = 0;
    }

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

    public LocalDate getShippedBefore()
    {
        return shippedBefore;
    }

    public void setShippedBefore(LocalDate shippedBefore)
    {
        this.shippedBefore = shippedBefore;
    }

    public LocalDate getRecieveBefore()
    {
        return recieveBefore;
    }

    public void setRecieveBefore(LocalDate recieveBefore)
    {
        this.recieveBefore = recieveBefore;
    }

    public LocalDate getShippedAfter()
    {
        return shippedAfter;
    }

    public void setShippedAfter(LocalDate shippedAfter)
    {
        this.shippedAfter = shippedAfter;
    }

    public LocalDate getRecieveAfter()
    {
        return recieveAfter;
    }

    public void setRecieveAfter(LocalDate recieveAfter)
    {
        this.recieveAfter = recieveAfter;
    }

    @Override
    public int hashCode()
    {
        int hash = 5;
        hash = 97 * hash + Objects.hashCode(this.partNumber);
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
        final PartDTO other = (PartDTO) obj;
        
        return Objects.equals(this.partNumber, other.partNumber);
    }

    

}
