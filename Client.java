/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package chatting.applicaton;

/**
 *
 * @author ASUS
 */
import static chatting.applicaton.Server.dos;
import static chatting.applicaton.Server.format_of_message;
import static chatting.applicaton.Server.frame;
import static chatting.applicaton.Server.vertical;
import java.net.*;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;


public class Client implements ActionListener {
    
    JTextField text_area;
    static JPanel area;
    static Box vertical= Box.createVerticalBox();// to make the messages align one below another
    static DataOutputStream dos;
    
    static JFrame frame=new JFrame();
    public Client()
    {
        frame.setLayout(null);
        
        JPanel panel_header= new JPanel();
        panel_header.setBackground(new Color(128,0,128));
        panel_header.setBounds(0, 0, 500, 60);
        panel_header.setLayout(null);
        frame.add(panel_header);
        
        ImageIcon image_back_arrow= new ImageIcon(ClassLoader.getSystemResource("icons//3.png"));
        Image scaled_image_1= image_back_arrow.getImage().getScaledInstance(25, 25, Image.SCALE_DEFAULT);
        ImageIcon image_new1=  new ImageIcon(scaled_image_1);
        JLabel back_arrow= new JLabel(image_new1);
        back_arrow.setBounds(5, 12, 25, 25);
        panel_header.add(back_arrow);
        back_arrow.addMouseListener(new MouseAdapter(){
        
            public void mouseClicked(MouseEvent e)
            {
                System.exit(0);
            }
        });
                
        ImageIcon image_profile_pic= new ImageIcon(ClassLoader.getSystemResource("icons//bunty.jpeg"));
        Image scaled_image_2= image_profile_pic.getImage().getScaledInstance(50, 50, Image.SCALE_DEFAULT);
        ImageIcon image_new2=  new ImageIcon(scaled_image_2);
        JLabel profile_pic= new JLabel(image_new2);
        profile_pic.setBounds(40, 5, 50, 50);
        panel_header.add(profile_pic);

        ImageIcon image_video_icon= new ImageIcon(ClassLoader.getSystemResource("icons//video.png"));
        Image scaled_image_3= image_video_icon.getImage().getScaledInstance(30, 30, Image.SCALE_DEFAULT);
        ImageIcon image_new3=  new ImageIcon(scaled_image_3);
        JLabel video_icon= new JLabel(image_new3);
        video_icon.setBounds(340, 13, 30, 30);
        panel_header.add(video_icon);
        
        ImageIcon image_call_icon= new ImageIcon(ClassLoader.getSystemResource("icons//phone.png"));
        Image scaled_image_4= image_call_icon.getImage().getScaledInstance(30, 30, Image.SCALE_DEFAULT);
        ImageIcon image_new4=  new ImageIcon(scaled_image_4);
        JLabel call_icon= new JLabel(image_new4);
        call_icon.setBounds(400, 13, 30, 30);
        panel_header.add(call_icon);
        
        ImageIcon image_dots_icon= new ImageIcon(ClassLoader.getSystemResource("icons//3icon.png"));
        Image scaled_image_5= image_dots_icon.getImage().getScaledInstance(30, 30, Image.SCALE_DEFAULT);
        ImageIcon image_new5=  new ImageIcon(scaled_image_5);
        JLabel dots_icon= new JLabel(image_new5);
        dots_icon.setBounds(450, 13, 7, 30);
        panel_header.add(dots_icon);
        
        JLabel name= new JLabel("Bunty");
        name.setBounds(100, 10, 200, 20);
        name.setForeground(Color.WHITE);
        name.setFont(new Font("Monospaced 18 Bold",Font.BOLD,20));
        panel_header.add(name);
        
        JLabel status= new JLabel("Active Now");
        status.setBounds(100, 30, 200, 20);
        status.setForeground(Color.WHITE);
        status.setFont(new Font("Monospaced 18 Bold",Font.BOLD,12));
        panel_header.add(status);
        
        area= new JPanel();
        area.setBounds(0, 70, 500, 630);
        frame.add(area);
        
        text_area= new JTextField();
        text_area.setBounds(5, 700, 320, 40);
        text_area.setFont(new Font("Monospaced 18 Bold",Font.BOLD,14));
        frame.add(text_area);
        
        JButton send= new JButton("Send");
        send.setBounds(335, 700, 80, 40);
        send.setBackground(new Color(128,0,128));
        send.setForeground(Color.WHITE);
        Border border_of_button= BorderFactory.createMatteBorder(5, 5, 5, 5, new Color(128,0,128));
        send.setBorder(border_of_button);
        
        frame.add(send);
        send.addActionListener(this);
        
        
        frame.setSize(500,800);
        frame.setUndecorated(true);
        frame.getContentPane().setBackground(Color.WHITE);//getContentPane() is used to get all methods in JFrame created.
        frame.setLocation(1200,100);
        
        frame.setVisible(true);
    }
    public void actionPerformed(ActionEvent e)
    {
        
        String message= text_area.getText();
        
        JPanel message_panel= format_of_message(message);
        
        
        area.setLayout(new BorderLayout());
        
        JPanel right= new JPanel(new BorderLayout());
        right.add(message_panel,BorderLayout.LINE_END);//to make the message align to the right boundary
        vertical.add(right);
        vertical.add(Box.createVerticalStrut(20));//to give space of 15 between the messages
        
        area.add(vertical, BorderLayout.PAGE_START);// to align the veritcal box from the start of area panel
        frame.repaint();
        frame.invalidate();//??
        frame.validate();//??
        try {
            dos.writeUTF(message);
        } catch (IOException ex) {
            Logger.getLogger(Client.class.getName()).log(Level.SEVERE, null, ex);
        }
        text_area.setText("");
    }
    public static JPanel format_of_message(String message)
    {
        JPanel return_panel=  new JPanel();
        return_panel.setLayout(new BoxLayout(return_panel,BoxLayout.Y_AXIS));
        JLabel output= new JLabel(message);
        
        output.setBackground(new Color(128,0,128));
        output.setForeground(Color.WHITE);
        output.setOpaque(true);
        output.setFont(new Font("Monospaced 18 Bold",Font.BOLD,18));
        output.setBorder(new EmptyBorder(15,15,15,50));
        
        Calendar cal=Calendar.getInstance();
        SimpleDateFormat sdf= new SimpleDateFormat("HH:mm");//? why small mm ?  
        JLabel time= new JLabel();
        time.setText(sdf.format(cal.getTime()));
        
        return_panel.add(output);
        return_panel.add(time);
        return return_panel;
    }
    
    public static void main(String[] args) {
        new Client();
        try
        {
            Socket soc=new Socket("127.0.0.1",6000);
            DataInputStream dis=new DataInputStream(soc.getInputStream());// to recieve mssgs
            dos=new DataOutputStream(soc.getOutputStream());// to send mssgs
            while(true)
                {
                    area.setLayout(new BorderLayout());
                    String msg=dis.readUTF();// THIS string now has the mssg recieved now I have to display it
                    
                    JPanel panel=format_of_message(msg);
                    JPanel left=new JPanel(new BorderLayout());
                    left.add(panel, BorderLayout.LINE_START);
                    
                    vertical.add(left);
                    
                    vertical.add(Box.createVerticalStrut(15));
                    area.add(vertical, BorderLayout.PAGE_START);
                    frame.validate();
                    
                }
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
    }
    
}
