package com.skype;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class VideoConference extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		System.out.println("VideoConference details");

		String fname = req.getParameter("Fname");
		String lname = req.getParameter("Lname");
		String mail = req.getParameter("email");
		String ph = req.getParameter("phone");
		String pwd = req.getParameter("pword");

		System.out.println(fname);
		System.out.println(lname);
		System.out.println(mail);
		System.out.println(ph);
		System.out.println(pwd);

		req.setAttribute("gmail", mail);
		req.setAttribute("p.word", ph);

		// database logic
		try {
			Class.forName("com.mysql.jdbc.Driver");
			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/gmail", "root", "ramya");
			PreparedStatement ps = con.prepareStatement(
					"insert into skype (MailId,Fname,Lname,Mobile,Pword) values(?,?,?,?,?)");
			ps.setString(1, mail);
			ps.setString(2, fname);
			ps.setString(3, lname);
			ps.setString(4, ph);
			ps.setString(5, pwd);
			int count = ps.executeUpdate();
			System.out.println("records count:"+count);
		} catch (Exception e) {
			System.out.println("Exception handling");
			e.printStackTrace();
		}
		req.getRequestDispatcher("kid.jsp").forward(req, resp);
	}
}
