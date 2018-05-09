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

        //I cant use spring / hibernate validators, so i'll perform simple manual validation
        DateTimeFormatter formatter = DateTimeFormat.forPattern("MMM dd, yyyy").withLocale(Locale.US);
        
        PartDTO partDto = new PartDTO();
        
        //I do not need to know which field is violated - so one error for all violations
        for(Map.Entry entry : req.getParameterMap().entrySet())
        {
            String key = (String)entry.getKey();
            String val = ((String[])entry.getValue())[0];
            try
            {
                if("qty".equals(key))        partDto.setQty(Integer.parseInt(val));
                if("shBefore".equals(key))   partDto.setShippedBefore(LocalDate.parse(val, formatter));
                if("shAfter".equals(key))    partDto.setShippedAfter(LocalDate.parse(val, formatter));
                if("reBefore".equals(key))   partDto.setRecieveBefore(LocalDate.parse(val, formatter));
                if("reAfter".equals(key))    partDto.setRecieveAfter(LocalDate.parse(val, formatter));
                if("partname".equals(key))   partDto.setPartName(val);
                if("partnumber".equals(key)) partDto.setPartNumber(val);
                if("vendor".equals(key))     partDto.setVendor(val);
            } 
            catch (Exception e)
            {
                LOG.debug("User posted wrong or empty data: " + e.getLocalizedMessage());
            }
            
        }

        List<Part> parts = PartDao.getInstance().getFiltered(partDto);
        req.setAttribute("partsList", parts);

        req.getRequestDispatcher("/WEB-INF/jsp/index.jsp").forward(req, resp);
        
    }
    
    
}
