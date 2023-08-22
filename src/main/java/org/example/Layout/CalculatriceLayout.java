package org.example.Layout;

import lombok.Data;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Arrays;
import java.util.List;

@Data
public class CalculatriceLayout {

    private GridBagLayout gridBagLayout;
    private JPanel jPanel;
    private JButton jButton;
    private String entry = "";
    private String labelText = "";
    private String labelTextHaut = "";

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

        Label ecrandHaut = new Label("test");
        ecrandHaut.setFont(new Font("name", 1, 25));
        ecrandHaut.setText(entry);
        ecrandHaut.setBackground(Color.BLACK);
        ecrandHaut.setForeground(Color.white);

        Label ecrandBas = new Label("test");
        ecrandBas.setFont(new Font("name", 1, 35));
        ecrandBas.setText(entry);
        ecrandBas.setBackground(Color.BLACK);
        ecrandBas.setForeground(Color.white);

        c.fill = GridBagConstraints.BOTH;
        c.gridwidth = 4;
        c.gridheight = 1;
        c.weightx = 1;
        c.weighty = 1;

        c.gridy = 0;
        c.gridx = 0;
        jPanel.add(ecrandHaut, c);
        c.gridy = 1;
        jPanel.add(ecrandBas, c);

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
                                if(labelText == ""){
                                    firstValue = null;
                                    secondValue = null;
                                    methode = "";
                                }
                                labelText = "";
                                break;
                            case "/":
                            case "*":
                            case "+":
                            case "-":
                            case "%":
                                methode = entry;
                                firstValue = Double.parseDouble(labelText);
                                labelText = "";
                                break;
                            case "²":
                                methode = "";
                                firstValue = Double.parseDouble(labelText);
                                labelText = "" + (firstValue * firstValue);
                                firstValue = null;
                                secondValue = null;

                                break;
                            case "=":
                                secondValue = Double.parseDouble(labelText);
                                labelText = calcul(methode, firstValue, secondValue).toString();
                                labelTextHaut = firstValue + " " + methode + " " + secondValue +" =";
                                firstValue = null;
                                secondValue = null;
                                methode = "";

                                break;
                            default:
                                labelText += entry;
                                break;
                        }
                        ecrandBas.setText(labelText);
                        ecrandHaut.setText(labelTextHaut);
                        labelTextHaut = "";
                        if (firstValue != null) {
                            ecrandHaut.setText(firstValue.toString());
                        }
                    }
                });
                jPanel.add(jButton, c);

            }
        }
    }

    private Double calcul(String methode, Double firstValue, Double secondValue) {
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
