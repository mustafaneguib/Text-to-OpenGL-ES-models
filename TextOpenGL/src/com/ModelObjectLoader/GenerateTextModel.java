package com.ModelObjectLoader;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import javax.microedition.khronos.opengles.GL10;


import android.content.Context;
import android.content.res.AssetManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.widget.Toast;


public class GenerateTextModel {

	
	private ModelData model;
	private DrawModel modelDraw;
	private float x=0.0f;
	
	
	public GenerateTextModel()
	{	
		
	}
	
	public GenerateTextModel(Context context,List<ModelData> list, float x)
	{
		loadFromAssets(context,list,x);
	}
	
	
	
	
	public float getX() {
		return x;
	}

	public void setX(float x) {
		this.x = x;
	}

	
	
	public ModelData getModel() {
		return model;
	}

	public void setModel(ModelData model) {
		this.model = model;
	}

	public DrawModel getModelDraw() {
		return modelDraw;
	}

	public void setModelDraw(DrawModel modelDraw) {
		this.modelDraw = modelDraw;
	}

	

	public void loadFromAssets(Context context,List<ModelData> list, float x) 
	{
			float width=3050;
			float size=32;
					
			float [] textures=new float[8];
			textures[0]=x/width;
			textures[1]=1;
			
			textures[2]=(x+size)/width;
			textures[3]=1;
			
			textures[4]=x/width;
			textures[5]=0;
			
			textures[6]=(x+size)/width;
			textures[7]=0;
					
			  model=  list.get(0);
		      modelDraw=new DrawModel(model.getVertexBuffer(),textures, model.getFacesBuffer(), model.getTextureImageResourceId());
		            
		
		
	}
	
	public void draw(GL10 gl)
	{
		modelDraw.draw(gl);
		
	}
	
	public void loadTextures(GL10 gl, Context context)
	{
		modelDraw.loadGLTexture(gl,context);
		
	}
	
}

