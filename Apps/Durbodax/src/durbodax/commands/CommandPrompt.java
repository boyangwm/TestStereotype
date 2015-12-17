package durbodax.commands;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.EnumSet;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author Kirk Seddon
 */
public final class CommandPrompt {

    private int currentIndex;
    private final String[] userPrompts;
    private final BufferedReader bufferedReader;
  
    public CommandPrompt(String[] prompts) {

        userPrompts = prompts;
        currentIndex = 0;
        bufferedReader = new BufferedReader(new InputStreamReader(System.in));

    }
    
    public int size() {

        return userPrompts.length;
    }
    
    public String getPrompt(int i) {

        return userPrompts[i];
    }

    public void reset() {

        currentIndex = 0;

    }
    
    public String displayNextPrompt() {

        
        try {

            String userInput = "";
            boolean isValid = false;
            boolean firstTry = true;
            do {

                if(!firstTry) {

                    System.out.println("Invalid input, please try again.");

                }
                 System.out.print(userPrompts[currentIndex] + ": ");
                 userInput = bufferedReader.readLine();

                 Test test = Test.getTest(userPrompts[currentIndex+1]);
                 switch(test) {

                    case Number: isValid = number.validate(userInput); break;
                    case String: isValid = string.validate(userInput); break;
                    case Number1_or_2: isValid = number1_or_2.validate(userInput); break;
                    case Number0_2: isValid = number0_2.validate(userInput); break;
                    case Number0_3: isValid = number0_3.validate(userInput); break;
                    case Number0_4: isValid = number0_4.validate(userInput); break;
                    case Number0_6: isValid = number0_6.validate(userInput); break;
                    case Number0_7: isValid = number0_7.validate(userInput); break;
                    case Number0_8: isValid = number0_8.validate(userInput); break;
                    case Number0_9: isValid = number0_9.validate(userInput); break;
                    case Number1_6: isValid = number1_6.validate(userInput); break;
                    case Number1_7: isValid = number1_7.validate(userInput); break;
                    case Number1_13: isValid = number1_13.validate(userInput); break;
                    case Last_Year: isValid = lastyear.validate(userInput); break;
                    case Link_to_Father: isValid = linktofather.validate(userInput); break;
                    case Number_0_1_2_9: isValid = number_0_1_2_9.validate(userInput); break;
                    case Hours_Worked: isValid = hoursworked.validate(userInput); break;
                    case Age_Compare:
                        isValid = ageCompare.validate(userInput);
                        if(isValid) {
                            if(!"".equals(userInput.trim()))
                                userInput =  "-a~"+ userInput;
                        }
                        break;
                    case  Income_Compare:
                        isValid = incomeCompare.validate(userInput);
                        if(isValid) {
                            if(!"".equals(userInput.trim()))
                                userInput = "-i~"+ userInput;
                        }
                        break;
                    case  MaritalStatus_Compare:
                        isValid = marital_status.validate(userInput);
                        if(isValid) {
                            if(!"".equals(userInput.trim()))
                                userInput = "-m~"+ userInput;
                        }
                        break;

                 }
                 firstTry = false;

            } while(isValid == false) ;

            currentIndex+=2;
            return userInput.trim();

        } catch(IOException e) {

             System.out.println(e.getStackTrace());
             System.out.println(e.getMessage());
        }
        
        currentIndex+=2;
        return " ";      
  }

    PromptTest number = new PromptTest() {
        public boolean validate(String input) {
            try {
                Integer.parseInt(input);
                return true;

            } catch (NumberFormatException e) {
                return false;
            }
        }
    };

    PromptTest lastyear = new PromptTest() {
        public boolean validate(String input) {
            try {
                int value = Integer.parseInt(input);

                if((value == 0) ||
                   (value == 10) ||
                   (value == 20) ||
                   (value == 31) ||
                   (value == 32) ||
                   (value == 33) ||
                   (value == 34) ||
                   (value == 35) ||
                   (value == 36) ||
                   (value == 40) ||
                   (value == 50)) {

                    return true;

                } else {

                    return false;
                }

            } catch (NumberFormatException e) {
                return false;
            }
        }
    };

    PromptTest hoursworked = new PromptTest() {
        public boolean validate(String input) {
            try {
                int value = Integer.parseInt(input);

                if((value == 0) ||
                   (value == 2) ||
                   (value == 3) ||
                   (value == 4) ||
                   (value == 5) ||
                   (value == 6) ||
                   (value == 7) ||
                   (value == 8)) {

                    return true;

                } else {

                    return false;
                }

            } catch (NumberFormatException e) {
                return false;
            }
        }
    };
    
     PromptTest number_0_1_2_9 = new PromptTest() {
        public boolean validate(String input) {
            try {
                int value = Integer.parseInt(input);

                if((value == 0) ||
                   (value == 1) ||
                   (value == 2) ||
                   (value == 9)) {

                    return true;

                } else {

                    return false;
                }

            } catch (NumberFormatException e) {
                return false;
            }
        }
    };

    PromptTest linktofather = new PromptTest() {
        public boolean validate(String input) {
            try {
                int value = Integer.parseInt(input);

                if((value == 0) ||
                   (value == 1) ||
                   (value == 2) ||
                   (value == 3) ||
                   (value == 4) ||
                   (value == 7)) {

                    return true;

                } else {

                    return false;
                }

            } catch (NumberFormatException e) {
                return false;
            }
        }
    };

     PromptTest number1_or_2 = new PromptTest() {
        public boolean validate(String input) {
            try {
                int value = Integer.parseInt(input);

                if((value == 1) || (value == 2)) {

                    return true;

                } else {

                    return false;
                }

            } catch (NumberFormatException e) {
                return false;
            }
        }
    };

    PromptTest number1_6 = new PromptTest() {
        public boolean validate(String input) {
            try {
                int value = Integer.parseInt(input);

                if((1<=value) && (value<=6)) {

                    return true;

                } else {

                    return false;
                }

            } catch (NumberFormatException e) {
                return false;
            }
        }
    };

    PromptTest number1_7 = new PromptTest() {
        public boolean validate(String input) {
            try {
                int value = Integer.parseInt(input);

                if((1<=value) && (value<=7)) {

                    return true;

                } else {

                    return false;
                }

            } catch (NumberFormatException e) {
                return false;
            }
        }
    };

    PromptTest number1_13 = new PromptTest() {
        public boolean validate(String input) {
            try {
                int value = Integer.parseInt(input);

                if((1<=value) && (value<=13)) {

                    return true;

                } else {

                    return false;
                }

            } catch (NumberFormatException e) {
                return false;
            }
        }
    };

    PromptTest number0_2 = new PromptTest() {
        public boolean validate(String input) {
            try {
                int value = Integer.parseInt(input);

                if((0<=value) && (value<=2)) {

                    return true;

                } else {

                    return false;
                }

            } catch (NumberFormatException e) {
                return false;
            }
        }
    };

    PromptTest number0_3 = new PromptTest() {
        public boolean validate(String input) {
            try {
                int value = Integer.parseInt(input);

                if((0<=value) && (value<=3)) {

                    return true;

                } else {

                    return false;
                }

            } catch (NumberFormatException e) {
                return false;
            }
        }
    };
    
    PromptTest number0_4 = new PromptTest() {
        public boolean validate(String input) {
            try {
                int value = Integer.parseInt(input);

                if((0<=value) && (value<=4)) {

                    return true;

                } else {

                    return false;
                }

            } catch (NumberFormatException e) {
                return false;
            }
        }
    };

    PromptTest number0_6 = new PromptTest() {
        public boolean validate(String input) {
            try {
                int value = Integer.parseInt(input);

                if((0<=value) && (value<=6)) {

                    return true;

                } else {

                    return false;
                }

            } catch (NumberFormatException e) {
                return false;
            }
        }
    };

    PromptTest number0_7 = new PromptTest() {
        public boolean validate(String input) {
            try {
                int value = Integer.parseInt(input);

                if((0<=value) && (value<=7)) {

                    return true;

                } else {

                    return false;
                }

            } catch (NumberFormatException e) {
                return false;
            }
        }
    };

    PromptTest number0_8 = new PromptTest() {
        public boolean validate(String input) {
            try {
                int value = Integer.parseInt(input);

                if((0<=value) && (value<=8)) {

                    return true;

                } else {

                    return false;
                }

            } catch (NumberFormatException e) {
                return false;
            }
        }
    };


    PromptTest number0_9 = new PromptTest() {
        public boolean validate(String input) {
            try {
                int value = Integer.parseInt(input);

                if((0<=value) && (value<=9)) {

                    return true;

                } else {

                    return false;
                }

            } catch (NumberFormatException e) {
                return false;
            }
        }
    };

    PromptTest string = new PromptTest() {
        public boolean validate(String input) {
          return !"".equals(input.trim());
        }
      };

    PromptTest compare = new PromptTest() {
        public boolean validate(String input) {
          if("".equals(input.trim()))
          {
              return true;
          } else {
            String[] values = input.split("-");
            if(values != null && values.length == 2) {
                if(values[0].equalsIgnoreCase("Under") || values[0].equalsIgnoreCase("Over")) {
                    if(number.validate(values[1])) {
                        return true;
                    }
                }
            }
          }
          return false;
        }
      };

     PromptTest ageCompare = compare;
     PromptTest incomeCompare = compare;
     PromptTest marital_status = number1_6;

      public enum Test {

          Number("number"),
          String("string"),
          Number1_or_2("number1_or_2"),
          Number0_2("number0_2"),
          Number0_3("number0_3"),
          Number0_4("number0_4"),
          Number0_6("number0_6"),
          Number0_7("number0_7"),
          Number0_8("number0_8"),
          Number0_9("number0_9"),
          Number1_6("number1_6"),
          Number1_7("number1_7"),
          Number1_13("number1_13"),
          Link_to_Father("linktofather"),
          Number_0_1_2_9("number_0_1_2_9"),
          Hours_Worked("hoursworked"),
          Last_Year("lastyear"),
          Age_Compare("ageCompare"),
          Income_Compare("incomeCompare"),
          MaritalStatus_Compare("marital_status");

          private static final Map<String, Test> lookup = new HashMap<String, Test>();
          private String testType;

          static {

              for(Test test : EnumSet.allOf(Test.class)) {

                  lookup.put(test.getTestType(), test);

              }

          }

          private Test(String testType) {

              this.testType = testType;

          }

          public String getTestType() {

              return testType;

          }

          public static Test getTest(String testType) {

            return lookup.get(testType);

        }

      }
}