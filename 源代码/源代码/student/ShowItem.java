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
    	super("��ʾ����");
    	this.department = department;
    	container =this.getContentPane();
    	setSize(1000,700);
    	this.setLocation(100, 100);
    	setLayout(null);

        menu();//�����˵�

        this.setVisible(true);
    }
    public void Close() throws Throwable{
    	this.finalize();
    }
    public void actionPerformed(ActionEvent e){

    	String s = new String(e.getActionCommand());
    	if(s.equals("�������")){
    		new TypeChoice(this,this.container,department);
    	}
    }
    private void menu(){
    	JMenuBar menubar = new JMenuBar();
    	JMenu type = new JMenu("��ʼ����");
    	JMenu help = new JMenu("����");

    	JMenuItem typechoice = new JMenuItem("�������");
    	typechoice.addActionListener(this);
    	type.add(typechoice);

    	JMenuItem itemhelp = new JMenuItem("����");
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
        new ShowItem("�������ѧ�뼼��");
        }*/
}

