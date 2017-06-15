package bddtests.utils;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;


public class ScenarioHelper {

    public String convertXMLToString(String filePath) throws IOException {

        String line;
        BufferedReader br = new BufferedReader(new FileReader(new File(filePath)));
        StringBuilder sb = new StringBuilder();

        while((line=br.readLine())!= null){
            sb.append(line.trim());
        }
        return sb.toString();
    }

}
