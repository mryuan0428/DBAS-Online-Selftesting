package teacher;

import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

import student.TypeChoice;

@SuppressWarnings("serial")
public class Interface extends JFrame implements ActionListener{
	Container container;
	String subject;//�����������ݿ�
	String department;

    public Interface (String  subject,String department){
    	super("��ʦ");
    	setSize(1000,700);
    	this.setLocation(10,40);
    	setLayout(null);
    	container =this.getContentPane();
    	container.setLayout(null);

    	this.subject = subject;
    	this.department = department;
        menu();//�����˵�

        this.setVisible(true);
        //this.setResizable(false);
    }
public void actionPerformed(ActionEvent e){
    	String s = new String(e.getActionCommand());
    	if(s.equals("�������")){
    		new TestManage(this,this.container,subject);
    	}
    	else if(s.equals("�������")){
    		new TypeChoice(this,this.container,department);
    	}
    }
    private void menu(){
    	JMenuBar menubar = new JMenuBar();
    	JMenu testmanage = new JMenu("�������");
    	JMenu oltsmanage = new JMenu("�������");
    	
    	JMenuItem test = new JMenuItem("�������");
    	JMenuItem olts = new JMenuItem("�������");
    	test.addActionListener(this);
    	olts.addActionListener(this);
    	testmanage.add(test);
    	oltsmanage.add(olts);
    	menubar.add(testmanage);
    	menubar.add(oltsmanage);
    	oltsmanage.addActionListener(this);
    	testmanage.addActionListener(this);
    	this.setJMenuBar( menubar);
    }

   /* public static void main(String args[]){
        JDialog.setDefaultLookAndFeelDecorated(true);
        @SuppressWarnings("unused")
 		Font font=new Font("JFrame",Font.PLAIN,14);
        new Interface("subject002");
        }*/
}
