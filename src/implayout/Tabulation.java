package implayout;

import java.awt.Color;
import java.awt.Component;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;
 
// class tabulates result in GUI frame neural network trained output and correlation coefficient matrix 
public class Tabulation {
    // frame
    JFrame f;
    // Table
    JTable j;
 
    // Constructor


    
    public Tabulation(String[][] data, String[] columnNames, int size, String [] res) {
    	  // Frame initialization
        f = new JFrame();
 
        // Frame Title
        f.setTitle("Corelation Cofficient");
        JPanel p = new JPanel();

        p.add(new JLabel("(Input)  (Actual Output) (Result)",SwingConstants.LEFT));
        for(int i=0;i<res.length;i++) {
        	 JLabel l = new JLabel("<html>"+res[i]+"<br></html>",SwingConstants.CENTER); 
        	 //l.setText(res[i]+);
        	 p.add(l);
        	 
        	
        }
      /*  JLabel l = new JLabel(); 
        l.setText("<html>Praise the Lord Jesus Christ<br/>Enoch</html>");
       
        JLabel l1 = new JLabel(); 
        l1.setText("<html>Testing Testing Testing Testing Testing Testing Testing Testing Testing Testing Testing Testing Testing Testing Testing Testing Testing Testing</html> ");*/
        p.setSize(300, 300);
       
       
        // Initializing the JTable
        j = new JTable(data, columnNames);
        
        j.setBounds(30, 40, 200, 400);
 
        // adding it to JScrollPane
        JScrollPane sp = new JScrollPane(j);
        sp.setAutoscrolls(false);
        JPanel p1 = new JPanel();
        p1.add(sp);
        f.add(p);
        f.add(p1);
      
        
        // Frame Size
        f.setSize(1600, size);
        
        // Frame Visible = true
        f.setVisible(true);
        for(int i=0;i<4;i++)
        	changeTable(j, i);
        
	}

	public void changeTable(JTable table, final int column_index) {
  
    	table.getColumnModel().getColumn(column_index).setCellRenderer(new DefaultTableCellRenderer() {
    		@Override
    		public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
    			final Component c = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
    			if(row<50) {
    				if(row%3==0) {

    					c.setBackground(Color.LIGHT_GRAY);
    					c.setForeground(Color.black);
    				}
    				else if(row%3==1)  {

    					try {

    					String dubValue = table.getValueAt(row, column).toString();
    					
    					if(!dubValue.isEmpty()) {

    						double val =  Double.valueOf(dubValue);
    						c.setForeground(Color.black);
    						if(Math.abs(val)>0.75) 
    							c.setBackground(Color.GREEN);
    						else
    							c.setBackground(Color.pink);

    					}
    					else
    						c.setBackground(Color.white);
    					}
    					catch(NullPointerException e) {
    						
    						
    					}
    					
    					//c.setf
    				}
    				else if(row%3==2)  {

    					c.setBackground(Color.white);
    					c.setForeground(Color.white);
    				}

    			}


    			return c;
    		}
    	});
    }

    // Driver  method
    public static void main(String[] args)
    {
        new Tabulation(null, args, 0, args);
    }
}