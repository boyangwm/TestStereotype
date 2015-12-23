package edu.wm.core;
import java.io.IOException;
import java.util.logging.ConsoleHandler;
import java.util.logging.FileHandler;
import java.util.logging.Handler;
import java.util.logging.Level;
import java.util.logging.Logger;


public class MyLog {
	public static final Logger LOGGER = Logger.getLogger("myLog");
	//LOGGER.log(Level.SEVERE, "Error occur in FileHandler.", exception);

	public static void initLog(){
		Handler consoleHandler = null;
		Handler fileHandler  = null;
		try{
			//Creating consoleHandler and fileHandler
			//consoleHandler = new ConsoleHandler();
			fileHandler  = new FileHandler("./TestStereotype.log");

			//Assigning handlers to LOGGER object
			//LOGGER.addHandler(consoleHandler);
			LOGGER.addHandler(fileHandler);

			//Setting levels to handlers and LOGGER
			//consoleHandler.setLevel(Level.ALL);
			fileHandler.setLevel(Level.ALL);
			LOGGER.setLevel(Level.ALL);
		}catch(IOException exception){
			LOGGER.log(Level.SEVERE, "Error occur in FileHandler.", exception);
		}
	}
}
