package com.example.proj.action;

import java.io.FileInputStream;
import java.io.FileOutputStream;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;
import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.interceptor.ServletResponseAware;

import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Document;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.Image;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.Rectangle;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfReader;
import com.itextpdf.text.pdf.PdfStamper;
import com.itextpdf.text.pdf.PdfWriter;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.util.ArrayList;

import com.example.proj.model.Appointment;
import com.opensymphony.xwork2.ActionSupport;

public class Admin extends ActionSupport implements ServletRequestAware, ServletResponseAware {
    private HttpServletRequest request;
	private HttpServletResponse response;
    private int accountId;
    private int clients;
    private int pendingAppointments;
    private int approvedAppointments;
    private int totalSales;
    private String today;
    LocalDate now = LocalDate.now();
    ArrayList<Appointment> appointments = new ArrayList<Appointment>();
    @Override
    public String execute() throws Exception{
        
        setClients(numberOfClients());
        setApprovedAppointments(numOfApprovedAppointments());
        setTotalSales(totalSales());
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        try {
            String URL = "jdbc:mysql://localhost:3306/petclinic?useTimezone=true&serverTimezone=UTC";
            Class.forName("com.mysql.jdbc.Driver");
            connection = DriverManager.getConnection(URL, "root", "password");

            if (connection != null) {
                String sql = "SELECT appointments.*, concat(DATE_FORMAT(schedule, '%a, %b %d %Y'),' ', time_format(time, '%h:%i %p')) AS currentSchedule, CONCAT(customer_name.first_name,' ',customer_name.last_name) AS customer,CONCAT(veterinarian_name.first_name,' ', veterinarian_name.last_name) AS Veterinarian, pets.pet_name, services.service FROM appointments INNER JOIN accounts AS customer_name ON appointments.customer_id=customer_name.account_id INNER JOIN accounts AS veterinarian_name ON appointments.veterinarian_id = veterinarian_name.account_id INNER JOIN pets ON appointments.pet_id = pets.pet_id INNER JOIN services ON appointments.service = services.service_id inner join time on appointments.timeID=time.timeID where schedule = '"+now+"' order by schedule ASC";
                preparedStatement = connection.prepareStatement(sql);
                ResultSet rs= preparedStatement.executeQuery();
                while(rs.next()){
                    Appointment appointment=new Appointment();
                    appointment.setAppointmentId(rs.getInt(1));
                    appointment.setClientId(rs.getInt(2));
                    appointment.setPetId(rs.getInt(3));
                    appointment.setVeterinarianId(rs.getInt(4));
                    appointment.setServiceId(rs.getInt(5));
                    appointment.setSchedule(rs.getString(9));
                    // appointment.setSchedule(appointment.getSchedule().substring(0, 16));
                    appointment.setTimeOfAppointment(rs.getInt(7));
                    appointment.setStatus(rs.getString(8));
                    appointment.setCustomer(rs.getString(10));
                    appointment.setVeterinarian(rs.getString(11));
                    appointment.setPetName(rs.getString(12));
                    appointment.setService(rs.getString(13));
                    appointments.add(appointment);
                }
                
                setToday(now.format(DateTimeFormatter.ofLocalizedDate(FormatStyle.FULL)));
                
            } 
         } catch (Exception e) {

         } finally {
            if (preparedStatement != null) try { preparedStatement.close(); } catch (SQLException ignore) {}
            if (connection != null) try { connection.close(); } catch (SQLException ignore) {}
         }
        return SUCCESS;
    }
    public int numberOfClients() throws Exception{
        
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        try {
            String URL = "jdbc:mysql://localhost:3306/petclinic?useTimezone=true&serverTimezone=UTC";
            Class.forName("com.mysql.jdbc.Driver");
            connection = DriverManager.getConnection(URL, "root", "password");

            if (connection != null) {
                String sql = "SELECT COUNT(*) FROM accounts where account_type_id = 3";
                preparedStatement = connection.prepareStatement(sql);
                ResultSet rs= preparedStatement.executeQuery();
                while(rs.next()){
                setClients(rs.getInt(1));
                }
                
            } 
         } catch (Exception e) {

         } finally {
            if (preparedStatement != null) try { preparedStatement.close(); } catch (SQLException ignore) {}
            if (connection != null) try { connection.close(); } catch (SQLException ignore) {}
         }
         return clients;
    }
    public int numOfApprovedAppointments() throws Exception{
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        try {
            String URL = "jdbc:mysql://localhost:3306/petclinic?useTimezone=true&serverTimezone=UTC";
            Class.forName("com.mysql.jdbc.Driver");
            connection = DriverManager.getConnection(URL, "root", "password");

            if (connection != null) {
                String sql = "SELECT COUNT(*) FROM appointments where status = 'approved'";
                preparedStatement = connection.prepareStatement(sql);
                ResultSet rs= preparedStatement.executeQuery();
                while(rs.next()){
                    setApprovedAppointments(rs.getInt(1));
                }
                
            } 
         } catch (Exception e) {

         } finally {
            if (preparedStatement != null) try { preparedStatement.close(); } catch (SQLException ignore) {}
            if (connection != null) try { connection.close(); } catch (SQLException ignore) {}
         }
         return approvedAppointments;
    }

    public int totalSales() throws Exception{
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        try {
            String URL = "jdbc:mysql://localhost:3306/petclinic?useTimezone=true&serverTimezone=UTC";
            Class.forName("com.mysql.jdbc.Driver");
            connection = DriverManager.getConnection(URL, "root", "password");

            if (connection != null) {
                String sql = "SELECT SUM(services.fee)AS 'Total Sale' FROM services INNER JOIN appointments ON services.service_id = appointments.service where status = 'approved'";
                preparedStatement = connection.prepareStatement(sql);
                ResultSet rs= preparedStatement.executeQuery();
                while(rs.next()){
                    setTotalSales(rs.getInt(1));
                }
                
            } 
         } catch (Exception e) {

         } finally {
            if (preparedStatement != null) try { preparedStatement.close(); } catch (SQLException ignore) {}
            if (connection != null) try { connection.close(); } catch (SQLException ignore) {}
         }
         return totalSales;
    }

    public String generateReport() throws Exception{
        setClients(numberOfClients());
        setApprovedAppointments(numOfApprovedAppointments());
        setTotalSales(totalSales());
        setToday(now.format(DateTimeFormatter.ofLocalizedDate(FormatStyle.FULL)));

        try {

			// Creating document and set the page size
			Document document = new Document(PageSize.A4, 20, 20, 20, 20);
			String imagePath = ServletActionContext.getServletContext().getRealPath("/assets");
			PdfWriter writer = PdfWriter.getInstance(document, new FileOutputStream(imagePath + "/temp1" + ".pdf"));
			document.open();

			// Add image on the document
			Image image = Image.getInstance(imagePath + "/PetClinic" + ".png");
			image.scaleToFit(90f, 90f);
			image.setAlignment(Image.MIDDLE);
			image.setAbsolutePosition(70, 770);
			image.scaleAbsolute(100, 40);
			document.add(image);

			// Creating the paragraph and add it to document
			Paragraph p1 = new Paragraph(" REPORTS ",
					FontFactory.getFont(FontFactory.HELVETICA, 14, Font.BOLD, new BaseColor(0, 0, 0)));

			Paragraph p2 = new Paragraph(getToday(),
					FontFactory.getFont(FontFactory.HELVETICA, 10, Font.UNDERLINE, new BaseColor(0, 0, 0)));
			Paragraph p3 = new Paragraph("\n\n\n");
			p1.setAlignment(Element.ALIGN_CENTER);
			p2.setAlignment(Element.ALIGN_CENTER);
			document.add(p1);
			document.add(p2);
			document.add(p3);

			// Create table and set the width of columns

            PdfPTable myT = new PdfPTable(3);
			float widths2[] = { 5, 5, 5,};
			myT.setWidths(widths2);
			myT.setHeaderRows(1);
			myT.setTotalWidth(100f);

            PdfPCell c2 = new PdfPCell(new Phrase("Number Of Clients", FontFactory.getFont(FontFactory.HELVETICA, 8)));
			c2.setBorderWidth((float) 0.25);
			c2.setBackgroundColor(new BaseColor(232, 232, 232));
			c2.setHorizontalAlignment(Element.ALIGN_CENTER);
			c2.setVerticalAlignment(Element.ALIGN_MIDDLE);
			myT.addCell(c2);

            c2 = new PdfPCell(new Phrase("Approved Appointments", FontFactory.getFont(FontFactory.HELVETICA, 8)));
			c2.setBorderWidth((float) 0.25);
			c2.setBackgroundColor(new BaseColor(232, 232, 232));
			c2.setHorizontalAlignment(Element.ALIGN_CENTER);
			c2.setVerticalAlignment(Element.ALIGN_MIDDLE);
			myT.addCell(c2);

            c2 = new PdfPCell(new Phrase("Total Sales", FontFactory.getFont(FontFactory.HELVETICA, 8)));
			c2.setBorderWidth((float) 0.25);
			c2.setBackgroundColor(new BaseColor(232, 232, 232));
			c2.setHorizontalAlignment(Element.ALIGN_CENTER);
			c2.setVerticalAlignment(Element.ALIGN_MIDDLE);
			myT.addCell(c2);

            Phrase phr;
            c2 = new PdfPCell();
			c2.setBorderWidth((float) 0.25);
			c2.setHorizontalAlignment(Element.ALIGN_CENTER);
			phr = new Phrase( String.valueOf(getClients()), FontFactory.getFont(FontFactory.HELVETICA, 8));
			c2.addElement(phr);
			myT.addCell(c2);

            c2 = new PdfPCell();
			c2.setBorderWidth((float) 0.25);
			c2.setHorizontalAlignment(Element.ALIGN_CENTER);
			phr = new Phrase( String.valueOf(getApprovedAppointments()), FontFactory.getFont(FontFactory.HELVETICA, 8));
			c2.addElement(phr);
			myT.addCell(c2);

			c2 = new PdfPCell();
			c2.setBorderWidth((float) 0.25);
			c2.setHorizontalAlignment(Element.ALIGN_CENTER);
			phr = new Phrase( String.valueOf(getTotalSales()), FontFactory.getFont(FontFactory.HELVETICA, 8));
			c2.addElement(phr);
			myT.addCell(c2);

			PdfPTable t = new PdfPTable(5);
			float widths[] = { 3, 6, 3, 3, 4,};
			t.setWidths(widths);
			t.setHeaderRows(1);
			t.setTotalWidth(100f);

			// Add table header row
			PdfPCell c1 = new PdfPCell(new Phrase("Pet", FontFactory.getFont(FontFactory.HELVETICA, 8)));
			c1.setBorderWidth((float) 0.25);
			c1.setBackgroundColor(new BaseColor(232, 232, 232));
			c1.setHorizontalAlignment(Element.ALIGN_CENTER);
			c1.setVerticalAlignment(Element.ALIGN_MIDDLE);
			t.addCell(c1);

			c1 = new PdfPCell(new Phrase("Veterinarian", FontFactory.getFont(FontFactory.HELVETICA, 8)));
			c1.setBorderWidth(0.25f);
			c1.setBackgroundColor(new BaseColor(232, 232, 232));
			c1.setHorizontalAlignment(Element.ALIGN_CENTER);
			c1.setVerticalAlignment(Element.ALIGN_MIDDLE);
			t.addCell(c1);

			c1 = new PdfPCell(new Phrase("Service", FontFactory.getFont(FontFactory.HELVETICA, 8)));
			c1.setBorderWidth(0.25f);
			c1.setBackgroundColor(new BaseColor(232, 232, 232));
			c1.setHorizontalAlignment(Element.ALIGN_CENTER);
			c1.setVerticalAlignment(Element.ALIGN_MIDDLE);
			t.addCell(c1);

			c1 = new PdfPCell(new Phrase("Schedule", FontFactory.getFont(FontFactory.HELVETICA, 8)));
			c1.setBorderWidth(0.25f);
			c1.setBackgroundColor(new BaseColor(232, 232, 232));
			c1.setHorizontalAlignment(Element.ALIGN_CENTER);
			c1.setVerticalAlignment(Element.ALIGN_MIDDLE);
			t.addCell(c1);


			c1 = new PdfPCell(new Phrase("Status", FontFactory.getFont(FontFactory.HELVETICA, 8)));
			c1.setBorderWidth(0.25f);
			c1.setBackgroundColor(new BaseColor(232, 232, 232));
			c1.setHorizontalAlignment(Element.ALIGN_CENTER);
			c1.setVerticalAlignment(Element.ALIGN_MIDDLE);
			t.addCell(c1);

			// Add the table details row
			Phrase ph;
			c1 = new PdfPCell();
			c1.setBorderWidth((float) 0.25);
			c1.setHorizontalAlignment(Element.ALIGN_CENTER);
			ph = new Phrase("Alpaca", FontFactory.getFont(FontFactory.HELVETICA, 8));
			c1.addElement(ph);
			t.addCell(c1);

			c1 = new PdfPCell();
			c1.setBorderWidth((float) 0.25);
			c1.setHorizontalAlignment(Element.ALIGN_CENTER);
			ph = new Phrase("Shaira Asis", FontFactory.getFont(FontFactory.HELVETICA, 8));
			c1.addElement(ph);
			t.addCell(c1);

			c1 = new PdfPCell();
			c1.setBorderWidth((float) 0.25);
			c1.setHorizontalAlignment(Element.ALIGN_CENTER);
			ph = new Phrase("Consultation", FontFactory.getFont(FontFactory.HELVETICA, 8));
			c1.addElement(ph);
			t.addCell(c1);

			c1 = new PdfPCell();
			c1.setBorderWidth((float) 0.25);
			c1.setHorizontalAlignment(Element.ALIGN_CENTER);
			ph = new Phrase("2022-09-10", FontFactory.getFont(FontFactory.HELVETICA, 8));
			c1.addElement(ph);
			t.addCell(c1);

			c1 = new PdfPCell();
			c1.setBorderWidth((float) 0.25);
			c1.setHorizontalAlignment(Element.ALIGN_CENTER);
			ph = new Phrase("Pending", FontFactory.getFont(FontFactory.HELVETICA, 8));
			c1.addElement(ph);
			t.addCell(c1);
	
			// Finally add all into the document
			response.setHeader("Content-disposition", "inline; filename=\"PetClinicReport.pdf\"");
			document.add(myT);
            document.add(p3);
            document.add(t);
			document.close();
			addPageNumber("temp1.pdf", "newName.pdf", response, request);
		} catch (Exception e) {
			e.printStackTrace();
		}

		return null;
	}
    // Method for adding page number on the document
	public static void addPageNumber(String oldFileName, String newFileName, HttpServletResponse response,HttpServletRequest request) {
    try {
        String realPath = ServletActionContext.getServletContext().getRealPath("/assets");
        FileInputStream fis = new FileInputStream(realPath + "/" + "temp1.pdf");
        PdfReader reader = new PdfReader(fis);
        int totalPages = reader.getNumberOfPages();
        PdfStamper stamper = new PdfStamper(reader, response.getOutputStream());
        for (int i = 1; i <= totalPages; i++) {
            getHeaderTable(i, totalPages).writeSelectedRows(0, -1, 34, 30, stamper.getOverContent(i));
        }
        stamper.close();
        fis.close();
    } catch (Exception e) {
        e.printStackTrace();
    }

    }
    public static PdfPTable getHeaderTable(int x, int y) {
    PdfPTable table = new PdfPTable(2);
    try {
        table.setTotalWidth(490);
        table.setLockedWidth(true);
        table.getDefaultCell().setFixedHeight(20);
        table.getDefaultCell().setBorder(Rectangle.NO_BORDER);

        PdfPCell cell = new PdfPCell(new com.itextpdf.text.Phrase((""),
                new com.itextpdf.text.Font(com.itextpdf.text.Font.FontFamily.HELVETICA, 5)));
        cell.setBorder(Rectangle.NO_BORDER);
        table.addCell(cell);

        table.getDefaultCell().setHorizontalAlignment(Element.ALIGN_RIGHT);
        table.getDefaultCell().setBorder(Rectangle.NO_BORDER);

        cell = new PdfPCell(new com.itextpdf.text.Phrase(String.format("Page %d of %d", x, y),
                new com.itextpdf.text.Font(com.itextpdf.text.Font.FontFamily.HELVETICA, 5)));
        cell.setBorder(Rectangle.NO_BORDER);
        cell.setHorizontalAlignment(Element.ALIGN_RIGHT);

        table.addCell(cell);

    } catch (Exception e) {
        System.out.println("getHeaderTable exception" + e.getMessage());
        e.printStackTrace();
    }
    return table;
}


    public int getAccountId() {
        return accountId;
    }

    public void setAccountId(int accountId) {
        this.accountId = accountId;
    }

    
    public int getPendingAppointments() {
        return pendingAppointments;
    }

    public void setPendingAppointments(int pendingAppointments) {
        this.pendingAppointments = pendingAppointments;
    }

    
    public int getClients() {
        return clients;
    }

    public void setClients(int clients) {
        this.clients = clients;
    }
    public int getTotalSales() {
        return totalSales;
    }
    public void setTotalSales(int totalSales) {
        this.totalSales = totalSales;
    }
    public LocalDate getNow() {
        return now;
    }
    public void setNow(LocalDate now) {
        this.now = now;
    }
    public ArrayList<Appointment> getAppointments() {
        return appointments;
    }
    public void setAppointments(ArrayList<Appointment> appointments) {
        this.appointments = appointments;
    }
    public String getToday() {
        return today;
    }
    public void setToday(String today) {
        this.today = today;
    }
    public int getApprovedAppointments() {
        return approvedAppointments;
    }
    public void setApprovedAppointments(int approvedAppointments) {
        this.approvedAppointments = approvedAppointments;
    }

    @Override
	public void setServletRequest(HttpServletRequest request) {
		this.request = request;
	}

	public HttpServletRequest getServletRequest() {
		return request;
	}

	@Override
	public void setServletResponse(HttpServletResponse response) {
		this.response = response;
	}

	public HttpServletResponse getServletResponse() {
		return response;
	}

    
}
