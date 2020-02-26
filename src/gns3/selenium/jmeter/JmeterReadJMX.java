package gns3.selenium.jmeter;

import org.apache.jmeter.engine.StandardJMeterEngine;
import org.apache.jmeter.save.SaveService;
import org.apache.jmeter.util.JMeterUtils;
import org.apache.jorphan.collections.HashTree;

import java.io.File;


public class JmeterReadJMX {

	public static void main(String[] args) throws Exception {
		// JMeter Engine
        StandardJMeterEngine jmeter = new StandardJMeterEngine();

        // Initialize Properties, logging, locale, etc.
        JMeterUtils.loadJMeterProperties("/Users/aalif/Documents/GitHub/jmeter/apache-jmeter-5.2.1/bin/jmeter.properties");
        JMeterUtils.setJMeterHome("/Users/aalif/Documents/GitHub/jmeter/apache-jmeter-5.2.1/");
        //JMeterUtils.initLogging();// you can comment this line out to see extra log messages of i.e. DEBUG level
        JMeterUtils.initLocale();

        // Initialize JMeter SaveService
        SaveService.loadProperties();

        // Load existing .jmx Test Plan
        //FileInputStream in = new FileInputStream("/Users/aalif/Documents/GitHub/jmeter/apache-jmeter-5.2.1/jmx-files/KeycloakAdmin.jmx");
        //HashTree testPlanTree = SaveService.loadTree(in);
        HashTree testPlanTree = SaveService.loadTree(new File("/Users/aalif/Documents/GitHub/jmeter/apache-jmeter-5.2.1/jmx-files/KeycloakAdmin.jmx"));
        //in.close();

        // Run JMeter Test
        jmeter.configure(testPlanTree);
        jmeter.run();

	}

}
