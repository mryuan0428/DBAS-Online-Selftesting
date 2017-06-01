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
	JTable item;//��ʾ�ɼ�
	DefaultTableModel dm;//�������
	ConnectData  blankitem;
	ResultSet itemsrs;//���������������ݿ�
	ResultSet rs;
	int id;//������Ŀ
	int mindoradd;//��ʶ�޸Ļ���Ӳ�����0Ϊ�޸�1Ϊ���

	JScrollPane itemmeaning;
	JPanel itemmean;
	JPanel itemnumber;
	JPanel itemright;
	JPanel itemlevel;
	JPanel buttonpanel;

	JLabel itemnumberl;//"���"��ǩ
	JLabel itemmeaningl;//"����"��ǩ
	JLabel itemrightl;//"��ȷ��"��ǩ
	JLabel itemlevell;//"��Ŀ�Ѷ�"��ǩ

	JTextField itemnumbert;//"���"�ı���
	JTextArea itemmeaningt;//"����"�ı���
	JComboBox itemlevelt;//"��Ŀ�Ѷ�"ѡ���
	JTextField itemrightt;

	JButton search;//"��ѯ"��ť
	JButton mind;//"�޸�"��ť
	JButton cancel;//"ɾ��"��ť
	JButton adding;//"����"��ť
	JButton save;//"����"��ť
	JButton exit;//"ȡ��"��ť

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
		String[] cols = {"���","��Ŀ����","��ȷ��","��Ŀ�Ѷ�"};//����
		dm = new DefaultTableModel(cols,0);
		item = new JTable(dm);
		items.setViewportView(item);//�������ӵ��������
		items.setHorizontalScrollBarPolicy(32);//����ˮƽ������һֱ��ʾ
		items.setVerticalScrollBarPolicy(22);//���ô�ֱ������һֱ��ʾ
		dopanel.add(items);

		itemnumberl = new JLabel("���");//"���"��ǩ
		itemmeaningl = new JLabel("����");//"����"��ǩ

		itemrightl = new JLabel("��ȷ��");//"��ȷ��"��ǩ
		itemlevell = new JLabel("��Ŀ�Ѷ�");//"��Ŀ�Ѷ�"��ǩ

		itemnumbert = new JTextField(10);//"���"�ı���
		itemnumbert.setEditable(true);
		itemmeaningt = new JTextArea();//"����"�ı���
		itemmeaningt.setColumns(40);
		itemmeaningt.setRows(3);
		itemmeaningt.setLineWrap(true);
		itemmeaningt.setEditable(false);
		itemrightt = new JTextField(30);
		itemrightt.setEditable(false);
		itemlevelt = new JComboBox();//"��Ŀ�Ѷ�"�ı���
		itemlevelt.addItem("��");
		itemlevelt.addItem("��ͨ");
		itemlevelt.addItem("����");
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

		search = new JButton("��ѯ");//"��ѯ"��ť
		mind = new JButton("�޸�");//"�޸�"��ť
		cancel = new JButton("ɾ��");//"ɾ��"��ť
		adding = new JButton("����");//"����"��ť
		save = new JButton("����");//"����"��ť
		exit = new JButton("ȡ��");//"ȡ��"��ť
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
			 //���Ϊ��
			 JOptionPane.showMessageDialog(null,"������Ҫ��ѯ��������ţ�");
			 return;
		 }
		 else{//��Ų�Ϊ��
			 int i = 0;
			 rs = blankitem.search("BlankFill",id, "��Ŀ���", number);
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
				 JOptionPane.showMessageDialog(null,"�����ڸü�¼��");
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
			 //���Ϊ��
			 JOptionPane.showMessageDialog(null,"������Ҫɾ����������ţ�");
			 return;
		 }
		 else{//��Ų�Ϊ��
			 int i = 0;
			 rs = blankitem.search("BlankFill",id, "��Ŀ���", number);
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
				  JOptionPane.showMessageDialog(null,"�����ڸü�¼��");
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
			 //���Ϊ��
			 JOptionPane.showMessageDialog(null,"������Ҫɾ����������ţ�");
			 return;
		 }
		 else{//��Ų�Ϊ��
			 int i = 0;
			 rs = blankitem.search("BlankFill",id, "��Ŀ���", number);
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
				 JOptionPane.showMessageDialog(null,"�����ڸü�¼��");
				 return;
			 }
			 blankitem.cancel("BlankFill", "��Ŀ���", number);
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
		 itemnumbert.setEditable(false);//�Զ����
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
		 itemnumbert.setEditable(true);//�Զ����
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
			 //�޸ĺ󱣴�
			 String setstr = "��Ŀ���� = '" + itemmeaningt.getText().trim() + 
			                 "',�� = '" + itemrightt.getText().trim() +
			                 "',��Ŀ�Ѷ�='" + itemlevelt.getSelectedItem().toString()+"'";
			 blankitem.mind("BlankFill",id, setstr,"��Ŀ���", number);
		 }else{
			 //��Ӻ󱣴�
			 if(itemmeaningt.getText().trim().equals("")||
					 itemrightt.getText().trim().equals("")||
					 itemlevelt.getSelectedItem()==null){
				 JOptionPane.showMessageDialog(null,"��Ϣ������������дȫ����Ϣ��");
				 return;
			 }else{
				 String addstr = " (��Ŀ���,��Ŀ����,��,��Ŀ�Ѷ�,������Ŀ)  values ('" +
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
				int showid = itemsrs.getInt("������Ŀ");
				if(id == showid){
					//�ҵ���ǰ�γ̵�����
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
