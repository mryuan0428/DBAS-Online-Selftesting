package teacher;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import com.ConnectData;

public class BlankItem {
	Interface frame;
	JPanel dopanel;
	JScrollPane items;
	JTable item;//显示成绩
	DefaultTableModel dm;//表格类型
	ConnectData  blankitem;
	ResultSet itemsrs;//用来接收试题数据库
	ResultSet rs;
	int id;//所属科目
	int mindoradd;//标识修改或添加操作，0为修改1为添加

	JScrollPane itemmeaning;
	JPanel itemmean;
	JPanel itemnumber;
	JPanel itemright;
	JPanel itemlevel;
	JPanel buttonpanel;

	JLabel itemnumberl;//"题号"标签
	JLabel itemmeaningl;//"内容"标签
	JLabel itemrightl;//"正确答案"标签
	JLabel itemlevell;//"题目难度"标签

	JTextField itemnumbert;//"题号"文本框
	JTextArea itemmeaningt;//"内容"文本框
	JComboBox itemlevelt;//"题目难度"选择框
	JTextField itemrightt;

	JButton search;//"查询"按钮
	JButton mind;//"修改"按钮
	JButton cancel;//"删除"按钮
	JButton adding;//"增加"按钮
	JButton save;//"保存"按钮
	JButton exit;//"取消"按钮

	public BlankItem(Interface frame,JPanel dopanel, int id){
		this.frame = frame;
	    this.dopanel = dopanel;
	    this.id = id;
	    dopanel.setLayout(null);
	    panel();

	    showitem(id);
	}
    public void Close() throws Throwable{
    	this.finalize();
    }
	private void panel(){
		items = new JScrollPane();
		items.setBounds(0, 0, 600, 300);
		String[] cols = {"题号","题目内容","正确答案","题目难度"};//列名
		dm = new DefaultTableModel(cols,0);
		item = new JTable(dm);
		items.setViewportView(item);//将表格添加到滚动面板
		items.setHorizontalScrollBarPolicy(32);//设置水平滚动条一直显示
		items.setVerticalScrollBarPolicy(22);//设置垂直滚动条一直显示
		dopanel.add(items);

		itemnumberl = new JLabel("题号");//"题号"标签
		itemmeaningl = new JLabel("内容");//"内容"标签

		itemrightl = new JLabel("正确答案");//"正确答案"标签
		itemlevell = new JLabel("题目难度");//"题目难度"标签

		itemnumbert = new JTextField(10);//"题号"文本框
		itemnumbert.setEditable(true);
		itemmeaningt = new JTextArea();//"内容"文本框
		itemmeaningt.setColumns(40);
		itemmeaningt.setRows(3);
		itemmeaningt.setLineWrap(true);
		itemmeaningt.setEditable(false);
		itemrightt = new JTextField(30);
		itemrightt.setEditable(false);
		itemlevelt = new JComboBox();//"题目难度"文本框
		itemlevelt.addItem("简单");
		itemlevelt.addItem("普通");
		itemlevelt.addItem("困难");
		itemlevelt.setSelectedItem(null);
		itemlevelt.setEditable(false);

		itemnumber = new JPanel();
		itemnumber.setBounds(0, 300, 150, 30);		
		itemmean = new JPanel();
		itemmean.setBounds(0, 330, 100, 25);
		itemmeaning = new JScrollPane();
		itemmeaning.setViewportView(itemmeaningt);
		itemmeaning.setBounds(15, 360, itemmeaningt.getPreferredSize().width+5, itemmeaningt.getPreferredSize().height+5);
		itemright = new JPanel();
		itemright.setBounds(0, 450, 400, 30);
		itemlevel = new JPanel();
		itemlevel.setBounds(300,300,150, 33);
		buttonpanel = new JPanel();
		buttonpanel.setBounds(50,550,400, 100);

		itemnumber.add(itemnumberl);
		itemnumber.add(itemnumbert);;
		itemmean.add(itemmeaningl);
		itemright.add(itemrightl);
		itemright.add(itemrightt);
		itemlevel.add(itemlevell);
		itemlevel.add(itemlevelt);

		search = new JButton("查询");//"查询"按钮
		mind = new JButton("修改");//"修改"按钮
		cancel = new JButton("删除");//"删除"按钮
		adding = new JButton("增加");//"增加"按钮
		save = new JButton("保存");//"保存"按钮
		exit = new JButton("取消");//"取消"按钮
		exit.setEnabled(false);
		save.setEnabled(false);
		buttonpanel.add(search);
		search.addActionListener(new search_actionAdapter(this));
		buttonpanel.add(mind);
		mind.addActionListener(new mind_actionAdapter(this));
		buttonpanel.add(cancel);
		cancel.addActionListener(new cancel_actionAdapter(this));
		buttonpanel.add(adding);
		adding.addActionListener(new adding_actionAdapter(this));
		buttonpanel.add(save);
		save.addActionListener(new save_actionAdapter(this));
		buttonpanel.add(exit);
		exit.addActionListener(new exit_actionAdapter(this));

		dopanel.add(itemnumber);
		dopanel.add(itemmean);
		dopanel.add(itemmeaning);
		dopanel.add(itemright);
		dopanel.add(itemlevel);
		dopanel.add(buttonpanel);

		frame.setVisible(true);
	}
	class search_actionAdapter implements ActionListener{
		 BlankItem adaptee;
		 search_actionAdapter(BlankItem adaptee){
			 this.adaptee = adaptee;
	    }
	    public void actionPerformed(ActionEvent e){
	    	adaptee.search_actionPerformed(e);
	    }
	}
	 public void search_actionPerformed(ActionEvent e){
		 String number = itemnumbert.getText().trim();
		 if(number.equals("")){
			 //题号为空
			 JOptionPane.showMessageDialog(null,"请输入要查询的试题题号！");
			 return;
		 }
		 else{//题号不为空
			 int i = 0;
			 rs = blankitem.search("BlankFill",id, "题目编号", number);
			 try{
				while(rs.next()){
					i ++;
					itemmeaningt.setText(rs.getString(2));
					itemrightt.setText(rs.getString(3));
					itemlevelt.setSelectedItem(rs.getString(4));
			    }
			 }catch(SQLException ee){
		    		ee.printStackTrace();
		    	}
			 if(i==0){
				 JOptionPane.showMessageDialog(null,"不存在该记录！");
				 return;
			 }
		 }
	 }
	 class mind_actionAdapter implements ActionListener{
		 BlankItem adaptee;
		 mind_actionAdapter(BlankItem adaptee){
			 this.adaptee = adaptee;
	    }
	    public void actionPerformed(ActionEvent e){
	    	adaptee.mind_actionPerformed(e);
	    }
	}
	 public void mind_actionPerformed(ActionEvent e){
		 mindoradd = 0;
		 String number = itemnumbert.getText().trim();
		 if(number.equals("")){
			 //题号为空
			 JOptionPane.showMessageDialog(null,"请输入要删除的试题题号！");
			 return;
		 }
		 else{//题号不为空
			 int i = 0;
			 rs = blankitem.search("BlankFill",id, "题目编号", number);
			 try{
				while(rs.next()){
					i ++;
					itemmeaningt.setText(rs.getString(2));
					itemrightt.setText(rs.getString(3));
					itemlevelt.setSelectedItem(rs.getString(4));
			    }
			 }catch(SQLException ee){
		    		ee.printStackTrace();
		    	}
			 if(i==0){
				  JOptionPane.showMessageDialog(null,"不存在该记录！");
				 return;
			 }else{
				 itemmeaningt.setEditable(true);
			     itemrightt.setEditable(true);
			     itemlevelt.setEditable(true);
			     save.setEnabled(true);
			     exit.setEnabled(true);
			 }
		 }
	 }
	 class cancel_actionAdapter implements ActionListener{
		 BlankItem adaptee;
		 cancel_actionAdapter(BlankItem adaptee){
			 this.adaptee = adaptee;
	    }
	    public void actionPerformed(ActionEvent e){
	    	adaptee.cancel_actionPerformed(e);
	    }
	}
	 public void cancel_actionPerformed(ActionEvent e){
		 String number = itemnumbert.getText().trim();
		 if(number.equals("")){
			 //题号为空
			 JOptionPane.showMessageDialog(null,"请输入要删除的试题题号！");
			 return;
		 }
		 else{//题号不为空
			 int i = 0;
			 rs = blankitem.search("BlankFill",id, "题目编号", number);
			 try{
				while(rs.next()){
					i ++;
					itemmeaningt.setText(rs.getString(2));
					itemrightt.setText(rs.getString(3));
					itemlevelt.setSelectedItem(rs.getString(4));
			    }
			 }catch(SQLException ee){
		    		ee.printStackTrace();
		    	}
			 if(i==0){
				 JOptionPane.showMessageDialog(null,"不存在该记录！");
				 return;
			 }
			 blankitem.cancel("BlankFill", "题目编号", number);
			 blankitem.Close();
			 clearRows();
			 showitem(id);
			 itemnumbert.setText("");
			 itemmeaningt.setText("");
			 itemrightt.setText("");
			 itemlevelt.setSelectedItem(null);
			 itemmeaningt.setEditable(false);
			 itemrightt.setEditable(false);
			 itemlevelt.setEditable(false);
		 }
	 }
	 class adding_actionAdapter implements ActionListener{
		 BlankItem adaptee;
		 adding_actionAdapter(BlankItem adaptee){
			 this.adaptee = adaptee;
	    }
	    public void actionPerformed(ActionEvent e){
	    	adaptee.adding_actionPerformed(e);
	    }
	}
	 public void adding_actionPerformed(ActionEvent e){
		 mindoradd = 1;
	     itemnumbert.setText(getID());
		 itemmeaningt.setText("");
		 itemrightt.setText("");
		 itemlevelt.setSelectedItem(null);
		 itemnumbert.setEditable(false);//自动编号
		 itemmeaningt.setEditable(true);
		 itemrightt.setEditable(true);
		 itemlevelt.setEditable(true);
		 save.setEnabled(true);
		 exit.setEnabled(true);
	 }
	 class exit_actionAdapter implements ActionListener{
		 BlankItem adaptee;
		 exit_actionAdapter(BlankItem adaptee){
			 this.adaptee = adaptee;
	    }
	    public void actionPerformed(ActionEvent e){
	    	adaptee.exit_actionPerformed(e);
	    }
	 }
	 public void exit_actionPerformed(ActionEvent e){
		 itemnumbert.setText("");
		 itemmeaningt.setText("");
		 itemrightt.setText("");
		 itemlevelt.setSelectedItem(null);
		 itemnumbert.setEditable(true);//自动编号
		 itemmeaningt.setEditable(false);
		 itemrightt.setEditable(false);
		 itemlevelt.setEditable(false);
		 save.setEnabled(false);
		 exit.setEnabled(false);
	 }
	 class save_actionAdapter implements ActionListener{
		 BlankItem adaptee;
		 save_actionAdapter(BlankItem adaptee){
			 this.adaptee = adaptee;
	    }
	    public void actionPerformed(ActionEvent e){
	    	adaptee.save_actionPerformed(e);
	    }
	}
	 public void save_actionPerformed(ActionEvent e){
		 String number = itemnumbert.getText().trim();
		 if(mindoradd == 0){
			 //修改后保存
			 String setstr = "题目内容 = '" + itemmeaningt.getText().trim() + 
			                 "',答案 = '" + itemrightt.getText().trim() +
			                 "',题目难度='" + itemlevelt.getSelectedItem().toString()+"'";
			 blankitem.mind("BlankFill",id, setstr,"题目编号", number);
		 }else{
			 //添加后保存
			 if(itemmeaningt.getText().trim().equals("")||
					 itemrightt.getText().trim().equals("")||
					 itemlevelt.getSelectedItem()==null){
				 JOptionPane.showMessageDialog(null,"信息不完整！请填写全部信息！");
				 return;
			 }else{
				 String addstr = " (题目编号,题目内容,答案,题目难度,所属科目)  values ('" +
				                 itemnumbert.getText().trim()+"','"+
				                 itemmeaningt.getText().trim()+"','"+
				                 itemrightt.getText().trim()+"','"+
				                 itemlevelt.getSelectedItem().toString()+"',"+id +")";
				 blankitem.adding("BlankFill", addstr);
			 }
		 }

		 blankitem.Close();
		 clearRows();
		 showitem(id);
		 
		 itemnumbert.setText("");
		 itemmeaningt.setText("");
		 itemrightt.setText("");
		 itemlevelt.setSelectedItem(null);
		

		 itemnumbert.setEditable(true);
		 itemmeaningt.setEditable(false);
		 itemrightt.setEditable(false);
		 itemlevelt.setEditable(false);
		 save.setEnabled(false);
		 exit.setEnabled(false);
	 }
	 private void clearRows(){
		 int n = dm.getRowCount();
		 for(int i = n-1;i>=0 ;i--){
			 dm.removeRow(i);
		 }
	 }
	 private String  getID(){
		 int n = 0;
		 blankitem = new ConnectData("BlankFill");
		 itemsrs = blankitem.getResultSet();
			try{
				itemsrs.first();
				while(itemsrs.next()){
					int nn = Integer.parseInt(itemsrs.getString(1));
					if(nn > n){
						n=nn;
					}
			   }
			 }catch(SQLException es){
		            System.out.println(es);
		        }
			 n = n + 1;
			 return String.valueOf(n);
	 }

	 private void showitem(int id){
		 blankitem = new ConnectData("BlankFill");
		itemsrs = blankitem.getResultSet();
		try{
			itemsrs.first();
			while(itemsrs.next()){
				int showid = itemsrs.getInt("所属科目");
				if(id == showid){
					//找到当前课程的试题
					String[] oneitem={"","","","","","","",""};
	        		oneitem[0] = itemsrs.getString(1);
	        		oneitem[1] = itemsrs.getString(2);
	        		oneitem[2] = itemsrs.getString(3);
	        		oneitem[3] = itemsrs.getString(4);
	        		dm.addRow(oneitem);
				}
		   }
		 }catch(SQLException es){
	            System.out.println(es);
	        }
		 }
	 }
