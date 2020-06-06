package com.orangehrm.base;

import java.io.File;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

public class ExtentManager {

	ExtentReports report;
	public static final DateFormat dateFormat = new SimpleDateFormat("DD_MM_YYYY SSS");

	private static ExtentReports extent;

	public static ExtentReports createReportInstance() {
		// To Get the Report Name from getReportName()
		String fileName = getReportName();

		String directory = System.getProperty("user.dir") + "/test-reports/";
		new File(directory).mkdirs();
		String path = directory + fileName;

		ExtentHtmlReporter htmlreporter = new ExtentHtmlReporter(path);

		htmlreporter.config().setEncoding("utf-8");
		htmlreporter.config().setDocumentTitle("Automation Test Report");
		htmlreporter.config().setReportName("Automation Test Results");
		htmlreporter.config().setTheme(Theme.STANDARD);

		extent = new ExtentReports();

		extent.setSystemInfo("Machine", "Deva-007");
		extent.setSystemInfo("Env", "Test");
		extent.setSystemInfo("Build", "Integration");
		extent.setSystemInfo("Browser", "Chrome");

		extent.attachReporter(htmlreporter);
		return extent;

	}

	public static String getReportName() {
		Date date = new Date();
		String reportName = "Automation Report" + "_" + dateFormat.format(date) + ".html";
		return reportName;
	}
}
