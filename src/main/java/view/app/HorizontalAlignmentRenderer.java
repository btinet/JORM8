package view.app;

import javax.swing.*;
import javax.swing.table.TableCellRenderer;
import java.awt.*;

public class HorizontalAlignmentRenderer implements TableCellRenderer {

    private int horizontalAlignment = SwingConstants.LEFT;

    public HorizontalAlignmentRenderer(int horizontalAlignment) {
        this.horizontalAlignment = horizontalAlignment;
    }

    @Override
    public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
        TableCellRenderer r = table.getTableHeader().getDefaultRenderer();
        JLabel l = (JLabel) r.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
        l.setHorizontalAlignment(horizontalAlignment);
        return l;
    }

}
