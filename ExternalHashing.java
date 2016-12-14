/*
Mandi Fuccillo; Data Structures & Algorithms
Assignment 4: Hash table and external storage
This creates the app to run and to store information into the external file.
*/
import java.util.InputMismatchException;
import java.util.*;

public class ExternalHashing
{
	public static void main(String[] args)
	{
/*
		String strN = "";
		int itN =  "";
		System.out.println("Please enter an integer for N");
		strN = scan.nextLine();
		itN = Integer.parseInt(in);

		String strR = "";
		int itR = "";
		System.out.println("Please enter an integer for R");
		strR = scan.nextLine();
		itR = Integer.parseInt(in);
*/

		Scanner input = new Scanner(System.in);

		IndexHashTable iData = new IndexHashTable(int);


		String listOfCommands = "Enter a number to run command:"
				+ " 0: terminates program"
				+ " 1: displays commands"
				+ " 2: adds a new record"
				+ " 3: removes a record"
				+ " 4: searches for a record";


		boolean userWouldLikeToContinue = true;
				while(userWouldLikeToContinue)
				{
					System.out.println(listOfCommands);


					boolean userStillWorking = true;
					while(userStillWorking)
					{
						System.out.print("Enter a new command: ");

						try
						{
							int in = input.nextInt();
							input.nextLine();//flushes the scanner buffer
							switch(in)
							{

								case 0:
										userStillWorking = false;
										userWouldLikeToContinue = false;
										break;

								//displays list of commands
								case 1:
										System.out.println(listOfCommands);
										break;


								case 2:
										String name = "";
										int id = 0;
										boolean nameNotEntered = true;
										while(nameNotEntered)
										{
											try
											{
												System.out.print("Enter student's name: ");
												name = input.nextLine();
												nameNotEntered = false;
											}
											catch(Exception e)
											{
												System.out.println("Name was invalid. Please enter a valid name.");
												input.next();
											}
										}

										boolean idNotEntered = true;
										while(idNotEntered)
										{
											try
											{

												System.out.println("Enter student's ID:");
												id = input.nextInt();
												idNotEntered = false;
											}
											catch(Exception e)
											{
												System.out.println(" ID was invalid. Please enter a valid ID.");
												input.next();
											}
										}
										System.out.println("Record added to table.");
										iData.add(new Record(name,id));

									break;

								//removes student
								case 3:

										if(!iData.isEmpty())
										{
											System.out.print("Enter ID of student you want to remove: ");
											System.out.print("\n"+ iData.remove(input.nextInt()).iData.toString() + "\n\n...was removed from the list.");
										}
										else
											System.out.println("The Table is empty.");

										break;

								//searches for a student
								case 4:
										if(!iData.isEmpty())
										{
											System.out.println("Enter ID of the student you want to find: ");
											Record searchResults = iData.search(input.nextInt());

											if(searchResults != null)
												System.out.print(searchResults.iData.toString());
											else
												System.out.print("The ID was not found in the list.");
										}
										else
											System.out.println("The List is empty.");
										break;

								//displays elements in the list
								case 5:
										if(!iData.isEmpty())
											System.out.println(iData.toString());
										else
											System.out.println("List is empty.");
										break;

							    //invalid command entered not part of command list
								default:
										System.out.println(" An invalid command was entered. Please enter a valid command.");
										break;
							}
						}
						catch(InputMismatchException ime)
						{
							System.out.println(" An invalid command was entered. Please try again.");
							input.next();
						}
					}
				}
				System.out.println("Fin.");
			}
}
