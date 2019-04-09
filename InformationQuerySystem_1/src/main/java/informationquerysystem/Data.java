package informationquerysystem;

import java.util.HashMap;
import java.util.Map;

public class Data
{
    private Map<String, String> contactDetails = new HashMap<>();
    private Map<String, String> enrolledUnits = new HashMap<>();
    private Map<String, String> unitCode = new HashMap<>();

    public Data()
    {
        contactDetails.put("S1000001","S1000001 Nirav Patel Melbourn Australia niravpatel@cqu.edu.au");
        contactDetails.put("S1000002","Contact2");
        contactDetails.put("S1000003","Contact3");

        enrolledUnits.put("S1000001","COIT20262 COIT20257 COIT20259");
        enrolledUnits.put("S1000002","COIT20262 COIT20257");
        enrolledUnits.put("S1000003","COIT20257 COIT20259");

        unitCode.put("Unit1", "Detail1");
        unitCode.put("Unit2", "Detail2");
        unitCode.put("Unit3", "Detail3");
        unitCode.put("Unit4", "Detail4");
        unitCode.put("COIT20262", "COIT20262 Details of Subject: Level term1, term 2");
        unitCode.put("COIT20257", "COIT20257 Distributed Systems: Principles and Development Level 3 Term1, Term2, Term3");
        unitCode.put("COIT20259", "COIT20259 Details of subject: Level Terms");
    }

    public Map<String, String> getContactDetails()
    {
        return contactDetails;
    }

    public Map<String, String> getEnrolledUnits()
    {
        return enrolledUnits;
    }

    public Map<String, String> getUnitCode()
    {
        return unitCode;
    }
}
