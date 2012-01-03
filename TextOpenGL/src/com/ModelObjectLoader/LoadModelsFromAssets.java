/******************
 * 
 * 
    Copyright (C) 2012  Mustafa Neguib
    Copyright (C) 2008-2012 MN Tech Solutions

    This program is free software: you can redistribute it and/or modify
    it under the terms of the GNU General Public License as published by
    the Free Software Foundation, either version 3 of the License, or
    (at your option) any later version.

    This program is distributed in the hope that it will be useful,
    but WITHOUT ANY WARRANTY; without even the implied warranty of
    MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
    GNU General Public License for more details.

    You should have received a copy of the GNU General Public License
    along with this program.  If not, see <http://www.gnu.org/licenses/>.
Also add information on how to contact you by electronic and paper mail.
 * 
 * 
 */



package com.ModelObjectLoader;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutput;
import java.io.ObjectOutputStream;
import java.io.Reader;
import java.util.ArrayList;
import java.util.List;


import com.TextOpenGL.R;

import android.content.Context;
import android.util.Log;


public class LoadModelsFromAssets {


	private ModelData model;
	private List<ModelData> list;


	public LoadModelsFromAssets()
	{	

	}

	
	public int loadFromAssets(Context context,String fileName)
	{

		try {

			FileOutputStream fos;

			list=new ArrayList<ModelData>();

			model=  LoadFromAssets.loadFromAsset("assets/square.obj", R.drawable.fonts, context.getAssets());//0
			list.add(model);
			
			
			ByteArrayOutputStream bos = new ByteArrayOutputStream(); 
			byte[] buffer;
			int length=0;

			ObjectOutput out = new ObjectOutputStream(bos); 
			out.writeObject(list); 
			out.close(); 
			// Get the bytes of the serialized object 
			buffer  = bos.toByteArray(); 
			fos = context.openFileOutput(fileName,context.MODE_PRIVATE);
			fos.write(buffer);

			length=buffer.length;
			return length;


		}//end try
		catch(IOException ioe) { 
			Log.e("serializeObject", "error", ioe); 
			return -1;
		}//end catch


	}


	@SuppressWarnings("unchecked")
	public List<ModelData> loadFromInternalMemory(Context context, int length, String fileName)
	{
		try {
		byte [] buffer=new byte[length];

		FileInputStream fis;
		
			fis = context.openFileInput(fileName);
		

		fis.read(buffer);

		ObjectInputStream in = new ObjectInputStream(new ByteArrayInputStream(buffer)); 
		Object object = in.readObject(); 
		in.close(); 

		List<ModelData> list=new ArrayList<ModelData>();

		list=(List<ModelData>)object;

		return list;
		
		
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			
			e.printStackTrace();
			return null;
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			
			e.printStackTrace();
			return null;
			
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}

	}

	@SuppressWarnings("null")
	public int getLengthOfDataInBytes(Context context,String fileName)
	{
		
		try {
		StringBuffer buffer= new StringBuffer();
		FileInputStream fis = context.openFileInput(fileName);
    	InputStreamReader isr = new InputStreamReader(fis);
    	Reader in = new BufferedReader(isr);
    	
    	int ch;
    	
    	while ((ch = in.read()) > -1) {
    		buffer.append((char)ch);
    	}
    	
			in.close();
			
			return Integer.parseInt(buffer.toString());
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return -1;
		}

		
	}
	
}
