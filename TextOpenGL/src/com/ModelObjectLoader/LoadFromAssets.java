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

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import android.content.res.AssetManager;
import android.util.Log;

public class LoadFromAssets {
	
	
	public static String loadTextFile(InputStream inputStream) throws IOException {
	ByteArrayOutputStream byteStream = new ByteArrayOutputStream();
	byte[] bytes = new byte[4096];
	int len = 0;
	while ((len = inputStream.read(bytes)) > 0)
	byteStream.write(bytes, 0, len);
	return new String(byteStream.toByteArray(), "UTF8");
	}
	
		
	public static ModelData loadFromAsset(String assetName,int textureImageResourceId,AssetManager assetManager)
	{//this function gets the data from the assets and then returns the ModelData object which contains
		  
		InputStream inputStream = null;
		ModelData modelData=new ModelData(null,null,null,textureImageResourceId);
		
		try {
		inputStream = assetManager.open(assetName);
		String text = loadTextFile(inputStream);
		String text1;
		List<String> lines=new ArrayList<String>();
		
		
		int index=0,index1=0,numOfLines=0;
		
		
		
		
		while(index<text.length())
	    {
			index1=text.indexOf("\n",index);
			text1=text.substring(index,index1);
			lines.add(text1);
			index=index1+1;
			numOfLines++;

			
		}//end while
		
		
		float[] vertices = new float[numOfLines*3];
		int [] faces = new int[numOfLines * 3];
		float [] vertexBuffer;
		
		
		int numVertices = 0;
		int numFaces = 0;
		int numTextures=0;
		
		float[] textures1 = new float[numOfLines*3];
		float [] textureBuffer;
		short[] facesBuffer; 
		
		String line=null;	
		int vertexIndex=0, facesIndex=0, texIndex=0;
		String[] tokens=null;
		String[] tokens1=null;
		
		
		for (int i = 0; i < lines.size(); i++) {
		
		line=lines.get(i);
		
		if (line.startsWith("v ")) 
		{
			tokens = line.split(" ");//at index 0 is v
			vertices[vertexIndex] = Float.parseFloat(tokens[1]);
			vertices[vertexIndex + 1] = Float.parseFloat(tokens[2]);
			vertices[vertexIndex + 2] = Float.parseFloat(tokens[3]);
			vertexIndex += 3;
			numVertices++;
			
			}//end if
		if (line.startsWith("vt ")) 
		{
			tokens = line.split(" ");//at index 0 is vt
			textures1[texIndex] = Float.parseFloat(tokens[1]);
			textures1[texIndex + 1] = Float.parseFloat(tokens[2]);

			texIndex += 2;
			numTextures++;
			
			}//end if
		else if(line.startsWith("f "))
		{
			tokens=line.split(" ");
			tokens1=tokens[1].split("/");//element at location 0 is f
			faces[facesIndex]=Integer.parseInt(tokens1[0]);
			tokens1=tokens[2].split("/");//element at location 0 is f
			faces[facesIndex+1]=Integer.parseInt(tokens1[0]);
			tokens1=tokens[3].split("/");//element at location 0 is f
			faces[facesIndex+2]=Integer.parseInt(tokens1[0]);
			
			facesIndex += 3;//this is keeping track of how many vertices are in the faces array, that i will need to create the vertexBuffer array
			numFaces++;//i am using the file test2.obj, and in it there are 6 vertices in the faces triangles, or 2 faces(triangles). 
		}//end else if
		
				
		}//end for
		
		textureBuffer=new float[numTextures*3];
		vertexBuffer=new float[numVertices*3];//i want the size of the final array to be equal to the number of faces times 3 because i have 3 components. ie. x,y,z
		//there will be number of facesIndex vertices in the shape defined. for example, there will be 6 vertices to define the 2 triangles which will make up the square.
		facesBuffer = new short[numFaces*3];
		 int i=0;
		
		 
	    for(i=0;i<vertexBuffer.length;i++)
	    {
	    		vertexBuffer[i]=vertices[i];
	    	
	    }//end for
	    
	    
	    for(i=0;i<textureBuffer.length;i++)
	    {
	    		textureBuffer[i]=textures1[i];
	    	
	    }//end for
	    
	    for(i=0;i<facesBuffer.length;i++)
	    {
	    		facesBuffer[i]=(short) (faces[i]-1);
	    
	    }//end for
	   			  
	    
	    modelData.setVertexBuffer(vertexBuffer);
	    modelData.setTextureBuffer(textureBuffer);
	    modelData.setFacesBuffer(facesBuffer);
	    
	    
	    return modelData;
	    
		
		}//end try
		catch (IOException e) 
		{
		//Couldn't load file
			Log.v("Could not load file", " ");
			return null;
			
		}//end catch
		finally
		{
		if (inputStream != null)
		{
			try 
			{
			inputStream.close();
			}//end try 
			catch (IOException e) 
			{//Couldn't close file
				Log.v("Could not close file", " ");
				return null;
			}//end catch
		}//end if
	}//end finally
		
  }
	
	
}