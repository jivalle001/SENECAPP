package tta.intel.eus.senecapp;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by jose on 8/01/17.
 */
public class ExpandableListDataPump {
    public static HashMap<String, List<String>> getData() {
        HashMap<String, List<String>> expandableListDetail = new HashMap<String, List<String>>();

        List<String> ocio = new ArrayList<String>();
        ocio.add("Bizkaia");
        ocio.add("Araba");
        ocio.add("Gipuzkoa");

        List<String> gastronomia = new ArrayList<String>();
        gastronomia.add("Bizkaia");
        gastronomia.add("Araba");
        gastronomia.add("Gipuzkoa");

        List<String> deporte = new ArrayList<String>();
        deporte.add("Bizkaia");
        deporte.add("Araba");
        deporte.add("Gipuzkoa");

        expandableListDetail.put("OCIO",ocio);
        expandableListDetail.put("GASTRONOMÍA",gastronomia);
        expandableListDetail.put("DEPORTE",deporte);

        return expandableListDetail;
    }
}
