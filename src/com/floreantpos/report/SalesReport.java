/**
 * ************************************************************************
 * * The contents of this file are subject to the MRPL 1.2
 * * (the  "License"),  being   the  Mozilla   Public  License
 * * Version 1.1  with a permitted attribution clause; you may not  use this
 * * file except in compliance with the License. You  may  obtain  a copy of
 * * the License at http://www.floreantpos.org/license.html
 * * Software distributed under the License  is  distributed  on  an "AS IS"
 * * basis, WITHOUT WARRANTY OF ANY KIND, either express or implied. See the
 * * License for the specific  language  governing  rights  and  limitations
 * * under the License.
 * * The Original Code is FLOREANT POS.
 * * The Initial Developer of the Original Code is OROCUBE LLC
 * * All portions are Copyright (C) 2015 OROCUBE LLC
 * * All Rights Reserved.
 * ************************************************************************
 */
package com.floreantpos.report;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import net.sf.jasperreports.engine.JREmptyDataSource;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRTableModelDataSource;
import net.sf.jasperreports.view.JRViewer;

import org.jdesktop.swingx.calendar.DateUtils;

import com.floreantpos.Messages;
import com.floreantpos.model.Shift;
import com.floreantpos.model.Terminal;
import com.floreantpos.model.Ticket;
import com.floreantpos.model.TicketItem;
import com.floreantpos.model.TicketItemModifier;
import com.floreantpos.model.User;
import com.floreantpos.model.UserType;
import com.floreantpos.model.dao.AttendenceHistoryDAO;
import com.floreantpos.model.dao.ShiftDAO;
import com.floreantpos.model.dao.TicketDAO;
import com.floreantpos.report.HourlyLaborReportView.LaborReportData;
import com.floreantpos.report.service.ReportService;
import com.floreantpos.ui.dialog.POSMessageDialog;
import com.floreantpos.util.CurrencyUtil;

public class SalesReport extends Report {
	private SalesReportModel itemReportModel;
	private SalesReportModel modifierReportModel;

	private ArrayList<LaborReportData> rows;
	private ArrayList<LaborReportData> shiftReportRows;
	public SalesReport() {
		super();
	}

	@Override
	public void refresh() throws Exception {
		createModels();
		viewReport();
		JasperReport itemReport = ReportUtil.getReport("sales_sub_report"); //$NON-NLS-1$
		JasperReport modifierReport = ReportUtil.getReport("sales_sub_report"); //$NON-NLS-1$

		HashMap map = new HashMap();
		ReportUtil.populateRestaurantProperties(map);
		map.put("reportTitle", Messages.getString("SalesReport.3")); //$NON-NLS-1$ //$NON-NLS-2$
		map.put("reportTime", ReportService.formatFullDate(new Date())); //$NON-NLS-1$
		map.put("dateRange", ReportService.formatShortDate(getStartDate()) + " to " + ReportService.formatShortDate(getEndDate())); //$NON-NLS-1$ //$NON-NLS-2$
		map.put("terminalName", getTerminal() == null ? com.floreantpos.POSConstants.ALL : getTerminal().getName()); //$NON-NLS-1$
		map.put("itemDataSource", new JRTableModelDataSource(itemReportModel)); //$NON-NLS-1$
		map.put("modifierDataSource", new JRTableModelDataSource(modifierReportModel)); //$NON-NLS-1$
		map.put("currency", Messages.getString("SalesReport.8") + CurrencyUtil.getCurrencyName() + " (" + CurrencyUtil.getCurrencySymbol() + ")"); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$ //$NON-NLS-4$ 
		map.put("itemTotalQuantity", itemReportModel.getTotalQuantity()); //$NON-NLS-1$
		map.put("itemTotal", itemReportModel.getTotalAsString()); //$NON-NLS-1$
		map.put("itemGrossTotal", itemReportModel.getGrossTotalAsDouble()); //$NON-NLS-1$
		map.put("itemDiscountTotal", itemReportModel.getDiscountTotalAsString()); //$NON-NLS-1$
		map.put("itemTaxTotal", itemReportModel.getTaxTotalAsString()); //$NON-NLS-1$
		map.put("itemGrandTotal", itemReportModel.getGrandTotalAsString()); //$NON-NLS-1$
		map.put("modifierTotalQuantity", modifierReportModel.getTotalQuantity()); //$NON-NLS-1$
		map.put("modifierGrossTotal", modifierReportModel.getGrossTotalAsDouble()); //$NON-NLS-1$
		map.put("modifierTaxTotal", modifierReportModel.getTaxTotalAsString()); //$NON-NLS-1$
		map.put("modifierGrandTotal", modifierReportModel.getGrandTotalAsString()); //$NON-NLS-1$
		map.put("modifierTotal", modifierReportModel.getTotalAsString()); //$NON-NLS-1$
		map.put("itemReport", itemReport); //$NON-NLS-1$
		map.put("modifierReport", modifierReport); //$NON-NLS-1$

		//hatran add full report
		JasperReport hourlyReport = ReportUtil.getReport("hourly_labor_subreport"); //$NON-NLS-1$
		JasperReport shiftReport = ReportUtil.getReport("hourly_labor_shift_subreport"); //$NON-NLS-1$

		map.put("hourlyReport", hourlyReport); //$NON-NLS-1$
		map.put("hourlyReportDatasource", new JRTableModelDataSource(new HourlyLaborReportModel(rows))); //$NON-NLS-1$
		map.put("shiftReport", shiftReport); //$NON-NLS-1$
		map.put("shiftReportDatasource", new JRTableModelDataSource(new HourlyLaborReportModel(shiftReportRows))); //$NON-NLS-1$

		
		
		JasperReport masterReport = ReportUtil.getReport("sales_report_full"); //$NON-NLS-1$

		JasperPrint print = JasperFillManager.fillReport(masterReport, map, new JREmptyDataSource());
		viewer = new JRViewer(print);
	}

	@Override
	public boolean isDateRangeSupported() {
		return true;
	}

	@Override
	public boolean isTypeSupported() {
		return true;
	}

	public void createModels() {
		Date date1 = DateUtils.startOfDay(getStartDate());
		Date date2 = DateUtils.endOfDay(getEndDate());

		List<Ticket> tickets = TicketDAO.getInstance().findTickets(date1, date2, getReportType() == Report.REPORT_TYPE_1 ? true : false, getTerminal());

		HashMap<String, ReportItem> itemMap = new HashMap<String, ReportItem>();
		HashMap<String, ReportItem> modifierMap = new HashMap<String, ReportItem>();

		for (Iterator iter = tickets.iterator(); iter.hasNext();) {
			Ticket t = (Ticket) iter.next();

			Ticket ticket = TicketDAO.getInstance().loadFullTicket(t.getId());

			List<TicketItem> ticketItems = ticket.getTicketItems();
			if (ticketItems == null)
				continue;

			String key = null;
			for (TicketItem ticketItem : ticketItems) {
				if (ticketItem.getUnitPrice() == 0 && !isIncludedFreeItems() && !ticket.getOrderType().getName().toLowerCase().contains("uber")) { //hatran add: fix does not count item price 0.0 when ordering by UBER
					continue;
				}
				if (ticketItem.getItemId() == null) {
					key = ticketItem.getName();
				}
				else {
					key = ticketItem.getItemId().toString();
				}
				key += "-" + ticketItem.getName() + ticketItem.getUnitPrice() + ticketItem.getTaxRate(); //$NON-NLS-1$

				ReportItem reportItem = itemMap.get(key);

				if (reportItem == null) {
					reportItem = new ReportItem();
					reportItem.setId(key);
					reportItem.setUniqueId(ticketItem.getItemId().toString());
					reportItem.setPrice(ticketItem.getUnitPrice());
					reportItem.setName(ticketItem.getName());
					reportItem.setTaxRate(ticketItem.getTaxRate());

					itemMap.put(key, reportItem);
				}

				//set the quantity
				if (ticketItem.isFractionalUnit()) {
					reportItem.setQuantity(ticketItem.getItemQuantity() + reportItem.getQuantity());
				}
				else {
					reportItem.setQuantity(ticketItem.getItemCount() + reportItem.getQuantity());
				}

				//reportItem.setQuantity(ticketItem.getItemCount() + reportItem.getQuantity());

				reportItem.setGrossTotal(reportItem.getGrossTotal() + ticketItem.getTotalAmountWithoutModifiers());
				reportItem.setDiscount(reportItem.getDiscount() + ticketItem.getDiscountAmount());
				reportItem.setTaxTotal(reportItem.getTaxTotal() + ticketItem.getTaxAmountWithoutModifiers());
				reportItem.setTotal(reportItem.getTotal() + ticketItem.getSubtotalAmountWithoutModifiers());

				List<TicketItemModifier> modifiers = ticketItem.getTicketItemModifiers();
				if (modifiers != null) {
					for (TicketItemModifier modifier : modifiers) {
						if (modifier.getUnitPrice() == 0 && !isIncludedFreeItems()) {
							continue;
						}

						if (modifier.getModifierId() == null) {
							key = modifier.getName();
						}
						else {
							key = modifier.getModifierId().toString();
						}
						key += "-" + modifier.getName() + modifier.getModifierType() + "-" + modifier.getUnitPrice() + modifier.getTaxRate(); //$NON-NLS-1$ //$NON-NLS-2$

						ReportItem modifierReportItem = modifierMap.get(key);
						if (modifierReportItem == null) {
							modifierReportItem = new ReportItem();
							modifierReportItem.setId(key);
							modifierReportItem.setUniqueId(modifier.getModifierId().toString());

							modifierReportItem.setPrice(modifier.getUnitPrice());
							modifierReportItem.setName(modifier.getName());
							modifierReportItem.setTaxRate(modifier.getTaxRate());

							modifierMap.put(key, modifierReportItem);
						}
						modifierReportItem.setQuantity(modifierReportItem.getQuantity() + modifier.getItemCount());
						modifierReportItem.setGrossTotal(modifierReportItem.getGrossTotal() + modifier.getTotalAmount());
						modifierReportItem.setTaxTotal(modifierReportItem.getTaxTotal() + modifier.getTaxAmount());
						modifierReportItem.setTotal(modifierReportItem.getTotal() + modifier.getSubTotalAmount());
					}
				}
			}
			ticket = null;
			iter.remove();
		}
		itemReportModel = new SalesReportModel();

		List<ReportItem> itemList = new ArrayList<ReportItem>(itemMap.values());
		Collections.sort(itemList, new Comparator<ReportItem>() {

			public int compare(ReportItem o1, ReportItem o2) {
				return Integer.parseInt(o1.getUniqueId()) - Integer.parseInt(o2.getUniqueId());
			}
		});

		itemReportModel.setItems(itemList);
		itemReportModel.calculateTotalQuantity();
		itemReportModel.calculateDiscountTotal();
		itemReportModel.calculateGrossTotal();
		itemReportModel.calculateTaxTotal();
		itemReportModel.calculateGrandTotal();
		itemReportModel.calculateTotal();

		modifierReportModel = new SalesReportModel();

		List<ReportItem> modifierList = new ArrayList<ReportItem>(modifierMap.values());
		Collections.sort(modifierList, new Comparator<ReportItem>() {

			public int compare(ReportItem o1, ReportItem o2) {
				return Integer.parseInt(o1.getUniqueId()) - Integer.parseInt(o2.getUniqueId());
			}
		});
		modifierReportModel.setItems(modifierList);
		modifierReportModel.calculateTotalQuantity();
		modifierReportModel.calculateGrossTotal();
		modifierReportModel.calculateTaxTotal();
		modifierReportModel.calculateGrandTotal();
		modifierReportModel.calculateTotal();
	}
	
	private void viewReport() {
		Date fromDate = DateUtils.startOfDay(getStartDate());
		Date toDate = DateUtils.endOfDay(getEndDate());
		
		if (fromDate.after(toDate)) {
			POSMessageDialog.showError(com.floreantpos.util.POSUtil.getFocusedWindow(), com.floreantpos.POSConstants.FROM_DATE_CANNOT_BE_GREATER_THAN_TO_DATE_);
			return;
		}

		UserType userType = null;

		Terminal terminal = null;
		
		Calendar calendar = Calendar.getInstance();
		calendar.clear();

		Calendar calendar2 = Calendar.getInstance();
		calendar2.setTime(fromDate);

		calendar.set(Calendar.YEAR, calendar2.get(Calendar.YEAR));
		calendar.set(Calendar.MONTH, calendar2.get(Calendar.MONTH));
		calendar.set(Calendar.DATE, calendar2.get(Calendar.DATE));
		calendar.set(Calendar.HOUR, 0);
		calendar.set(Calendar.MINUTE, 0);
		calendar.set(Calendar.SECOND, 0);
		fromDate = calendar.getTime();

		calendar.clear();
		calendar2.setTime(toDate);
		calendar.set(Calendar.YEAR, calendar2.get(Calendar.YEAR));
		calendar.set(Calendar.MONTH, calendar2.get(Calendar.MONTH));
		calendar.set(Calendar.DATE, calendar2.get(Calendar.DATE));
		calendar.set(Calendar.HOUR, 23);
		calendar.set(Calendar.MINUTE, 59);
		calendar.set(Calendar.SECOND, 59);
		toDate = calendar.getTime();

		TicketDAO ticketDAO = TicketDAO.getInstance();
		AttendenceHistoryDAO attendenceHistoryDAO = new AttendenceHistoryDAO();
		rows = new ArrayList<LaborReportData>();

		DecimalFormat formatter = new DecimalFormat("00"); //$NON-NLS-1$

		int grandTotalChecks = 0;
		int grandTotalGuests = 0;
		double grandTotalSales = 0;
		double grandTotalMHr = 0;
		double grandTotalLabor = 0;
		double grandTotalSalesPerMHr = 0;
		double grandTotalGuestsPerMHr = 0;
		double grandTotalCheckPerMHr = 0;
		double grandTotalLaborCost = 0;

		for (int i = 0; i < 24; i++) {
			List<Ticket> tickets = ticketDAO.findTicketsForLaborHour(fromDate, toDate, i, userType, terminal);
			List<User> users = attendenceHistoryDAO.findNumberOfClockedInUserAtHour(fromDate, toDate, i, userType, terminal);

			int manHour = users.size();
			int totalChecks = 0;
			int totalGuests = 0;
			double totalSales = 0;
			double labor = 0;
			double salesPerMHr = 0;
			double guestsPerMHr = 0;
			double checksPerMHr = 0;
			//double laborCost = 0;

			for (Ticket ticket : tickets) {
				++totalChecks;
				totalGuests += ticket.getNumberOfGuests();
				totalSales += ticket.getTotalAmount();
			}

			for (User user : users) {
				labor += (user.getCostPerHour() == null ? 0 : user.getCostPerHour());
			}
			if (manHour > 0) {
				labor = labor / manHour;
				salesPerMHr = totalSales / manHour;
				guestsPerMHr = (double) totalGuests / manHour;
				checksPerMHr = totalChecks / manHour;
				//laborCost =
			}

			LaborReportData reportData = new LaborReportData();
			reportData.setPeriod(formatter.format(i) + ":00 - " + formatter.format(i) + ":59"); //$NON-NLS-1$ //$NON-NLS-2$
			reportData.setManHour(manHour);
			reportData.setNoOfChecks(totalChecks);
			reportData.setSales(totalSales);
			reportData.setNoOfGuests(totalGuests);
			reportData.setLabor(labor);
			reportData.setSalesPerMHr(salesPerMHr);
			reportData.setGuestsPerMHr(guestsPerMHr);
			reportData.setCheckPerMHr(checksPerMHr);

			rows.add(reportData);

			grandTotalChecks += totalChecks;
			grandTotalGuests += totalGuests;
			grandTotalSales += totalSales;
			grandTotalMHr += manHour;
			grandTotalLabor += labor;
			grandTotalSalesPerMHr += salesPerMHr;
			grandTotalCheckPerMHr += checksPerMHr;
			grandTotalGuestsPerMHr += guestsPerMHr;
			//grandTotalLaborCost +=

		}

		shiftReportRows = new ArrayList<LaborReportData>();
		ShiftDAO shiftDAO = new ShiftDAO();
		List<Shift> shifts = shiftDAO.findAll();
		for (Shift shift : shifts) {
			List<Ticket> tickets = ticketDAO.findTicketsForShift(fromDate, toDate, shift, userType, terminal);
			List<User> users = attendenceHistoryDAO.findNumberOfClockedInUserAtShift(fromDate, toDate, shift, userType, terminal);

			int manHour = users.size();
			int totalChecks = 0;
			int totalGuests = 0;
			double totalSales = 0;
			double labor = 0;
			double salesPerMHr = 0;
			double guestsPerMHr = 0;
			double checksPerMHr = 0;
			//double laborCost = 0;

			for (Ticket ticket : tickets) {
				++totalChecks;
				totalGuests += ticket.getNumberOfGuests();
				totalSales += ticket.getTotalAmount();
			}

			for (User user : users) {
				labor += (user.getCostPerHour() == null ? 0 : user.getCostPerHour());
			}
			if (manHour > 0) {
				labor = labor / manHour;
				salesPerMHr = totalSales / manHour;
				guestsPerMHr = (double) totalGuests / manHour;
				checksPerMHr = totalChecks / manHour;
				//laborCost =
			}

			LaborReportData reportData = new LaborReportData();
			reportData.setPeriod(shift.getName());
			reportData.setManHour(manHour);
			reportData.setNoOfChecks(totalChecks);
			reportData.setSales(totalSales);
			reportData.setNoOfGuests(totalGuests);
			reportData.setLabor(labor);
			reportData.setSalesPerMHr(salesPerMHr);
			reportData.setGuestsPerMHr(guestsPerMHr);
			reportData.setCheckPerMHr(checksPerMHr);

			shiftReportRows.add(reportData);
		}
	}
}
