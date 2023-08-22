package org.example.Layout;

import lombok.Data;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Data
public class CalculatriceLayout {

    private GridBagLayout gridBagLayout;
    private JPanel jPanel;
    private JButton jButton;
    private String entry = "";
    private String labelText = "";

    private Double firstValue;
    private Double secondValue;
    private String methode;

    public CalculatriceLayout() {
        jPanel = new JPanel();
        gridBagLayout = new GridBagLayout();
        jPanel.setLayout(gridBagLayout);
        GridBagConstraints c = new GridBagConstraints();

        gridBagLayout.columnWidths = new int[4];
        gridBagLayout.rowHeights = new int[7];

        List<String> list = Arrays.asList("C", "²", "%", "/", "7", "8", "9", "*", "4", "5", "6", "-", "1", "2", "3", "+", "0", ".", "=");

        Label label = new Label();
        label.setFont(new Font("name", 1, 25));
        label.setText(entry);
        label.setBackground(Color.BLACK);
        label.setForeground(Color.white);
        c.fill = GridBagConstraints.BOTH;
        c.gridwidth = 4;
        c.gridheight = 2;
        c.gridy = 0;
        c.gridx = 0;
        c.weightx = 1;
        c.weighty = 1;
        jPanel.add(label, c);

        int cpt = 0;

        for (int i = 2; i < 7; i++) {
            for (int j = 0; j < 4; j++) {
                jButton = new JButton(list.get(cpt));
                jButton.setFont(new Font("name", 1, 25));
                cpt++;

                c.gridwidth = 1;
                c.gridheight = 1;
                c.gridy = i;
                c.gridx = j;
                if (j == 3) {
                    jButton.setBackground(Color.ORANGE);
                    jButton.setForeground(Color.white);
                } else {
                    jButton.setBackground(Color.GRAY);
                    jButton.setForeground(Color.BLACK);
                }
                if (i == 6 && j == 0) {
                    c.gridwidth = 2;
                    cpt--;
                }

                jButton.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        entry = ((JButton) (e.getSource())).getText();
                        switch (entry) {
                            case "C":
                                labelText = "";
                                break;
                            case "/":
                            case "*":
                            case "+":
                            case "-":
                            case "%":
                                methode = entry;
                                firstValue = Double.parseDouble(labelText);
                                labelText ="";
                                break;
                            case "²":
                                methode ="";
                                firstValue = Double.parseDouble(labelText);
                                labelText = ""+(firstValue * firstValue);
                                firstValue =0D;
                                secondValue =0D;

                                break;
                            case "=":
                                secondValue = Double.parseDouble(labelText);
                                labelText = calcul(methode,firstValue,secondValue).toString();
                                firstValue =0D;
                                secondValue =0D;
                                methode ="";
                                break;
                            default:
                                labelText += entry;
                                break;
                        }
                        label.setText(labelText);
                    }
                });
                jPanel.add(jButton, c);

            }
        }
    }
    private Double calcul (String methode, Double firstValue, Double secondValue){
        return switch (methode) {
            case "+" -> firstValue + secondValue;
            case "-" -> firstValue - secondValue;
            case "/" -> firstValue / secondValue;
            case "*" -> firstValue * secondValue;
            case "%" -> firstValue % secondValue;
            default -> null;
        };
    }

}
