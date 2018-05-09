package ru.codemine.partsfilter.router;

import ru.codemine.partsfilter.model.dao.PartDao;
import ru.codemine.partsfilter.model.entity.Part;
import ru.codemine.partsfilter.model.entity.PartDTO;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import org.apache.log4j.Logger;
import org.joda.time.LocalDate;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

public class DefaultRouter extends HttpServlet
{
    private static final Logger LOG = Logger.getLogger("DefaultRouter");
    
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException
    {
        List<Part> parts = PartDao.getInstance().getAll();
        req.setAttribute("partsList", parts);

        req.getRequestDispatcher("/WEB-INF/jsp/index.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException
    {
        Map<String, String[]> parameters = req.getParameterMap();
        
        //I cant use spring / hibernate validators, so i'll perform simple manual validation
        DateTimeFormatter formatter = DateTimeFormat.forPattern("MMM dd, yyyy").withLocale(Locale.US);
        
        int qty = 0;
        LocalDate shBefore = null;
        LocalDate shAfter = null;
        LocalDate reBefore = null;
        LocalDate reAfter = null;
        
        try
        {
            qty = Integer.parseInt(parameters.get("qty")[0]);
        } 
        catch (NumberFormatException e)
        {
            LOG.info("User posted wrong or empty quantity");
        }
        
        try
        {
            shBefore = LocalDate.parse(parameters.get("shBefore")[0], formatter);
        }
        catch (IllegalArgumentException e)
        {
            LOG.info("User posted wrong or empty shipped before date");
        }
        
        try
        {
            shAfter = LocalDate.parse(parameters.get("shAfter")[0], formatter);
        }
        catch (IllegalArgumentException e)
        {
            LOG.info("User posted wrong or empty shipped after date");
        }
        
        try
        {
            reBefore = LocalDate.parse(parameters.get("reBefore")[0], formatter);
        }
        catch (IllegalArgumentException e)
        {
            LOG.info("User posted wrong or empty recieved before date");
        }
        
        try
        {
            reAfter = LocalDate.parse(parameters.get("reAfter")[0], formatter);
        }
        catch (IllegalArgumentException e)
        {
            LOG.info("User posted wrong or empty recieved after date");
        }

        
        //I'll use DTO to transfer data
        PartDTO partDto = new PartDTO();
        partDto.setPartName(parameters.get("partname")[0]);
        partDto.setPartNumber(parameters.get("partnumber")[0]);
        partDto.setVendor(parameters.get("vendor")[0]);
        partDto.setQty(qty);
        partDto.setShippedBefore(shBefore);
        partDto.setShippedAfter(shAfter);
        partDto.setRecieveBefore(reBefore);
        partDto.setRecieveAfter(reAfter);
        
        List<Part> parts = PartDao.getInstance().getFiltered(partDto);
        req.setAttribute("partsList", parts);

        req.getRequestDispatcher("/WEB-INF/jsp/index.jsp").forward(req, resp);
        
    }
    
    
}
