
import java.io.*;

public class PrintWriterExample{
	public static void main(String[] args){
		try{
		FileWriter  writer      = new FileWriter("report.txt");
		PrintWriter printWriter = new PrintWriter(writer);


		printWriter.print(true);
		printWriter.print((int) 123);
		printWriter.print((float) 123.456);

		//printWriter.printf(Locale.UK, "Text + data: %1$", 123);
	printWriter.close();
	}
		catch (IOException e){
		}

	}
}