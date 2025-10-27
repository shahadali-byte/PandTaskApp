package controller;

import model.Label;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class LabelController {
    ArrayList<Label> labels = new ArrayList<>();

    public void loadLabelsFromCSV(String filename) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader(filename));
        br.readLine();
        String line;

        while((line = br.readLine()) != null){
            String[] labelItems = line.split(",");

            String name = labelItems[0];
            String color = labelItems[1];

            Label label = new Label(name, color);
            labels.add(label);
        }
        br.close();
    }

    public Label findLabelByName(String name){
        for(Label label: labels){
            if(label.getName().equals(name)){
                return label;
            }
        }
        return null;
    }

    public void addLabel(Label label){
        labels.add(label);
    }

    public void deleteLabel(Label label){
        labels.remove(label);
    }

    public void updateLabel(Label label, String newName, String newColor){
    }

    public ArrayList<Label> getLabels() {
        return labels;
    }
}
