package CS321.Project.Code;

import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;

import javax.imageio.ImageIO;

public class Util {

	// Method to make sure methods in here work
	public static void main(String[] args) {
		String contents = "";
		contents = readTextFromFile("c:/321/input/inv.txt");
		System.out.println(contents);
	}

	public static String readTextFromFile(String fileName) {
		// Variables
		String out = "";
		BufferedReader in = null;
		// Opens file
		try {
			in = new BufferedReader(new FileReader(fileName));
		} catch (Exception e) {
			e.printStackTrace();
		}
		if (in == null)
			return out;
		// Reads actual file
		try {
			String curLine = in.readLine();
			// Loop to read until end of file
			while (curLine != null) {
				out += curLine + "\n";
				curLine = in.readLine();
			}
			out = out.substring(0, out.length() - 2);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return out;
	}

	public static BufferedImage readImageFromFile(String fileName) {
		BufferedImage img = null;
		try {
			img = ImageIO.read(new File(fileName));
		} catch (Exception e) {
			e.printStackTrace();
		}

		return null;
	}

	public static void writeImageToFolder(BufferedImage img, String folderName) {
		File temp = new File(folderName);
		int amt = 0;
		String name = "image" + amt + ".png";
		while (amt < temp.list().length) {
			boolean nameUsed = false;
			for (int a = 0; a < temp.list().length; a++)
				if (temp.list()[a].equalsIgnoreCase(name)) {
					// System.out.println(temp.list()[a]);
					nameUsed = true;
				}

			if (nameUsed != true)
				break;
			name = "image" + (++amt) + ".png";
		}

		writeImageToFile(img, folderName, name);
	}

	public static void writeImageToFile(BufferedImage img, String loc, String name) {
		File dest = new File(loc + name);
		try {
			ImageIO.write(img, "png", dest);
		} catch (Exception e) {
			e.printStackTrace();
		}
		System.out.println("Image written to " + dest.toString());
	}

	public static void writeTextToFile(String folderName) {

	}
}
