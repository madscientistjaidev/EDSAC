import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;
import static java.lang.System.out;

/**
 *
 * @author Mad Scientist
 */
public final class EDSAC
{
	/**
	 * Memory
	 */
	ArrayList<Long> mem;

	/**
	 * Input tape
	 */
	ArrayList<Long> tape;

	/**
	 * Execution trace
	 */
	ArrayList<String> trace;

	/**
	 * Size of memory
	 */
	final int MEM_SIZE = 1024;

	long accumulator;
	long multiplier;

	long headPos = 0;

	/**
	 * Prompt text
	 */
	String prompt = "EDSAC";

	/**
	 * Holds program
	 */
	ArrayList<String> program;

	boolean isRunning;

	/**
	 * Constructor to initialize data structures.
	 */
	EDSAC()
	{
		cp();
		cm();
		ce();

		isRunning = true;
	}

	/**
	 * Main Function
	 *
	 * @param args
	 */
	public static void main(String args[])
	{
		EDSAC e = new EDSAC();

		out.println("EDSAC Simulator");

		Scanner sc = new Scanner(System.in);

		String input;

		out.println("Type he for help");

		while (e.isRunning)
		{
			out.print("\n" + e.prompt + ">");
			input = sc.nextLine();

			e.handleInput(input);
		}
	}

	//############################################################################
	//####################                                    ####################
	//####################    EDSAC ASSEMBLY CODE FUNCTIONS   ####################
	//####################                                    ####################
	//############################################################################
	/**
	 * Handles EDSAC Assembly code commands.
	 *
	 * @param inst
	 */
	void instHandler(String[] inst)
	{
		switch (inst.length)
		{
			case 1:
				switch (inst[0].trim())
				{
					case "X":
						return;
					case "Y":
						return;
					case "Z":
						return;
					default:
						out.println("Unknown command:" + inst[0]);
				}
				break;

			case 2:
				switch (inst[0].trim())
				{
					case "A":
						A(Integer.parseInt(inst[1]));
						break;
					case "S":
						S(Integer.parseInt(inst[1]));
						break;
					case "H":
						H(Integer.parseInt(inst[1]));
						break;
					case "V":
						V(Integer.parseInt(inst[1]));
						break;
					case "N":
						N(Integer.parseInt(inst[1]));
						break;
					case "T":
						T(Integer.parseInt(inst[1]));
						break;
					case "U":
						U(Integer.parseInt(inst[1]));
						break;
					case "C":
						C(Integer.parseInt(inst[1]));
						break;
					case "R":
						R(Integer.parseInt(inst[1]));
						break;
					case "L":
						L(Integer.parseInt(inst[1]));
						break;
					case "E":
						E(Integer.parseInt(inst[1]));
						break;
					case "G":
						G(Integer.parseInt(inst[1]));
						break;
					case "I":
						I(Integer.parseInt(inst[1]));
						break;
					case "O":
						O(Integer.parseInt(inst[1]));
						break;
					case "F":
						F(Integer.parseInt(inst[1]));
						break;
					default:
						out.println("Unknown command:" + inst[0]);
						break;
				}
				break;

			default:
				out.println("Unknown command:" + inst[0]);
				break;
		}
	}

	/**
	 * Add the number in storage location n into the accumulator.
	 *
	 * @param n
	 */
	void A(int n)
	{
		if (n < 0 || n > 1023)
		{
			out.println("Address out of bounds :" + n);
			return;
		}

		accumulator += mem.get(n);
	}

	void A(long n)
	{
		if (n < 0 || n > 1023)
		{
			out.println("Address out of bounds :" + n);
			return;
		}

		accumulator += mem.get((int) n);
	}

	/**
	 * Subtract the number in storage location n from the accumulator.
	 *
	 * @param n
	 */
	void S(int n)
	{
		if (n < 0 || n > 1023)
		{
			out.println("Address out of bounds :" + n);
			return;
		}
		accumulator -= mem.get(n);
	}

	void S(long n)
	{
		if (n < 0 || n > 1023)
		{
			out.println("Address out of bounds :" + n);
			return;
		}

		accumulator -= mem.get((int) n);
	}

	/**
	 * Copy the number in storage location n into the multiplier register.
	 *
	 * @param n
	 */
	void H(int n)
	{
		if (n < 0 || n > 1023)
		{
			out.println("Address out of bounds :" + n);
			return;
		}

		multiplier = mem.get(n);
	}

	/**
	 * Multiply the number in storage location n by the number in the multiplier register and add the product into the
	 * accumulator.
	 *
	 * @param n
	 */
	void V(int n)
	{
		if (n < 0 || n > 1023)
		{
			out.println("Address out of bounds :" + n);
			return;
		}

		A(mem.get(n) * multiplier);
	}

	/**
	 * Multiply the number in storage location n by the number in the multiplier register and subtract the product from
	 * the accumulator.
	 *
	 * @param n
	 */
	void N(int n)
	{
		if (n < 0 || n > 1023)
		{
			out.println("Address out of bounds :" + n);
			return;
		}

		S(mem.get(n) * multiplier);
	}

	/**
	 * Transfer the contents of the accumulator to storage location n and clear the accumulator.
	 *
	 * @param n
	 */
	void T(int n)
	{
		if (n < 0 || n > 1023)
		{
			out.println("Address out of bounds :" + n);
			return;
		}

		mem.set(n, accumulator);
		accumulator = 0l;
	}

	/**
	 * Transfer the contents of the accumulator to storage location n and do not clear the accumulator.
	 *
	 * @param n
	 */
	void U(int n)
	{
		if (n < 0 || n > 1023)
		{
			out.println("Address out of bounds :" + n);
			return;
		}

		mem.set(n, accumulator);
	}

	/**
	 * Collate [logical AND] the number in storage location n with the number in the multiplier register and add the
	 * result into the accumulator.
	 *
	 * @param n
	 */
	void C(int n)
	{
		if (n < 0 || n > 1023)
		{
			out.println("Address out of bounds :" + n);
			return;
		}

		A(mem.get(n) & multiplier);
	}

	/**
	 * Shift the number in the accumulator n places to the right.
	 *
	 * @param n
	 */
	void R(int n)
	{
		accumulator >>= n;
	}

	/**
	 * Shift the number in the accumulator n places to the left.
	 *
	 * @param n
	 */
	void L(int n)
	{
		accumulator <<= n;
	}

	/**
	 * If the sign of the accumulator is positive, jump to location n; otherwise proceed serially.
	 *
	 * @param n
	 */
	void E(int n)
	{
		if (n < 0 || n > 1023)
		{
			out.println("Address out of bounds :" + n);
			return;
		}

		if (accumulator >= 0l)
			headPos = n;
		else if (headPos != 1023)
			headPos++;
	}

	/**
	 * If the sign of the accumulator is negative, jump to location n; otherwise proceed serially.
	 *
	 * @param n
	 */
	void G(int n)
	{
		if (n < 0 || n > 1023)
		{
			out.println("Address out of bounds :" + n);
			return;
		}

		if (accumulator < 0l)
			headPos = n;
		else if (headPos != 1023)
			headPos++;
	}

	/**
	 * Read the next value from paper tape, and store it as the least significant 5 bits of location n.
	 *
	 * @param n
	 */
	void I(int n)
	{
		if (n < 0 || n > 1023)
		{
			out.println("Address out of bounds :" + n);
			return;
		}

		mem.set(n, 0l);
	}

	/**
	 * Print the character represented by the most significant 5 bits of storage location n.
	 *
	 * @param n
	 */
	void O(int n)
	{
		if (n < 0 || n > 1023)
		{
			out.println("Address out of bounds :" + n);
			return;
		}

		out.println(mem.get(n));
	}

	/**
	 * Read the last character output for verification.
	 *
	 * @param n
	 */
	void F(int n)
	{
		if (n < 0 || n > 1023)
		{
			out.println("Address out of bounds :" + n);
			return;
		}

		mem.set(n, 0l);
	}

	//############################################################################
	//####################                                    ####################
	//####################    SIMULATOR COMMAND FUNCTIONS     ####################
	//####################                                    ####################
	//############################################################################
	/**
	 * Handles input from simulator console.
	 *
	 * @param input
	 */
	void handleInput(String input)
	{
		String[] inst = input.split(" ");

		switch (inst[0])
		{
			case "ce":
				ce();
				break;
			case "cm":
				cm();
				break;
			case "cp":
				cp();
				break;
			//case "de" : de(inst);   break;
			case "dm":
				dm(inst);
				break;
			//out.println("\tee\t\t\tExamine execution trace");
			case "el":
				el(inst);
				break;
			case "er":
				er(inst);
				break;
			case "ex":
				System.out.println("Exiting...");
				System.exit(0);
			case "he":
				he();
				break;
			//out.println("\tli\t\t\t\tShow license information");
			case "lm":
				lm(inst);
				break;
			case "lp":
				lp(inst);
				break;
			//out.println("\tlt <filename>\t\tLoad tape from file");
			//out.println("\tpb <string>\t\tPush button");
			case "pr":
				pr(inst);
				break;
			case "rc":
				rc(inst);
				break;
			case "rp":
				rp();
				break;
			//out.println("\trs\t\t\tRun script");
			//out.println("\tsc <filename>\t\tLoad script from file");
			//out.println("\tsm <location> <value>\tSet value of location");

			default:
				out.println("Unknown command:" + inst[0]);
		}
	}

	/**
	 * Clear execution trace.
	 */
	void ce()
	{
		trace = new ArrayList<>();
	}

	/**
	 * Clear memory map
	 */
	void cm()
	{
		mem = new ArrayList<>();
		for (int i = 0; i < MEM_SIZE; i++)
			mem.add(0l);
	}

	/**
	 * Clear Program
	 */
	void cp()
	{
		program = new ArrayList<>();
	}

	/**
	 * Dump memory to file.
	 * @param inst 
	 */
	void dm(String[] inst)
	{
		if (inst.length != 2)
		{
			out.println("Wrong number of arguments.");
			return;
		}

		File memDump = null;
		FileWriter writer = null;

		try
		{
			memDump = new File(inst[1]);
			if (!memDump.createNewFile())
			{
				out.println("Could not create file.");
				return;
			}

			writer = new FileWriter(memDump);

			String op = "";

			for (int i = 0; i < MEM_SIZE; i++)
			{
				op += i;
				op += " ";
				op += mem.get(i);
				op += "\n";
			}

			writer.write(op);
			writer.flush();
			writer.close();
		} catch (IOException e)
		{
			out.println("Could not create file.");
		}
	}

	/**
	 * Examine memory location.
	 * @param inst 
	 */
	void el(String[] inst)
	{
		if (inst.length != 2)
		{
			System.out.println("Wrong number of arguments.");
			return;
		}

		if(inst[1]=="a") System.out.println("\tAcc : " + accumulator);
		else if(inst[1]=="m") System.out.println("\tAcc : " + multiplier);
		else
		{
			int loc = 0;
			try
			{
				loc = Integer.parseInt(inst[1]);
			}
			
			catch(NumberFormatException e)
			{
				out.println("Bad argument : " + inst[1]);
				return;
			}
			
			System.out.println("\t" + loc + " : " + mem.get(loc));
		}
	}

	/**
	 * Examine range of locations.
	 * @param inst 
	 */
	void er(String[] inst)
	{
		if (inst.length != 3)
		{
			System.out.println("Wrong number of arguments.");
			return;
		}

		int start = 0;

		try
		{
			start = Integer.parseInt(inst[1]);
		}
		
		catch (NumberFormatException e)
		{
			out.println("Bad argument : " + inst[1]);
			return;
		}
		
		int end = 0;

		try
		{
			end = Integer.parseInt(inst[2]);
		}
		
		catch (NumberFormatException e)
		{
			out.println("Bad argument : " + inst[2]);
			return;
		}
				
		if (start < 0 || start > 1023)
		{
			out.println("Address out of bounds :" + start);
			return;
		}
		
		if (end < 0 || end > 1023)
		{
			out.println("Address out of bounds :" + end);
			return;
		}

		for (int i = start; i <= end; i++)
			System.out.println("\t" + i + " : " + mem.get(i));
	}

	/**
	 * Prints help
	 */
	void he()
	{
		out.println("\tce\t\t\t\tClear execution trace");
		out.println("\tcm\t\t\t\tClear memory map");
		out.println("\tcp\t\t\t\tClear program");
		out.println("\tcs\t\t\t\tClear script");
		out.println("\tde <filename>\t\t\tDump execution trace to file");
		out.println("\tdm <filename>\t\t\tDump memory to file");
		out.println("\tee\t\t\t\tExamine execution trace");
		out.println("\tel <location>\t\t\tExamine memory location");
		out.println("\ter <start> <end>\t\tExamine range of locations");
		out.println("\tex\t\t\t\tExit simulator");
		out.println("\the\t\t\t\tPrint Help");
		out.println("\tli\t\t\t\tShow license information");
		out.println("\tlm <filename>\t\t\tLoad memory map from file");
		out.println("\tlp <filename>\t\t\tLoad program from file");
		out.println("\tls <filename>\t\t\tLoad script from file");
		out.println("\tlt <filename>\t\t\tLoad tape from file");
		out.println("\tpb <string>\t\t\tPush button");
		out.println("\tpr <string>\t\t\tSet Prompt");
		out.println("\trc <command>\t\t\tRun command");
		out.println("\trp\t\t\t\tRun program");
		out.println("\trs\t\t\t\tRun script");
		out.println("\tsm <location> <value>\t\tSet value of location");
	}

	/**
	 * Load memory from file
	 *
	 * @param s
	 * @return
	 */
	void lm(String[] inst)
	{
		if (inst.length != 2)
		{
			System.out.println("Wrong number of arguments.");
			return;
		}

		File memFile = new File(inst[1]);

		if (!memFile.canRead() || !memFile.exists() || !memFile.isFile())
		{
			System.out.println("Could not open file.");
			return;
		}

		Scanner memReader;

		try
		{
			memReader = new Scanner(memFile);
		} catch (FileNotFoundException e)
		{
			System.out.println("Could not open file.");
			return;
		}

		String line;
		String memLoc[];

		while (memReader.hasNextLine())
		{
			line = memReader.nextLine();

			if (line.charAt(0) == '#')
				continue;

			memLoc = line.split(" ");
			if (memLoc.length == 2)
				mem.set(Integer.parseInt(memLoc[0]), Long.parseLong(memLoc[1]));
		}
	}

	/**
	 * Load program from file
	 *
	 * @param s
	 * @return
	 */
	void lp(String[] inst)
	{
		if (inst.length != 2)
		{
			System.out.println("Wrong number of arguments.");
			return;
		}

		File progFile = new File(inst[1]);

		if (!progFile.canRead() || !progFile.exists() || !progFile.isFile())
		{
			System.out.println("Could not open file.");
			return;
		}

		Scanner progReader;

		try
		{
			progReader = new Scanner(progFile);
		} catch (FileNotFoundException e)
		{
			System.out.println("Could not open file.");
			return;
		}

		String line;
		String memLoc[];

		while (progReader.hasNextLine())
		{
			line = progReader.nextLine();

			if (line.length() == 0)
				continue;
			if (line.charAt(0) == '#')
				continue;

			memLoc = line.split(" ");
			if (memLoc.length != 0)
				program.add(line);
		}
	}

	/**
	 * Set Prompt.
	 * @param inst 
	 */
	void pr(String[] inst)
	{
		if (inst.length != 2)
		{
			System.out.println("Wrong number of arguments.");
			return;
		}

		prompt = inst[1];
	}

	/**
	 * Run command.
	 * @param inst 
	 */
	void rc(String[] inst)
	{
		if (program.isEmpty())
		{
			System.out.println("No program loaded.");
			return;
		}

		program.forEach((String i) ->
		{
			instHandler(i.split(" "));
		});
	}

	/**
	 * Run program.
	 */
	void rp()
	{
		if (program.isEmpty())
		{
			System.out.println("No program loaded.");
			return;
		}

		program.forEach((String i) ->
		{
			instHandler(i.split(" "));
		});
	}
}
