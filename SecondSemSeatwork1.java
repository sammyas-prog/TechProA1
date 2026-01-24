import java.util.Scanner;

class Product {
    private String name;
    private double price;
    private int quantity;

    
    public boolean setName(String name) {
        name = name.toLowerCase();

        
        if (name.equals("gaming mouse")) {
            this.name = "Gaming Mouse";
            this.price = 1500.5;
      
        }

        return true; 
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public double getTotalAmount() {
        return price * quantity;
    }

    public void showDetails() {
        System.out.println("Price: " + price);
        System.out.println("Total Amount: " + getTotalAmount());
    }
}

public class SecondSemSeatwork1 {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        Product item = new Product();

   
       

        boolean valid = false;
        while (!valid) {
            System.out.print("Enter product name: ");
            String name = input.nextLine();
            valid = item.setName(name);

            if (!valid) {
                System.out.println("Invalid product! Please choose a product from the list.\n");
            }
        }

        System.out.print("Enter quantity: ");
        item.setQuantity(input.nextInt());

        
        item.showDetails();
    }
}
