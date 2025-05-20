package com.supportjobsearch.Location;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class LocationData {
//    String pathFile = "../webapp/public/json/vietnam.json";
    public static void main(String[] args) {
        String content = readFile("E:\\Study\\LTW\\Ecommerce_Final\\src\\main\\webapp\\public\\json\\vietnam.json");

        JSONObject jsonObject = new JSONObject(content);
        JSONArray provinces = jsonObject.getJSONArray("province");
        System.out.println("Provinces:");
        for (int i = 0; i < provinces.length(); i++) {
            JSONObject province = provinces.getJSONObject(i);
            int idProvince = province.getInt("idProvince");
            String name = province.getString("name");
            System.out.println("ID: " + idProvince + ", Name: " + name);
        }

        JSONArray districts = jsonObject.getJSONArray("district");
        System.out.println("\nDistricts:");
        for (int i = 0; i < districts.length(); i++) {
            JSONObject district = districts.getJSONObject(i);
            int idDistrict = district.getInt("idDistrict");
            int idProvince = district.getInt("idProvince");
            String name = district.getString("name");
            System.out.println("ID: " + idDistrict + ", Province ID: " + idProvince + ", Name: " + name);
        }
    }

    private static String readFile(String filePath) {
        try {
            byte[] bytes = Files.readAllBytes(Paths.get(filePath));
            return new String(bytes);
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static String findProvine(String id) {

        String content = readFile("D:\\LTW\\LTW_project\\Ecommerce_Final\\src\\main\\webapp\\public\\json\\vietnam.json"  );
        JSONObject jsonObject = new JSONObject(content);
        JSONArray provinces = jsonObject.getJSONArray("province");

        for (int i = 0; i < provinces.length(); i++) {
            JSONObject province = provinces.getJSONObject(i);
            String provinceId = String.valueOf(province.getInt("idProvince"));
            if (provinceId.equals(id)) {
                return province.getString("name");
            }
        }

        return null;
    }
}