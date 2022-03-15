// Copyright 2000-2022 JetBrains s.r.o. and other contributors. Use of this source code is governed by the Apache 2.0 license that can be found in the LICENSE file.

package org.intellij.sdk.toolWindow;

import com.intellij.openapi.project.Project;
import com.intellij.openapi.wm.ToolWindow;
import com.intellij.openapi.wm.ToolWindowFactory;
import com.intellij.ui.components.JBLabel;
import com.intellij.ui.components.JBPanel;
import com.intellij.ui.content.Content;
import com.intellij.ui.content.ContentFactory;
import com.intellij.ui.table.JBTable;
import org.jetbrains.annotations.NotNull;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import java.awt.*;
import java.util.Random;

public class MyToolWindowFactory implements ToolWindowFactory {

  /**
   * Create the tool window content.
   *
   * @param project    current project
   * @param toolWindow current tool window
   */
  public void createToolWindowContent(@NotNull Project project, @NotNull ToolWindow toolWindow) {

    String data[][]={ {"1","2","3"},
          {"1","2","3"},
          {"1","2","3"}};
    String columnNames[]={"Col1","Col2","Col3"};

    JBTable table = new JBTable();

    table.setDefaultRenderer(String.class, new BoardTableCellRenderer());
    table.setVisible(true);
    table.setBounds(30,40,200,300);

    DefaultTableModel tableModel = new DefaultTableModel();
    for(String columnName : columnNames){
      tableModel.addColumn(columnName);
    }
    tableModel.addRow(new Object[]{"1", "2", "3"});
    tableModel.addRow(new Object[]{"1", "2", "3"});

    table.setModel(tableModel);

    table.getColumn("Col2").setCellRenderer(new BoardTableCellRenderer());
    table.setVisible(true);
    table.setBounds(30,40,200,300);

    ContentFactory contentFactory = ContentFactory.SERVICE.getInstance();
    Content content = contentFactory.createContent(table, "Table Name", false);
    toolWindow.getContentManager().addContent(content);
  }

  public class BoardTableCellRenderer extends DefaultTableCellRenderer {

    private static final long serialVersionUID = 1L;

    @Override
    public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
      Component c = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
      c.setBackground(Color.yellow); // custom background color to proof that we are actrually calling the rendered
      return c;
    }

  }

}
