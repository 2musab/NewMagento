import java.util.Random;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class parameters {

	WebDriver driver = new ChromeDriver();
	Random rand = new Random();
	JavascriptExecutor js = (JavascriptExecutor) driver;

	String[] firstName = { "Aurelia", "Cassius", "Flavia", "Gaius", "Lucius", "Octavia", "Quintus", "Tiberius",
			"Valeria", "Vibius", "Alice", "Bob", "Charlie", "David", "Eve", "Frank", "Grace", "Hannah", "Isaac", "John",
			"Aaliyah", "Jamal", "Imani", "Malik", "Tiana", "Kwame", "Latoya", "Darius", "Nia", "Andre", "Oliver",
			"Charlotte", "Harry", "Emily", "George", "Sophie", "Arthur", "Amelia", "Henry", "Isla", "Ji-ho", "Soo-jin",
			"Min-ji", "Hyun-woo", "Yeon-hee", "Jung-hoon", "Seo-yeon", "Dae-hyun", "Hye-jin", "Joon" };

	String[] lastNames = { "Smith", "Johnson", "Brown", "Taylor", "Anderson", "Thomas", "Jackson", "White", "Harris",
			"Martin", "Antonius", "Cornelius", "Fabius", "Julius", "Marcellus", "Pompeius", "Scipio", "Seneca",
			"Tullius", "Varro", "Washington", "Williams", "Brown", "Jackson", "Harris", "Robinson", "Walker", "Young",
			"King", "Johnson", "Smith", "Jones", "Taylor", "Wilson", "Brown", "Davies", "Evans", "Thomas", "Roberts",
			"Walker", "Kim", "Lee", "Park", "Choi", "Jung", "Kang", "Yoon", "Lim", "Han", "Shin"};

	int firstNameLength = firstName.length;
	int lastNamesLength = lastNames.length;

	int randomFN = rand.nextInt(firstNameLength);
	int randomLN = rand.nextInt(lastNamesLength);

	String firstRandomName = firstName[randomFN];
	String lastRandomName = lastNames[randomLN];

}
