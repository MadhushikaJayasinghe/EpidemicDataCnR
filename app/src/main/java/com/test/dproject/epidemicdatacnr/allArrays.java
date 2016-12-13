package com.test.dproject.epidemicdatacnr;

/**
 * Created by Subhashinie on 12/13/2016.
 */
public class AllArrays {

    public String[] getProvinceArray() {
        String[] prArray = {"All island", "Central", "Eastern", "North Central",
                "Northern", "North Western", "Sabaragamuwa", "Southern", "Uva", "Western"};
        return prArray;
    }

    public String[] getInsertProvinceArray() {
        String[] prArray = {"Not selected", "Central", "Eastern", "North Central",
                "Northern", "North Western", "Sabaragamuwa", "Southern", "Uva", "Western"};
        return prArray;
    }

    public String[] getDistrictArray(String prov) {
        String[] districts2;
        if (prov.equals("Central")) {
            districts2 = new String[]{"Kandy", "Matale", "Nuwara Eliya"};
        } else if (prov.equals("Eastern")) {
            districts2 = new String[]{"Ampara", "Batticaloa", "Trincomalee"};
        } else if (prov.equals("North Central")) {
            districts2 = new String[]{"Anuradhapura", "Polonnaruwa"};
        } else if (prov.equals("Northern")) {
            districts2 = new String[]{"Jaffna", "Kilinochchi", "Mannar", "Mullaitivu", "Vavuniya"};
        } else if (prov.equals("North Western")) {
            districts2 = new String[]{"Kurunegala", "Puttalam"};
        } else if (prov.equals("Sabaragamuwa")) {
            districts2 = new String[]{"Kegalle", "Ratnapura"};
        } else if (prov.equals("Southern")) {
            districts2 = new String[]{"Galle", "Hambantota", "Matara"};
        } else if (prov.equals("Uva")) {
            districts2 = new String[]{"Badulla", "Monaragala"};
        } else if (prov.equals("Western")) {
            districts2 = new String[]{"Colombo", "Gampaha", "Kalutara"};
        } else {
            districts2 = new String[]{"Province not specified"};
        }
        return districts2;
    }
}
