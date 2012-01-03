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

package com.TextOpenGL;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.microedition.khronos.egl.EGLConfig;
import javax.microedition.khronos.opengles.GL10;

import com.ModelObjectLoader.GenerateTextModel;
import com.ModelObjectLoader.LoadModelsFromAssets;
import com.ModelObjectLoader.ModelData;

import android.app.Activity;
import android.content.Context;
import android.opengl.GLSurfaceView;
import android.opengl.GLU;
import android.os.Bundle;
import android.util.Log;
import android.view.Window;
import android.view.WindowManager;


public class TextOpenGLActivity extends Activity implements GLSurfaceView.Renderer{

	private List<TextConverter> listText;
	private GenerateTextModel generateTextModelA;
	private GenerateTextModel generateTextModelB;
	private GenerateTextModel generateTextModelC;
	private GLSurfaceView glSurface;
	private float translateX=0.0f;
	private Integer num=0;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);


		requestWindowFeature(Window.FEATURE_NO_TITLE); 
		getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
				WindowManager.LayoutParams.FLAG_FULLSCREEN); // (NEW)
		glSurface= new GLSurfaceView(this);
		//glSurface.setEGLConfigChooser(false);//do not use this. this will not letthe depth buffering to work  

		glSurface.setRenderer(this);

		glSurface.setRenderMode(GLSurfaceView.RENDERMODE_CONTINUOUSLY);

		//glSurface.setRenderMode(GLSurfaceView.RENDERMODE_WHEN_DIRTY);
		setContentView(glSurface);
		getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);

	}

	private int counter =0;
	private String word;

	public void onDrawFrame(GL10 gl) {
		//Clear Screen And Depth Buffer
		gl.glClear(GL10.GL_COLOR_BUFFER_BIT | GL10.GL_DEPTH_BUFFER_BIT);	
		gl.glLoadIdentity();					//Reset The Current Modelview Matrix

		gl.glEnable(GL10.GL_BLEND);
		gl.glBlendFunc(GL10.GL_SRC_ALPHA, GL10.GL_ONE_MINUS_SRC_ALPHA);


		GLU.gluLookAt(gl, 0, 0, 5, 0, 0, 0, 0, 1,0);

		gl.glScalef(0.2f,0.2f,0.2f);
		//gl.glTranslatef(-30,18,0);
		int i=0,j=0;

		gl.glPushMatrix();

		/* 
		gl.glTranslatef(translateX,0,0);
		while(i<94)
		{
			listText.get(i).getCharacterModel().draw(gl);
			gl.glTranslatef(2,0,0);

			i++;
		}//end while

		translateX=translateX-0.1f;

		if(translateX<-160)
		{
			translateX=94;	
		}

		 */
		//translateX=-5;	

		//word=((Integer)counter).toString();
		//word="abacadaeafagahaiajakalamanaoapaqarasatauavawaxayaz";

		//char [] characters={'A','B','C','D','E','F','G','H','I','J','K','L','M','N','O','P','Q','R','S','T','U','V','W','X','Y','Z','a','b','c','d','e','f','g','h','i','j','k','l','m','n','o','p','q','r','s','t','u','v','w','x','y','z',',','<','>','.','/','?',';',':','\'','\"','[',']','{','}','`','~','!','@','#','$','%','^','&','*','(',')','_','+','-','=','0','1','2','3','4','5','6','7','8','9','\\','|',' '};

		word="abacadaeafagahaiajakalamanaoapaqarasatauavawaxayaz";


		//word=num.toString();
		//word="Hello World!!! My name is Mustafa\n Neguib. HElLo";
		//word="Hello World!!! My name is Mustafa Neguib.";
		gl.glTranslatef(translateX,0,0);
		gl.glPushMatrix();

		while(j<word.length())
		{
			i=0;


			while(i<95)
			{

				if(word.charAt(j)==listText.get(i).getCharacter())
				{

					listText.get(i).getCharacterModel().draw(gl);
					gl.glTranslatef(1.2f,0,0);
				}//end if
				/*else if(word.charAt(j)=='\n')
					{
						gl.glTranslatef(-0.415f,-0.02f,0);
					}//end else if
				 */
				i++;
			}//end while


			j++;
		}//while

		gl.glPopMatrix();

		translateX=translateX-0.05f;

		if(translateX<-80)
		{
			translateX=0;	
		}		

		/*		word=((Integer)counter).toString();
		while(j<word.length())
		{i=0;
			while(i<94)
			{

				if(word.charAt(j)==listText.get(i).getCharacter())
				{
					listText.get(82).getCharacterModel().draw(gl);
					gl.glTranslatef(2,0,0);
				}//end if

				i++;
			}//end while

			j++;
		}//while

		counter++;
		if(counter>10)
		{
			counter=0;
		}//end if
		 */	
		gl.glPopMatrix();

		gl.glDisable(GL10.GL_BLEND);

		num++;
		if(num%2==0)
		{
			num++;
		}//end if

	}

	public void onSurfaceChanged(GL10 gl, int width, int height) {
		if(height == 0) { 						//Prevent A Divide By Zero By
			height = 1; 						//Making Height Equal One
		}

		gl.glViewport(0, 0, width, height); 	//Reset The Current Viewport
		gl.glMatrixMode(GL10.GL_PROJECTION); 	//Select The Projection Matrix
		gl.glLoadIdentity(); 					//Reset The Projection Matrix

		//Calculate The Aspect Ratio Of The Window
		GLU.gluPerspective(gl, 45.0f, (float)width / (float)height, 0.1f, 3000);
		//gl.glFrustumf(-4, 4, -4, 4, 3, 10);
		//gl.glFrustumf(-400, 400, -221, 221, 0.1f, 100);

		gl.glMatrixMode(GL10.GL_MODELVIEW); 	//Select The Modelview Matrix
		gl.glLoadIdentity(); 	

	}

	public void onSurfaceCreated(GL10 gl, EGLConfig config) {

		gl.glEnable(GL10.GL_TEXTURE_2D);			//Enable Texture Mapping ( NEW )
		gl.glShadeModel(GL10.GL_SMOOTH); 			//Enable Smooth Shading
		//gl.glClearColor(1.0f, 1.0f, 1.0f, 0.5f);
		gl.glClearColor(0.62f, 0.75f, 1.0f, 0); 	
		gl.glClearDepthf(1.0f); 					//Depth Buffer Setup
		gl.glEnable(GL10.GL_DEPTH_TEST); 			//Enables Depth Testing
		gl.glDepthFunc(GL10.GL_LEQUAL); 			//The Type Of Depth Testing To Do

		//Really Nice Perspective Calculations
		gl.glHint(GL10.GL_PERSPECTIVE_CORRECTION_HINT, GL10.GL_NICEST); 

		LoadModelsFromAssets load;

		try
		{//check if the internal file exists. if it does, then the data has already been loaded from the assets in the past and i do not need to load them again.

			openFileInput("TextOpenGLModelsData");
			load=new LoadModelsFromAssets();

		}//end try
		catch(Exception e)
		{//the file does not exist, so i have to load the models in the file from the assets

			load=new LoadModelsFromAssets();

			int lengthOfData=load.loadFromAssets(this,"TextOpenGLModelsData");
			String buffer=((Integer)lengthOfData).toString();

			FileOutputStream fos;
			try {
				fos = this.openFileOutput("TextOpenGLModelsDataLength",Context.MODE_PRIVATE);
				fos.write(buffer.getBytes());
				fos.close();


			}//end try
			catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}//end catch

		}//end catch	


		int length=load.getLengthOfDataInBytes(this,"TextOpenGLModelsDataLength");
		List<ModelData> list=load.loadFromInternalMemory(this, length, "TextOpenGLModelsData");
		listText=new ArrayList<TextConverter>();

		float x=0.0f;
		char [] characters={'A','B','C','D','E','F','G','H','I','J','K','L','M','N','O','P','Q','R','S','T','U','V','W','X','Y','Z','a','b','c','d','e','f','g','h','i','j','k','l','m','n','o','p','q','r','s','t','u','v','w','x','y','z',',','<','>','.','/','?',';',':','\'','\"','[',']','{','}','`','~','!','@','#','$','%','^','&','*','(',')','_','+','-','=','0','1','2','3','4','5','6','7','8','9','\\','|',' '};
		int i=0;
		while(i<95)
		{
			listText.add(new TextConverter(characters[i],new GenerateTextModel(this,list,x)));
			listText.get(i).getCharacterModel().loadTextures(gl, this);

			/*
			if(characters[i]==' ')
			{
				Log.v("x: ",((Float)x).toString());
			}//end if
			 */
			x=x+32.0f;
			i++;
		}//end while


	}
}

