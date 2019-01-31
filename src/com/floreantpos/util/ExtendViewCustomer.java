package com.floreantpos.util;

import java.awt.BorderLayout;
import java.awt.Button;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.List;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.SwingConstants;
import javax.swing.JLabel;
import javax.swing.border.EmptyBorder;

import com.floreantpos.IconFactory;
import com.floreantpos.bo.ui.BackOfficeWindow;
import com.floreantpos.config.TerminalConfig;
import com.floreantpos.demo.KitchenDisplayView;
import com.floreantpos.extension.ExtensionManager;
import com.floreantpos.extension.OrderServiceExtension;
import com.floreantpos.extension.OrderServiceFactory;
import com.floreantpos.main.Application;
import com.floreantpos.model.ITicketItem;
import com.floreantpos.model.OrderType;
import com.floreantpos.model.TicketItem;
import com.floreantpos.model.dao.OrderTypeDAO;
import com.floreantpos.swing.PosUIManager;
import com.floreantpos.ui.HeaderPanel;
import com.floreantpos.ui.ticket.TicketViewerTable;
import com.floreantpos.ui.views.CustomerView;
import com.floreantpos.ui.views.IView;
import com.floreantpos.ui.views.LoginView;
import com.floreantpos.ui.views.SwitchboardOtherFunctionsView;
import com.floreantpos.ui.views.SwitchboardView;
import com.floreantpos.ui.views.TableMapView;
import com.floreantpos.ui.views.order.RootView;
import com.floreantpos.ui.views.payment.SettleTicketDialog;
import com.floreantpos.util.TicketAlreadyExistsException;

import net.miginfocom.swing.MigLayout;

public class ExtendViewCustomer  extends com.floreantpos.swing.TransparentPanel {
	private JLabel lblRestaurantName;
	private JPanel centerPanel = new JPanel();
	private int width;
	private int height;
	private static ExtendViewCustomer instance;
	private static JTextArea txtlistItem;
	private static JTextArea txtTotal;
	private ExtendViewCustomer() {
		setLayout(new BorderLayout(5, 5));


		centerPanel.setLayout(new BorderLayout(5, 5)); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
//		JLabel titleLabel = new JLabel(IconFactory.getIcon("/ui_icons/", "title.png")); //$NON-NLS-1$ //$NON-NLS-2$
//		titleLabel.setOpaque(true);
//		titleLabel.setBackground(Color.WHITE);


		initView();

	}
	private void initView() {
//		setOpaque(true);
//		setBackground(Color.WHITE);
		
		
		lblRestaurantName = new JLabel(Application.getInstance().getRestaurant().getName());
		lblRestaurantName.setPreferredSize(new Dimension(1000, 100));
		lblRestaurantName.setForeground(Color.BLACK);
		lblRestaurantName.setFont(new Font("Dialog", Font.BOLD, PosUIManager.getFontSize(28)));
		lblRestaurantName.setHorizontalAlignment(SwingConstants.CENTER);
		
		
		JPanel jLeft = new JPanel();
		JPanel jRight = new JPanel();
		JPanel jTop = new JPanel();
		
		jTop.setLayout(new GridLayout(2,0));
		JPanel jBottom = new JPanel();

		JLabel logoLabel = new JLabel(IconFactory.getIcon("/ui_icons/", "header-logo.png")); //$NON-NLS-1$ //$NON-NLS-2$
		logoLabel.setBackground(Color.white);
				
		JPanel row1 = new JPanel();
		JPanel row2 = new JPanel();
		row1.add(logoLabel);
		row1.setBackground(Color.WHITE);
		
		row2.add(lblRestaurantName);
		
		jTop.add(row1);//hatran head menu 
		jTop.add(row2);
		add(jTop, "North");
		jTop.setPreferredSize(new Dimension(1280, 200));

		add(jLeft, "West");
		jLeft.setPreferredSize(new Dimension(100, 480));

		add(jRight, "East");
		jRight.setPreferredSize(new Dimension(100, 480));


		add(jBottom, "South");
		jBottom.setPreferredSize(new Dimension(640, 20));
	   
		// MediaPanel slide = new MediaPanel();
		// centerPanel.add(slide);
	     add(centerPanel, BorderLayout.CENTER);
	     centerPanel.setPreferredSize(new Dimension(1000, 600));

	}
	public synchronized static ExtendViewCustomer getInstance() {
		if (instance == null) {
			instance = new ExtendViewCustomer();
		}
		return instance;
	}
	public void showTextTest(String text,String fontSize)
	{
	
		
		centerPanel.removeAll();
		JPanel contentPanel = new JPanel();
		contentPanel.setLayout(new MigLayout("", "[grow]", "[][]"));
		
		JLabel lbOrder = new JLabel("Order # ");
		lbOrder.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
		lbOrder.setFont(new Font("Serif", Font.PLAIN, 60));
		
		JLabel lblText = new javax.swing.JLabel();
		lblText.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
		lblText.setText("<html>" + text.replaceAll("<","&lt;").replaceAll(">", "&gt;").replaceAll("\n", "<br/>") + "</html>");
		lblText.setFont(new Font("Serif", Font.PLAIN, Integer.parseInt(fontSize)));
		
		contentPanel.add(lbOrder,"align 50% 50%");
		contentPanel.add(lblText,"align 50% 50%, newline");//hatran head menu 
		centerPanel.add(contentPanel,BorderLayout.CENTER);//hatran head menu 
	
		
	}
	public void showText(String text,boolean isOrder)
	{
		centerPanel.removeAll();
		JPanel contentPanel = new JPanel();
		contentPanel.setLayout(new MigLayout("", "[grow]", "[][]"));
		
		JLabel lbOrder = new JLabel("Order # ");
		lbOrder.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
		lbOrder.setFont(new Font("Serif", Font.PLAIN, 60));
		
		JLabel lblText = new javax.swing.JLabel();
		lblText.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
		lblText.setText("<html>" + text.replaceAll("<","&lt;").replaceAll(">", "&gt;").replaceAll("\n", "<br/>") + "</html>");
		int fontSize = Integer.parseInt(TerminalConfig.getCustomerDisplayFontSize());
		lblText.setFont(new Font("Serif", Font.PLAIN, fontSize));
		
		if (isOrder)
		{
			contentPanel.add(lbOrder,"align 50% 50%");
		}
		else
		{
			lblText.setFont(new Font("Serif", Font.PLAIN, 22));
		}
		
		contentPanel.add(lblText, "align 50% 50%, newline");//hatran head menu 
		centerPanel.add(contentPanel,BorderLayout.CENTER);//hatran head menu 
	
		
	}
	
	public static String fixedLengthString(String string, int length) {
	    return String.format("%1$"+length+ "s", string);
	}
	public void showTalbeTicket(TicketViewerTable ticketViewerTable, String totalPrice) //hatran : print out contain of ticket to extend screen.
	{
		int count = ticketViewerTable.getActualRowCount();
		txtlistItem = new JTextArea();
		txtlistItem.setOpaque(false);
		for(int i=0;i<count;i++)
        {
			ITicketItem  item = ticketViewerTable.get(i);
			String itemName = item.getNameDisplay();
//			itemName =  String.format("%-40s", itemName);

			String itemCount = item.getItemQuantityDisplay();
//			double itemPrice = item.getSubTotalAmountDisplay() == null ? 0 : item.getSubTotalAmountDisplay();//hatran rem to modified : list sub items with its prices
			double itemPrice = item.getSubTotalAmountWithoutModifiersDisplay() == null ? 0 : item.getSubTotalAmountWithoutModifiersDisplay();
			String line = String.format("%s      %s \t %s",itemCount, fixedLengthString(itemName,-25), NumberUtil.formatNumber(itemPrice, true)); //$NON-NLS-1$

            txtlistItem.setText( txtlistItem.getText()+" \n "+line);
            txtlistItem.setFont(new Font("Dialog", Font.BOLD, PosUIManager.getFontSize(18)));
        }
		
		txtTotal = new JTextArea();
		txtTotal.setOpaque(false);
		txtTotal.setText("---------------------------------------------------------------------- ");
		String total = "\n TOTAL: "; //$NON-NLS-1$
		String line2 = String.format(" %s \t \t %20s", total, CurrencyUtil.getCurrencySymbol() +" "+ totalPrice); //$NON-NLS-1$
		txtTotal.setText( txtTotal.getText()+" \n  "+line2);
		txtTotal.setFont(new Font("Dialog", Font.BOLD, PosUIManager.getFontSize(22)));
		 centerPanel.removeAll();

		 centerPanel.revalidate();
		 centerPanel.repaint();
		JPanel jTop = new JPanel();
		JPanel jBottom = new JPanel();
		jTop.add(txtlistItem,BorderLayout.NORTH);
		jBottom.add(txtTotal,BorderLayout.NORTH);
		centerPanel.add(jTop,BorderLayout.NORTH);
		centerPanel.add(jBottom,BorderLayout.SOUTH);
		
	}
}
