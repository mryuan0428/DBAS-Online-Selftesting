package student;

import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;
@SuppressWarnings("serial")
public class ShowItem extends JFrame implements ActionListener{
	Container container;
	String department;

    public ShowItem (String department){
    	super("显示试题");
    	this.department = department;
    	container =this.getContentPane();
    	setSize(1000,700);
    	this.setLocation(100, 100);
    	setLayout(null);

        menu();//建立菜单

        this.setVisible(true);
    }
    public void Close() throws Throwable{
    	this.finalize();
    }
    public void actionPerformed(ActionEvent e){

    	String s = new String(e.getActionCommand());
    	if(s.equals("随机测试")){
    		new TypeChoice(this,this.container,department);
    	}
    }
    private void menu(){
    	JMenuBar menubar = new JMenuBar();
    	JMenu type = new JMenu("开始测试");
    	JMenu help = new JMenu("帮助");

    	JMenuItem typechoice = new JMenuItem("随机测试");
    	typechoice.addActionListener(this);
    	type.add(typechoice);

    	JMenuItem itemhelp = new JMenuItem("帮助");
    	itemhelp.addActionListener(this);
    	help.add(itemhelp);

    	menubar.add(type);
    	menubar.add(help);

    	this.setJMenuBar( menubar);
    }

    /*public static void main(String args[]){
        JDialog.setDefaultLookAndFeelDecorated(true);
        @SuppressWarnings("unused")
 		Font font=new Font("JFrame",Font.PLAIN,14);
        new ShowItem("计算机科学与技术");
        }*/
}

