/*
Mandi Fuccillo; Data Structures & Algorithms
Assignment 4: Hash table and external storage
This is the IndexHashTable class.
*/

import java.io.*;
import java.util.*;

public class IndexHashTable
{
	private int[] hashTable;
	private int sizeOfBlock;
	private int recsInBlock;
	private int lengthOfBlock;
	private int randomNumber = 0;

	private int[] blockCount;
	private final Record DELETED_RECORD = new Record("D", "-1");

	public IndexHashTable(int numberOfBlocks, int numRecordsPerBlock)
	{
		hashTable = new int[numberOfBlocks];
		populateHashTable();

		recsInBlock = numRecordsPerBlock;
		lengthOfBlock = 40 * recsInBlock;
		blockCount= new int[hashTable.length];
	}

	public int hashFunc(int key)
	{
		return key % hashTable.length;
	}

	public int insert(RandomAccessFile file, Record rec)
	{
			int recordId = Integer.parseInt(rec.getId());
			int hashedId = hashFunc(recordId);
            int hashedIndex = hashTable[hashedId];

            if(blockCount[hashedIndex] < recsInBlock)
            {
            	try
            	{
                    Record fileReader = new Record();

                    file.seek(hashedIndex * lengthOfBlock);

                    for(int i = 0; i < recsInBlock; i++)
                    {
                            fileReader.read(file);

                            if(fileReader.getId().equals("    ") || fileReader.getId().equals(DELETED_RECORD.getId( )))
                            {
                                    file.seek(file.getFilePointer() - 40);

                                    rec.write(file);

                                    blockCount[hashedIndex]++;

                                    return 0;
                            }
                            else if(fileReader.getId().equals(rec.getId()))

                                return 1;
                    }
            	}
            	catch(IOException ioe)
            	{
            		return -2;
            	}
            }
            return -1;
        }

    public boolean search(RandomAccessFile file, String id)
    {

    	int recordId = Integer.parseInt(id);
		int hashedId = hashFunc(recordId);
        int hashedIndex = hashTable[hashedId];

        if(blockCount[hashedIndex] != 0)
        {
        	try
    		{

        		Record fileReader = new Record();

				file.seek(hashedIndex * lengthOfBlock);

				for(int i = 0; i < recsInBlock; i++)
				{
					fileReader.read(file);

					if(fileReader.getId().equals(id))
			            	return true;
				}
    		}

        	catch(Exception e)
        	{

        	}
        }
        return false;
    }

    public boolean delete(RandomAccessFile file, String id)
    {

    		int recordId = Integer.parseInt(id);
    		int hashedId = hashFunc(recordId);
    		int hashedIndex = hashTable[hashedId];

            Record fileReader = new Record();
            Record deletedRecord;

            try
            {
		        file.seek(hashedIndex * lengthOfBlock);

		        for(int i = 0; i < recsInBlock; i++)
		        {
		                fileReader.read(file);

		                if(fileReader.getId().equals(id))
		                {
		                        deletedRecord = fileReader;
		                        file.seek(file.getFilePointer() - 40);
		                        DELETED_RECORD.write(file);
		                        blockCount[hashedIndex]--;
		                        return true;
		                }
		        }
            }
            catch(Exception e)
            {

            }

            return false;
    }


    private void populateHashTable()
    {
            Random random = new Random();
            int randomNumber;

            for(int i = 0; i < hashTable.length; i++)
            {
                    boolean numberAlreadyEntered = true;

                    while(numberAlreadyEntered)
                    {
                    	numberAlreadyEntered = false;

                    	randomNumber = random.nextInt(hashTable.length);

                    	for(int j = 0; j < i && !numberAlreadyEntered; j++)
                    	{
                    		if(hashTable[j] == randomNumber)
                    		{
                    			numberAlreadyEntered = true;
                    		}
                    	}
                    	hashTable[i] = randomNumber;
                    }

            	// hashTable[i] = randomNumber;
            }
   	 }
}

