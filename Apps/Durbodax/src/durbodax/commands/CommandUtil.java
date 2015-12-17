package durbodax.commands;

import java.util.HashMap;


/**
 *
 * @author Kirk Seddon
 */
public class CommandUtil {
    
    private static HashMap commands = new HashMap();
    
    private CommandUtil() {}
    
    public static final Command getCommand(String name) {
        
        Command command = ((Command)commands.get(name.toLowerCase()));
 
        return command;
        
    }    
    
    public static final void registerCommand(String name, Command command) {
        
        commands.put(name.toLowerCase(), command);
        
    }

    public static Command getComputeChildCreditCommand() {

        ComputeChildCredit command = new ComputeChildCredit();
        command.suppressOutput();
        return command;
    }

    public static Command getComputeInLawTaxCommand() {

        ComputeInLawTax command = new ComputeInLawTax();
        command.suppressOutput();
        return command;
    }

}

