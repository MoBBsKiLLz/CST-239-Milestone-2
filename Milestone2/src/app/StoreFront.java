package app;

import storefront.*;
import product.*;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * StoreFront class, driver
 * @author migg_
 *
 */
public class StoreFront {

	/**
	 * Main method
	 * @param args Default parameter
	 */
    public static void main(String[] args) {
		Store myStore = new Store(); // Create new store
		ArrayList<Armor> armor = new ArrayList<Armor>(); // Create new armor ArrayList
		ArrayList<Weapon> weapons = new ArrayList<Weapon>(); // Create new weapons ArrayList
		ArrayList<Health> health = new ArrayList<Health>(); // Create new health ArrayList
		ArrayList<Product> receipt = new ArrayList<Product>(); // Create a receipt
		
		// Create 2 armor for inventory
		Armor armor1 = new Armor();
		armor1.setName("Light Armor");
		armor1.setDescription("Light armor with low resistance.");
		armor1.setQuantity(7);
		armor1.setPrice(5.99);
		armor1.setResistance(30);
		
		Armor armor2 = new Armor();
		armor2.setName("Heavy Armor");
		armor2.setDescription("Heavy armor with high resistance.");
		armor2.setQuantity(3);
		armor2.setPrice(9.99);
		armor2.setResistance(50);
		
		// Add the armor to the armor array
		armor.add(armor1);
		armor.add(armor2);
		
		// Add armor to the inventory
		myStore.setInventory(armor);
		
		// Create 2 weapons for inventory
		Weapon weapon1 = new Weapon();
		weapon1.setName("Sword");
		weapon1.setDescription("Close range with high damage.");
		weapon1.setQuantity(5);
		weapon1.setPrice(3.99);
		weapon1.setDamage(35);
		
		Weapon weapon2 = new Weapon();
		weapon2.setName("Bow");
		weapon2.setDescription("Long range weapon with low damage.");
		weapon2.setQuantity(2);
		weapon2.setPrice(7.99);
		weapon2.setDamage(12);
		
		// Add the weapon to the weapons array
		weapons.add(weapon1);
		weapons.add(weapon2);
		
		// Add weapons to the inventory
		myStore.setInventory(weapons);
		
		// Create 2 health for inventory
		Health health1 = new Health();
		health1.setName("Small Health");
		health1.setDescription("Small amount of health.");
		health1.setQuantity(12);
		health1.setPrice(5.00);
		health1.setAmount(15);
		
		Health health2 = new Health();
		health2.setName("Large Health");
		health2.setDescription("Large amount of health.");
		health2.setQuantity(6);
		health2.setPrice(15.00);
		health2.setAmount(10);
		
		// Add the health to the health array
		health.add(health1);
		health.add(health2);
		
		// Add health to the inventory
		myStore.setInventory(health);
		
		System.out.println("WELCOME TO THE EQUIPMENT STORE!");
		System.out.println();
		System.out.println("INVENTORY");
		System.out.println();
		
		// Show the inventory
		myStore.showInventory();
		
		// Ask user what they would like to purchase
		Scanner input = new Scanner(System.in);
		
		System.out.println("Enter -1 at any time to end.");
		System.out.print("Enter the product you want to purchase: ");
		String product = input.nextLine();
		String addProduct = "";
		System.out.println();
		
		if(product.compareTo("-1") != 0) {
		    System.out.print("Enter the quantity you want to purchase: ");
		    String quantity = input.nextLine();
		    
		    System.out.println();
		    
		    if(quantity.compareTo("-1") != 0) {
				while(product.compareTo("-1") != 0 && quantity.compareTo("-1") != 0) {
				    // AddProduct value
					if (addProduct.toUpperCase().compareTo("N") != 0) {
						// Add product to shopping cart
						boolean addToCart = myStore.addToCart(product, Integer.parseInt(quantity));
						if (addToCart) {
							System.out.println("Product was added to the shopping cart");
							System.out.println();
						}
						else if (!addToCart){
							System.out.println("Product was not added to the shopping cart");
							System.out.println();
						}
				    }
				    
				    // Ask user if they would like to check-out
				    System.out.print("Would you like to check-out? (Y/N) ");
				    String checkOut = input.nextLine();
				    if(checkOut.toUpperCase().compareTo("Y") == 0) {
						System.out.println();
						receipt = myStore.purchase();
						
						if(receipt != null) {
						    break;
						}
				    }
				    else if (checkOut.compareTo("-1") == 0) {
					break;
				    }
				    
				    System.out.println();
				    
				    // Ask user if they would like to remove an item
				    System.out.print("Would you like to remove a product? (Y/N) ");
				    String remove = input.nextLine();
				    if(remove.toUpperCase().compareTo("Y") == 0) {
						System.out.println();
						System.out.print("Enter the name of the product: ");
						product = input.nextLine();
						System.out.println();
						System.out.print("Enter the quantity: ");
						quantity = input.nextLine();
						myStore.removeFromCart(product, Integer.parseInt(quantity));
				    }
				    else if (remove.compareTo("-1") == 0) {
				    	break;
				    }
				    
				    System.out.println();
				    
				    // Ask user if they would like to add another product
				    System.out.print("Would you like to add another product? (Y/N) ");
				    addProduct = input.nextLine();
				    System.out.println();
				    if(addProduct.toUpperCase().compareTo("Y") == 0) {
						System.out.print("Enter the product you want to purchase: ");
						product = input.nextLine();
						System.out.println();
						System.out.print("Enter the quantity you want to purchase: ");
						quantity = input.nextLine();
						System.out.println();
				    }
				    else if (addProduct.compareTo("-1") == 0) {
				    	break;
				    }
				}
		    }
		}
		
		if (receipt != null && receipt.size() > 0) {
			System.out.println();
			System.out.println("RECEIPT");
			receipt.forEach(r -> {
				System.out.println("Product: " + r.getName() + " Qty: " + r.getQuantity());
			});
			
			System.out.println();
			
		    // The user if they want to cancel their purchase
		    System.out.print("Would you like to cancel your purchase? (Y/N) ");
		    String cancel = input.nextLine();
		    if (cancel.toUpperCase().compareTo("Y") == 0) {
		    	myStore.cancel(receipt);
		    }
		}
		
		System.out.println();
		System.out.println("Goodbye.");
		input.close();
    }

}
