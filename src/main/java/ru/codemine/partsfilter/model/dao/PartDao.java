package ru.codemine.partsfilter.model.dao;


import org.joda.time.LocalDate;
import ru.codemine.partsfilter.model.entity.Part;
import ru.codemine.partsfilter.model.entity.PartDTO;
import java.io.IOException;
import java.io.InputStream;
import java.sql.*;
import java.util.*;
import org.apache.log4j.Logger;

//This is database access class, all-in-one the dao and service for retrieving data
//I'll use singleton here, because spring is unavaible by the terms

public class PartDao
{
    private static final Logger LOG = Logger.getLogger("PartDAO");

    //database credentials
    //it will be loaded from properties file in constructor, here are default values
    private String DB_URL = "jdbc:postgresql://localhost:5432/parts";
    private String USER = "postgres";
    private String PASS = "postgres";


    private PartDao()
    {
        //loading db connection settings from db.properties in classpath
        InputStream input = this.getClass().getClassLoader().getResourceAsStream("db.properties");
        if(input != null)
        {
            Properties props = new Properties();
            try
            {
                props.load(input);
                DB_URL = props.getProperty("db.url");
                USER = props.getProperty("db.user");
                PASS = props.getProperty("db.password");
            }
            catch (IOException e)
            {
                LOG.error("Error loading database properties: " + e.getLocalizedMessage());
            }
            
            LOG.info("DB settings loaded: " + DB_URL + " / " + USER);
        }
        else
        {
            LOG.warn("Cant load db.properties! defaults used");
        }

    }

    private static PartDao instance = new PartDao();
    public static PartDao getInstance()
    {
        return instance;
    }

    public List<Part> getAll()
    {
        //I'll use empty DTO to get all records
        return getFiltered(new PartDTO());
    }
    
    
    public List<Part> getFiltered(PartDTO partDto)
    {
        List<Part> result = new ArrayList<>();

        try
        {
            Class.forName("org.postgresql.Driver");
            Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
            
            //this part could be better, i know...
            String sql = "SELECT * FROM parts WHERE qty >= " + partDto.getQty() + " ";
            
            if(!partDto.getPartName().isEmpty()) 
                sql = sql + "AND partname LIKE '%" + partDto.getPartName() + "%' ";
            
            if(!partDto.getPartNumber().isEmpty()) 
                sql = sql + "AND partnumber LIKE '%" + partDto.getPartNumber()+ "%' ";
            
            if(!partDto.getVendor().isEmpty()) 
                sql = sql + "AND vendor LIKE '%" + partDto.getVendor()+ "%' ";
            
            if(partDto.getShippedBefore() != null && partDto.getShippedAfter() != null)
            {
                sql = sql 
                        + "AND shipped BETWEEN '" 
                        + partDto.getShippedAfter().toString("YYYY-MM-dd")
                        + "' AND '"
                        + partDto.getShippedBefore().toString("YYYY-MM-dd")
                        + "' ";
            }
            
            if(partDto.getRecieveBefore() != null && partDto.getRecieveAfter() != null)
            {
                sql = sql 
                        + "AND recieve BETWEEN '" 
                        + partDto.getRecieveAfter().toString("YYYY-MM-dd")
                        + "' AND '"
                        + partDto.getRecieveBefore().toString("YYYY-MM-dd")
                        + "' ";
            }
            
            LOG.debug(sql);
            
            Statement statement = conn.createStatement();
            ResultSet resultSet = statement.executeQuery(sql);
            while(resultSet.next())
            {
                Part part = new Part();

                part.setPartName(resultSet.getString("partname"));
                part.setPartNumber(resultSet.getString("partnumber"));
                part.setVendor(resultSet.getString("vendor"));
                part.setQty(resultSet.getInt("qty"));
                part.setShipped(new LocalDate(resultSet.getDate("shipped")));
                part.setRecieve(new LocalDate(resultSet.getDate("recieve")));

                result.add(part);
            }
        }
        catch (ClassNotFoundException e)
        {
            LOG.error("Error loading database driver: " + e.getLocalizedMessage());
        }
        catch (SQLException e)
        {
            LOG.error("Database error while retrieving all parts list: " + e.getLocalizedMessage());
        }

        return result;
    }
}
