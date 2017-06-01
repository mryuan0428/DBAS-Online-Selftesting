package admin;

import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;

@SuppressWarnings("serial")
public class Manage extends JFrame implements ActionListener{
		Container container;
		JPanel dopanel;
                JFrame jframe;
		JMenuBar menubar;
    	JMenu manage;
    	JMenu about;
    	JMenuItem studentmanage;
    	JMenuItem teachermanage;
    	JMenuItem subjectmanage;
        StudentManage students;
        TeacherManage teachers;

	    public Manage (){
	    	super("管理员");
	    	setSize(1000,700);
	    	this.setLocation(10,40);
	    	setLayout(null);
	    	container =this.getContentPane();
	    	container.setLayout(null);

	        menu();//建立菜单
                this.setDefaultCloseOperation(EXIT_ON_CLOSE);
	        this.setVisible(true);
	        //this.setResizable(false);
	    }
	public void actionPerformed(ActionEvent e){
	    	String s = new String(e.getActionCommand().toString());
	    	if(s.equals("学生管理")){
	    	   this.dopanel.removeAll();
                   this.invalidate();
                    new StudentManage(this,this.dopanel);
                    
	    	}
                else if(s.equals("教师管理"))
                {
                  
                   this.dopanel.removeAll();
                   this.invalidate();
                   new TeacherManage(this,this.dopanel);
                   

                }
                else
                {
                    this.dopanel.removeAll();
                   this.invalidate();
                   new SubjectManage(this,this.dopanel);
                }
	    }
	private void menu(){
	    	menubar = new JMenuBar();
	    	manage = new JMenu("系统管理");
	    	about = new JMenu("关于");
	    	studentmanage = new JMenuItem("学生管理");
	    	teachermanage = new JMenuItem("教师管理");
	    	subjectmanage = new JMenuItem("课程管理");
	    	studentmanage.addActionListener(this);
	    	teachermanage.addActionListener(this);
	    	subjectmanage.addActionListener(this);
	    	manage.add(studentmanage);
	    	manage.add(teachermanage);
	    	manage.add(subjectmanage);
	    	menubar.add(manage);
	    	menubar.add(about);
	    	this.setJMenuBar( menubar);
                jframe=new JFrame();

	    	dopanel = new JPanel();
	    	dopanel.setBounds(0,0,1000,700);
	        container.add(dopanel);
	    }

	    /*public static void main(String args[]){
	        JDialog.setDefaultLookAndFeelDecorated(true);
	        @SuppressWarnings("unused")
	 		Font font=new Font("JFrame",Font.PLAIN,14);
	        new Manage();
	        }*/
	}

