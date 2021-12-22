package miniproject.Facebook_DropDown_Testing;

public class Automate {
	
	static String[][] excel_values;
	
	public void automate(){
		
		try
    	{
			//Object Creation For Excel File 
			GetExcelValues Excel_Key = new GetExcelValues();

			//Object Creation For Registration File
			Registration obj = new Registration();   

			//Reading Values From Excel
			excel_values = Excel_Key.readExcel();		        	 
			
			int rows = excel_values.length;
			
			//int columns = excel_values[0].length;			
			
			int iter = 0;

			//Iterating every row from the Excel Sheet
			
        	while(iter < rows){        		        	
        		
        		String browser_input = excel_values[iter][0];
        		
        		String first_name = excel_values[iter][1];
        		
        		String last_name = excel_values[iter][2];
        		
        		String mobile = excel_values[iter][3];
        		
        		String pass = excel_values[iter][4];

        		String dob = excel_values[iter][5];
        		
        		String gender = excel_values[iter][6]; 
        		        		
        		obj.createDriver(browser_input);	        		        	
        		
        		obj.setFormValues(first_name, last_name, mobile, pass, dob, gender);
        		
        		obj.closeBrowser();

        		iter++;
        	}             	
    	}
	catch(Exception e)
		{	
			e.getStackTrace();            
		}
	}
}
