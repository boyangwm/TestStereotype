package durbodax.commands;

/**
 *
 * @author Kirk Seddon
 */
public class SampleCommand implements Command {

    /*
    IDAO object;
      
    //DAO object could be passed into object providing direct access to the DB
    public SampleCommand(IDAO object);     
     */
    
    public SampleCommand() {}
    
    public Object execute(String[] params) {
                
        Object retObj = new Object();

       System.out.println("Param 1 = " + params[1]);
       System.out.println("Param 2 = " + params[2]);
       System.out.println("Param 3 = " + params[3]);

        return retObj;
    }
    
}
