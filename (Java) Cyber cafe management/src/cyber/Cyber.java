package cyber;

import java.awt.Button;
import java.awt.Color;
import java.awt.Frame;
import java.awt.Label;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;


class lay extends Frame implements ActionListener
{
    Button sta[],stp[],coll[],cal[];
    Label pc[],stime[],sttime[],fare[],ffare[],man,ttime,earn,ttime2,earn2;
    int farepm=1,finfare=0,m;
    
    Connection con;
    Statement stmt = null;  
    ResultSet rs = null;  
    String url = "jdbc:sqlserver://localhost:1433;databaseName=cyber;integratedSecurity=true"; 
    
    lay()
    {
        ttime=new Label(String.valueOf(0));
        earn=new Label(String.valueOf(0));
        ttime2=new Label("TOTAL TIME");
        earn2=new Label("TOTAL EARNING");
       
        try
        {
        Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");  
        con = DriverManager.getConnection(url);  
        
        stmt = con.createStatement();  
        rs = stmt.executeQuery("select * from account");  
        while (rs.next()) 
        {  
            ttime.setText(String.valueOf(rs.getInt(1)));
            earn.setText(String.valueOf(rs.getInt(2)));
        }
        
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
        setLayout(null);
        setSize(1800,900);
        setVisible(true);
        
        sta=new Button[10];
        stp=new Button[10];
        coll=new Button[10];
        cal=new Button[10];
        
        pc=new Label[10];
        stime=new Label[10];
        sttime=new Label[10];
        fare=new Label[10];
        ffare=new Label[10];
        man=new Label("CYBER CAFE MANAGEMENT");
        
        int i=0,x=50,y=50;
        man.setBounds(x, y, 350, 40);
        add(man);
        y=y+50;
        for(i=0;i<10;i++)
        {
            pc[i]=new Label("COMPUTER "+(i+1));
            pc[i].setBackground(Color.black);
            pc[i].setForeground(Color.white);
            
            stime[i]=new Label();
            stime[i].setBackground(Color.black);
            stime[i].setForeground(Color.white);
            
            sttime[i]=new Label();
            sttime[i].setBackground(Color.black);
            sttime[i].setForeground(Color.white);
            
            fare[i]=new Label("FARE ");
            fare[i].setBackground(Color.black);
            fare[i].setForeground(Color.white);
            
            ffare[i]=new Label("");
            ffare[i].setBackground(Color.black);
            ffare[i].setForeground(Color.white);
            
            sta[i]=new Button("START "+(i+1));
            sta[i].addActionListener(this);
            stp[i]=new Button("STOP "+(i+1));
            stp[i].addActionListener(this);
            stp[i].setEnabled(false);
            coll[i]=new Button("COLLECT "+(i+1));
            coll[i].addActionListener(this);
            coll[i].setEnabled(false);
            cal[i]=new Button("CALCULATE "+(i+1));
            cal[i].addActionListener(this);
            cal[i].setEnabled(false);
            if(i==5)
            {
                y=y+236;
                x=50;
            }
                pc[i].setBounds(x, y, 210, 50);
                sta[i].setBounds(x, y+52, 100, 30);
                stp[i].setBounds(x, y+84, 100, 30);
                
                stime[i].setBounds(x+110, y+52, 100, 30);
                sttime[i].setBounds(x+110, y+84, 100, 30);
                
                fare[i].setBounds(x, y+116, 100, 30);
                ffare[i].setBounds(x+110, y+116, 100, 30);
                cal[i].setBounds(x, y+148, 100, 30);
                coll[i].setBounds(x+110, y+148, 100, 30);
                
                
                ttime2.setBounds(50, y+200, 100, 30);
                ttime.setBounds(50+110, y+200, 100, 30);
                earn2.setBounds(50, y+232, 100, 30);
                earn.setBounds(50+110, y+232, 100, 30);
                
                
            x=x+280;
            add(pc[i]);
            add(sta[i]);
            add(stp[i]);
            add(fare[i]);
            add(coll[i]);
            add(stime[i]);
            add(sttime[i]);
            add(ffare[i]);
            add(cal[i]);
            add(ttime);
            add(earn);
            add(ttime2);
            add(earn2);
        
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) 
    {
        if(
           e.getSource()==sta[0] || e.getSource()==sta[1] ||
           e.getSource()==sta[2] || e.getSource()==sta[3] ||
           e.getSource()==sta[4] || e.getSource()==sta[5] ||
           e.getSource()==sta[6] || e.getSource()==sta[7] ||
           e.getSource()==sta[8] || e.getSource()==sta[9] 
          )
        {
            String temp=e.getActionCommand();
            int n=Integer.parseInt(temp.substring(6));
            n=n-1;
            sta[n].setEnabled(false);
            stp[n].setEnabled(true);
            String time=String.valueOf(java.time.LocalTime.now());
            int k=0;
            while(time.charAt(k)!='.')
            {
                k++;
            }
            String starttime=time.substring(0, k);
            stime[n].setText(starttime);
        }
        else if(
           e.getSource()==stp[0] || e.getSource()==stp[1] ||
           e.getSource()==stp[2] || e.getSource()==stp[3] ||
           e.getSource()==stp[4] || e.getSource()==stp[5] ||
           e.getSource()==stp[6] || e.getSource()==stp[7] ||
           e.getSource()==stp[8] || e.getSource()==stp[9] 
          )
        {
            String temp=e.getActionCommand();
            int n=0;
            if(temp.length()>6)
            {
            n=Integer.parseInt(temp.substring(7));
            n=n-1;
            stp[n].setLabel("STOP "+(n+1));
            sttime[n].setText("");
            coll[n].setEnabled(false);
            }
            else
            {
            n=Integer.parseInt(temp.substring(5));
            n=n-1;
            stp[n].setLabel("RESUME "+(n+1));
            String time=String.valueOf(java.time.LocalTime.now());
            int k=0;
            while(time.charAt(k)!='.')
            {
                k++;
            }
            String stoptime=time.substring(0, k);
            sttime[n].setText(stoptime);
            cal[n].setEnabled(true);
            }
        }
        else if(
           e.getSource()==cal[0] || e.getSource()==cal[1] ||
           e.getSource()==cal[2] || e.getSource()==cal[3] ||
           e.getSource()==cal[4] || e.getSource()==cal[5] ||
           e.getSource()==cal[6] || e.getSource()==cal[7] ||
           e.getSource()==cal[8] || e.getSource()==cal[9] 
          )
        {
            String temp=e.getActionCommand();
            int n=Integer.parseInt(temp.substring(10));
            n=n-1;
            String t1=sttime[n].getText();
            String t2=stime[n].getText();
            int k=0;
            StringBuffer sb1=new StringBuffer();
            StringBuffer sb2=new StringBuffer();
            while(k<t1.length())
            {
                if(t1.charAt(k)!=':')
                {
                    sb1.append(t1.charAt(k));
                    sb2.append(t2.charAt(k));
                }
                k++;
            }
            int res=Integer.parseInt(sb1.toString())-Integer.parseInt(sb2.toString());
            int h=0;
            m=0;
            res=20000;
            if(res>9999)
            {
                h=res/10000;
                res=res%10000;
                m=res/100;
                m=m+(h*60);
            }
            else if(res>99)
            {
                m=res/100;
            }
            if(m!=0)
            {
            int fm=m%15;
            if(fm<8)
            {
                m=m-fm;
            }
            else
            {
                m=m+(15-fm);
            }
            
            
            if(m>60)
            {
                finfare=farepm*m;
                int t=m/60;
                while(t!=0)
                {
                    finfare=finfare-10;
                    t--;
                }
            }
            else
            {
                finfare=farepm*m;
            }
            coll[n].setEnabled(true);
            ffare[n].setText(String.valueOf(finfare)+" Rs.");
            }
                
        }
        else if(
           e.getSource()==coll[0] || e.getSource()==coll[1] ||
           e.getSource()==coll[2] || e.getSource()==coll[3] ||
           e.getSource()==coll[4] || e.getSource()==coll[5] ||
           e.getSource()==coll[6] || e.getSource()==coll[7] ||
           e.getSource()==coll[8] || e.getSource()==coll[9] 
          )
        {
            int t=0,ea=0;
            String temp=e.getActionCommand();
            int n=Integer.parseInt(temp.substring(8));
            n=n-1;
             stime[n].setText("");
            sttime[n].setText("");
            sta[n].setEnabled(true);
            stp[n].setEnabled(false);
            stp[n].setLabel("STOP "+(n+1));
           cal[n].setEnabled(false);
           ffare[n].setText("");
           coll[n].setEnabled(false);
            try
            {
                stmt = con.createStatement();  
                rs = stmt.executeQuery("select * from account");  
                while (rs.next()) 
                {  
                   t=rs.getInt(1);
                   ea=rs.getInt(2);
                }
                t=t+m;
                ea=ea+finfare;
                PreparedStatement ps=con.prepareStatement("update account set ttime=?,money=?");
                ps.setInt(1, t);
                ps.setInt(2, ea);
                int result=ps.executeUpdate();
                ttime.setText(String.valueOf(t));
                earn.setText(String.valueOf(ea));
            }
            catch(Exception ex)
            {
                ex.printStackTrace();
            }
        }
    
    }
}

public class Cyber 
{
    public static void main(String[] args) 
    {
        lay l=new lay();
    
    }
    
}
