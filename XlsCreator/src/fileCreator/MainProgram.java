package fileCreator;

public class MainProgram {

	public static void main(String[] args) {
		
		//Create Xls File (filePath, fileName, extension)
		FileCreator fileCreator = new FileCreator("D:\\TEST files\\", "excel1", "xls");
		fileCreator.createXls();
		
	
	}
}


