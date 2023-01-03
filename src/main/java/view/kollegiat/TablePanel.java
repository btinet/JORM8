package view.kollegiat;

import core.model.Entity;
import core.view.View;
import entity.Kollegiat;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumn;
import java.awt.*;
import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.ArrayList;

public class TablePanel extends JPanel {
    private JList<Object> list;
    private JTable table;
    private View view;

    private GridBagConstraints gbc;

    public TablePanel(View view) {
        this.view = view;
        setLayout(new GridBagLayout());
        this.gbc = new GridBagConstraints();
        this.gbc.ipadx = 10;
        this.gbc.anchor = GridBagConstraints.PAGE_START;
        this.gbc.fill = GridBagConstraints.HORIZONTAL; //Fill the panels horizontally. A weightx is needed for this to work.
        this.gbc.gridx = 0;
        this.gbc.weightx = 1;
        this.gbc.weighty = 0;
        this.list = new JList<>();
        this.table = new JTable();
        this.table.setEnabled(false);
        TableCellRenderer tableRenderer = table.getDefaultRenderer(JButton.class);
        table.setDefaultRenderer(JButton.class, new JTableButtonRenderer(tableRenderer));
    }

    public void setContent (ArrayList<Kollegiat> ar) throws IllegalAccessException {



        DefaultTableModel tableModel = new DefaultTableModel();

        for (Field field : ar.get(1).getClass().getDeclaredFields()){
            if(field.getModifiers() == Modifier.PROTECTED){
                tableModel.addColumn(field.getName());
            }
        }
        this.table.setModel(tableModel);
        JTableHeader header = table.getTableHeader();

        for(Kollegiat a : ar){
            tableModel.addRow(new Object[]{
                    a.getKID(),
                    a.getVorname(),
                    a.getName(),
                    a.getTutorID(),
                    a.getBetreuerID()
            });

        }
        this.table.updateUI();
        this.gbc.gridy = 0;
        add(header, this.gbc);
        this.gbc.gridy = 1;
        add(this.table, this.gbc);

    }
}
